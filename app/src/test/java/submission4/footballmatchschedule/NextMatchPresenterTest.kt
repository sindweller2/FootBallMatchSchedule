package submission4.footballmatchschedule

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    private val idLeague = "1402"
    private lateinit var nextMatchPresenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        nextMatchPresenter = NextMatchPresenter()
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
            nextMatchPresenter.getLeague()
            Mockito.verify(NextMatchPresenter().getLeague())
        }
    }

    @Test
    fun testGetEvent() {
        val events: MutableList<Event> = mutableListOf()
        val eventResponses = EventResponse(events)

        GlobalScope.launch {
            Mockito.`when`(
                Gson().fromJson(
                    APIRepository().doRequest(APIList.next15EventsByLeagueId(idLeague)),
                    EventResponse::class.java
                )
            ).thenReturn(eventResponses)
            nextMatchPresenter.getEvent(idLeague)
            Mockito.verify(NextMatchPresenter().getEvent(idLeague))
        }
    }
}