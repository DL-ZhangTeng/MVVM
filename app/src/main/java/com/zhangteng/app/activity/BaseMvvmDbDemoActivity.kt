package com.zhangteng.app.activity

import android.os.Bundle
import com.zhangteng.app.R
import com.zhangteng.app.databinding.ActivityBaseMvvmDbDemoDbBinding
import com.zhangteng.app.mvvm.vm.BaseMvvmDbDemoDbViewModel
import com.zhangteng.mvvm.mvvm.db.BaseMvvmActivity

class BaseMvvmDbDemoActivity :
    BaseMvvmActivity<ActivityBaseMvvmDbDemoDbBinding, BaseMvvmDbDemoDbViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_mvvm_db_demo_db)
    }

    override fun initView() {

    }

    override fun initData() {

    }
}