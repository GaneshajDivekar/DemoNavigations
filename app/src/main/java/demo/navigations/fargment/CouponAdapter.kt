package demo.navigations.fargment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import demo.navigations.R
import demo.navigations.fargment.interfaces.CouponsItemClick
import demo.navigations.model.CouponDataDB

class CouponAdapter(
    var activity: FragmentActivity,
    var its: List<CouponDataDB>,
    var itemClick: CouponsItemClick
) :
    RecyclerView.Adapter<CouponAdapter.RecycelrViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CouponAdapter.RecycelrViewHolder {
        return CouponAdapter.RecycelrViewHolder(
            LayoutInflater.from(activity).inflate(
                R.layout.coupon_layout,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return its.size
    }

    override fun onBindViewHolder(holder: CouponAdapter.RecycelrViewHolder, position: Int) {
        holder.textView.setText(its.get(position).coupon_code)
        holder.itemView.setOnClickListener {
            itemClick.itemClick(its.get(position),position)
        }
    }


    class RecycelrViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.textView)
    }
}
