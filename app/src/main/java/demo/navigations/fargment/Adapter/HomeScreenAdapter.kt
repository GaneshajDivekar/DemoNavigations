package demo.navigations.fargment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import demo.navigations.R
import demo.navigations.model.HomeScreenModel.HomeScreenPOJO

class HomeScreenAdapter(
    var activity: FragmentActivity,
    var its: HomeScreenPOJO
):RecyclerView.Adapter<HomeScreenAdapter.RecycelrViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeScreenAdapter.RecycelrViewHolder {
        return HomeScreenAdapter.RecycelrViewHolder(
            LayoutInflater.from(activity).inflate(
                R.layout.home_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return its.data.homescreenlist.size
    }

    override fun onBindViewHolder(holder: HomeScreenAdapter.RecycelrViewHolder, position: Int) {

        holder.checkBox3.setChecked(its.data.homescreenlist.get(position).checkIs)
        holder.textView.setText(its.data.homescreenlist.get(position).appMenuCode)
        holder.checkBox3.setOnClickListener {
               its.data.homescreenlist.get(position).checkIs=true
            }
    }

    fun updateList(homeScreenPOJO: HomeScreenPOJO) {
        its=homeScreenPOJO
        notifyDataSetChanged()
    }

    class RecycelrViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.textView3)
        var checkBox3:CheckBox=view.findViewById(R.id.checkBox3)
       }
}