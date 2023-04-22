package com.zhangteng.mvvm.base.mvi

import androidx.lifecycle.viewModelScope
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.utils.IException
import com.zhangteng.utils.IResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel的带加载中基类
 */
abstract class BaseMviViewModel : BaseViewModel() {

    /**
     * description: UI 状态
     */
    private val _uiStateFlow by lazy { MutableStateFlow(initUiState()) }
    val uiStateFlow: StateFlow<IUiState> = _uiStateFlow.asStateFlow()

    /**
     * description: 事件意图, 点击事件、刷新等都是Intent。表示用户的主动操作
     */
    private val _userIntent = MutableSharedFlow<IUiIntent>()
    private val userIntent = _userIntent.asSharedFlow()

    init {
        viewModelScope.launch {
            userIntent.distinctUntilChanged().collect {
                handleUserIntent(it)
            }
        }
    }

    abstract fun handleUserIntent(intent: IUiIntent)

    protected open fun initUiState(): IUiState {
        return LoadingState(true)
    }

    protected fun sendUiState(block: IUiState.() -> IUiState) {
        _uiStateFlow.update { _uiStateFlow.value.block() }
    }

    /**
     * description 分发意图 仅此一个 公开函数。供 V 调用
     */
    open fun dispatch(intent: IUiIntent) {
        viewModelScope.launch {
            _userIntent.emit(intent)
        }
    }

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
        launchUI {
            launchFlow { block }
                .onStart {
                    if (isShowDialog) {
                        sendUiState { LoadingState(true) }
                    }
                }
                .flowOn(Dispatchers.IO)
                .catch { // 代码运行异常
                    error(IException.handleException(it))
                    sendUiState { LoadingState(false) }
                }
                .onCompletion {
                    // 如果发生了 异常，则可能多发送一次 LoadingState(false)
                    sendUiState { LoadingState(false) }
                }
                .flowOn(Dispatchers.Main)
                .collect {
                    sendUiState { LoadingState(false) }
                    complete()
                }
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
        launchUI {
            launchFlow { block() }
                .onStart {
                    if (isShowDialog) {
                        sendUiState { LoadingState(true) }
                    }
                }
                .flowOn(Dispatchers.IO)
                .catch { // 代码运行异常
                    error(IException.handleException(it))
                    sendUiState { LoadingState(false) }
                }
                .onCompletion {
                    // 如果发生了 异常，则可能多发送一次 LoadingState(false)
                    sendUiState { LoadingState(false) }
                }
                .flowOn(Dispatchers.Main)
                .collect {
                    handleException(
                        {
                            run {
                                if (it.isSuccess()) it.getResult()
                                else
                                    throw IException(it.getMsg(), it.getCode())
                            }.also { success(it) }
                        },
                        { error(it) },
                        {
                            sendUiState { LoadingState(false) }
                            complete()
                        }
                    )
                }
        }
    }
}
