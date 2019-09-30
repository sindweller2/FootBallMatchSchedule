package submission4.footballmatchschedule

import com.google.gson.Gson

class DetailPresenter {
    fun getTeam(teamId: String?): TeamResponse {

        EspressoIdlingResource.increment()

        val list = Gson().fromJson(APIRepository().doRequest(APIList.teamDetailsById(teamId)), TeamResponse::class.java)

        if (!EspressoIdlingResource.gettingIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }

        return list
    }

    fun getEvent(eventId: String): EventResponse {

        EspressoIdlingResource.increment()

        val list =
            Gson().fromJson(APIRepository().doRequest(APIList.eventDetailsById(eventId)), EventResponse::class.java)

        if (!EspressoIdlingResource.gettingIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }

        return list
    }
}