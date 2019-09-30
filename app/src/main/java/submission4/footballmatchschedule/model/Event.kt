package submission4.footballmatchschedule

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strDate")
    var strDate: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null,

    @SerializedName("intHomeShots")
    var intHomeShots: String? = null,

    @SerializedName("strHomeFormation")
    var strHomeFormation: String? = null,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String? = null,

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String? = null,

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null,

    @SerializedName("intAwayShots")
    var intAwayShots: String? = null,

    @SerializedName("strAwayFormation")
    var strAwayFormation: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String? = null,

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String? = null,

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String? = null
)