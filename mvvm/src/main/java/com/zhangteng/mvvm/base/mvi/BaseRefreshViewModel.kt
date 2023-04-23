package com.zhangteng.mvvm.base.mvi

/**
 * ViewModel的带刷新基类
 */
abstract class BaseRefreshViewModel : BaseStateViewModel() {

    /**
     * description: 完成加载更多&刷新页面状态
     * author: Swing
     * date: 2023/4/22
     */
    sealed class RefreshUiState {
        class finishLoadMoreState : IUiState
        class finishRefreshState : IUiState
    }
}
