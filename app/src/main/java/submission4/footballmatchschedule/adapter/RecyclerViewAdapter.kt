package submission4.footballmatchschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lastmatch_list_item.view.*

class RecyclerViewAdapter(private val items: List<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lastmatch_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val textviewDate = view.textviewDate
    val textviewHomeTeam = view.textviewHomeTeam
    val textviewHomeScore = view.textviewHomeScore
    val textviewAwayTeam = view.textviewAwayTeam
    val textviewAwayScore = view.textviewAwayScore

    fun bind(events: Event, listener: (Event) -> Unit) {
        textviewDate.text = dateFormater(events.dateEvent)
        textviewHomeTeam.text = events.strHomeTeam
        textviewHomeScore.text = events.intHomeScore
        textviewAwayTeam.text = events.strAwayTeam
        textviewAwayScore.text = events.intAwayScore

        view.setOnClickListener { listener(events) }
    }
}

