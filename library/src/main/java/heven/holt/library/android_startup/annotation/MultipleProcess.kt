package heven.holt.library.android_startup.annotation

@MustBeDocumented
@Retention
@Target(AnnotationTarget.CLASS)
annotation class MultipleProcess(vararg val process: String)
