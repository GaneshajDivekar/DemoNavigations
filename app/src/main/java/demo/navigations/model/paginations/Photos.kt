package demo.navigations.model.paginations


import com.google.gson.annotations.SerializedName
import demo.navigations.model.StudentDB

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    var photo: List<Photo> = ArrayList<Photo>(),
    val total: String
)