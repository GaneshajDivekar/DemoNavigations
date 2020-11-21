package demo.navigations.model.HomeScreenModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("homescreen_count")
    val homescreenCount: Int,
    val homescreenlist: List<Homescreenlist>
)