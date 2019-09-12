package lv.st.sbogdano.domain.interactor

import io.reactivex.Observable
import lv.st.sbogdano.domain.Schedulers
import lv.st.sbogdano.domain.gateway.Gateway
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.domain.utils.TestSchedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class GetObjectByNameTest {

    @Mock
    private lateinit var gateway: Gateway

    private lateinit var schedulers: Schedulers
    private lateinit var getObjectByNameUseCase: GetObjectByNameUseCase

    private val name = "As105"

    @Before
    fun setup() {
        schedulers = TestSchedulers()
        getObjectByNameUseCase = GetObjectByNameUseCase(schedulers, gateway)
    }

    @Test
    @Throws(Exception::class)
    fun `Given stObjects item data, When get stObjects, Should fetch data from gateway`() {

        // Given
        val items = listOf(
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
                "y",
                "zone_of_responsibility: String"
            )
        )

        Mockito.`when`(gateway.getStObjects(name)).thenReturn(Observable.just(items))

        // When
        val testObserver = getObjectByNameUseCase.execute(name).test()

        // Should
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(items)

        Mockito.verify(gateway).getStObjects(name)
    }

}