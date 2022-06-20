package heven.holt.library.android_startup.utils

import android.util.Log
import heven.holt.library.android_startup.model.LoggerLevel
import java.util.*

internal object StartupLogUtils {
    private const val TAG = "StartupTrack"
    var level: LoggerLevel = LoggerLevel.NONE

    fun e(block: () -> String) {
        if (level >= LoggerLevel.ERROR) print(Log.ERROR, block())
    }

    fun d(block: () -> String) {
        if (level >= LoggerLevel.DEBUG) print(Log.DEBUG, block())
    }

    /**
     * 打印日志到控制台（解决Android控制台丢失长日志记录）
     */
    private fun print(priority: Int, content: String) {
        if (content.length < 1000) {
            Log.println(priority, TAG, content)
            return
        }

        // The maximum number of bytes to print at one time.
        val maxByteNum = 4000

        var bytes = content.toByteArray()

        // Print directly out of range.
        if (maxByteNum >= bytes.size) {
            Log.println(priority, TAG, content)
            return
        }

        // Section print counting.
        var count = 1

        // In the range of the array, the loop is segmented.
        while (maxByteNum < bytes.size) {
            // Capture a string by byte length.
            val subStr = cutStr(bytes, maxByteNum)

            val desc = String.format("Block printing(%s):%s", count++, subStr)
            Log.println(priority, TAG, desc)

            // Intercepts an array of bytes not yet printed.
            bytes = bytes.copyOfRange(subStr!!.toByteArray().size, bytes.size)

            /*if (count == 10) {
                break;
            }*/
        }

        Log.println(priority, TAG, String.format("Block printing(%s):%s", count, String(bytes)))
    }

    /**
     * 按字节长度将ByteArray转换成String
     */
    private fun cutStr(bytes: ByteArray?, subLength: Int): String? {
        if (bytes == null || subLength < 1) {
            return null
        }

        if (subLength >= bytes.size) {
            return String(bytes)
        }

        // Copy out a fixed - length byte array and convert it to a string.
        val subStr = String(Arrays.copyOf(bytes, subLength))

        // To avoid the end character being split, subtract 1 here to keep the string intact.
        return subStr.substring(0, subStr.length - 1)
    }
}