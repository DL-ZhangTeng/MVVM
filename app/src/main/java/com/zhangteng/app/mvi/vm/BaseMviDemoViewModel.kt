package com.zhangteng.app.mvi.vm

import com.zhangteng.app.mvi.repository.BaseMviDemoRepository
import com.zhangteng.mvvm.base.mvi.BaseRefreshViewModel
import com.zhangteng.mvvm.base.mvi.DataUiState
import com.zhangteng.mvvm.base.mvi.IUiIntent
import com.zhangteng.mvvm.base.mvi.IUiState
import com.zhangteng.mvvm.base.mvi.InitDataIntent

class BaseMviDemoViewModel : BaseRefreshViewModel() {
    private val mRepository by lazy { BaseMviDemoRepository() }

    override fun handleUserIntent(intent: IUiIntent) {
        when (intent) {
            // 初始化 或 刷新，都加载第1页
            is InitDataIntent, is BaseMviDemoIntent.Refresh -> getProjectList(294, 1)
            is BaseMviDemoIntent.LoadPageData -> getProjectList(294, intent.page)
        }
    }

    fun getProjectList(cid: Int, page: Int) {
        /**
         * 只返回结果，其他全抛自定义异常
         * */
        launchOnlyResult({
            mRepository.getProjectList(page, cid)
        }, {
            sendUiState {
                DataUiState.LoadSuccessPageState(
                    if (page == 1) BaseMviDemoUiState.RefreshPageDataSuccess()
                    else BaseMviDemoUiState.LoadPageDataSuccess(),
                    it.datas,
                    page
                )
            }
        }, error = {
            sendUiState { DataUiState.LoadErrorState(it) }
        }, complete = {
            sendUiState {
                if (page == 1) RefreshUiState.FinishRefreshState()
                else RefreshUiState.FinishLoadMoreState()
            }
        }, isShowDialog = true)
    }

    sealed class BaseMviDemoUiState {

        class LoadPageDataSuccess : IUiState

        class RefreshPageDataSuccess : IUiState
    }

    sealed class BaseMviDemoIntent {
        /*
         * 尽量不要用 object 定义 意图类；每次从 UI层 发送来的意图，正常情况下都是要响应的；
         * 而使用 object 单例后，和 sharedStateFlow 的 distinctUntilChanged() 本意有冲突了：
         * 连续发送单例事件 非首次的、后续的 就被过滤掉了。
         */
        class Refresh(val page: Int = 1) : IUiIntent // 刷新

        class LoadPageData(val page: Int) : IUiIntent // 加载分页数据
    }
}