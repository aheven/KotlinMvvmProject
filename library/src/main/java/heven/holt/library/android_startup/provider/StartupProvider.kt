package heven.holt.library.android_startup.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import heven.holt.library.android_startup.StartupInitializer
import heven.holt.library.android_startup.StartupManager
import heven.holt.library.android_startup.exception.StartupException

class StartupProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        context.takeIf { context -> context != null }?.let {
            val store = StartupInitializer.instance.discoverAndInitialize(it, this::class.java.name)
            StartupManager.Builder()
                .setConfig(store.config?.getConfig())
                .addAllStartup(store.result)
                .build(it)
                .start()
                .await()
        } ?: throw StartupException("Context cannot be null.")

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        throw IllegalStateException("Not allowed.")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw IllegalStateException("Not allowed.")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw IllegalStateException("Not allowed.")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        throw IllegalStateException("Not allowed.")
    }
}