package com.zhangteng.app.activity

import android.os.Bundle
import android.widget.TextView
import com.zhangteng.app.R
import com.zhangteng.app.databinding.ActivityTabLayoutDbBinding
import com.zhangteng.app.mvvm.vm.TabLayoutDbViewModel
import com.zhangteng.base.adapter.CommonFragmentAdapter
import com.zhangteng.base.widget.MyTabLayout
import com.zhangteng.base.widget.MyTabLayoutMediator
import com.zhangteng.mvvm.mvvm.db.BaseMvvmActivity

class TabLayoutActivity : BaseMvvmActivity<ActivityTabLayoutDbBinding, TabLayoutDbViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_db)
    }

    override fun initView() {
        mViewModel.fm = supportFragmentManager

        //使用newTab初始化Tab
        mViewModel.onTabSelectedListener = object : MyTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: MyTabLayout.Tab?) {

            }

            override fun onTabSelected(tab: MyTabLayout.Tab?) {
                tab?.getPosition()?.let { mDatabind.vp2.currentItem = it }
            }

            override fun onTabUnselected(tab: MyTabLayout.Tab?) {

            }

        }
        mViewModel.onPageChangeListener =
            MyTabLayout.TabLayoutOnPageChangeListener(mDatabind.tabLayout2)

        //使用MyTabLayoutMediator初始化Tab
        mViewModel.tblViewPagerAdapter = CommonFragmentAdapter(
            supportFragmentManager,
            mViewModel.titleList,
            mViewModel.fragments3
        )
        mViewModel.tblTabConfigurationStrategy =
            object : MyTabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: MyTabLayout.Tab, position: Int) {
                    tab.setCustomView(TextView(this@TabLayoutActivity).apply {
                        text = mViewModel.titleList[position]
                    })
                }
            }
    }

    override fun initData() {

    }
}