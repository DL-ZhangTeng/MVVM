package com.zhangteng.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.app.R
import com.zhangteng.app.adapter.BaseListDemoAdapter
import com.zhangteng.app.bean.BaseListDemoBean
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.base.base.BaseListFragment

class BaseListDemoFragment :
    BaseListFragment<BaseListDemoBean, BaseAdapter.DefaultViewHolder, BaseListDemoAdapter>() {

    companion object {
        fun newInstance() = BaseListDemoFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_list_demo, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
    }

    override fun createAdapter(): BaseListDemoAdapter {
        return BaseListDemoAdapter()
    }

    override fun getRecyclerView(): RecyclerView? {
        return view?.findViewById(R.id.recyclerView)
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout? {
        return view?.findViewById(R.id.smartRefreshLayout)
    }

    override fun loadData(i: Int) {}
    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
}