package demo.navigations.repository

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.demoproject.database.MyRoomDataBase
import com.demoproject.network.ApiServices
import demo.navigations.model.CouponDataDB
import demo.navigations.model.HomeScreenModel.HomeScreenPOJO
import demo.navigations.model.ImageSlider.ImageSliderPOJO
import demo.navigations.model.StudentDB
import demo.navigations.model.couponModel.CouponModel
import demo.navigations.model.paginations.Pagigations
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val context: Context,
    private var apiServices: ApiServices
) {
    fun getSTudentData(activity: FragmentActivity): MutableLiveData<List<StudentDB>> {
        var getStudentLiveData = MutableLiveData<List<StudentDB>>()
        var getStudent = MyRoomDataBase.getInstance(activity).getNoteDao().getStudent()
        getStudentLiveData.postValue(getStudent)
        return getStudentLiveData
    }

    fun getHomeSCreenData(userId: String, businessUnitId: String): MutableLiveData<HomeScreenPOJO> {
        var getHomeLiveData=MutableLiveData<HomeScreenPOJO>()
        apiServices.getHomeSCreen(userId,businessUnitId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { loginResponse ->
                    getHomeLiveData.postValue(loginResponse)
                    System.out.println("login"+loginResponse)
                },
                { error ->
                    getHomeLiveData.postValue(null)
                    handledError(error)
                    System.out.println("login"+error.message)

                }

            )

        return getHomeLiveData
    }

    private fun handledError(error: Throwable?) {
        System.out.println("error"+error)
    }

    fun getCouponData(plant_id: String): MutableLiveData<CouponModel> {
        var getCouponModelLiveData= MutableLiveData<CouponModel>()
        apiServices.getCouponData(plant_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { loginResponse ->
                    getCouponModelLiveData.postValue(loginResponse)
                    System.out.println("login"+loginResponse)
                },
                { error ->
                    getCouponModelLiveData.postValue(null)
                    handledError(error)
                    System.out.println("login"+error.message)
                }

            )
        return getCouponModelLiveData
    }

    fun getCouponsData(activity: FragmentActivity): MutableLiveData<List<CouponDataDB>> {
        var getCouponsLiveData = MutableLiveData<List<CouponDataDB>>()
        var getStudent = MyRoomDataBase.getInstance(activity).getNoteDao().getCoupons()
        getCouponsLiveData.postValue(getStudent)
        return getCouponsLiveData
    }

    fun getPagigations(page: Int, perPage: Int): MutableLiveData<Pagigations> {
        var getPagigationsLiveData = MutableLiveData<Pagigations>()
        apiServices.getpagigationData(page,perPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { loginResponse ->
                    getPagigationsLiveData.postValue(loginResponse)
                    System.out.println("login"+loginResponse)
                },
                { error ->
                    getPagigationsLiveData.postValue(null)
                    handledError(error)
                    System.out.println("login"+error.message)
                }

            )
        return getPagigationsLiveData
    }

    fun getImageSliderData(): MutableLiveData<ImageSliderPOJO> {
        var getImageSliderLiveData=MutableLiveData<ImageSliderPOJO>()
        apiServices.getImageSliderData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { loginResponse ->
                    getImageSliderLiveData.postValue(loginResponse)
                    System.out.println("login"+loginResponse)
                },
                { error ->
                    getImageSliderLiveData.postValue(null)
                    handledError(error)
                    System.out.println("login"+error.message)
                }

            )
        return getImageSliderLiveData
    }


}