package demo.navigations.utils

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.smarteist.autoimageslider.SliderViewAdapter
import java.util.*

abstract class SliderViewAdapter<VH : SliderViewAdapter.ViewHolder> :
    PagerAdapter() {

    private var dataSetListener: DataSetListener? = null

    //Default View holder class
    abstract class ViewHolder(val itemView: View)
    private val destroyedItems: Queue<VH> = LinkedList()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var viewHolder = destroyedItems.poll()
        if (viewHolder == null) {
            viewHolder = onCreateViewHolder(container)
        }
        // Re-add existing view before rendering so that we can make change inside getView()
        container.addView(viewHolder!!.itemView)
        onBindViewHolder(viewHolder, position)
        return viewHolder
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView((`object` as VH)!!.itemView)
        destroyedItems.add(`object` as VH)
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return (`object` as VH)!!.itemView === view
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        if (dataSetListener != null) {
            dataSetListener!!.dataSetChanged()
        }
    }

    /**
     * Create a new view holder
     *
     * @param parent wrapper view
     * @return view holder
     */
    abstract fun onCreateViewHolder(parent: ViewGroup?): VH

    /**
     * Bind data at position into viewHolder
     *
     * @param viewHolder item view holder
     * @param position   item position
     */
    abstract fun onBindViewHolder(viewHolder: VH, position: Int)

    fun dataSetChangedListener(dataSetListener: DataSetListener) {
        this.dataSetListener = dataSetListener
    }

    interface DataSetListener {
        fun dataSetChanged()
    }

}