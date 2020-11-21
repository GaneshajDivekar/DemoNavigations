package demo.navigations.model.ImageSlider


import com.google.gson.annotations.SerializedName

data class ImageSliderPOJOItem(
    val genre: List<String>,
    val image: String,
    val rating: Double,
    val releaseYear: Int,
    var title: String
)