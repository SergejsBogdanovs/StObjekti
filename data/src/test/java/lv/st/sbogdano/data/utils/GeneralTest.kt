package lv.st.sbogdano.data.utils

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class GeneralTest {

    @Test
    fun getFormattedName_as() {
        val asList = listOf("as 1", "as1", "as.1", "a/st. 1", "a/st.1", "a/st1", "AS 1", "A/st.1")

        for (item in asList) {
            assertThat(getFormattedName(item), `is`("A/st.1"))
        }
    }

    @Test
    fun getFormattedName_tp() {
        val asList = listOf("tp 106", "tp-106", "tp_106", "tp106", "TP 106", "Tp 106")

        for (item in asList) {
            assertThat(getFormattedName(item), `is`("TP106"))
        }
    }

    @Test
    fun getFormattedName_sp() {
        val asList = listOf("sp 1", "sp-1", "sp_1", "sp1", "SP1", "Sp 1")

        for (item in asList) {
            assertThat(getFormattedName(item), `is`("SP1"))
        }
    }

    @Test
    fun getFormattedName_fp() {
        val asList = listOf("fp 3", "fp-3", "fp_3", "fp3", "FP3", "Fp 3")

        for (item in asList) {
            assertThat(getFormattedName(item), `is`("FP3"))
        }
    }

    @Test
    fun getFormattedName_t() {
        val asList = listOf("t 50383", "t-50383", "t_50383", "t50383", "T 50383", "T 50383")

        for (item in asList) {
            assertThat(getFormattedName(item), `is`("T50383"))
        }
    }
}