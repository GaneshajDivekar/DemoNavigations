package demo.navigations.model.couponModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("coupon_list")
    val couponList: List<Coupon>
)