package demo.navigations.model.jsonpojo


import com.google.gson.annotations.SerializedName

data class ItemHeader(
    @SerializedName("brand_id")
    val brandId: String?="",
    @SerializedName("hsn_code")
    val hsnCode: Int,
    @SerializedName("hsn_code_id")
    val hsnCodeId: Int,
    @SerializedName("item_category_id")
    val itemCategoryId: String?="",
    @SerializedName("item_description")
    val itemDescription: String,
    @SerializedName("item_group_id")
    val itemGroupId: String?="",
    @SerializedName("item_id")
    val itemId: String?="",
    @SerializedName("item_image_url")
    val itemImageUrl: String,
    @SerializedName("modified_ts")
    val modifiedTs: String,
    @SerializedName("total_price")
    val totalPrice: String?="",
    @SerializedName("uom_code")
    val uomCode: String,
    @SerializedName("uom_id")
    val uomId: String="",
    @SerializedName("item_name")
    val item_name: String="",
    @SerializedName("group_name")
    val group_name: String=""

)