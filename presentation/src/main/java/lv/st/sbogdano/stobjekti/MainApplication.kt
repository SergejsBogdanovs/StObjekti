package lv.st.sbogdano.stobjekti

import android.app.Application
import lv.st.sbogdano.stobjekti.internal.injection.applicationModule
import lv.st.sbogdano.stobjekti.internal.injection.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin(this, appComponent)
    }
}

val appComponent = listOf(
    applicationModule,
    viewModelModule
)