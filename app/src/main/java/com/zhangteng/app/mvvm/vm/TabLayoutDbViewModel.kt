package com.zhangteng.app.mvvm.vm

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.zhangteng.app.fragment.BaseDemoFragment
import com.zhangteng.app.mvvm.repository.TabLayoutDbRepository
import com.zhangteng.base.widget.MyTabLayout
import com.zhangteng.base.widget.MyTabLayoutMediator
import com.zhangteng.mvvm.base.BaseViewModel

class TabLayoutDbViewModel : BaseViewModel() {
    private val mRepository by lazy { TabLayoutDbRepository() }
    public lateinit var fm: FragmentManager
    public val titleList: Array<String?> =
        arrayOf("111111", "111111", "111111", "111111", "111111")

    public val fragments1 = ArrayList<Fragment>().apply {
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
    }

    public lateinit var onTabSelectedListener: MyTabLayout.OnTabSelectedListener
    public lateinit var onPageChangeListener: ViewPager.OnPageChangeListener
    public val fragments2 = ArrayList<Fragment>().apply {
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
    }

    public lateinit var tblViewPagerAdapter: PagerAdapter
    public lateinit var tblTabConfigurationStrategy: MyTabLayoutMediator.TabConfigurationStrategy
    public val fragments3 = ArrayList<Fragment>().apply {
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
        add(BaseDemoFragment())
    }

}