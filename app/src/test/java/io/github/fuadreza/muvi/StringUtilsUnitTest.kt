package io.github.fuadreza.muvi

import io.github.fuadreza.core_android.utils.toLocalDateFormat
import junit.framework.Assert.assertEquals
import org.junit.Test

class StringUtilsUnitTest {

    @Test
    fun toLocalDateFormatTest(){
        val date = "2017-02-13T22:23:01.268Z"
        val expected = "2017-02-14"
        assertEquals(expected, date.toLocalDateFormat())
    }
}