package submission4.footballmatchschedule

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity

class FavoriteFragment : Fragment() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swiperefreshUI()
        recyclerviewUI()
    }

    override fun onResume() {
        super.onResume()
        recyclerviewUI()
    }

    private fun recyclerviewUI() {
        recyclerviewFavorite.layoutManager = LinearLayoutManager(context)

        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())


            val adapter = FavoriteAdapter(favorite)
            {
                //                        Toast.makeText(context, it.idEvent, Toast.LENGTH_SHORT).show()
                startActivity<DetailActivity>(
                    "idEvent" to it.idEvent
                )
            }

            adapter.notifyDataSetChanged()
            recyclerviewFavorite.adapter = adapter
        }
    }

    private fun swiperefreshUI() {
        mHandler = Handler()

        swipe_refresh_layout.setOnRefreshListener {
            mRunnable = Runnable {

                recyclerviewUI()
                swipe_refresh_layout.isRefreshing = false
            }

            mHandler.postDelayed(
                mRunnable,
                (1000).toLong()
            )
        }
    }
}
