package com.zhangteng.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvvm.BR
import com.zhangteng.mvvm.R

/**
 * description: DataBinding 列表适配器
 *              class BaseListDemoDbBean : BindingIdBean() {
 *                  override var viewType: Int = R.layout.item_base_list_demo_db
 *              }
 *              class BaseListDemoDbAdapter :
 *                  BindingAdapter<BaseListDemoDbBean, BindingAdapter.BindingViewHolder<BaseListDemoDbBean>>()
 * author: Swing
 * date: 2022/12/5
 */
open class BindingAdapter<T : BindingBean, VH : BindingAdapter.BindingViewHolder<T>> :
    BaseAdapter<T, BindingAdapter.BindingViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T> {
        return BindingViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return data?.get(position)?.viewType ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<T>, item: T?, position: Int) {
        holder.bindData(item)
    }

    /**
     * description: DataBinding ViewHolder实现了数据绑定
     * author: Swing
     * date: 2022/12/5
     */
    open class BindingViewHolder<T>(val view: View) : DefaultViewHolder(view) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(view)

        /**
         * description 绑定数据
         * @param item
         */
        fun bindData(item: T?) {
            binding?.setVariable(BR.item, item)
            //如果item不是BaseObservable，手动启动数据绑定
            if (item !is BaseObservable) {
                binding?.executePendingBindings()
            }
        }
    }
}

/**
 * description: DataBinding 列表数据id实现
 * author: Swing
 * date: 2022/12/5
 */
open class BindingIdBean : BaseObservable(), BindingBean {
    @LayoutRes
    override var viewType: Int = R.layout.mvvm_db_placeholder

    private var id: String? = null

    /**
     * description: Bindable生成BR.*
     */
    @Bindable
    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
        notifyPropertyChanged(BR.id)
    }
}

/**
 * description: DataBinding 列表数据接口
 * author: Swing
 * date: 2022/12/5
 */
interface BindingBean {
    /**
     * description: 使用LayoutRes做为viewType
     */
    var viewType: Int
}