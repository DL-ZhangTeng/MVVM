package com.zhangteng.app.ui.mvvmdb

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout

object TabLayoutAdapter {

    @BindingAdapter(value = ["items"], requireAll = false)
    @JvmStatic
    fun setTabText(tabLayout: TabLayout, items: List<String>) {
        items.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }
    }


    @BindingAdapter(value = ["tabItemClick"], requireAll = false)
    @JvmStatic
    fun tabItemClick(tabLayout: TabLayout, listener: TabLayout.OnTabSelectedListener) {
        tabLayout.addOnTabSelectedListener(listener)
    }


}