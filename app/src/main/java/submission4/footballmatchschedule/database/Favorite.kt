package submission4.footballmatchschedule

data class Favorite(
//    val ID: Long,
    val idEvent: String,
    val dateEvent: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intHomeScore: String,
    val intAwayScore: String
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        //        const val ID: String = "ID_"
        const val idEvent: String = "idEvent"
        const val dateEvent: String = "dateEvent"
        const val strHomeTeam: String = "strHomeTeam"
        const val strAwayTeam: String = "strAwayTeam"
        const val intHomeScore: String = "intHomeScore"
        const val intAwayScore: String = "intAwayScore"
    }
}