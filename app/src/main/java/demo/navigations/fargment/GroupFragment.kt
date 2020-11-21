package demo.navigations.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.demoproject.ui.base.BaseFragment
import demo.navigations.R
import demo.navigations.databinding.FragmentGroupBinding
import demo.navigations.viewModel.MainViewModel


class GroupFragment(
    var title: String) : BaseFragment<MainViewModel>() {

    var fragmentGroupBinding: FragmentGroupBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentGroupBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_group, container, false)
        fragmentGroupBinding?.viewModel = viewModel
        fragmentGroupBinding?.lifecycleOwner = this

        System.out.println("title"+title)

        return fragmentGroupBinding?.root
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}