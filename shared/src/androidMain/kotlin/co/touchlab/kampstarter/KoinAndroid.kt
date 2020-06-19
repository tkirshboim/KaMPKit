package co.touchlab.kampstarter

import android.content.Context
import android.content.SharedPreferences
import co.touchlab.kampstarter.db.KampstarterDb
import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            KampstarterDb.Schema,
            get(),
            "KampStarterDb"
        )
    }

    single<SharedPreferences> {
        get<Context>().getSharedPreferences("KAMPSTARTER_SETTINGS", Context.MODE_PRIVATE)
    }
    single<Settings> {
        val preferences: SharedPreferences = get()
        AndroidSettings(preferences)
    }

    val baseKermit = Kermit(LogcatLogger()).withTag("KampKit")
    factory { (tag: String?) -> if (tag != null) baseKermit.withTag(tag) else baseKermit }
}
