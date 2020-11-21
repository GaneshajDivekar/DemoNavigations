package demo.navigations.model.jsonpojo


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("item_header_list")
    val itemHeaderList: List<ItemHeader>
)