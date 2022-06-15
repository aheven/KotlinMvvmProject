package heven.holt.mvvm.im

import java.lang.reflect.Method

/**
 * 定义 Commander 保存实例与方法名
 */
class Commander(
    val o: Any, //类名
    val method: Method,//方法名
    val parameterTypes: Array<Class<*>>?// 形参类型
) {
    constructor(o: Any, method: Method) : this(o, method, method.parameterTypes)
}