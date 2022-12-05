package com.zhangteng.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangteng.app.R
import com.zhangteng.app.bean.BaseListDemoDbBean
import com.zhangteng.mvvm.adapter.BindingAdapter

class BaseListDemoDbAdapter(data: MutableList<BaseListDemoDbBean?>?) :
    BindingAdapter<BaseListDemoDbBean, BindingAdapter.BindingViewHolder<BaseListDemoDbBean>>(data) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<BaseListDemoDbBean> {
        return BindingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_base_list_demo_db, parent, false)
        )
    }
}