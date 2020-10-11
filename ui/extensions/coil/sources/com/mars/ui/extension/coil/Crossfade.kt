@file:Suppress("FunctionName", "EXPERIMENTAL_API_USAGE", "SpellCheckingInspection")

package com.mars.ui.extension.coil

import android.graphics.drawable.Drawable
import androidx.annotation.FloatRange
import coil.ImageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.OriginalSize
import coil.size.Scale
import coil.size.Size
import coil.transform.Transformation
import coil.transition.CrossfadeTransition
import com.mars.ui.Ui
import com.mars.ui.asLayout
import com.mars.ui.core.Modifier
import com.mars.ui.extension.coil.preview.defaultImageLoader
import com.mars.ui.widget.implement.Image

@PublishedApi internal const val DefaultTransitionDuration = 1000


/**
 * 具有淡入淡出动画的 Coil 图片视图
 * @see CoilImage
 * @see CrossfadeTransition
 */
fun Ui.CoilFadeImage(
  data: Any?,
  lazy: Boolean = false,
  modifier: Modifier = Modifier,
  loader: ImageLoader = this.asLayout.context.defaultImageLoader,
  scale: Scale = Scale.FIT,
  size: Size = OriginalSize,
  transformation: Transformation? = null,
  transformations: List<Transformation>? = null,
  fadeDuration: Int = DefaultTransitionDuration,
  placeholder: Drawable? = null,
  error: Drawable? = null,
  fallback: Drawable? = null,
  memoryCache: CachePolicy = CachePolicy.ENABLED,
  diskCache: CachePolicy = CachePolicy.ENABLED,
  networkCache: CachePolicy = CachePolicy.ENABLED,
  allowHardware: Boolean = true,
  allowRgb565: Boolean = false,
  @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1.0f,
): Image = CoilImage(
  data,
  lazy,
  modifier,
  loader,
  scale,
  size,
  transformation,
  transformations,
  CrossfadeTransition(fadeDuration),
  placeholder,
  error,
  fallback,
  memoryCache,
  diskCache,
  networkCache,
  allowHardware,
  allowRgb565,
  alpha
)


/**
 * 具有淡入淡出动画的 Coil 图片视图
 * @see CoilImage
 * @see CrossfadeTransition
 */
inline fun Ui.CoilFadeImage(
  data: Any?,
  lazy: Boolean = false,
  modifier: Modifier = Modifier,
  loader: ImageLoader = this.asLayout.context.defaultImageLoader,
  fadeDuration: Int = DefaultTransitionDuration,
  @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1.0f,
  builder: ImageRequest.Builder.() -> Unit,
): Image = CoilImage(data, lazy, modifier, loader, alpha) {
  crossfade(fadeDuration)
  builder()
}