package kkn.vishu.mathana.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
 import kkn.vishu.mathana.R
import kkn.vishu.mathana.data.HomeData
import kkn.vishu.mathana.databinding.ItemHomeBinding


class HomeItemAdapter(context: Context) : RecyclerView.Adapter<HomeItemAdapter.ViewHolder>() {

    private var cContext: Context = context

    private var onItemClick: OnDrawerClick? = null

    private var menusList = getList()

    fun setListener(onClick: OnDrawerClick) {

        this.onItemClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(cContext).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menusList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val height: Int = holder.itemView.measuredHeight / 2


        val getCurrentIndex = menusList[holder.adapterPosition]

        holder.binding.apply {

            ivImage.setImageResource(getCurrentIndex.image)
            parent.minimumHeight = height
        }

        holder.itemView.setOnClickListener {

            onItemClick?.onDrawerItemClick(holder.adapterPosition)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemHomeBinding.bind(itemView)
    }

    interface OnDrawerClick {

        fun onDrawerItemClick(item: Int)
    }

    private fun getList(): ArrayList<HomeData> {

        val list = ArrayList<HomeData>()
        list.add(HomeData(1, R.drawable.icon_about))
        list.add(HomeData(1, R.drawable.icon_poster))
        list.add(HomeData(1, R.drawable.icon_logo))
        list.add(HomeData(1, R.drawable.icon_flyers))
        list.add(HomeData(1, R.drawable.icon_painting))
        list.add(HomeData(1, R.drawable.icon_artist))

        return list
    }

}
