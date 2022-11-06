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
import kkn.vishu.mathana.databinding.ItemHorizontalImageBinding
import kkn.vishu.mathana.databinding.ItemImageBinding


class ListAdapterHorizontal(context: Context) : RecyclerView.Adapter<ListAdapterHorizontal.ViewHolder>() {

    private var cContext: Context = context


    private var imageLIst = mutableListOf<StorageReference>()

    lateinit var firebaseStorage: FirebaseStorage

    fun setData(list: MutableList<StorageReference>) {

        this.imageLIst = list

        notifyDataSetChanged()
    }


    fun setFirebase(reference: FirebaseStorage) {

        this.firebaseStorage = reference
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

            val createPath = FIREBASE_URL.plus(getCurrentIndex.path)

            println("THE THE THE $createPath")

            val gsReference = firebaseStorage.getReferenceFromUrl(createPath)

            gsReference.downloadUrl.addOnSuccessListener { uri ->

                println("THE THE THE $uri")
                Glide.with(cContext).load(uri)
                    .into(ivImage)

                ivImage.tag = uri
            }

            //ivImage.setImageResource(getCurrentIndex.image)

        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemHorizontalImageBinding.bind(itemView)
    }


}
