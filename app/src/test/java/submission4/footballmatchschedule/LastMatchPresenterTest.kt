package submission4.footballmatchschedule

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {

    private val idLeague = "6093"
    private lateinit var lastMatchPresenter: LastMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lastMatchPresenter = LastMatchPresenter()
    }

    @Test
    fun testGetLeague() {
        val leagues: MutableList<League> = mutableListOf()
        val leagueResponses = LeagueResponse(leagues)

        GlobalScope.launch {
            Mockito.`when`(
                Gson().fromJson(
                    APIRepository().doRequest(APIList.listAllLeagues()),
                    LeagueResponse::class.java
                )
            ).thenReturn(leagueResponses)
            lastMatchPresenter.getLeague()
            Mockito.verify(LastMatchPresenter().getLeague())
        }
    }

    @Test
    fun testGetEvent() {
        val events: MutableList<Event> = mutableListOf()
        val eventResponses = EventResponse(events)

        GlobalScope.launch {
            Mockito.`when`(
                Gson().fromJson(
                    APIRepository().doRequest(APIList.last15EventsByLeagueId(idLeague)),
                    EventResponse::class.java
                )
            ).thenReturn(eventResponses)
            lastMatchPresenter.getEvent(idLeague)
            Mockito.verify(LastMatchPresenter().getEvent(idLeague))
        }
    }
}
