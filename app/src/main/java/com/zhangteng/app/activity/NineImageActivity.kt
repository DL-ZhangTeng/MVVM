package com.zhangteng.app.activity

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zhangteng.app.R
import com.zhangteng.base.adapter.NineGridViewClickAdapter
import com.zhangteng.base.base.BaseActivity
import com.zhangteng.base.bean.PreviewImageInfo
import com.zhangteng.base.widget.NineGridView


class NineImageActivity : BaseActivity() {
    private var nineGrid: NineGridView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nineimage)
    }

    override fun initView() {
        NineGridView.imageLoader = object : NineGridView.ImageLoader {
            override fun getCacheImage(context: Context?, url: String?): Bitmap? {
                return null
            }

            override fun onDisplayImage(
                context: Context?,
                imageView: ImageView?,
                thumbnailUrl: String?,
                bigImageUrl: String?,
                onProgressListener: NineGridView.OnProgressListener?
            ) {
                context?.let {
                    imageView?.let { it1 ->
                        Glide.with(it)
                            .load(bigImageUrl ?: thumbnailUrl)
                            .apply(
                                RequestOptions()
                                    .placeholder(R.mipmap.ic_launcher)
                                    .centerCrop()
                            )
                            .into(it1)
                    }
                }
            }

        }

        nineGrid = findViewById(R.id.nineGrid)
        val imageInfo: ArrayList<PreviewImageInfo> = ArrayList()
        imageInfo.add(PreviewImageInfo().apply {
            bigImageUrl =
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F16%2F08%2F26%2F1657bffac4e3795.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633421267&t=d59b36f659f72d06989c79f3cee54bb7"
            thumbnailUrl = bigImageUrl
        })
        imageInfo.add(PreviewImageInfo().apply {
            bigImageUrl =
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F16%2F08%2F26%2F1657bffac4e3795.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633421267&t=d59b36f659f72d06989c79f3cee54bb7"
            thumbnailUrl = bigImageUrl
        })
        imageInfo.add(PreviewImageInfo().apply {
            bigImageUrl =
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F16%2F08%2F26%2F1657bffac4e3795.jpg&refer=http%3A%2F%2Fbpic.588ku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633421267&t=d59b36f659f72d06989c79f3cee54bb7"
            thumbnailUrl = bigImageUrl
        })
//        if (imageInfo.size == 1) {
//            nineGrid?.setSingleImageRatio()
//        }
        nineGrid?.setAdapter(NineGridViewClickAdapter(this, imageInfo))

    }

    override fun initData() {

    }
}