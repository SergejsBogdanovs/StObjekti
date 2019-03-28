package lv.st.sbogdano.stobjekti.internal.injection

import com.google.firebase.database.FirebaseDatabase
import lv.st.sbogdano.data.gateway.GatewayImpl
import lv.st.sbogdano.data.local.database.StObjectsDatabase
import lv.st.sbogdano.data.repository.StObjectsRepository
import lv.st.sbogdano.data.repository.mapper.StObjectsListMapper
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.interactor.GetObjectByNameUseCase
import lv.st.sbogdano.stobjekti.internal.schedulers.AppSchedulers
import lv.st.sbogdano.stobjekti.navigation.Navigator
import lv.st.sbogdano.stobjekti.search.StObjectsSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val dataModule = module {

    single { FirebaseDatabase.getInstance().reference }

    single { StObjectsDatabase.newInstance(androidContext()).recentObjectsDao() }

    single { StObjectsRepository(get(), get()) }
}

val domainModule = module {

    single<Gateway> { GatewayImpl(get()) }

    single<Schedulers> { AppSchedulers() }

    single { GetObjectByNameUseCase(get(), get()) }
}

val presentationModule = module {

    viewModel { StObjectsSearchViewModel(androidContext(), get()) }

    single { Navigator() }
}