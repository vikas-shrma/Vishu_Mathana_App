package kkn.vishu.mathana.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kkn.vishu.mathana.R
import kkn.vishu.mathana.data.HomeData
import kkn.vishu.mathana.data.ImageData
import kkn.vishu.mathana.databinding.ItemImageBinding


class AboutAdapter(context: Context) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {

    private var cContext: Context = context

    private var onItemClick: OnAboutClick? = null

    private var imageLIst = mutableListOf<ImageData>()


    fun setListener(onClick: OnAboutClick) {

        this.onItemClick = onClick
    }

    fun setData(list: MutableList<ImageData>) {

        this.imageLIst = list

        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(cContext).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageLIst.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val getCurrentIndex = imageLIst[holder.adapterPosition]

        holder.binding.apply {


            Glide.with(cContext).load(getCurrentIndex.link)
                .into(ivImage)

        }

        holder.itemView.setOnClickListener {

             onItemClick?.onItemClick(getCurrentIndex.link,holder.adapterPosition)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemImageBinding.bind(itemView)
    }

    interface OnAboutClick {

        fun onItemClick(item: String,position : Int)
    }
}
