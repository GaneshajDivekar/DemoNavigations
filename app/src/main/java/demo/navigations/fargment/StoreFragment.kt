package demo.navigations.fargment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demoproject.ui.base.BaseFragment
import demo.navigations.R
import demo.navigations.databinding.FragmentStoreBinding
import demo.navigations.fargment.Adapter.SubjectAdapter
import demo.navigations.model.HomeScreenModel.HomeScreenPOJO
import demo.navigations.model.ImageSlider.ImageSliderPOJO
import demo.navigations.model.ImageSlider.ImageSliderPOJOItem
import demo.navigations.utils.SearchAdapter
import demo.navigations.viewModel.MainViewModel
import java.util.*

class StoreFragment : BaseFragment<MainViewModel>() {
    var fragmentStoreBinding: FragmentStoreBinding? = null
    var homeNestedAdapter: SubjectAdapter? = null
    var homeScreenPOJO: HomeScreenPOJO? = null
    var searchAdapter: SearchAdapter? = null
    var imageSliderPOJO: ImageSliderPOJO? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentStoreBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false)
        fragmentStoreBinding?.viewModel = viewModel
        fragmentStoreBinding?.lifecycleOwner = this


        viewModel.getSliderData().observe(viewLifecycleOwner, Observer<ImageSliderPOJO> {
            if (it != null) {
                imageSliderPOJO = it
                searchAdapter =
                    SearchAdapter(activity!!, it)
                fragmentStoreBinding!!.hrView.setLayoutManager(LinearLayoutManager(activity))
                fragmentStoreBinding!!.hrView.setAdapter(searchAdapter)

            } else {

            }
        })

        fragmentStoreBinding!!.editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
                searchText(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        /*viewModel.getHomeData().observe(viewLifecycleOwner, Observer {
    if (it != null) {
        homeScreenPOJO = it
        homeNestedAdapter =
            SubjectAdapter(
                activity!!,
                it
            )
        fragmentStoreBinding!!.hrView.setLayoutManager(LinearLayoutManager(activity))
        fragmentStoreBinding!!.hrView.setAdapter(homeNestedAdapter)
    } else {

    }
})*/


        return fragmentStoreBinding?.root
    }

    private fun searchText(text: String) {
        var temp = ImageSliderPOJO()
        for (d in this!!.imageSliderPOJO!!) {
            if (text.contains(d.title))
                temp.add(d)
        }
        if (searchAdapter != null) {
            searchAdapter!!.updateList(temp, activity!!)
        }
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }


}