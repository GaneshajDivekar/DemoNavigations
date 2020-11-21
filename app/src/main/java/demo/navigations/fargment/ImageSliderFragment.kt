package demo.navigations.fargment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.demoproject.ui.base.BaseFragment
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import demo.navigations.R
import demo.navigations.databinding.FragmentImageSliderBinding
import demo.navigations.fargment.Adapter.SliderAdapterExample
import demo.navigations.model.ImageSlider.ImageSliderPOJO
import demo.navigations.viewModel.MainViewModel

class ImageSliderFragment : BaseFragment<MainViewModel>() {
    var fragmentImageSliderBinding:FragmentImageSliderBinding?=null
    var sliderAdapterExample:SliderAdapterExample?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentImageSliderBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_image_slider, container, false)
        fragmentImageSliderBinding?.viewModel = viewModel
        fragmentImageSliderBinding?.lifecycleOwner = this
        viewModel.getSliderData().observe(viewLifecycleOwner,Observer<ImageSliderPOJO>{
            if(it!=null)
            {

                sliderAdapterExample = SliderAdapterExample(activity!!,it)
                fragmentImageSliderBinding!!.imageSlider.setSliderAdapter(sliderAdapterExample!!)
                fragmentImageSliderBinding!!.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                fragmentImageSliderBinding!!.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                fragmentImageSliderBinding!!.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
                fragmentImageSliderBinding!!.imageSlider.setIndicatorSelectedColor(Color.WHITE)
                fragmentImageSliderBinding!!.imageSlider.setIndicatorUnselectedColor(Color.GRAY)
                fragmentImageSliderBinding!!.imageSlider.setScrollTimeInSec(3)
                fragmentImageSliderBinding!!.imageSlider.setAutoCycle(true)
                fragmentImageSliderBinding!!.imageSlider.startAutoCycle()
            }else{

            }
        })

        return fragmentImageSliderBinding!!.root
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }


}