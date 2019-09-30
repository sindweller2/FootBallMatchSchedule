package submission4.footballmatchschedule

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    private val idTeam = "133604"
    private val idEvent = "441613"
    private lateinit var detailPresenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailPresenter = DetailPresenter()
    }

    @Test
    fun testGetEvent() {
        val events: MutableList<Event> = mutableListOf()
        val eventResponses = EventResponse(events)

        GlobalScope.launch {
            Mockito.`when`(
                Gson().fromJson(
                    APIRepository().doRequest(APIList.eventDetailsById(idEvent)),
                    EventResponse::class.java
                )
            ).thenReturn(eventResponses)
            detailPresenter.getEvent(idEvent)
            Mockito.verify(DetailPresenter().getEvent(idEvent))
        }
    }

    @Test
    fun testGetTeam() {
        val teams: MutableList<Team> = mutableListOf()
        val teamResponses = TeamResponse(teams)

        GlobalScope.launch {
            Mockito.`when`(
                Gson().fromJson(
                    APIRepository().doRequest(APIList.teamDetailsById(idTeam)),
                    TeamResponse::class.java
                )
            ).thenReturn(teamResponses)
            detailPresenter.getTeam(idTeam)
            Mockito.verify(DetailPresenter().getTeam(idTeam))
        }
    }
}