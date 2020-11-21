package demo.navigations.fargment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demoproject.ui.base.BaseFragment
import demo.navigations.R
import demo.navigations.databinding.FragmentCollapasBinding
import demo.navigations.viewModel.MainViewModel


class CollapasFragment : BaseFragment<MainViewModel>() {
    var fragmentCollapasBinding: FragmentCollapasBinding? = null
    var adapterPagigations: AdapterPagigations? = null
    private val isLoaderVisible = false

    var page_number = 1
    var per_page = 20
    var mLayoutManager: LinearLayoutManager? = null
    var isLoading = true
    var pastVisiableItem = 0
    var visialeItemCount = 0
    var totalItemCount = 0
    var previous_total = 0
    var view_threashould = 10


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCollapasBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_collapas, container, false)
        fragmentCollapasBinding?.viewModel = viewModel
        fragmentCollapasBinding?.lifecycleOwner = this
        mLayoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        fragmentCollapasBinding!!.rvPaging.setLayoutManager(mLayoutManager)
        fragmentCollapasBinding!!.rvPaging.setItemAnimator(DefaultItemAnimator())

        loadNextPage()

        fragmentCollapasBinding!!.rvPaging.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                visialeItemCount = mLayoutManager!!.getChildCount();
                totalItemCount = mLayoutManager!!.getItemCount();
                pastVisiableItem = mLayoutManager!!.findFirstVisibleItemPosition();
                if (isLoading) {
                    if (totalItemCount > previous_total) {
                        isLoading = false;
                        previous_total = totalItemCount;
                    }
                }
                if (!isLoading && (totalItemCount - visialeItemCount)
                    <= (pastVisiableItem + view_threashould)
                ) {
                    page_number++
                    performPagigations()
                    isLoading = true;
                }
            }
        })
        return fragmentCollapasBinding!!.root
    }

    private fun loadNextPage() {
        viewModel.getPagigations(page_number, per_page).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                fragmentCollapasBinding!!.mainProgress.setVisibility(View.GONE)
                adapterPagigations = AdapterPagigations(activity!!, it!!)
                fragmentCollapasBinding!!.rvPaging.setAdapter(adapterPagigations)
            }
        })

    }

    private fun performPagigations() {
        System.out.println("page_number"+page_number+"Per_page"+per_page)
        viewModel.getPagigations(page_number, per_page).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapterPagigations!!.updateList(it)
                isLoading = false

            }
        })
    }


    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

}