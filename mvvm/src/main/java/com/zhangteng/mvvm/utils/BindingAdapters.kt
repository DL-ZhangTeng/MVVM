package com.zhangteng.mvvm.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
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
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * description: BindingAdapter
 * author: Swing
 * date: 2022/12/3
 */
object BindingAdapters {
    /**
     * description: 设置网络图
     *
     *              缩放类型                      downsampleStrategy                    transformation
     *              CenterCrop:                 DownsampleStrategy.CENTER_OUTSIDE     CenterCrop()
     *              CenterInside:               DownsampleStrategy.CENTER_INSIDE      CenterInside()
     *              FitCenter:                  DownsampleStrategy.FIT_CENTER         FitCenter()
     *              CircleCrop:                 DownsampleStrategy.CENTER_INSIDE      CircleCrop()
     *              RoundedCorners:             DownsampleStrategy.CENTER_INSIDE      RoundedCorners()
     *              GranularRoundedCorners:     DownsampleStrategy.CENTER_INSIDE      GranularRoundedCorners()
     *
     *              requireAll为false, 你没有填写的属性值将为null. 所以需要做非空判断
     *
     *    <ImageView
     *          android:layout_width="180dp"
     *          android:layout_height="match_parent"
     *          app:glideDownsampleStrategy="@{GlideBindingUtils.DownampleStrategies.INSTANCE.CENTER_INSIDE}"
     *          app:glideError="@{R.mipmap.ic_launcher}"
     *          app:glideImageUrl='@{"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F16%2F08%2F26%2F1657bffac4e3795.jpg&amp;refer=http%3A%2F%2Fbpic.588ku.com&amp;app=2002&amp;size=f9999,10000&amp;q=a80&amp;n=0&amp;g=0n&amp;fmt=jpeg?sec=1633421267&amp;t=d59b36f659f72d06989c79f3cee54bb7"}'
     *          app:glidePlaceHolder="@{R.mipmap.ic_launcher}"
     *          app:glideTransformation="@{GlideBindingUtils.BitmapTransformations.INSTANCE.CIRCLE_CROP}" />
     *
     *
     *     <ImageView
     *          android:layout_width="180dp"
     *          android:layout_height="match_parent"
     *          app:glideImageUrl='@{"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F16%2F08%2F26%2F1657bffac4e3795.jpg&amp;refer=http%3A%2F%2Fbpic.588ku.com&amp;app=2002&amp;size=f9999,10000&amp;q=a80&amp;n=0&amp;g=0n&amp;fmt=jpeg?sec=1633421267&amp;t=d59b36f659f72d06989c79f3cee54bb7"}'
     *          app:glideRequestOptions="@{GlideBindingUtils.RequestOptionsEntities.INSTANCE.ROUNDED_CORNERS_OPTIONS.error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)}" />
     */
    @BindingAdapter(
        value = [
            "glideImageUrl",
            "glidePlaceHolder",
            "glideError",
            "glideDownsampleStrategy",
            "glideTransformation",
            "glideRequestOptions"
        ],
        requireAll = false
    )
    @JvmStatic
    fun ImageView.glideLoadImage(
        glideImageUrl: String?,
        glidePlaceHolder: Drawable?,
        glideError: Drawable?,
        glideDownsampleStrategy: DownsampleStrategy?,
        glideTransformation: Transformation<Bitmap>?,
        glideRequestOptions: RequestOptions? = null,
    ) {
        var requestOptions = glideRequestOptions
        if (requestOptions == null) {
            requestOptions = RequestOptions()
        }
        glideError?.let { requestOptions.error(glideError) }
        glidePlaceHolder?.let { requestOptions.placeholder(glidePlaceHolder) }
        glideTransformation?.let { requestOptions.transform(glideTransformation) }
        glideDownsampleStrategy?.let { requestOptions.downsample(glideDownsampleStrategy) }
        Glide.with(context)
            .load(glideImageUrl)
            .apply(requestOptions)
            .into(this)
    }

    /**
     * description: 设置Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("setDrawableStart")
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
    @BindingAdapter("setDrawableTop")
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
    @BindingAdapter("setDrawableEnd")
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
    @BindingAdapter("setDrawableBottom")
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
    @BindingAdapter("setTextString")
    @JvmStatic
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
    @BindingAdapter("setTextWatcher")
    @JvmStatic
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
    @InverseBindingAdapter(attribute = "setTextString", event = "setTextWatcher")
    @JvmStatic
    fun TextView.getTextString(): CharSequence {
        return this.text
    }
}