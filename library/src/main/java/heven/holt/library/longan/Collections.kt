
@file:Suppress("unused")

package heven.holt.library.longan

inline fun <T> List<T>.percentage(predicate: (T) -> Boolean) =
  filter(predicate).size.toFloat() / size
