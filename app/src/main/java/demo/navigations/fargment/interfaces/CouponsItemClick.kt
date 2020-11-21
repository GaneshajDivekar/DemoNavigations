package demo.navigations.fargment.interfaces

import demo.navigations.model.CouponDataDB

interface CouponsItemClick {
    fun itemClick(couponDataDB: CouponDataDB,pos:Int)
}