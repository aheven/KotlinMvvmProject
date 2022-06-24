@file:Suppress("unused")

package heven.holt.library.longan

inline fun <T> Array<T>.percentage(predicate: (T) -> Boolean) =
  filter(predicate).size.toFloat() / size
