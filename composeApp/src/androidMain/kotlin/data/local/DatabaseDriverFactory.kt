package data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.charlesmuchogo.multiplatformpaging.AppDatabase

actual class DatabaseDriverFactory (
    private val context: Context
){
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            AppDatabase.Schema,
            context,
            "appDatabase.db"
        )
    }
}