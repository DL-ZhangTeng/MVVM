package com.zhangteng.mvvm.base.mvvm

import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.livedata.SingleLiveData
import com.zhangteng.utils.IException
import com.zhangteng.utils.IResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

/**
 * ViewModel的带加载中基类（建议定义*UiState保存页面状态）
 *
 * data class NewsUiState(
 *  val isSignedIn: Boolean = false,
 *  val isPremium: Boolean = false,
 *  val newsItems: List<NewsItemUiState> = listOf(),
 *  val userMessages: List<Message> = listOf()
 * )
 *
 * data class NewsItemUiState(
 *  val title: String,
 *  val body: String,
 *  val bookmarked: Boolean = false,
 *  ...
 * )
 */
open class BaseLoadingViewModel : BaseViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    /**
     *  不过滤请求结果
     * @param block 请求体
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun launchGo(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(IException) -> Unit,
        complete: suspend CoroutineScope.() -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) loadingChange.showLoadingView.call()
        launchUI {
            handleException(
                withContext(Dispatchers.IO) { block },
                { error(it) },
                {
                    loadingChange.dismissLoadingView.call()
                    complete()
                }
            )
        }
    }

    /**
     * 过滤请求结果，其他全抛异常
     * @param block 请求体
     * @param success 成功回调
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun <T> launchOnlyResult(
        block: suspend CoroutineScope.() -> IResponse<T>,
        success: (T) -> Unit,
        error: (IException) -> Unit,
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) loadingChange.showLoadingView.call()
        launchUI {
            handleException(
                {
                    withContext(Dispatchers.IO) {
                        block().let {
                            if (it.isSuccess()) it.getResult()
                            else
                                throw IException(it.getMsg(), it.getCode())
                        }
                    }.also { success(it) }
                },
                { error(it) },
                {
                    loadingChange.dismissLoadingView.call()
                    complete()
                }
            )
        }
    }

    inner class UiLoadingChange {
        //显示加载框
        val showLoadingView by lazy { SingleLiveData<String>() }

        //隐藏
        val dismissLoadingView by lazy { SingleLiveData<Boolean>() }
    }
}