package submission4.footballmatchschedule

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.uiThread

class NextMatchFragment : Fragment() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swiperefreshUI()
        spinnerUI()
    }

    private fun spinnerUI() {
        doAsync {
            val listLeague = NextMatchPresenter().getLeague()

            val listIdLeague = listLeague.leagues
                .filter { it.strSport == "Soccer" }
                .map { it.idLeague }

            val listStrLeague = listLeague.leagues
                .filter { it.strSport == "Soccer" }
                .map { it.strLeague }

            val spinnerArray = arrayOfNulls<String>(listIdLeague.size)
            val spinnerMap = SparseArray<String>()

            for (i in 0 until listIdLeague.size) {
                spinnerMap.put(i, listIdLeague[i].toString())
                spinnerArray[i] = listStrLeague[i]
            }

            uiThread {
                try {
                    if (spinnerLeaguesNextMatch != null) {
                        val arrayAdapter =
                            ArrayAdapter(
                                context!!,
                                android.R.layout.simple_spinner_dropdown_item,
                                listStrLeague
                            ) as SpinnerAdapter
                        spinnerLeaguesNextMatch.adapter = arrayAdapter

                        spinnerLeaguesNextMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                                Toast.makeText(context,spinnerMap.get(spinnerLeaguesNextMatch.selectedItemPosition),Toast.LENGTH_SHORT).show()
                                recyclerviewUI(spinnerMap.get(spinnerLeaguesNextMatch.selectedItemPosition).toString())
                            }

                            override fun onNothingSelected(parent: AdapterView<*>) {
                            }
                        }
                    }
                } catch (e: Exception) {
                    spinnerLeaguesNextMatch.adapter = null
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun recyclerviewUI(idLeague: String) {
        doAsync {

            val listEvent = NextMatchPresenter().getEvent(idLeague)

            uiThread {
                try {
                    recyclerviewNextMatch.layoutManager = LinearLayoutManager(context)

                    val adapter = RecyclerViewAdapter(listEvent.events)
                    {
                        startActivity<DetailActivity>(
                            "idEvent" to it.idEvent
                        )
                    }

                    adapter.notifyDataSetChanged()
                    recyclerviewNextMatch.adapter = adapter
                } catch (e: Exception) {
                    Toast.makeText(context!!, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun swiperefreshUI() {
        mHandler = Handler()

        swipe_refresh_layout.setOnRefreshListener {
            mRunnable = Runnable {

                spinnerUI()
                swipe_refresh_layout.isRefreshing = false
            }

            mHandler.postDelayed(
                mRunnable,
                (1000).toLong()
            )
        }
    }
}