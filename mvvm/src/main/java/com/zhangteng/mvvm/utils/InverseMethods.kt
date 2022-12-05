package com.zhangteng.mvvm.utils

import androidx.databinding.InverseMethod
import java.math.BigDecimal

/**
 * description: InverseMethods
 * author: Swing
 * date: 2022/12/3
 */
object InverseMethods {
    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.byteToString(demo.name)}”/>
     */
    @InverseMethod("stringToByte")
    @JvmStatic
    fun byteToString(value: Byte?): String {
        return value?.toString() ?: ""
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun stringToByte(value: String?): Byte {
        return value?.toByte() ?: 0
    }

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.shortToString(demo.name)}”/>
     */
    @InverseMethod("stringToShort")
    @JvmStatic
    fun shortToString(value: Short?): String {
        return value?.toString() ?: ""
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun stringToShort(value: String?): Short {
        return value?.toShort() ?: 0
    }

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.intToString(demo.name)}”/>
     */
    @InverseMethod("stringToInt")
    @JvmStatic
    fun intToString(value: Int?): String {
        return value?.toString() ?: ""
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun stringToInt(value: String?): Int {
        return value?.toInt() ?: 0
    }

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.longToString(demo.name)}”/>
     */
    @InverseMethod("stringToLong")
    @JvmStatic
    fun longToString(value: Long?): String {
        return value?.toString() ?: ""
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun stringToLong(value: String?): Long {
        return value?.toLong() ?: 0
    }

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.floatToString(demo.name)}”/>
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

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.doubleToString(demo.name)}”/>
     */
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double?): String {
        return value?.toString() ?: ""
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun stringToDouble(value: String?): Double {
        return value?.toDouble() ?: 0.0
    }

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.bigDecimalToString(demo.name)}”/>
     */
    @InverseMethod("stringToBigDecimal")
    @JvmStatic
    fun bigDecimalToString(value: BigDecimal?): String {
        return value?.toString() ?: ""
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun stringToBigDecimal(value: String?): BigDecimal {
        return value?.toBigDecimal() ?: BigDecimal("0")
    }

    /**
     * description 双向数据转换 正向转换器/反向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.booleanToString(demo.name)}”/>
     */
    @InverseMethod("stringToBoolean")
    @JvmStatic
    fun booleanToString(value: Boolean?): String {
        return value?.toString() ?: "false"
    }

    /**
     * description 双向数据转换 正向转换器/反向转换器
     *  <import type=“..InverseMethods”/>
     *  <RadioButton
     *  android:checked=“@={InverseMethods.stringToBoolean(demo.name)}”/>
     */
    @InverseMethod("booleanToString")
    @JvmStatic
    fun stringToBoolean(value: String?): Boolean {
        return value?.toBoolean() ?: false
    }

    /**
     * description 双向数据转换 正向转换器/反向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.booleanToCnString(demo.name)}”/>
     */
    @InverseMethod("cnStringToBoolean")
    @JvmStatic
    fun booleanToCnString(value: Boolean?): String {
        return if (value == true) "是" else "否"
    }

    /**
     * description 双向数据转换 正向转换器/反向转换器
     *  <import type=“..InverseMethods”/>
     *  <RadioButton
     *  android:checked=“@={InverseMethods.cnStringToBoolean(demo.name)}”/>
     */
    @InverseMethod("booleanToCnString")
    @JvmStatic
    fun cnStringToBoolean(value: String?): Boolean {
        return value == "是" || value == "对" || value == "正确"
    }

    /**
     * description 双向数据转换 正向转换器/反向转换器
     *  <import type=“..InverseMethods”/>
     *  <EditText
     *  android:text=“@={InverseMethods.booleanToMathString(demo.name)}”/>
     */
    @InverseMethod("mathStringToBoolean")
    @JvmStatic
    fun booleanToMathString(value: Boolean?): String {
        return if (value == true) "1" else "0"
    }

    /**
     * description 双向数据转换 正向转换器/反向转换器
     *  <import type=“..InverseMethods”/>
     *  <RadioButton
     *  android:checked=“@={InverseMethods.mathStringToBoolean(demo.name)}”/>
     */
    @InverseMethod("booleanToMathString")
    @JvmStatic
    fun mathStringToBoolean(value: String?): Boolean {
        return value != null && value != "0"
    }

    /**
     * description 双向数据转换 正向转换器
     *  <import type=“..InverseMethods”/>
     *  <RadioButton
     *  android:checked=“@={InverseMethods.intToBoolean(demo.name)}”/>
     */
    @InverseMethod("booleanToInt")
    @JvmStatic
    fun intToBoolean(value: Int?): Boolean {
        return value != null && value != 0
    }

    /**
     * description 双向数据转换 反向转换器
     */
    @JvmStatic
    fun booleanToInt(value: Boolean?): Int {
        return if (value == true) 1 else 0
    }
}