package kkn.vishu.mathana.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kkn.vishu.mathana.R
import kkn.vishu.mathana.databinding.ItemBookLinkBinding



class BookLinkAdapter(context: Context) : RecyclerView.Adapter<BookLinkAdapter.ViewHolder>() {

    private var cContext: Context = context

    private var onItemClick: OnAboutClick? = null



    var nameList = arrayListOf<String>("AMAZON","FLIPKART","GOOGLE","BLUEROSE");

    var imageLink = arrayListOf<Int>(R.drawable.amazon,R.drawable.flipkart,R.drawable.google_play_books,R.drawable.bluerose);


    fun setListener(onClick: OnAboutClick) {

        this.onItemClick = onClick
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(cContext).inflate(R.layout.item_book_link, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.binding.apply {


 tvTitle.text = nameList[position]

            ivImage.setImageResource(imageLink[position])

        }

        holder.itemView.setOnClickListener {

             //onItemClick?.onItemClick(getCurrentIndex.link,holder.adapterPosition)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemBookLinkBinding.bind(itemView)
    }

    interface OnAboutClick {

        fun onItemClick(item: String,position : Int)
    }
}
