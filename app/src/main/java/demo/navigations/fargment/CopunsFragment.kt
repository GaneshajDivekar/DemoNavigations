package demo.navigations.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demoproject.database.MyRoomDataBase
import com.demoproject.ui.base.BaseFragment
import demo.navigations.R
import demo.navigations.databinding.FragmentCopunsBinding
import demo.navigations.fargment.interfaces.CouponsItemClick
import demo.navigations.model.CouponDataDB
import demo.navigations.model.jsonpojo.ItemHeader
import demo.navigations.viewModel.MainViewModel

class CopunsFragment : BaseFragment<MainViewModel>(), CouponsItemClick {

    var fragmentCopunsBinding: FragmentCopunsBinding? = null
    var couponAdapter: CouponAdapter? = null
    var itemHeaderList = ArrayList<ItemHeader>()
    var itemClick: CouponsItemClick? = null
    var couponDataDBList: List<CouponDataDB> = ArrayList<CouponDataDB>()
    var item = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCopunsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_copuns, container, false)
        fragmentCopunsBinding?.viewModel = viewModel
        fragmentCopunsBinding?.lifecycleOwner = this
        itemClick = this

        var itemHeader = ItemHeader(
            "1,2", 1, 2, "1,2", "Apple", "1,2"
            , "1", "1", "1", "100", "nos", "1", "FAV-001", "LEAFY"
        )
        itemHeaderList.add(itemHeader)

        showDropDown()
        viewModel.getCouponsData(activity!!).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                couponDataDBList = it
                couponAdapter = CouponAdapter(activity!!, it, itemClick as CopunsFragment)
                fragmentCopunsBinding!!.rvCoupons.setLayoutManager(LinearLayoutManager(activity))
                fragmentCopunsBinding!!.rvCoupons.setAdapter(couponAdapter)
            } else {

            }
        })
        return fragmentCopunsBinding!!.root
    }

    private fun showDropDown() {
        var data = MyRoomDataBase.getInstance(activity!!).getNoteDao().getStudent()

        for (i in data.indices) {
            item.add(data.get(i).first_name!!)
        }
        val arrayAdapter =
            ArrayAdapter<String>(activity!!, R.layout.spinner_custom_textview, item)
        fragmentCopunsBinding!!.autoCompleteTextView.setAdapter(arrayAdapter)

        fragmentCopunsBinding!!.autoCompleteTextView.setOnClickListener {
            fragmentCopunsBinding!!.autoCompleteTextView.showDropDown()
        }
        fragmentCopunsBinding!!.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
        var firstName = item.get(i)

            Toast.makeText(activity!!,"name Clicked"+firstName,Toast.LENGTH_SHORT).show()

        }
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun itemClick(couponDataDB: CouponDataDB, pos: Int) {
        var couponsDataDB = couponDataDB
        var item = itemHeaderList.get(0).item_name
        var groupname = couponDataDB.item_group_name
        val strs = couponsDataDB.item_name!!.split(",").toTypedArray()
        for (i in strs.indices) {

            if ((item.equals(
                    strs.get(i).trim()
                )) || (groupname!!.equals(itemHeaderList.get(0).group_name))
            ) {
                var total =
                    itemHeaderList.get(0).totalPrice!!.toDouble() * couponsDataDB.percentage_applicable.toDouble() / 100
                var amountTotal = total.toDouble() - itemHeaderList.get(0).totalPrice!!.toDouble()
                System.out.println("amount" + amountTotal)
                Toast.makeText(context, "Coupon Apply Successfully", Toast.LENGTH_SHORT).show()
            } else {

            }
        }
    }

}