package com.zhangteng.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangteng.app.R
import com.zhangteng.app.http.entity.ArticlesBean
import com.zhangteng.mvvm.adapter.BindingAdapter

class BaseListMvvmDemoDbAdapter(data: MutableList<ArticlesBean?>?) :
    BindingAdapter<ArticlesBean, BindingAdapter.BindingViewHolder<ArticlesBean>>(data) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ArticlesBean> {
        return BindingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_base_list_demo_db, parent, false)
        )
    }
}