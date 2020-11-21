package com.demoproject.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import demo.navigations.model.CouponDataDB
import demo.navigations.model.StudentDB

@Dao
interface WeatherDao {

    @Insert
    fun insertStudentDB(studentDB: StudentDB)

    @Query("SELECT * From student_data")
    fun getStudent(): List<StudentDB>

    @Query("SELECT * From coupon_data")
    fun getCoupons(): List<CouponDataDB>


    @Query("Update student_data Set counter =:count Where studentId =:studentIDS")
    fun updateCounter(count: Int, studentIDS: Int)

    @Insert
    fun insertCoupon(couponDataDB: CouponDataDB)

    @Query("Delete From  coupon_data")
    fun deleteCoupons()



}