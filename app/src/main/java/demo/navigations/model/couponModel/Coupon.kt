package demo.navigations.model.couponModel


import com.google.gson.annotations.SerializedName

data class Coupon(
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("cart_value")
    val cartValue: Double,
    @SerializedName("coupon_code")
    val couponCode: String,
    @SerializedName("coupon_desc")
    val couponDesc: String,
    @SerializedName("coupon_id")
    val couponId: Int,
    @SerializedName("discount_applicable_on")
    val discountApplicableOn: String,
    @SerializedName("discount_based_on")
    val discountBasedOn: String,
    @SerializedName("item_category_name")
    val itemCategoryName: String,
    @SerializedName("item_group_name")
    val itemGroupName: String,
    @SerializedName("item_name")
    val itemName: String,
    @SerializedName("item_sub_group_name")
    val itemSubGroupName: String,
    @SerializedName("max_discount_value")
    val maxDiscountValue: Double,
    @SerializedName("max_quantity")
    val maxQuantity: Double,
    @SerializedName("min_quantity")
    val minQuantity: Double,
    @SerializedName("percentage_applicable")
    val percentageApplicable: Double,
    @SerializedName("store_type_name")
    val storeTypeName: String,
    @SerializedName("territory_name")
    val territoryName: String
)