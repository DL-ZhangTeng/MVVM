package com.zhangteng.mvvm.base.mvi

import com.zhangteng.utils.IException

interface IUiState

/**
 * description: 加载失败
 * author: Swing
 * date: 2023/4/22
 */
class LoadErrorState(val error: IException) : IUiState

/**
 * description: 加载成功
 * author: Swing
 * date: 2023/4/22
 */
class LoadSuccessState<T>(val subState: IUiState, val data: T?) : IUiState

/**
 * description: 加载分页成功
 * author: Swing
 * date: 2023/4/22
 */
class LoadSuccessPageState<T>(val subState: IUiState, val data: List<T>?, val pageNum: Int) :
    IUiState

/**
 * description: 正在加载页面状态
 * author: Swing
 * date: 2023/4/22
 */
class LoadingState(val isShowLoadingView: Boolean) : IUiState

/**
 * description: 加载更多&刷新页面状态
 * author: Swing
 * date: 2023/4/22
 */
sealed class RefreshUiState {
    class LoadMoreSuccess : IUiState
    class RefreshSuccess : IUiState
}

/**
 * description: 多状态页面状态
 * author: Swing
 * date: 2023/4/22
 */
sealed class StateUiState {
    //显示（View是将要被无网络视图替换的视图）
    class ShowNoNetView : IUiState

    //显示（View是将要被超时视图替换的视图）
    class showTimeOutView : IUiState

    //显示（View是将要被无数据视图替换的视图）
    class showEmptyView : IUiState

    //显示（View是将要被数据异常视图替换的视图）
    class showErrorView : IUiState

    //显示（View是将要被未登录视图替换的视图）
    class showNoLoginView : IUiState

    //隐藏（View是将要展示的View）
    class showContentView : IUiState
}