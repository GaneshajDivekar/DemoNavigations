package demo.navigations.fargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.demoproject.database.MyRoomDataBase
import com.demoproject.ui.base.BaseFragment
import demo.navigations.R
import demo.navigations.databinding.FragmentAddDetailsBinding
import demo.navigations.model.StudentDB
import demo.navigations.viewModel.MainViewModel

class AddDetailsFragment : BaseFragment<MainViewModel>(),View.OnClickListener {
    var fragmentAddDetailsBinding:FragmentAddDetailsBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_details, container, false)
        fragmentAddDetailsBinding?.mainViewModel = viewModel
        fragmentAddDetailsBinding?.lifecycleOwner = this
        fragmentAddDetailsBinding?.btnSubmit?.setOnClickListener(this)
        return fragmentAddDetailsBinding?.root
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.btnSubmit ->{
                var studentDB=StudentDB()
                studentDB.first_name=fragmentAddDetailsBinding!!.username.text.toString()
                studentDB.second_name=fragmentAddDetailsBinding!!.secondName.text.toString()
                studentDB.last_name=fragmentAddDetailsBinding!!.lastName.text.toString()
                studentDB.address=fragmentAddDetailsBinding!!.address.text.toString()
                studentDB.counter=0
                MyRoomDataBase.getInstance(activity!!).getNoteDao().insertStudentDB(studentDB)

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BlogFragment())
                    .addToBackStack(null)
                    .commit()
                Toast.makeText(activity!!,"submit clicked",Toast.LENGTH_SHORT).show()
            }

        }
    }


}