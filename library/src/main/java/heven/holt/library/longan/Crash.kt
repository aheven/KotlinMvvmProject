
@file:Suppress("unused")

package heven.holt.library.longan

import android.os.Looper
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun saveCrashLogLocally(dirPath: String = cacheDirPath) =
  handleUncaughtException { thread, e ->
    val dateTime = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault()).format(Date())
    val file = File(dirPath, "crash_$dateTime.txt")
    file.print {
      println("Time:          $dateTime")
      println("App version:   $appVersionName ($appVersionCode)")
      println("OS version:    Android $sdkVersionName ($sdkVersionCode)")
      println("Manufacturer:  $deviceManufacturer")
      println("Model:         $deviceModel")
      println("Thread:        ${thread.name}")
      println()
      e.printStackTrace(this)
    }
  }

inline fun handleUncaughtException(crossinline block: (Thread, Throwable) -> Unit) {
  val defaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
  Thread.setDefaultUncaughtExceptionHandler { t, e ->
    block(t, e)
    defaultCrashHandler?.uncaughtException(t, e)
  }
}

inline fun handleLooperException(crossinline block: (Throwable) -> Unit){
  mainThreadHandler.post {
    while (true){
      try {
        Looper.loop()
      } catch (e: Throwable) {
        block(e)
      }
    }
  }
}
