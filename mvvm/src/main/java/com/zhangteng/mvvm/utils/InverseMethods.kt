package com.zhangteng.mvvm.utils

import androidx.databinding.InverseMethod

/**
 * description: InverseMethods
 * author: Swing
 * date: 2022/12/3
 */
object InverseMethods {

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“DataBindingUtils”/>
     *  <EditText
     *  android:text=“@={DataBindingUtils.floatToString(demo.name)}”/>
     */
    @InverseMethod("stringToFloat")
    @JvmStatic
    fun floatToString(value: Float?): String {
        return value?.toString() ?: ""
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun stringToFloat(value: String?): Float {
        return value?.toFloat() ?: 0f
    }
}