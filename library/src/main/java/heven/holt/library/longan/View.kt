
@file:Suppress("unused")

package heven.holt.library.longan

import android.content.res.TypedArray
import android.graphics.Outline
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import heven.holt.library.R
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private var View.lastClickTime: Long? by viewTags(R.id.tag_last_click_time)

fun List<View>.doOnClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
  forEach { it.doOnClick(clickIntervals, isSharingIntervals, block) }

fun View.doOnClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
  setOnClickListener {
    val view = if (isSharingIntervals) context.activity?.window?.decorView ?: this else this
    val currentTime = System.currentTimeMillis()
    val lastTime = view.lastClickTime ?: 0L
    if (currentTime - lastTime > clickIntervals) {
      view.lastClickTime = currentTime
      block()
    }
  }

inline fun List<View>.doOnClick(crossinline block: () -> Unit) =
  forEach { it.doOnClick(block) }

inline fun View.doOnClick(crossinline block: () -> Unit) =
  setOnClickListener { block() }

fun List<View>.doOnLongClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
  forEach { it.doOnLongClick(clickIntervals, isSharingIntervals, block) }

fun View.doOnLongClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
  doOnLongClick {
    val view = if (isSharingIntervals) context.activity?.window?.decorView ?: this else this
    val currentTime = System.currentTimeMillis()
    val lastTime = view.lastClickTime ?: 0L
    if (currentTime - lastTime > clickIntervals) {
      view.lastClickTime = currentTime
      block()
    }
  }

inline fun List<View>.doOnLongClick(crossinline block: () -> Unit) =
  forEach { it.doOnLongClick(block) }

inline fun View.doOnLongClick(crossinline block: () -> Unit) =
  setOnLongClickListener {
    block()
    true
  }

var View.roundCorners: Float
  @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR)
  get() = noGetter()
  set(value) {
    clipToOutline = true
    outlineProvider = object : ViewOutlineProvider() {
      override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(0, 0, view.width, view.height, value)
      }
    }
  }

fun View?.isTouchedAt(x: Float, y: Float): Boolean =
  isTouchedAt(x.toInt(), y.toInt())

fun View?.isTouchedAt(x: Int, y: Int): Boolean =
  this?.locationOnScreen?.run { x in left..right && y in top..bottom } ?: false

fun View.findTouchedChild(x: Float, y: Float): View? =
  findTouchedChild(x.toInt(), y.toInt())

fun View.findTouchedChild(x: Int, y: Int): View? =
  touchables.find { it.isTouchedAt(x, y) }

/**
 * Computes the coordinates of this view on the screen.
 */
inline val View.locationOnScreen: Rect
  get() = IntArray(2).let {
    getLocationOnScreen(it)
    Rect(it[0], it[1], it[0] + width, it[1] + height)
  }

@Deprecated(
  "Replace with new api",
  replaceWith = ReplaceWith("withStyledAttributes(set, attrs, defStyleAttr, defStyleRes, block)")
)
inline fun View.withStyledAttrs(
  set: AttributeSet?,
  @StyleableRes attrs: IntArray,
  @AttrRes defStyleAttr: Int = 0,
  @StyleRes defStyleRes: Int = 0,
  block: TypedArray.() -> Unit
) =
  context.withStyledAttributes(set, attrs, defStyleAttr, defStyleRes, block)

inline fun View.withStyledAttributes(
  set: AttributeSet?,
  @StyleableRes attrs: IntArray,
  @AttrRes defStyleAttr: Int = 0,
  @StyleRes defStyleRes: Int = 0,
  block: TypedArray.() -> Unit
) =
  context.withStyledAttributes(set, attrs, defStyleAttr, defStyleRes, block)

val View.rootWindowInsetsCompat: WindowInsetsCompat? by viewTags(R.id.tag_root_window_insets) {
  ViewCompat.getRootWindowInsets(this)
}

val View.windowInsetsControllerCompat: WindowInsetsControllerCompat? by viewTags(R.id.tag_window_insets_controller) {
  ViewCompat.getWindowInsetsController(this)
}

fun View.doOnApplyWindowInsets(action: (View, WindowInsetsCompat) -> WindowInsetsCompat) =
  ViewCompat.setOnApplyWindowInsetsListener(this, action)

fun <T> viewTags(key: Int) = object : ReadWriteProperty<View, T?> {
  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: View, property: KProperty<*>) =
    thisRef.getTag(key) as? T

  override fun setValue(thisRef: View, property: KProperty<*>, value: T?) =
    thisRef.setTag(key, value)
}

@Suppress("UNCHECKED_CAST")
fun <T> viewTags(key: Int, block: View.() -> T) = ReadOnlyProperty<View, T> { thisRef, _ ->
  if (thisRef.getTag(key) == null) {
    thisRef.setTag(key, block(thisRef))
  }
  thisRef.getTag(key) as T
}
