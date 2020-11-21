package demo.navigations.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.demoproject.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import demo.navigations.R
import demo.navigations.databinding.FragmentDynamicTabBinding
import demo.navigations.fargment.Adapter.ViewPagerAdapter
import demo.navigations.model.ImageSlider.ImageSliderPOJO
import demo.navigations.viewModel.MainViewModel


class DynamicTabFragment : BaseFragment<MainViewModel>() {
    var fragmentDynamicTabBinding: FragmentDynamicTabBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDynamicTabBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic_tab, container, false)
        fragmentDynamicTabBinding?.viewModel = viewModel
        fragmentDynamicTabBinding?.lifecycleOwner = this
        viewModel.getSliderData().observe(viewLifecycleOwner, Observer<ImageSliderPOJO> {

            if (it != null) {
                var size: Int = it!!.size - 63
                for (i in 0..size) {
                    fragmentDynamicTabBinding!!.tabLayout.addTab(fragmentDynamicTabBinding!!.tabLayout.newTab().setText(it.get(i).title))
                }
                var adapter = ViewPagerAdapter(childFragmentManager,it)
                fragmentDynamicTabBinding!!.viewPager.setAdapter(adapter)
                fragmentDynamicTabBinding!!.viewPager.setOffscreenPageLimit(1)
                fragmentDynamicTabBinding!!.viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(fragmentDynamicTabBinding!!.tabLayout))
                fragmentDynamicTabBinding!!.tabLayout.setupWithViewPager(fragmentDynamicTabBinding!!.viewPager)
                var sizee: Int = it!!.size - 63
                for (i in 0..sizee) {
                    adapter.addFragment(GroupFragment(it.get(i).title),it.get(i).title)
                }

                fragmentDynamicTabBinding!!.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab) {
                        System.out.println("tabbbb"+tab.text)
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab) {
                        System.out.println("tab1"+tab.position)

                    }
                    override fun onTabReselected(tab: TabLayout.Tab) {
                        System.out.println("tab2"+tab)

                    }
                })
            } else {

            }
        })


        return fragmentDynamicTabBinding?.root
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}
