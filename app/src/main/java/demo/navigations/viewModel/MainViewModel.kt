package demo.navigations.viewModel

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.demoproject.application.WeatherApplication
import com.demoproject.di.ApiComponent
import com.demoproject.network.ApiServices
import com.demoproject.ui.base.BaseViewModel
import demo.navigations.model.CouponDataDB
import demo.navigations.model.HomeScreenModel.HomeScreenPOJO
import demo.navigations.model.ImageSlider.ImageSliderPOJO
import demo.navigations.model.StudentDB
import demo.navigations.model.couponModel.CouponModel
import demo.navigations.model.paginations.Pagigations
import demo.navigations.repository.MainRepository
import retrofit2.Retrofit
import javax.inject.Inject

class MainViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var retrofit: Retrofit
    lateinit var apiServices: ApiServices
    lateinit var mainRepository: MainRepository
    init {
        val apiComponent: ApiComponent = getApplication<WeatherApplication>().getNetComponent()
        apiComponent.inject(this)
        apiServices = retrofit.create(ApiServices::class.java)
        mainRepository = MainRepository(application, apiServices)
    }

    fun getStudentData(activity: FragmentActivity): MutableLiveData<List<StudentDB>> {
        return mainRepository.getSTudentData(activity)
    }

    fun getHomeData(): MutableLiveData<HomeScreenPOJO> {
        return mainRepository.getHomeSCreenData("1320","2")
    }

    fun getCouponData():MutableLiveData<CouponModel>{
        return mainRepository.getCouponData("1")
    }

    fun getCouponsData(activity: FragmentActivity): MutableLiveData<List<CouponDataDB>> {
        return mainRepository.getCouponsData(activity)
    }

    fun getPagigations(page: Int, perPage: Int): MutableLiveData<Pagigations> {
        return mainRepository.getPagigations(page,perPage)
    }

    fun getSliderData(): MutableLiveData<ImageSliderPOJO> {
        return mainRepository.getImageSliderData()
    }
}