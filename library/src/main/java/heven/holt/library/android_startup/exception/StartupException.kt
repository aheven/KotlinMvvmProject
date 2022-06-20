package heven.holt.library.android_startup.exception

internal class StartupException : RuntimeException {
    constructor(message: String?) : super(message)

    constructor(t: Throwable?) : super(t)
}