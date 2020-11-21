package demo.navigations.fargment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import demo.navigations.model.HomeScreenModel.Homescreenlist

class ChapterAdapter(var activity: FragmentActivity, var homescreenlist: List<Homescreenlist>) :
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterAdapter.ViewHolder {
        return ChapterAdapter.ViewHolder(
            LayoutInflater.from(activity).inflate(
                demo.navigations.R.layout.single_chapter,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return homescreenlist.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val chapter: Homescreenlist = homescreenlist.get(position)
        holder.tvChapterName.setText(chapter.appMenuCode)
        //Picasso.get().load(chapter.imageUrl).into(holder.ivChapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvChapterName: TextView = view.findViewById(demo.navigations.R.id.tvChapterName)
        var rvChapters: ImageView =view.findViewById(demo.navigations.R.id.ivChapter)
    }
}
