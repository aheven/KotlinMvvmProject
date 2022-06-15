package heven.holt.mvvm.im

import android.util.Log
import android.util.SparseArray

object MessageHandlerDispatcher {
    private val TAG: String = MessageHandlerDispatcher::class.java.simpleName
    private val commanders = SparseArray<Commander>()

    fun register(o: Any) {
        val methods = o::class.java.declaredMethods
        for (method in methods) {
            val cmd = method.getAnnotation(CMD::class.java)
            if (cmd != null && commanders.get(cmd.id) == null) {
                commanders.put(cmd.id, Commander(o, method))
            }
        }
    }

    fun invoke(cmd: Int, customTypeMessage: CustomTypeMessage) {
        val commander = commanders[cmd]
        if (commander == null) {
            Log.e(TAG, "invoke: 没有协议号 CustomTypeMessage ($cmd)")
            return
        }

        var params: Array<Any>? = null
        if (commander.parameterTypes != null && commander.parameterTypes.size == 1) {
            params = Array(1) { 0 }
            Log.i(
                TAG,
                "invoke: MessageHandlerDispatcher() 接收 ... invoke() --> cmd = $cmd , result = $customTypeMessage"
            )

            if (commander.parameterTypes.first() == CustomTypeMessage::class.java) {
                params[0] = customTypeMessage
            } else {
                Log.e(TAG, "invoke: MessageHandlerDispatcher() 接收 ... invoke() --> 不存在的类型")
            }
        }
        if (params != null)
            commander.method.invoke(commander.o, *params)
        else
            commander.method.invoke(commander.o)
    }
}