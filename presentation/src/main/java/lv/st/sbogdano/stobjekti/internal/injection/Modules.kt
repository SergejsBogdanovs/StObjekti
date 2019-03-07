package lv.st.sbogdano.stobjekti.internal.injection

import lv.st.sbogdano.data.gateway.GatewayImpl
import lv.st.sbogdano.data.local.database.StObjectsDatabase
import lv.st.sbogdano.data.repository.RecentObjectsRepository
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.RecentObjectsGetAllUseCase
import lv.st.sbogdano.stobjekti.internal.schedulers.AppSchedulers
import lv.st.sbogdano.stobjekti.startup.StartupViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {

    single { StObjectsDatabase.newInstance(androidContext()).recentObjectsDao() }

    single { RecentObjectsRepository(get()) }

    single { GatewayImpl(get()) as Gateway }

    single { AppSchedulers() as Schedulers }

    single { RecentObjectsGetAllUseCase(get(), get()) }
}

val viewModelModule = module {

    viewModel { StartupViewModel(androidContext(), get()) }
}