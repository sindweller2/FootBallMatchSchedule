package submission4.footballmatchschedule

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null
)