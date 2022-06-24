
@file:Suppress("unused")

package heven.holt.library.longan

import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArguments(vararg pairs: Pair<String, *>) = apply {
  arguments = bundleOf(*pairs)
}

fun <T> Fragment.arguments(key: String) = lazy<T?> {
  arguments[key]
}

fun <T> Fragment.arguments(key: String, default: T) = lazy {
  arguments[key] ?: default
}

fun <T> Fragment.safeArguments(name: String) = lazy<T> {
  checkNotNull(arguments[name]) { "No intent value for key \"$name\"" }
}

fun Fragment.pressBackTwiceToExitApp(toastText: String, delayMillis: Long = 2000) =
  requireActivity().pressBackTwiceToExitApp(toastText, delayMillis, viewLifecycleOwner)

fun Fragment.pressBackTwiceToExitApp(@StringRes toastText: Int, delayMillis: Long = 2000) =
  requireActivity().pressBackTwiceToExitApp(toastText, delayMillis, viewLifecycleOwner)

fun Fragment.pressBackTwiceToExitApp(delayMillis: Long = 2000, onFirstBackPressed: () -> Unit) =
  requireActivity().pressBackTwiceToExitApp(delayMillis, viewLifecycleOwner, onFirstBackPressed)

fun Fragment.pressBackToNotExitApp() =
  requireActivity().pressBackToNotExitApp(viewLifecycleOwner)

fun Fragment.doOnBackPressed(onBackPressed: () -> Unit) =
  requireActivity().doOnBackPressed(viewLifecycleOwner, onBackPressed)
