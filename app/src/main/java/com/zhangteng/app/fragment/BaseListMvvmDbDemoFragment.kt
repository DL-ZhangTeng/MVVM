package com.zhangteng.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.app.R
import com.zhangteng.app.adapter.BaseListDemoDbAdapter
import com.zhangteng.app.bean.BaseListDemoDbBean
import com.zhangteng.app.databinding.FragmentBaseListMvvmDbDemoDbBinding
import com.zhangteng.app.mvvm.vm.BaseListMvvmDbDemoDbFragmentViewModel
import com.zhangteng.mvvm.adapter.BindingAdapter
import com.zhangteng.mvvm.mvvm.db.BaseListMvvmFragment

class BaseListMvvmDbDemoFragment :
    BaseListMvvmFragment<FragmentBaseListMvvmDbDemoDbBinding, BaseListMvvmDbDemoDbFragmentViewModel, BaseListDemoDbBean, BindingAdapter.BindingViewHolder<BaseListDemoDbBean>, BaseListDemoDbAdapter>() {

    companion object {
        fun newInstance() = BaseListMvvmDbDemoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_list_mvvm_db_demo_db, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
    }

    override fun createAdapter(): BaseListDemoDbAdapter {
        return BaseListDemoDbAdapter()
    }

    override fun getRecyclerView(): RecyclerView {
        return mDatabind.recyclerView
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout {
        return mDatabind.smartRefreshLayout
    }

    override fun loadData(i: Int) {}
    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
}