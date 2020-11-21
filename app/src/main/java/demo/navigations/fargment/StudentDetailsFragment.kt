package demo.navigations.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.demoproject.ui.base.BaseFragment
import demo.navigations.R
import demo.navigations.databinding.FragmentStudentDetailsBinding
import demo.navigations.model.StudentDB
import demo.navigations.viewModel.MainViewModel

class StudentDetailsFragment : BaseFragment<MainViewModel>() {

    var fragmentStudentDetailsBinding: FragmentStudentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentStudentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_details, container, false)
        fragmentStudentDetailsBinding?.viewmodel = viewModel
        fragmentStudentDetailsBinding?.lifecycleOwner = this
        val bundle = arguments
        val obj: StudentDB? = bundle!!.getSerializable("your_obj") as StudentDB?
        fragmentStudentDetailsBinding?.firstName?.setText(obj?.first_name)
        fragmentStudentDetailsBinding?.secondName?.setText(obj?.second_name)
        fragmentStudentDetailsBinding?.lastName?.setText(obj?.last_name)
        fragmentStudentDetailsBinding?.address?.setText(obj?.address)

        return fragmentStudentDetailsBinding?.root
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}