@file:Suppress("unused")

package heven.holt.library.longan

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.net.Uri
import android.os.Process
import android.provider.Settings
import androidx.core.content.getSystemService
import androidx.core.content.pm.PackageInfoCompat

lateinit var application: Application
  internal set

inline val packageName: String get() = application.packageName

inline val packageInfo: PackageInfo
  get() = application.packageManager.getPackageInfo(packageName, 0)

inline val appName: String
  get() = application.applicationInfo.loadLabel(application.packageManager).toString()

inline val appIcon: Drawable get() = packageInfo.applicationInfo.loadIcon(application.packageManager)

inline val appVersionName: String get() = packageInfo.versionName

inline val appVersionCode: Long get() = PackageInfoCompat.getLongVersionCode(packageInfo)

inline val isAppDebug: Boolean get() = application.isAppDebug

inline val Application.isAppDebug: Boolean
  get() = packageManager.getApplicationInfo(packageName, 0).flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

inline val isAppDarkMode: Boolean
  get() = (application.resources.configuration.uiMode and UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES

inline val isLocationEnabled: Boolean
  get() = application.getSystemService<LocationManager>()?.isProviderEnabled(GPS_PROVIDER) == true

fun launchAppSettings(): Boolean =
  Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    .apply { data = Uri.fromParts("package", packageName, null) }
    .startForActivity()

fun relaunchApp(killProcess: Boolean = true) =
  application.packageManager.getLaunchIntentForPackage(packageName)?.let {
    it.addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(it)
    if (killProcess) Process.killProcess(Process.myPid())
  }