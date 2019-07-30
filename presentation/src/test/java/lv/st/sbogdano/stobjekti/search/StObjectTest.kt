package lv.st.sbogdano.stobjekti.search

import android.app.Application
import android.content.Context
import io.reactivex.Observable
import lv.st.sbogdano.domain.interactor.GetObjectByNameUseCase
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeoutException

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class StObjectTest : KoinTest {

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var application: Application
    @Mock
    private lateinit var getObjectByNameUseCase: GetObjectByNameUseCase

    private lateinit var stObjectsSearchViewModel: StObjectsSearchViewModel

    private val stObjectName = "As105"

    @Before
    fun setup() {
        `when`(context.applicationContext).thenReturn(application)
        stObjectsSearchViewModel = StObjectsSearchViewModel(context, getObjectByNameUseCase)
    }

    /**
     * Checking if {@link StObjectsViewModel} class {@link searchStObject()} method onNext() is
     * working. i.e. update result.
     */
    @Test
    @Throws(Exception::class)
    fun `Given StObjects, When load stObjects, Should update result`() {

        // Given
        val stObjects = listOf(
                StObject(
                        "address",
                        "construction",
                        "year_of_manufacture",
                        "in_service_from",
                        "name",
                        "city_region",
                        "technical_object",
                        "type",
                        "x",
                        "y"
                )
        )

        `when`(getObjectByNameUseCase.execute(stObjectName)).thenReturn(Observable.just(stObjects))

        // When
        stObjectsSearchViewModel.searchStObject(stObjectName)

        // Should
        assertThat(stObjectsSearchViewModel.result.first(), `is`(stObjects.first()))
    }

    @Test
    @Throws(Exception::class)
    fun `Given empty StObjects list, When load empty stObjects, Should update empty`() {

        // Given
        val stObjects = listOf<StObject>()

        `when`(getObjectByNameUseCase.execute(stObjectName)).thenReturn(Observable.just(stObjects))

        // When
        stObjectsSearchViewModel.searchStObject(stObjectName)

        // Should
        assertThat(stObjectsSearchViewModel.empty.get(), `is`(true))
    }

    @Test
    @Throws(Exception::class)
    fun `Given IllegalArgumentException, When load stObjects with IllegalArgumentException, Should update message`() {

        // Given
        val exceptionMessage = context.getString(R.string.stobjects_search_viewmodel_object_not_found_msg)
        val exception = IllegalArgumentException()
        `when`(getObjectByNameUseCase.execute(stObjectName)).thenReturn(Observable.error(exception))

        // When
        stObjectsSearchViewModel.searchStObject(stObjectName)

        // Should
        assertThat(stObjectsSearchViewModel.message.get(), `is`(exceptionMessage))
    }

    @Test
    @Throws(Exception::class)
    fun `Given TimeoutException, When load stObjects with TimeoutException, Should update message`() {

        // Given
        val exceptionMessage = context.getString(R.string.stobjects_search_viewmodel_no_internet_connection_msg)
        val exception = TimeoutException()
        `when`(getObjectByNameUseCase.execute(stObjectName)).thenReturn(Observable.error(exception))

        // When
        stObjectsSearchViewModel.searchStObject(stObjectName)

        // Should
        assertThat(stObjectsSearchViewModel.message.get(), `is`(exceptionMessage))
    }

    @Test
    @Throws(Exception::class)
    fun `Given error emission, When load stObjects with error, Should update error`() {

        // Given
        val error = RuntimeException("Error emission")
        `when`(getObjectByNameUseCase.execute(stObjectName)).thenReturn(Observable.error(error))

        // When
        stObjectsSearchViewModel.searchStObject(stObjectName)

        // Should
        assertThat(stObjectsSearchViewModel.error.get(), `is`(error.message))
    }

}