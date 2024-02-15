package di

import data.local.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule() : Module = module {
    single<DatabaseDriverFactory>{ DatabaseDriverFactory(context = get()) }
}