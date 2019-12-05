package cn.leo.frame.image

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import java.io.File

/**
 * @author : ling luo
 * @date : 2019-08-31
 */

/**
 * glide 加载图片封装
 */
fun ImageView.loadImage(
    url: String? = null,
    uri: Uri? = null,
    @RawRes @DrawableRes
    resId: Int = -1,
    file: File? = null,
    bitmap: Bitmap? = null,
    circle: Boolean = false,
    skipCache: Boolean = false,
    corners: Int = 0,
    @DrawableRes defResId: Int = -1,
    @DrawableRes errResId: Int = defResId,
    onLoadFailed: (
        exception: GlideException?,
        isFirstResource: Boolean
    ) -> Unit = { _, _ -> },
    onLoadSuccess: (
        resource: Drawable?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ) -> Unit = { _, _, _ -> }

) {
    //页面泄漏处理
    if ((context as? Activity)?.isDestroyed == true) {
        return
    }
    //图片位置处理
    val transforms =
        when (scaleType) {
            ImageView.ScaleType.FIT_CENTER -> FitCenter()
            ImageView.ScaleType.CENTER_INSIDE -> CenterInside()
            else -> CenterCrop()
        }

    Glide.with(this)
        //资源加载途径
        .load(
            when {
                !url.isNullOrEmpty() -> url
                uri != null -> uri
                resId != -1 -> resId
                file != null -> file
                bitmap != null -> bitmap
                else -> null
            }
        )
        //缓存处理
        .skipMemoryCache(skipCache)
        .diskCacheStrategy(
            if (skipCache) {
                DiskCacheStrategy.NONE
            } else {
                DiskCacheStrategy.AUTOMATIC
            }
        )
        //图片裁剪
        .transform(
            *when {
                circle -> arrayOf(CircleCrop(), transforms)
                (corners > 0) -> arrayOf(RoundedCorners(corners), transforms)
                else -> arrayOf(transforms)
            }
        )
        //图片加载动画
        .transition(withCrossFade())
        //占位图
        .placeholder(defResId)
        .error(loadTransform(this, errResId, circle, corners))
        //加载回调
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadFailed(e, isFirstResource)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadSuccess(resource, dataSource, isFirstResource)
                return false
            }

        })
        //缩略图
        .thumbnail(loadTransform(this, defResId, circle, corners))
        .into(this)
}

/**
 * 占位图处理
 */
private fun loadTransform(
    view: ImageView,
    @DrawableRes resId: Int = -1,
    circle: Boolean = false,
    corners: Int = 0
): RequestBuilder<Drawable>? {
    if (!circle && corners == 0) {
        return null
    }
    return Glide.with(view)
        .load(resId)
        .apply(
            RequestOptions
                .centerCropTransform()
                .transform(
                    when {
                        circle -> CircleCrop()
                        corners > 0 -> RoundedCorners(corners)
                        else -> CenterCrop()
                    }
                )
        )
}

/**
 * 获取bitmap
 */
fun Context.getBitmap(
    url: String,
    callback: (bitmap: Bitmap, width: Int, height: Int) -> Unit
) {
    Glide.with(this)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {

            }

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                callback(resource, resource.width, resource.height)
            }

        })
}

/**
 * 下载图片文件
 */
fun Context.downloadImage(url: String): File? {
    return try {
        Glide.with(this)
            .asFile()
            .load(url)
            .submit()
            .get()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


