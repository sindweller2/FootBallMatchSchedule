package submission4.footballmatchschedule

import com.google.gson.Gson

class NextMatchPresenter {
    fun getLeague(): LeagueResponse {

        EspressoIdlingResource.increment()

        val list = Gson().fromJson(APIRepository().doRequest(APIList.listAllLeagues()), LeagueResponse::class.java)

        if (!EspressoIdlingResource.gettingIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }

        return list
    }

    fun getEvent(idLeague: String): EventResponse {

        EspressoIdlingResource.increment()

        val list = Gson().fromJson(
            APIRepository().doRequest(APIList.next15EventsByLeagueId(idLeague)),
            EventResponse::class.java
        )

        if (!EspressoIdlingResource.gettingIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }

        return list
    }
}