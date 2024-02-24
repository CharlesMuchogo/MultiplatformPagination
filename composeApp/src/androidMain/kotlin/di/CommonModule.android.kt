package di

import data.local.DatabaseDriverFactory
import data.network.ConnectivityObserver
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule() : Module = module {
    single<DatabaseDriverFactory>{ DatabaseDriverFactory(context = get()) }
    single<ConnectivityObserver>{ ConnectivityObserver(context = get()) }
}