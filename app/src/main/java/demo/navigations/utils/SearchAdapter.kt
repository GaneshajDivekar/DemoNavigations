package demo.navigations.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import demo.navigations.fargment.Adapter.SubjectAdapter
import demo.navigations.model.ImageSlider.ImageSliderPOJO
import java.util.ArrayList

class SearchAdapter(
    var activity: FragmentActivity,
    var its: ImageSliderPOJO
) : RecyclerView.Adapter<SearchAdapter.RecycelrViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.RecycelrViewHolder {
        return SearchAdapter.RecycelrViewHolder(
            LayoutInflater.from(activity).inflate(
                demo.navigations.R.layout.search_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return its.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.RecycelrViewHolder, position: Int) {
            holder.title.setText(its.get(position).title)
        holder.itemView.setOnClickListener {
            System.out.println("data"+its.get(position).title+"data1"+its.get(position).releaseYear)
        }
    }

    fun updateList(temp:ImageSliderPOJO, context: Context) {
        its=temp
        notifyDataSetChanged()
    }

    class RecycelrViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(demo.navigations.R.id.title)
    }
}