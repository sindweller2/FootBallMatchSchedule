package submission4.footballmatchschedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.favorite_list_item.view.*

class FavoriteAdapter(private val items: List<Favorite>, private val listener: (Favorite) -> Unit) :
    RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.favorite_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }
}

class FavoriteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val textviewDate = view.textviewDate
    val textviewHomeTeam = view.textviewHomeTeam
    val textviewAwayTeam = view.textviewAwayTeam
    val textviewHomeScore = view.textviewHomeScore
    val textviewAwayScore = view.textviewAwayScore

    fun bind(favorites: Favorite, listener: (Favorite) -> Unit) {
        textviewDate.text = favorites.dateEvent
        textviewHomeTeam.text = favorites.strHomeTeam
        textviewAwayTeam.text = favorites.strAwayTeam
        textviewHomeScore.text = favorites.intHomeScore
        textviewAwayScore.text = favorites.intAwayScore

        view.setOnClickListener { listener(favorites) }
    }
}