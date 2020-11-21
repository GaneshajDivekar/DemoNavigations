package demo.navigations.model.HomeScreenModel


import com.google.gson.annotations.SerializedName

data class Homescreenlist(
    @SerializedName("app_menu_code")
    val appMenuCode: String,
    @SerializedName("app_menu_desc")
    val appMenuDesc: String,
    @SerializedName("app_menu_id")
    val appMenuId: Int,
    @SerializedName("app_version")
    val appVersion: String,
    @SerializedName("has_more_opt")
    val hasMoreOpt: Int,
    @SerializedName("has_submenu")
    val hasSubmenu: Boolean,
    @SerializedName("menu_order")
    val menuOrder: Int,
    @SerializedName("parent_app_menu_id")
    val parentAppMenuId: Int,
    @SerializedName("pending_item_count")
    val pendingItemCount: Int,
    @SerializedName("visit_type_icon1_path")
    val visitTypeIcon1Path: String,
    @SerializedName("visit_type_icon2_path")
    val visitTypeIcon2Path: String,
    @SerializedName("visit_type_icon3_path")
    val visitTypeIcon3Path: String,
    @SerializedName("visit_type_icon4_path")
    val visitTypeIcon4Path: String,
    @SerializedName("visit_type_icon5_path")
    val visitTypeIcon5Path: String,

    var checkIs:Boolean

)