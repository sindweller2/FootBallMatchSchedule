package submission4.footballmatchschedule

import java.net.URL

class APIRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}