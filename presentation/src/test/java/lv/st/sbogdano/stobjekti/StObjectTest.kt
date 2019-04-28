// package lv.st.sbogdano.stobjekti
//
// import android.app.Application
// import android.content.Context
// import io.reactivex.Observable
// import lv.st.sbogdano.domain.interactor.GetObjectByNameUseCase
// import lv.st.sbogdano.domain.model.StObject
// import lv.st.sbogdano.stobjekti.search.StObjectsSearchViewModel
// import org.hamcrest.MatcherAssert.assertThat
// import org.hamcrest.core.Is.`is`
// import org.junit.Before
// import org.junit.Test
// import org.junit.runner.RunWith
// import org.koin.standalone.inject
// import org.koin.test.KoinTest
// import org.mockito.Mock
// import org.mockito.Mockito.`when`
// import org.mockito.junit.MockitoJUnitRunner
//
// @Suppress("IllegalIdentifier")
// @RunWith(MockitoJUnitRunner::class)
//
// class StObjectTest : KoinTest {
//
//    @Mock
//    private lateinit var context: Context
//    @Mock
//    private lateinit var application: Application
//    @Mock
//    private lateinit var getObjectByNameUseCase: GetObjectByNameUseCase
//
//    private val stObjectsSearchViewModel: StObjectsSearchViewModel by inject()
//
//    @Before
//    fun setup() {
//        `when`(context.applicationContext).thenReturn(application)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun `Given stObject item, When load stObjects, Should update result`() {
//
//        // Given
//        val items = listOf(
//            StObject(
//                "address",
//                "construction",
//                "2019",
//                "2000",
//                "name",
//                "Riga",
//                "technical object",
//                "type",
//                "x",
//                "y"
//            )
//        )
//
//        `when`(getObjectByNameUseCase.execute()).thenReturn(Observable.just(items))
//
//        // When
//        stObjectsSearchViewModel.searchStObject("as105")
//
//        // Should
//        assertThat(stObjectsSearchViewModel.result, `is`(items))
//    }
// }
