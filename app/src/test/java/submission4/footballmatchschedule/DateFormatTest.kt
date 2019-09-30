package submission4.footballmatchschedule

import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatTest {
    @Test
    fun testDateFormater() {
        val dateString = "2018-12-31"
        val result = dateFormater(dateString)
        assertEquals("Mon, 31 Dec 2018", result)
    }
}