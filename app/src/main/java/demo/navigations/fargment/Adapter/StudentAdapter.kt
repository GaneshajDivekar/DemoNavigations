package demo.navigations.fargment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import demo.navigations.R
import demo.navigations.fargment.BlogFragment
import demo.navigations.fargment.interfaces.ItemClick
import demo.navigations.fargment.interfaces.MinusClick
import demo.navigations.fargment.interfaces.PlusClick
import demo.navigations.model.StudentDB

class StudentAdapter(
    var activity: FragmentActivity,
    var studentDB: List<StudentDB>,
    var itemClick: ItemClick?,
    var plusClick: PlusClick,
    var minusClick: MinusClick
): RecyclerView.Adapter<StudentAdapter.RecyclerViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(activity).inflate(
                R.layout.student_list,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return studentDB.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.firstName?.setText(studentDB.get(position).first_name)
        holder.lastName?.setText(studentDB.get(position).last_name)
        holder.textView4?.setText(studentDB.get(position).counter.toString())
        holder.imageView.setOnClickListener {
            plusClick.plusclick(position)
        }
        holder.imageView2.setOnClickListener {
        minusClick.minusClick(position)
        }
        holder.cardView.setOnClickListener {
            itemClick!!.onClick(position)
        }
    }

    fun updateList(studentDBs: List<StudentDB>) {
        studentDB=studentDBs
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var firstName: TextView = view.findViewById(R.id.firstName)
        var lastName: TextView = view.findViewById(R.id.lastName)
        var cardView:CardView=view.findViewById(R.id.cardView)
        var imageView:ImageView=view.findViewById(R.id.imageView)
        var imageView2:ImageView=view.findViewById(R.id.imageView2)
        var textView4:TextView=view.findViewById(R.id.textView4)
    }

}