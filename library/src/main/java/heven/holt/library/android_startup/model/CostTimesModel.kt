package heven.holt.library.android_startup.model

data class CostTimesModel(
    val name: String,
    val callOnMainThread: Boolean,
    val waitOnMainThread: Boolean,
    val startTime: Long,
    var endTime: Long = 0L
)