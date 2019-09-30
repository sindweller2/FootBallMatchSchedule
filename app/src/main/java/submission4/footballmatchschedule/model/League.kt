package submission4.footballmatchschedule

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null
)