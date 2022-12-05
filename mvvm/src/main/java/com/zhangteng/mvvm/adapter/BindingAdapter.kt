package com.zhangteng.mvvm.adapter

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvvm.BR

/**
 * description: DataBinding 列表适配器
 *              class BaseListDemoDbBean : BindingIdBean()
 *              class BaseListDemoDbAdapter(data: MutableList<BaseListDemoDbBean?>?) :
 *                  BindingAdapter<BaseListDemoDbBean, BindingAdapter.BindingViewHolder<BaseListDemoDbBean>>(data) {
 *                  override fun onCreateViewHolder(
 *                      parent: ViewGroup,
 *                      viewType: Int
 *                  ): BindingViewHolder<BaseListDemoDbBean> {
 *                      return BindingViewHolder(
 *                          LayoutInflater.from(parent.context)
 *                              .inflate(R.layout.item_base_list_demo_db, parent, false)
 *                      )
 *                  }
 *              }
 * author: Swing
 * date: 2022/12/5
 */
abstract class BindingAdapter<T, VH : BindingAdapter.BindingViewHolder<T>> :
    BaseAdapter<T, VH> {
    constructor()
    constructor(data: MutableList<T?>?) : super() {
        this.data = data
    }

    override fun onBindViewHolder(holder: VH, item: T?, position: Int) {
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
            //如果item不是BindingBean，手动启动数据绑定
            if (item !is BindingBean) {
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
open class BindingIdBean : BindingBean() {
    private var id: String? = null

    /**
     * description: Bindable生成BR.*
     */
    @Bindable
    open fun getId(): String? {
        return id
    }

    open fun setId(id: String?) {
        this.id = id
        notifyPropertyChanged(BR.id)
    }
}

/**
 * description: DataBinding 列表数据接口
 * author: Swing
 * date: 2022/12/5
 */
open class BindingBean : BaseObservable()