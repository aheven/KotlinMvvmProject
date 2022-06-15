package heven.holt.mvvm.im

import android.util.Log

object RoomMessageHandler {
    fun register() {
        MessageHandlerDispatcher.register(this)
    }

    @CMD(id = 1000)
    fun test(customTypeMessage: CustomTypeMessage) {
        Log.e("TAG", "test: 1000")
    }

    @CMD(id = 1001)
    fun test1() {
        Log.e("TAG", "test: 1001")
    }

    @CMD(id = 1002)
    fun test2(customTypeMessage: CustomTypeMessage) {
        Log.e("TAG", "test: 1002")
    }
}