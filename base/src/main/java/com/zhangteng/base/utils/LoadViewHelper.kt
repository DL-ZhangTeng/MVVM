package com.zhangteng.base.utils

import android.app.Activity
import android.app.Dialog
import android.content.*
import android.graphics.drawable.AnimationDrawable
import android.view.*
import android.widget.*
import com.zhangteng.base.R
import com.zhangteng.base.widget.NoDataView
import java.util.*
import kotlin.collections.HashMap

/**
 * 将某个视图替换为正在加载、无数据、加载失败等视图(保证每一个页面一个实例，不可单例使用会造成内存泄露或闪退)
 * Created by swing on 2018/10/8.
 */
open class LoadViewHelper {
    private val contentViews: HashMap<View, NoDataView> = HashMap()
    private var mProgressDialog: Dialog? = null
    private var loadView: TextView? = null
    private var againRequestListener: AgainRequestListener? = null
    private var cancelRequestListener: CancelRequestListener? = null

    /**
     * @description: 解决连续进度弹窗问题
     */
    @Volatile
    private var showCount: Int = 0

    /**
     * 无网络view
     *
     * @param currentView 需要替换的view
     */
    open fun showNoNetView(currentView: View?) {
        showNoDataView(currentView, R.mipmap.wangluowu, "无网络", "点击重试")
    }

    /**
     * 无内容view
     *
     * @param currentView 需要替换的view
     */
    open fun showNoContentView(currentView: View?) {
        showNoDataView(currentView, R.mipmap.neirongwu, "暂无内容~", "")
    }

    /**
     * 显示无数据view
     *
     * @param currentView 需要替换的view
     */
    open fun showNoDataView(
        currentView: View?,
        drawableRes: Int,
        noDataText: String?,
        noDataAgainText: String?
    ) {
        if (currentView == null) return
        if (contentViews[currentView] == null) {
            contentViews[currentView] = NoDataView(currentView.context)
        }
        val mNoDataView = contentViews[currentView] ?: return
        mNoDataView.setNoDataImageResource(drawableRes)
        mNoDataView.setNoDataText(noDataText)
        if (null == noDataAgainText || "" == noDataAgainText) {
            mNoDataView.setNoDataAgainVisivility(View.GONE)
        } else {
            mNoDataView.setNoDataAgainText(noDataAgainText)
        }
        mNoDataView.setAgainRequestListener(object : NoDataView.AgainRequestListener {
            override fun request() {
                againRequestListener?.request()
            }
        })
        if (mNoDataView.isNoDataViewShow()) {
            return
        }
        val viewGroup = currentView.parent
        if (viewGroup != null) {
            viewGroup as ViewGroup
            viewGroup.removeView(currentView)
            viewGroup.addView(mNoDataView, currentView.layoutParams)
        }
        mNoDataView.setNoDataViewShow(true)
    }
    /**
     * 显示dialog
     *
     * @param mContext     dialog上下文
     * @param mLoadingText dialog文本
     */
    /**
     * 显示dialog
     *
     * @param mContext dialog上下文
     */
    @JvmOverloads
    open fun showProgressDialog(mContext: Context?, mLoadingText: String? = "加载中...") {
        if (mContext == null) {
            return
        }
        showProgressDialog(mContext, mLoadingText, R.layout.layout_dialog_progress)
    }

    /**
     * 显示dialog
     *
     * @param mContext     dialog上下文
     * @param mLoadingText dialog文本
     * @param layoutRes    dialog布局文件
     */
    @Synchronized
    open fun showProgressDialog(mContext: Context?, mLoadingText: String?, layoutRes: Int) {
        if (mContext == null) {
            return
        }

        if (mProgressDialog == null) {
            mProgressDialog = Dialog(mContext, R.style.progress_dialog)
            val view = View.inflate(mContext, layoutRes, null)
            loadView = view.findViewById(R.id.loadView)
            val mImageView = view.findViewById<ImageView?>(R.id.progress_bar)
            (mImageView?.drawable as AnimationDrawable).start()
            if (mLoadingText != null) {
                loadView?.text = mLoadingText
            }
            mProgressDialog?.setContentView(view)
            mProgressDialog?.setCancelable(true)
            mProgressDialog?.setCanceledOnTouchOutside(false)
            mProgressDialog?.setOnDismissListener {
                cancelRequestListener?.cancel()
            }
            val activity = findActivity(mContext)
            if (activity == null || activity.isDestroyed || activity.isFinishing) {
                if (mProgressDialog?.isShowing == true)
                    mProgressDialog?.dismiss()
                mProgressDialog = null
                return
            } else {
                if (mProgressDialog?.ownerActivity == null)
                    mProgressDialog?.setOwnerActivity(activity)
            }
        } else {
            if (mLoadingText != null && loadView != null) {
                loadView?.text = mLoadingText
            }
        }
        val activity1 = mProgressDialog?.ownerActivity
        if (activity1 == null || activity1.isDestroyed || activity1.isFinishing) {
            if (mProgressDialog?.isShowing == true)
                mProgressDialog?.dismiss()
            mProgressDialog = null
            return
        }
        showCount++
        if (mProgressDialog?.isShowing == false)
            mProgressDialog?.show()
    }

    /**
     * 完成dialog
     */
    @Synchronized
    open fun dismissProgressDialog() {
        showCount--
        if (mProgressDialog?.isShowing == true && showCount <= 0) {
            showCount = 0
            mProgressDialog?.dismiss()
        }
    }

    /**
     * 隐藏无网络view
     *
     * @param currentView 需要替换的view
     */
    open fun hiddenNoNetView(currentView: View?) {
        hiddenNoDataView(currentView)
    }

    /**
     * 隐藏无内容view
     *
     * @param currentView 需要替换的view
     */
    open fun hiddenNoContentView(currentView: View?) {
        hiddenNoDataView(currentView)
    }

    /**
     * 隐藏无数据view
     *
     * @param currentView 需要替换的view
     */
    open fun hiddenNoDataView(currentView: View?) {
        val mNoDataView = contentViews[currentView]
        if (mNoDataView?.isNoDataViewShow() == false) {
            return
        }
        val viewGroup = mNoDataView?.parent
        if (viewGroup != null) {
            viewGroup as ViewGroup
            viewGroup.removeView(mNoDataView)
            viewGroup.addView(currentView)
        }
        mNoDataView?.setNoDataViewShow(false)
    }

    public fun findActivity(context: Context?): Activity? {
        if (context is Activity) {
            return context
        }
        return if (context is ContextWrapper) {
            val wrapper = context as ContextWrapper?
            findActivity(wrapper?.baseContext)
        } else {
            null
        }
    }

    open fun setAgainRequestListener(againRequestListener: AgainRequestListener?) {
        this.againRequestListener = againRequestListener
    }

    open fun setCancelRequestListener(cancelRequestListener: CancelRequestListener?) {
        this.cancelRequestListener = cancelRequestListener
    }

    interface CancelRequestListener {
        open fun cancel()
    }

    interface AgainRequestListener {
        open fun request()
    }
}