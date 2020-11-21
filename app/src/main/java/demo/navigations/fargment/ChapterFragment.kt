package demo.navigations.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demoproject.ui.base.BaseFragment
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import demo.navigations.R
import demo.navigations.databinding.FragmentChapterBinding
import demo.navigations.fargment.Adapter.HomeScreenAdapter
import demo.navigations.model.HomeScreenModel.HomeScreenPOJO
import demo.navigations.viewModel.MainViewModel

class ChapterFragment : BaseFragment<MainViewModel>() {
    var fragmentChapterBinding:FragmentChapterBinding?=null
    var homeScreenAdapter: HomeScreenAdapter?=null
    var homeScreenPOJO:HomeScreenPOJO?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentChapterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chapter, container, false)
        fragmentChapterBinding?.viewModel = viewModel
        fragmentChapterBinding?.lifecycleOwner = this

        fragmentChapterBinding?.button?.setOnClickListener {
            for(i in homeScreenPOJO!!.data.homescreenlist.indices)
            {
                homeScreenPOJO!!.data.homescreenlist.get(i).checkIs=true
            }
            homeScreenAdapter!!.updateList(homeScreenPOJO!!)
        }
        fragmentChapterBinding?.button2?.setOnClickListener {
            for(i in homeScreenPOJO!!.data.homescreenlist.indices)
            {
                homeScreenPOJO!!.data.homescreenlist.get(i).checkIs=false
            }
            homeScreenAdapter!!.updateList(homeScreenPOJO!!)
        }

        fragmentChapterBinding!!.shimmerViewContainer.startShimmerAnimation()

        viewModel.getHomeData().observe(viewLifecycleOwner, Observer {
            if(it!=null)
            {
                homeScreenPOJO=it
                homeScreenAdapter= HomeScreenAdapter(activity!!,it)
                fragmentChapterBinding!!.rcView.setLayoutManager(LinearLayoutManager(activity))
                fragmentChapterBinding!!.rcView.setAdapter(homeScreenAdapter)
                fragmentChapterBinding!!.shimmerViewContainer.stopShimmerAnimation()
                fragmentChapterBinding!!.shimmerViewContainer.setVisibility(View.GONE);
            }else{
                fragmentChapterBinding!!.shimmerViewContainer.stopShimmerAnimation()
                fragmentChapterBinding!!.shimmerViewContainer.setVisibility(View.GONE);
            }
        })
        return fragmentChapterBinding?.root
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }


}