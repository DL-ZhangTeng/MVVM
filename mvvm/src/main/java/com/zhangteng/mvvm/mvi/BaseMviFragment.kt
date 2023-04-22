package com.zhangteng.mvvm.mvi

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zhangteng.base.base.BaseFragment
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.base.mvi.IUiState
import com.zhangteng.mvvm.base.mvi.LoadErrorState
import com.zhangteng.mvvm.base.mvi.LoadingState
import com.zhangteng.mvvm.utils.getVmClazz
import kotlinx.coroutines.flow.Flow

abstract class BaseMviFragment<VM : BaseViewModel> : BaseFragment() {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
    }

    /**
     * 创建viewModel
     */
    protected fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    protected fun stateFlowHandle(
        flow: Flow<IUiState>,
        handleError: Boolean,
        block: (state: IUiState) -> Unit
    ) {
        lifecycleScope.launchWhenCreated { // 开启新的协程
            // repeatOnLifecycle 是一个挂起函数；低于目标生命周期状态会取消协程，内部由suspendCancellableCoroutine实现
            // STATE.CREATED 低于 STARTED 状态；若因某种原因，界面重建，重走 Activity#onCreate 生命周期，就会取消该协程，直到 STARTED 状态之后，被调用者重新触发
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect {
                    when (it) {
                        is LoadingState -> {
                            if (it.isShowLoadingView) showProgressDialog() else dismissProgressDialog()
                        }

                        is LoadErrorState -> {
                            if (handleError) showToast(it.error.message)
                            else block(it)
                        }

                        else -> block(it)
                    }
                }
            }
        }
    }
}