package heven.holt.mvvm.im

/**
 * 定义 CMD 用来保存消息协议，将 CMD 与实现方法关联
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class CMD(val id: Int)
