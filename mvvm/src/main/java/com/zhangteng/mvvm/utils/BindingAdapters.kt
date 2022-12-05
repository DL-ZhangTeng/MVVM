package com.zhangteng.mvvm.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * description: BindingAdapter
 * author: Swing
 * date: 2022/12/3
 */
object BindingAdapters {
    /**
     * description: 设置网络图centerCrop
     */
    @BindingAdapter("app:setImageUrl")
    fun ImageView.setImageUrl(
        url: String?,
        options: RequestOptions = RequestOptions().centerCrop()
    ) {
        Glide.with(context)
            .load(url)
            .apply(options)
            .into(this)
    }

    /**
     * description: 设置Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("app:setDrawableStart")
    @JvmStatic
    fun TextView.setDrawableStart(@DrawableRes resId: Int) {
        val drawable: Drawable? = this.resources.getDrawable(resId, null)
        //注意查看方法TextView.setCompoundDrawables(Drawable, Drawable, Drawable, Drawable)
        //的注释，要求设置的drawable必须已经通过Drawable.setBounds方法设置过边界参数
        //所以，此种方式下该行必不可少
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        this.setCompoundDrawables(drawable, null, null, null)
    }

    /**
     * description: 设置Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("app:setDrawableTop")
    @JvmStatic
    fun TextView.setDrawableTop(@DrawableRes resId: Int) {
        val drawable: Drawable? = this.resources.getDrawable(resId, null)
        //注意查看方法TextView.setCompoundDrawables(Drawable, Drawable, Drawable, Drawable)
        //的注释，要求设置的drawable必须已经通过Drawable.setBounds方法设置过边界参数
        //所以，此种方式下该行必不可少
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        this.setCompoundDrawables(null, drawable, null, null)
    }

    /**
     * description: 设置Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("app:setDrawableEnd")
    @JvmStatic
    fun TextView.setDrawableEnd(@DrawableRes resId: Int) {
        val drawable: Drawable? = this.resources.getDrawable(resId, null)
        //注意查看方法TextView.setCompoundDrawables(Drawable, Drawable, Drawable, Drawable)
        //的注释，要求设置的drawable必须已经通过Drawable.setBounds方法设置过边界参数
        //所以，此种方式下该行必不可少
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        this.setCompoundDrawables(null, null, drawable, null)
    }

    /**
     * description: 设置Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("app:setDrawableBottom")
    @JvmStatic
    fun TextView.setDrawableBottom(@DrawableRes resId: Int) {
        val drawable: Drawable? = this.resources.getDrawable(resId, null)
        //注意查看方法TextView.setCompoundDrawables(Drawable, Drawable, Drawable, Drawable)
        //的注释，要求设置的drawable必须已经通过Drawable.setBounds方法设置过边界参数
        //所以，此种方式下该行必不可少
        drawable?.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        this.setCompoundDrawables(null, null, null, drawable)
    }

    /**
     * description: 自定义双向绑定 绑定数据 防止无限循环的发生，只有是更新的数据才可以被赋值。
     */
    @BindingAdapter("app:setTextString")
    fun TextView.setTextString(text: CharSequence?) {
        val oldText = this.text
        if (text == oldText || oldText.isNullOrEmpty()) {
            return
        }
        this.text = text
    }

    /**
     * description: 自定义双向绑定 绑定事件
     */
    @BindingAdapter("app:setTextWatcher")
    fun TextView.setTextWatcher(textAttrChanged: InverseBindingListener?) {
        val newValue: TextWatcher? = if (textAttrChanged == null) {
            null
        } else {
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    textAttrChanged.onChange()
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        }
        if (newValue != null) {
            this.addTextChangedListener(newValue)
        }
    }

    /**
     * description:自定义双向绑定 InverseBindingAdapter注解：该注解主要有两个参数，attribute指定数据绑定的名称，event指定事件绑定的名称。
     *                                              作用：将两者结合起来形成双向绑定，并且响应事件绑定，在事件发生时返回控件的值。
     * <EditText
     * app:setTextString=“@={demo.name}”/>
     */
    @InverseBindingAdapter(attribute = "app:setTextString", event = "app:setTextWatcher")
    fun TextView.getTextString(): CharSequence {
        return this.text
    }
}