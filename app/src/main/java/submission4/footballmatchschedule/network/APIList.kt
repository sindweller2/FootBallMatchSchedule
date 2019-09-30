package submission4.footballmatchschedule

//import android.net.Uri

object APIList {
    private fun apiURL(path: String?, queryParameter: String?, valueParameter: String?): String {
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.API_KEY)
//                .appendPath(path)
//                .appendQueryParameter(queryParameter, valueParameter)
//                .build()
//                .toString()

        return BuildConfig.BASE_URL + "api/v1/json/" + BuildConfig.API_KEY + "/" + path + "?" + queryParameter + "=" + valueParameter
    }

    fun listAllLeagues(): String {
        return apiURL("all_leagues.php", "", "")
    }

    fun next15EventsByLeagueId(idLeague: String?): String {
        return apiURL("eventsnextleague.php", "id", idLeague)
    }

    fun last15EventsByLeagueId(idLeague: String?): String {
        return apiURL("eventspastleague.php", "id", idLeague)
    }

    fun eventDetailsById(idEvent: String?): String {
        return apiURL("lookupevent.php", "id", idEvent)
    }

    fun teamDetailsById(idTeam: String?): String {
        return apiURL("lookupteam.php", "id", idTeam)
    }
}