package com.demoproject.network

import com.demoproject.utils.AppConstant
import demo.navigations.model.HomeScreenModel.HomeScreenPOJO
import demo.navigations.model.ImageSlider.ImageSliderPOJO
import demo.navigations.model.couponModel.CouponModel
import demo.navigations.model.movies.TopRatedMovies
import demo.navigations.model.paginations.Pagigations
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {

    @POST("GetHomeScreen")
    fun getHomeSCreen(
        @Query("user_id")userId:String,
        @Query("business_unit_id")businessUnitId:String
    ): Observable<HomeScreenPOJO>

    @POST("CouponData")
    fun getCouponData(
        @Query("plant_id")plantID:String
    ): Observable<CouponModel>


    @POST(AppConstant.verticalItemsApi)
    fun getpagigationData(
        @Query("page")page: Int,
        @Query("per_page")perPage: Int): Observable<Pagigations>

    @GET("quotes.json")
    fun getImageSliderData(): Observable<ImageSliderPOJO>



}