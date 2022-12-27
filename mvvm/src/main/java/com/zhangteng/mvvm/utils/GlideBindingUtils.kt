package com.zhangteng.mvvm.utils

import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.RequestOptions

class GlideBindingUtils {
    object DownampleStrategies {

        /**
         * Downsamples so the image's smallest dimension is between the given dimensions and 2x the given
         * dimensions, with no size restrictions on the image's largest dimension.
         *
         *
         * Does not upscale if the requested dimensions are larger than the original dimensions.
         */
        val AT_LEAST: DownsampleStrategy = DownsampleStrategy.AT_LEAST

        /**
         * Downsamples so the image's largest dimension is between 1/2 the given dimensions and the given
         * dimensions, with no restrictions on the image's smallest dimension.
         *
         *
         * Does not upscale if the requested dimensions are larger than the original dimensions.
         */
        val AT_MOST: DownsampleStrategy = DownsampleStrategy.AT_MOST

        /**
         * Scales, maintaining the original aspect ratio, so that one of the image's dimensions is exactly
         * equal to the requested size and the other dimension is less than or equal to the requested
         * size.
         *
         *
         * This method will upscale if the requested width and height are greater than the source width
         * and height. To avoid upscaling, use [.AT_LEAST], [.AT_MOST] or [ ][.CENTER_INSIDE].
         *
         *
         * On pre-KitKat devices, `FIT_CENTER` will downsample by a power of two only so that one
         * of the image's dimensions is greater than or equal to the requested size. No guarantees are
         * made about the second dimensions. This is *NOT* the same as [.AT_LEAST] because
         * only one dimension, not both, are greater than or equal to the requested dimensions, the other
         * may be smaller.
         */
        val FIT_CENTER: DownsampleStrategy = DownsampleStrategy.FIT_CENTER

        /** Identical to [.FIT_CENTER], but never upscales.  */
        val CENTER_INSIDE: DownsampleStrategy = DownsampleStrategy.CENTER_INSIDE

        /**
         * Scales, maintaining the original aspect ratio, so that one of the image's dimensions is exactly
         * equal to the requested size and the other dimension is greater than or equal to the requested
         * size.
         *
         *
         * This method will upscale if the requested width and height are greater than the source width
         * and height. To avoid upscaling, use [.AT_LEAST], [.AT_MOST], or [ ][.CENTER_INSIDE].
         *
         *
         * On pre-KitKat devices, [Downsampler] treats this as equivalent to [.AT_LEAST]
         * because only power of two downsampling can be used.
         */
        val CENTER_OUTSIDE: DownsampleStrategy = DownsampleStrategy.CENTER_OUTSIDE

        /** Performs no downsampling or scaling.  */
        val NONE: DownsampleStrategy = DownsampleStrategy.NONE
    }

    object BitmapTransformations {
        /**
         * description: Scale the image so that either the width of the image matches the given width and the height of the image is greater than the given height or vice versa, and then crop the larger dimension to match the given dimension.
         *              Does not maintain the image's aspect ratio
         */
        val CENTER_CROP: BitmapTransformation = CenterCrop()

        /**
         * description: Returns the image with its original size if its dimensions match or are smaller than the target's, couple with android.widget.ImageView.ScaleType.CENTER_INSIDE in order to center it in Target. If not, then it is scaled so that one of the dimensions of the image will be equal to the given dimension and the other will be less than the given dimension (maintaining the image's aspect ratio).
         */
        val CENTER_INSIDE: BitmapTransformation = CenterInside()

        /**
         * description: Scales the image uniformly (maintaining the image's aspect ratio) so that one of the dimensions of the image will be equal to the given dimension and the other will be less than the given dimension.
         */
        val FIT_CENTER: BitmapTransformation = FitCenter()

        /**
         * description: A Glide BitmapTransformation to circle crop an image. Behaves similar to a FitCenter transform, but the resulting image is masked to a circle.
         *              Uses a PorterDuff blend mode, see http://ssp.impulsetrain.com/porterduff.html.
         */
        val CIRCLE_CROP: BitmapTransformation = CircleCrop()

        /**
         * description: A BitmapTransformation which rounds the corners of a bitmap.
         */
        val ROUNDED_CORNERS: BitmapTransformation = RoundedCorners(8)

        /**
         * description: A BitmapTransformation which has a different radius for each corner of a bitmap.
         */
        val GRANULAR_ROUNDED_CORNERS: BitmapTransformation = GranularRoundedCorners(8f, 8f, 8f, 8f)

        /**
         * description: Params:roundingRadius – the corner radius (in device-specific pixels).
         *              Throws:IllegalArgumentException – if rounding radius is 0 or less.
         */
        fun getRoundedCorners(roundingRadius: Int): RoundedCorners {
            return RoundedCorners(roundingRadius)
        }

        /**
         * description: Provide the radii to round the corners of the bitmap.
         */
        fun getGranularRoundedCorners(
            topLeft: Float,
            topRight: Float,
            bottomRight: Float,
            bottomLeft: Float
        ): GranularRoundedCorners {
            return GranularRoundedCorners(topLeft, topRight, bottomRight, bottomLeft)
        }
    }

    object RequestOptionsEntities {
        /**
         * description: Scale the image so that either the width of the image matches the given width and the height of the image is greater than the given height or vice versa, and then crop the larger dimension to match the given dimension.
         *              Does not maintain the image's aspect ratio
         */
        val CENTER_CROP_OPTIONS = RequestOptions().centerCrop()

        /**
         * description: Returns the image with its original size if its dimensions match or are smaller than the target's, couple with android.widget.ImageView.ScaleType.CENTER_INSIDE in order to center it in Target. If not, then it is scaled so that one of the dimensions of the image will be equal to the given dimension and the other will be less than the given dimension (maintaining the image's aspect ratio).
         */
        val CENTER_INSIDE_OPTIONS = RequestOptions().centerInside()

        /**
         * description: Scales the image uniformly (maintaining the image's aspect ratio) so that one of the dimensions of the image will be equal to the given dimension and the other will be less than the given dimension.
         */
        val FIT_CENTER_OPTIONS = RequestOptions().fitCenter()

        /**
         * description: A Glide RequestOptions to circle crop an image. Behaves similar to a FitCenter transform, but the resulting image is masked to a circle.
         */
        val CIRCLE_CROP_OPTIONS = RequestOptions().circleCrop()

        /**
         * description: A RequestOptions which rounds the corners of a bitmap.
         */
        val ROUNDED_CORNERS_OPTIONS = RequestOptions()
            .downsample(DownampleStrategies.CENTER_INSIDE)
            .transform(BitmapTransformations.ROUNDED_CORNERS)

        /**
         * description: A RequestOptions which has a different radius for each corner of a bitmap.
         */
        val GRANULAR_ROUNDED_CORNERS_OPTIONS = RequestOptions()
            .downsample(DownampleStrategies.CENTER_INSIDE)
            .transform(BitmapTransformations.GRANULAR_ROUNDED_CORNERS)

        /**
         * description: A Glide RequestOptions to a no downsampling or scaling for a bitmap.
         */
        val NONE = RequestOptions()

        /**
         * description: Scale the image so that either the width of the image matches the given width and the height of the image is greater than the given height or vice versa, and then crop the larger dimension to match the given dimension.
         *              Does not maintain the image's aspect ratio
         */
        fun getCenterCropOptions(roundingRadius: Int): RequestOptions {
            return RequestOptions().centerCrop()
        }

        /**
         * description: Returns the image with its original size if its dimensions match or are smaller than the target's, couple with android.widget.ImageView.ScaleType.CENTER_INSIDE in order to center it in Target. If not, then it is scaled so that one of the dimensions of the image will be equal to the given dimension and the other will be less than the given dimension (maintaining the image's aspect ratio).
         */
        fun getCenterInsideOptions(roundingRadius: Int): RequestOptions {
            return RequestOptions().centerInside()
        }

        /**
         * description: Scales the image uniformly (maintaining the image's aspect ratio) so that one of the dimensions of the image will be equal to the given dimension and the other will be less than the given dimension.
         */
        fun getFitCenterOptions(roundingRadius: Int): RequestOptions {
            return RequestOptions().fitCenter()
        }

        /**
         * description: A Glide RequestOptions to circle crop an image. Behaves similar to a FitCenter transform, but the resulting image is masked to a circle.
         */
        fun getCircleCropOptions(roundingRadius: Int): RequestOptions {
            return RequestOptions().circleCrop()
        }

        /**
         * description: Params:roundingRadius – the corner radius (in device-specific pixels).
         *              Throws:IllegalArgumentException – if rounding radius is 0 or less.
         */
        fun getRoundedCornersOptions(roundingRadius: Int): RequestOptions {
            return RequestOptions()
                .downsample(DownampleStrategies.CENTER_INSIDE)
                .transform(BitmapTransformations.getRoundedCorners(roundingRadius))
        }

        /**
         * description: Provide the radii to round the corners of the bitmap.
         */
        fun getGranularRoundedCornersOptions(
            topLeft: Float,
            topRight: Float,
            bottomRight: Float,
            bottomLeft: Float
        ): RequestOptions {
            return RequestOptions()
                .downsample(DownampleStrategies.CENTER_INSIDE)
                .transform(
                    BitmapTransformations.getGranularRoundedCorners(
                        topLeft,
                        topRight,
                        bottomRight,
                        bottomLeft
                    )
                )
        }

        /**
         * description: A Glide RequestOptions to a no downsampling or scaling for a bitmap.
         */
        fun getNoneOptions(roundingRadius: Int): RequestOptions {
            return RequestOptions()
        }
    }
}