package com.zhangteng.app.activity

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.app.R
import com.zhangteng.app.adapter.BaseListMvvmDemoAdapter
import com.zhangteng.app.databinding.ActivityBaseMviDemoBinding
import com.zhangteng.app.http.entity.ArticlesBean
import com.zhangteng.app.mvi.vm.BaseMviDemoViewModel
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvvm.base.mvi.BaseRefreshViewModel
import com.zhangteng.mvvm.base.mvi.DataUiState
import com.zhangteng.mvvm.base.mvi.InitDataIntent
import com.zhangteng.mvvm.mvi.vb.BaseListMviActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseListMviDemoActivity :
    BaseListMviActivity<ActivityBaseMviDemoBinding, BaseMviDemoViewModel, ArticlesBean, BaseAdapter.DefaultViewHolder, BaseListMvvmDemoAdapter>() {

    private var mLastPage = mPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_mvi_demo)
    }

    override fun initView() {
        super.initView()
        lifecycleScope.launchWhenCreated { // 开启新的协程
            // repeatOnLifecycle 是一个挂起函数；低于目标生命周期状态会取消协程，内部由suspendCancellableCoroutine实现
            // STATE.CREATED 低于 STARTED 状态；若因某种原因，界面重建，重走 Activity#onCreate 生命周期，就会取消该协程，直到 STARTED 状态之后，被调用者重新触发
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                (mViewModel as BaseRefreshViewModel).uiStateFlow.collect {
                    if (it is DataUiState.LoadSuccessPageState<*>) {
                        when (it.subState) {
                            is BaseMviDemoViewModel.BaseMviDemoUiState.RefreshPageDataSuccess -> {
                                mLastPage = mPage
                                showDataSuccess(Int.MAX_VALUE, it.data)
                            }

                            is BaseMviDemoViewModel.BaseMviDemoUiState.LoadPageDataSuccess -> {
                                mLastPage = mPage
                                showDataSuccess(Int.MAX_VALUE, it.data)
                            }
                        }
                    }
                    if (it is DataUiState.LoadErrorState) {
                        mPage = mLastPage
                    }
                }
            }
        }
    }

    override fun initData() {
        mViewModel.dispatch(InitDataIntent())
    }

    override fun createAdapter(): BaseListMvvmDemoAdapter {
        return BaseListMvvmDemoAdapter(mList)
    }

    override fun getRecyclerView(): RecyclerView {
        return mBinding?.recyclerView ?: findViewById(R.id.recyclerView)
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout {
        return mBinding?.smartRefreshLayout ?: findViewById(R.id.smartRefreshLayout)
    }

    override fun loadData(i: Int) {
        if (i == 1) {
            mViewModel.dispatch(BaseMviDemoViewModel.BaseMviDemoIntent.Refresh())
        } else {
            mViewModel.dispatch(BaseMviDemoViewModel.BaseMviDemoIntent.LoadPageData(i))
        }
    }

    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
}