package kkn.vishu.mathana.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kkn.vishu.mathana.FIREBASE_URL
import kkn.vishu.mathana.R
import kkn.vishu.mathana.data.ImageData
import kkn.vishu.mathana.databinding.ItemHorizontalImageBinding
import kkn.vishu.mathana.databinding.ItemImageBinding


class HorizontalAdapter(context: Context) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    private var cContext: Context = context

    private var onItemClick: OnDrawerClick? = null

    private var imageLIst = mutableListOf<ImageData>()

    fun setData(list: MutableList<ImageData>) {

        this.imageLIst = list

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(cContext).inflate(R.layout.item_horizontal_image, parent, false)
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

            //ivImage.setImageResource(getCurrentIndex.image)

        }

        holder.itemView.setOnClickListener {

            val getUrl = holder.binding.ivImage.tag.toString()

             onItemClick?.onDrawerItemClick(getUrl)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemHorizontalImageBinding.bind(itemView)
    }

    interface OnDrawerClick {

        fun onDrawerItemClick(item: String)
    }


}
