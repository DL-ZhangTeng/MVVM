package com.zhangteng.mvvm.base.mvi

import android.view.View

/**
 * ViewModel的带状态基类
 */
abstract class BaseStateViewModel : BaseLoadingViewModel() {

    /**
     * description: 多状态页面状态
     * author: Swing
     * date: 2023/4/22
     */
    sealed class StateUiState {
        //显示（View是将要被无网络视图替换的视图）
        class ShowNoNetState(val view: View) : IUiState

        //显示（View是将要被超时视图替换的视图）
        class ShowTimeOutState(val view: View) : IUiState

        //显示（View是将要被无数据视图替换的视图）
        class ShowEmptyState(val view: View) : IUiState

        //显示（View是将要被数据异常视图替换的视图）
        class ShowErrorState(val view: View) : IUiState

        //显示（View是将要被未登录视图替换的视图）
        class ShowNoLoginState(val view: View) : IUiState

        //隐藏（View是将要展示的View）
        class ShowContentState(val view: View) : IUiState
    }
}
