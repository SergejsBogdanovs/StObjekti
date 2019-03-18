package lv.st.sbogdano.stobjekti

import android.app.Application
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import lv.st.sbogdano.stobjekti.internal.injection.*
import org.koin.android.ext.android.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        // Start Koin
        startKoin(this, appComponent)

        Timber.plant(DebugTree())

        Stetho.initializeWithDefaults(this)
    }
}

val appComponent = listOf(
    presentationModule,
    domainModule,
    dataModule
)