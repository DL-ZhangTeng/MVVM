package com.zhangteng.mvvm.utils

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.TextUtils
import androidx.databinding.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * description: BindingConversions
 * author: Swing
 * date: 2022/12/3
 */
object BindingConversions {
    /**
     * description: 单向数据绑定 String转color
     */
    @BindingConversion
    @JvmStatic
    fun convertStringToColorStateList(color: String?) =
        if (TextUtils.isEmpty(color)) {
            ColorStateList.valueOf(Color.parseColor("#00000000"))
        } else {
            ColorStateList.valueOf(Color.parseColor(color))
        }

    /**
     * description: 单向数据绑定 List转String
     */
    @BindingConversion
    @JvmStatic
    fun convertListToString(list: List<*>): String {
        val builder = StringBuilder()
        val itr = list.iterator()
        while (itr.hasNext()) {
            builder.append(itr.next())
            if (itr.hasNext()) {
                builder.append(", ")
            }
        }
        return builder.toString()
    }

    /**
     * description: 单向数据绑定 Date转String yyyy-MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    @BindingConversion
    @JvmStatic
    fun dateToString(time: Date): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time)
    }
}
