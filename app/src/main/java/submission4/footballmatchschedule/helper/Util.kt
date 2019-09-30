package submission4.footballmatchschedule

import java.text.SimpleDateFormat
import java.util.*

fun dateFormater(DateString: String?): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")

    val oldDate: Date = inputFormat.parse(DateString)

    val outputFormat = SimpleDateFormat("EEE, dd MMM yyyy")

    return outputFormat.format(oldDate)
}