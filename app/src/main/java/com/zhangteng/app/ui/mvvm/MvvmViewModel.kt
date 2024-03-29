package com.zhangteng.app.ui.mvvm

import androidx.lifecycle.MutableLiveData
import com.zhangteng.app.http.Api
import com.zhangteng.app.http.entity.ArticlesBean
import com.zhangteng.app.http.entity.NavTypeBean
import com.zhangteng.mvvm.base.mvvm.BaseLoadingViewModel
import com.zhangteng.httputils.http.HttpUtils
import com.zhangteng.utils.IException
import com.zhangteng.utils.d
import com.zhangteng.utils.e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MvvmViewModel : BaseLoadingViewModel() {

    private val mRepository by lazy { MvvmRepository() }

    var navData = MutableLiveData<MutableList<NavTypeBean>>()

    var items = MutableLiveData<MutableList<ArticlesBean?>?>()


    private var page: Int = 0


    fun getProjectList(cid: Int) {
        /**
         * 只返回结果，其他全抛自定义异常
         * */
        launchOnlyResult({
            mRepository.getProjectList(page, cid)
        }, {
            items.value = it.datas
        }, error = {

        }, complete = {

        }, isShowDialog = true)
    }

    /**
     * 先请求tab数据
     * */
    fun getData() {
        launchOnlyResult({
            mRepository.getData()
        }, success = {
            navData.value = it as MutableList<NavTypeBean>
        }, error = {
            it.message.e()
        })
    }

    /**
     * 用Flow流的方式
     * 操作符比较繁琐
     * */
    fun getFirstData() {

        launchUI {
            launchFlow {
                mRepository.getFirstData()
            }
                .flatMapConcat {
                    return@flatMapConcat if (it.isSuccess()) {
                        navData.value = it as MutableList<NavTypeBean>

                        launchFlow {
                            HttpUtils.instance
                                .ConfigGlobalHttpUtils()
                                .createService(Api::class.java).getProjectList(page, it.data[0].id)
                        }
                    } else throw IException(Throwable(it.errorMsg), it.errorCode)
                }
                .onStart { loadingChange.showLoadingView.postValue(null) }
                .flowOn(Dispatchers.IO)
                .onCompletion { loadingChange.dismissLoadingView.call() }
                .catch {
                    // 错误处理
                    val err = IException.handleException(it)
                    "${err.code}: ${err.message}".d()
                }
                .collect {
                    if (it.isSuccess()) items.value = it.data.datas
                }
        }
    }
}