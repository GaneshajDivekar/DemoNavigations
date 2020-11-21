package demo.navigations.fargment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import demo.navigations.R
import demo.navigations.model.paginations.Pagigations

class AdapterPagigations(
    var its1: FragmentActivity,
    var its: Pagigations
) : RecyclerView.Adapter<AdapterPagigations.RecycelrViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterPagigations.RecycelrViewHolder {
        return AdapterPagigations.RecycelrViewHolder(
            LayoutInflater.from(its1).inflate(
                R.layout.collaps_layout,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return if (its.photos.photo == null) 0 else its.photos.photo.size }


    override fun onBindViewHolder(holder: AdapterPagigations.RecycelrViewHolder, position: Int) {
        holder.textView.setText(its.photos.photo.get(position).title)
    }


    fun updateList(it: Pagigations) {
        its = it
        notifyDataSetChanged()
    }



    class RecycelrViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.textView2)
    }
}
