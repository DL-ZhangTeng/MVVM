package com.zhangteng.mvvm.mvi

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.base.base.BaseListFragment
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.base.mvi.BaseLoadingViewModel
import com.zhangteng.mvvm.base.mvi.BaseRefreshViewModel
import com.zhangteng.mvvm.base.mvi.BaseStateViewModel
import com.zhangteng.mvvm.manager.NetState
import com.zhangteng.mvvm.manager.NetworkStateManager
import com.zhangteng.mvvm.utils.getVmClazz

/**
 * ViewModelFragment基类，自动把ViewModel注入Fragment
 */

abstract class BaseListMviFragment<VM : BaseViewModel, D, VH : BaseAdapter.DefaultViewHolder, A : BaseAdapter<D, VH>> :
    BaseListFragment<D, VH, A>() {

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
        registerDefUIChange()
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        //在Fragment中，只有懒加载过了才能开启网络变化监听
        NetworkStateManager.instance.mNetworkStateCallback.observe(
            this
        ) {
            //不是首次订阅时调用方法，防止数据第一次监听错误
            if (!isFirst) {
                onNetworkStateChanged(it)
            }
        }
    }

    /**
     * 创建viewModel
     */
    protected fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 注册UI 事件(处理了加载中，无网络，无数据，完成刷新等)
     */
    protected fun registerDefUIChange() {
        lifecycleScope.launchWhenCreated { // 开启新的协程
            // repeatOnLifecycle 是一个挂起函数；低于目标生命周期状态会取消协程，内部由suspendCancellableCoroutine实现
            // STATE.CREATED 低于 STARTED 状态；若因某种原因，界面重建，重走 Activity#onCreate 生命周期，就会取消该协程，直到 STARTED 状态之后，被调用者重新触发
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (mViewModel is BaseRefreshViewModel) {
                    (mViewModel as BaseRefreshViewModel).uiStateFlow.collect {
                        when (it) {
                            is BaseRefreshViewModel.RefreshUiState.finishRefreshState -> {
                                finishRefreshOrLoadMore()
                            }

                            is BaseRefreshViewModel.RefreshUiState.finishLoadMoreState -> {
                                finishRefreshOrLoadMore()
                            }
                        }
                    }
                }
                if (mViewModel is BaseStateViewModel) {
                    (mViewModel as BaseStateViewModel).uiStateFlow.collect {
                        when (it) {
                            is BaseStateViewModel.StateUiState.ShowNoNetState -> {
                                showNoNetView(it.view)
                            }

                            is BaseStateViewModel.StateUiState.ShowTimeOutState -> {
                                showTimeOutView(it.view)
                            }

                            is BaseStateViewModel.StateUiState.ShowEmptyState -> {
                                showEmptyView(it.view)
                            }

                            is BaseStateViewModel.StateUiState.ShowErrorState -> {
                                showErrorView(it.view)
                            }

                            is BaseStateViewModel.StateUiState.ShowNoLoginState -> {
                                showNoLoginView(it.view)
                            }

                            is BaseStateViewModel.StateUiState.ShowContentState -> {
                                showContentView(it.view)
                            }
                        }
                    }
                }
                if (mViewModel is BaseLoadingViewModel) {
                    (mViewModel as BaseLoadingViewModel).uiStateFlow.collect {
                        when (it) {
                            is BaseLoadingViewModel.LoadingUiState.LoadingState -> {
                                if (it.isShowLoadingView) showProgressDialog() else dismissProgressDialog()
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 网络变化监听 子类重写
     */
    protected open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 完成加载刷新动画
     */
    protected open fun finishRefreshOrLoadMore() {}

}