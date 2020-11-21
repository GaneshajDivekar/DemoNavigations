package demo.navigations.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coupon_data")
class CouponDataDB {

    @PrimaryKey(autoGenerate = true)
    var coupon_unique_Id: Int = 0

    @ColumnInfo(name = "coupon_id")
    var coupon_id: Int = 0
    @ColumnInfo(name = "coupon_code")
    var coupon_code: String? = null
    @ColumnInfo(name = "coupon_desc")
    var coupon_desc: String? = null
    @ColumnInfo(name = "min_quantity")
    var min_quantity: Double = 0.0
    @ColumnInfo(name = "max_quantity")
    var max_quantity: Double = 0.0
    @ColumnInfo(name = "max_discount_value")
    var max_discount_value: Double = 0.0
    @ColumnInfo(name = "item_category_name")
    var item_category_name: String? = null
    @ColumnInfo(name = "item_name")
    var item_name: String? = null
    @ColumnInfo(name = "item_sub_group_name")
    var item_sub_group_name: String? = null
    @ColumnInfo(name = "item_group_name")
    var item_group_name: String? = null
    @ColumnInfo(name = "brand_name")
    var brand_name: String? = null
    @ColumnInfo(name = "store_type_name")
    var store_type_name: String? = null
    @ColumnInfo(name = "territory_name")
    var territory_name: String? = null
    @ColumnInfo(name = "discount_applicable_on")
    var discount_applicable_on: String? = null
    @ColumnInfo(name = "discount_based_on")
    var discount_based_on: String? = null
   @ColumnInfo(name = "percentage_applicable")
    var percentage_applicable: Double = 0.0
    @ColumnInfo(name = "cart_value")
    var cart_value: Double = 0.0
}