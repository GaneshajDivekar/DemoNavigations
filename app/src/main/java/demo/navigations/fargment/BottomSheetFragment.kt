package demo.navigations.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.demoproject.ui.base.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import demo.navigations.R
import demo.navigations.databinding.FragmentBottomSheetBinding
import demo.navigations.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_bottom_sheet2.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet2.view.*

class BottomSheetFragment : BaseFragment<MainViewModel>() {
    var fragmentBottomSheetBinding: FragmentBottomSheetBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBottomSheetBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false)
        fragmentBottomSheetBinding?.viewmodel = viewModel
        fragmentBottomSheetBinding?.lifecycleOwner = this

        val bottomSheetBehavior = BottomSheetBehavior.from(fragmentBottomSheetBinding!!.layoutBottomSheet)

        fragmentBottomSheetBinding!!.clickMe.setOnClickListener {

            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {

                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                } else {

                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

        bottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(view: View, state: Int) {

                view.tvSignUp.setOnClickListener {
                    Toast.makeText(activity!!,"Click me!",Toast.LENGTH_SHORT).show()
                }

                when (state) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    // floatingActionButton.visibility = View.GONE
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    //    floatingActionButton.visibility = View.VISIBLE
                    }
                }
            }

            override fun onSlide(view: View, p1: Float) {


            }
        })
        return fragmentBottomSheetBinding!!.root
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }


}