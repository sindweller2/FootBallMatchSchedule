package submission4.footballmatchschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import submission4.footballmatchschedule.R.drawable.ic_add_to_favorites
import submission4.footballmatchschedule.R.drawable.ic_added_to_favorites
import submission4.footballmatchschedule.R.id.add_to_favorite
import submission4.footballmatchschedule.R.menu.detail_menu

class DetailActivity : AppCompatActivity() {

    private var idEvent: String = ""
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        swiperefreshUI()
        cardviewUI()
        favoriteState()

    }

    private fun teamBadge(imageview_badge: ImageView, idTeam: String?) {

        doAsync {
            val listTeam = DetailPresenter().getTeam(idTeam)

            val listBadge = listTeam.teams
                .map { it.strTeamBadge }

            uiThread {
                try {

                    Glide.with(it.applicationContext).load(listBadge.first()).into(imageview_badge)
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun eventDetail(idEvent: String) {
        doAsync {
            val listEvent = DetailPresenter().getEvent(idEvent)

            uiThread {

                try {
                    val imageviewHomeTeam = findViewById<ImageView>(R.id.imageviewHomeTeam)
                    teamBadge(imageviewHomeTeam, listEvent.events.map { it.idHomeTeam }.first())

                    val imageviewAwayTeam = findViewById<ImageView>(R.id.imageviewAwayTeam)
                    teamBadge(imageviewAwayTeam, listEvent.events.map { it.idAwayTeam }.first())

                    val textviewDate = findViewById<TextView>(R.id.textviewDate)
                    textviewDate.text = dateFormater(listEvent.events.map { it.dateEvent }.first())

                    val textviewHomeTeam = findViewById<TextView>(R.id.textviewHomeTeam)
                    textviewHomeTeam.text = listEvent.events.map { it.strHomeTeam }.first()

                    val textviewAwayTeam = findViewById<TextView>(R.id.textviewAwayTeam)
                    textviewAwayTeam.text = listEvent.events.map { it.strAwayTeam }.first()

                    val textviewHomeScore = findViewById<TextView>(R.id.textviewHomeScore)
                    textviewHomeScore.text = listEvent.events.map { it.intHomeScore }.first()

                    val textviewAwayScore = findViewById<TextView>(R.id.textviewAwayScore)
                    textviewAwayScore.text = listEvent.events.map { it.intAwayScore }.first()

                    val textviewHomeFormation = findViewById<TextView>(R.id.textviewHomeFormation)
                    textviewHomeFormation.text = listEvent.events.map { it.strHomeFormation }.first()

                    val textviewAwayFormation = findViewById<TextView>(R.id.textviewAwayFormation)
                    textviewAwayFormation.text = listEvent.events.map { it.strAwayFormation }.first()

                    val textviewHomeGoalDetails = findViewById<TextView>(R.id.textviewHomeGoalDetails)
                    textviewHomeGoalDetails.text = listEvent.events.map { it.strHomeGoalDetails }.first()

                    val textviewAwayGoalDetails = findViewById<TextView>(R.id.textviewAwayGoalDetails)
                    textviewAwayGoalDetails.text = listEvent.events.map { it.strAwayGoalDetails }.first()

                    val textviewHomeShot = findViewById<TextView>(R.id.textviewHomeShot)
                    textviewHomeShot.text = listEvent.events.map { it.intHomeShots }.first()

                    val textviewAwayShot = findViewById<TextView>(R.id.textviewAwayShot)
                    textviewAwayShot.text = listEvent.events.map { it.intAwayShots }.first()

                    val textviewHomeGoalKeeper = findViewById<TextView>(R.id.textviewHomeGoalKeeper)
                    textviewHomeGoalKeeper.text = listEvent.events.map { it.strHomeLineupGoalkeeper }.first()

                    val textviewAwayGoalKeeper = findViewById<TextView>(R.id.textviewAwayGoalKeeper)
                    textviewAwayGoalKeeper.text = listEvent.events.map { it.strAwayLineupGoalkeeper }.first()

                    val textviewHomeLineupDefense = findViewById<TextView>(R.id.textviewHomeLineupDefense)
                    textviewHomeLineupDefense.text = listEvent.events.map { it.strHomeLineupDefense }.first()

                    val textviewAwayLineupDefense = findViewById<TextView>(R.id.textviewAwayLineupDefense)
                    textviewAwayLineupDefense.text = listEvent.events.map { it.strAwayLineupDefense }.first()

                    val textviewHomeLineupMidfield = findViewById<TextView>(R.id.textviewHomeLineupMidfield)
                    textviewHomeLineupMidfield.text = listEvent.events.map { it.strHomeLineupMidfield }.first()

                    val textviewAwayLineupMidfield = findViewById<TextView>(R.id.textviewAwayLineupMidfield)
                    textviewAwayLineupMidfield.text = listEvent.events.map { it.strAwayLineupMidfield }.first()

                    val textviewHomeLineupForward = findViewById<TextView>(R.id.textviewHomeLineupForward)
                    textviewHomeLineupForward.text = listEvent.events.map { it.strHomeLineupForward }.first()

                    val textviewAwayLineupForward = findViewById<TextView>(R.id.textviewAwayLineupForward)
                    textviewAwayLineupForward.text = listEvent.events.map { it.strAwayLineupForward }.first()

                    val textviewHomeLineupSubstitutes = findViewById<TextView>(R.id.textviewHomeLineupSubstitutes)
                    textviewHomeLineupSubstitutes.text = listEvent.events.map { it.strHomeLineupSubstitutes }.first()

                    val textviewAwayLineupSubstitutes = findViewById<TextView>(R.id.textviewAwayLineupSubstitutes)
                    textviewAwayLineupSubstitutes.text = listEvent.events.map { it.strAwayLineupSubstitutes }.first()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun cardviewUI() {

        idEvent = intent.getStringExtra("idEvent")

        eventDetail(idEvent)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun swiperefreshUI() {
        mHandler = Handler()

        swipe_refresh_layout.setOnRefreshListener {
            mRunnable = Runnable {

                cardviewUI()

                favoriteState()

                swipe_refresh_layout.isRefreshing = false
            }

            mHandler.postDelayed(
                mRunnable,
                (1000).toLong()
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(idEvent = {id})",
                    "id" to idEvent
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.idEvent to idEvent,
                    Favorite.dateEvent to textviewDate.text,
                    Favorite.strHomeTeam to textviewHomeTeam.text,
                    Favorite.strAwayTeam to textviewAwayTeam.text,
                    Favorite.intHomeScore to textviewHomeScore.text,
                    Favorite.intAwayScore to textviewAwayScore.text
                )
            }
            Toast.makeText(applicationContext, "Added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(idEvent = {id})",
                    "id" to idEvent
                )
            }
            Toast.makeText(applicationContext, "Removed from favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}