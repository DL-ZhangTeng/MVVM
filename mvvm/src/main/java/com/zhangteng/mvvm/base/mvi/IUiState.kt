package com.zhangteng.mvvm.base.mvi

import android.view.View
import com.zhangteng.utils.IException

interface IUiState

/**
 * description: 数据请求结果页面状态
 * author: Swing
 * date: 2023/4/22
 */
sealed class DataUiState {
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
}