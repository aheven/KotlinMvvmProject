package heven.holt.library.android_startup.extensions

import heven.holt.library.android_startup.Startup

private const val DEFAULT_KEY = "com.rousetime.android_startup.defaultKey"

internal fun Class<out Startup<*>>.getUniqueKey(): String {
    return "$DEFAULT_KEY:$name"
}

internal fun String.getUniqueKey(): String = "$DEFAULT_KEY:$this"