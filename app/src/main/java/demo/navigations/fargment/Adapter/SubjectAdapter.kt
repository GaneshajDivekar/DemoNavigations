package demo.navigations.fargment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import demo.navigations.model.HomeScreenModel.HomeScreenPOJO


class SubjectAdapter(
    var activity: FragmentActivity,
    var its: HomeScreenPOJO
):RecyclerView.Adapter<SubjectAdapter.RecycelrViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectAdapter.RecycelrViewHolder {
        return SubjectAdapter.RecycelrViewHolder(
            LayoutInflater.from(activity).inflate(
                demo.navigations.R.layout.single_subject,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return its.data.homescreenlist.size
    }

    override fun onBindViewHolder(holder: SubjectAdapter.RecycelrViewHolder, position: Int) {
      holder.rvChapters.setAdapter(ChapterAdapter(activity, its.data.homescreenlist))
        holder.rvChapters.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        holder.tvSubjectName.setText(its.data.homescreenlist.get(position).appMenuCode)
        holder.tvAll.setOnClickListener {
            System.out.println("position"+position)
        }
    }

    class RecycelrViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSubjectName: TextView = view.findViewById(demo.navigations.R.id.tvSubjectName)
        var tvAll: TextView = view.findViewById(demo.navigations.R.id.tvAll)
        var rvChapters: RecyclerView =view.findViewById(demo.navigations.R.id.rvChapters)
    }
}