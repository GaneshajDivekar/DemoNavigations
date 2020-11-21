package demo.navigations.fargment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demoproject.database.MyRoomDataBase
import com.demoproject.ui.base.BaseFragment
import demo.navigations.R
import demo.navigations.databinding.FragmentBlogBinding
import demo.navigations.fargment.Adapter.StudentAdapter
import demo.navigations.fargment.interfaces.ItemClick
import demo.navigations.fargment.interfaces.MinusClick
import demo.navigations.fargment.interfaces.PlusClick
import demo.navigations.model.CouponDataDB
import demo.navigations.model.StudentDB
import demo.navigations.viewModel.MainViewModel

class BlogFragment : BaseFragment<MainViewModel>(), ItemClick, PlusClick, MinusClick {

    private var blogBinding: FragmentBlogBinding? = null
    var studentDB: List<StudentDB> = ArrayList<StudentDB>()
    var studentAdapter: StudentAdapter? = null
    var plusClick: PlusClick? = null
    var itemClick: ItemClick? = null
    var minusClick: MinusClick? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        blogBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_blog, container, false)
        blogBinding?.mainviewModel = viewModel
        blogBinding?.lifecycleOwner = this
        itemClick = this
        plusClick = this
        minusClick = this
        blogBinding!!.shimmerViewContainer.startShimmerAnimation()
        blogBinding!!.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                searchText(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        viewModel.getCouponData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                MyRoomDataBase.getInstance(activity!!).getNoteDao().deleteCoupons()
                for (i in it.data.couponList.indices) {
                    var couponDataDB = CouponDataDB()
                    System.out.println("Percentage=" + it.data.couponList.get(i).percentageApplicable)
                    couponDataDB.percentage_applicable =
                        it.data.couponList.get(i).percentageApplicable
                    couponDataDB.brand_name = it.data.couponList.get(i).brandName
                    couponDataDB.item_category_name = it.data.couponList.get(i).itemCategoryName
                    couponDataDB.cart_value = it.data.couponList.get(i).cartValue
                    couponDataDB.coupon_code = it.data.couponList.get(i).couponCode
                    couponDataDB.coupon_desc = it.data.couponList.get(i).couponDesc
                    couponDataDB.coupon_id = it.data.couponList.get(i).couponId
                    couponDataDB.discount_applicable_on =
                        it.data.couponList.get(i).discountApplicableOn
                    couponDataDB.item_group_name = it.data.couponList.get(i).itemGroupName
                    couponDataDB.item_name = it.data.couponList.get(i).itemName
                    couponDataDB.item_sub_group_name = it.data.couponList.get(i).itemSubGroupName
                    couponDataDB.max_discount_value = it.data.couponList.get(i).maxDiscountValue
                    couponDataDB.max_quantity = it.data.couponList.get(i).maxQuantity
                    couponDataDB.min_quantity = it.data.couponList.get(i).minQuantity
                    couponDataDB.store_type_name = it.data.couponList.get(i).storeTypeName
                    couponDataDB.territory_name = it.data.couponList.get(i).territoryName
                    MyRoomDataBase.getInstance(activity!!).getNoteDao().insertCoupon(couponDataDB)
                }
            } else {

            }
        })


        viewModel.getStudentData(activity!!)
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it != null) {
                    studentDB = it
                    studentAdapter = StudentAdapter(
                        activity!!, studentDB, itemClick,
                        plusClick as BlogFragment, minusClick as BlogFragment
                    )
                    blogBinding!!.recyclerView.setLayoutManager(LinearLayoutManager(activity))
                    blogBinding!!.recyclerView.setAdapter(studentAdapter)
                    blogBinding!!.shimmerViewContainer.stopShimmerAnimation();
                    blogBinding!!.shimmerViewContainer.setVisibility(View.GONE);
                } else {
                    blogBinding!!.shimmerViewContainer.stopShimmerAnimation();
                    blogBinding!!.shimmerViewContainer.setVisibility(View.GONE);
                }
            })


        blogBinding?.floatingActionButton3?.setOnClickListener {
            Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddDetailsFragment())
                .addToBackStack(null)
                .commit()
        }
        return blogBinding?.root
    }

    private fun searchText(toString: String) {
        val tempPlantDaos: MutableList<StudentDB> = ArrayList<StudentDB>()
        for (plantDao in studentDB) {
            if (plantDao.first_name!!.toLowerCase().contains(toString.toLowerCase())||
                plantDao.last_name!!.toLowerCase().contains(toString.toLowerCase())) {
                tempPlantDaos.add(plantDao)
            }
        }
        if (studentAdapter != null) {
            studentAdapter!!.updateList(tempPlantDaos)
        } else {
        }
    }

    /* var studentDB: ArrayList<StudentDB> = ArrayList<StudentDB>()
         val users = ArrayList<StudentDB>()
         for (d in studentDB) {
             if (toString in d.first_name!!) {
                 users.add()

             }
         }
     }
 */
    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onClick(pos: Int) {
        Toast.makeText(
            activity!!,
            "First Name=" + studentDB.get(pos).first_name,
            Toast.LENGTH_SHORT
        ).show()
        val ft =
            activity!!.supportFragmentManager.beginTransaction()
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        val fragment2 = StudentDetailsFragment()
        val bundle = Bundle()
        val obj: StudentDB = studentDB.get(pos)
        bundle.putSerializable("your_obj", obj)
        fragment2.setArguments(bundle)
        ft.replace(R.id.container, fragment2)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun plusclick(pos: Int) {
        var count = studentDB.get(pos).counter.toInt()
        count = count + 1
        System.out.println("count" + count)
        MyRoomDataBase.getInstance(activity!!).getNoteDao()
            .updateCounter(count, studentDB.get(pos).studentId)
        viewModel.getStudentData(activity!!)
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it != null) {
                    studentDB = it
                    studentAdapter?.updateList(studentDB)
                } else {

                }
            })


    }

    override fun minusClick(pos: Int) {
        var count = studentDB.get(pos).counter.toInt()
        if (count > 0) {
            count = count - 1
            System.out.println("count" + count)
            MyRoomDataBase.getInstance(activity!!).getNoteDao()
                .updateCounter(count, studentDB.get(pos).studentId)
            viewModel.getStudentData(activity!!)
                .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    if (it != null) {
                        studentDB = it
                        studentAdapter?.updateList(studentDB)
                    } else {

                    }
                })

        } else {

        }

    }
}




