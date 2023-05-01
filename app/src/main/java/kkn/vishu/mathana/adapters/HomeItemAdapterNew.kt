package kkn.vishu.mathana.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
 import kkn.vishu.mathana.R
import kkn.vishu.mathana.databinding.ItemHomeNewBinding


class HomeItemAdapterNew(context: Context) : RecyclerView.Adapter<HomeItemAdapterNew.ViewHolder>() {

    private var cContext: Context = context

    private var onItemClick: OnDrawerClick? = null

        var menusList = context.resources.getStringArray(R.array.home_menus)

    fun setListener(onClick: OnDrawerClick) {

        this.onItemClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(cContext).inflate(R.layout.item_home_new, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menusList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        menusList = cContext.resources.getStringArray(R.array.home_menus)
        val getCurrentIndex = menusList[holder.adapterPosition]



        holder.binding.apply {

            tvText.setText(getCurrentIndex)

        }

        holder.itemView.setOnClickListener {

            onItemClick?.onDrawerItemClick(holder.adapterPosition)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemHomeNewBinding.bind(itemView)
    }

    interface OnDrawerClick {

        fun onDrawerItemClick(item: Int)
    }

}
