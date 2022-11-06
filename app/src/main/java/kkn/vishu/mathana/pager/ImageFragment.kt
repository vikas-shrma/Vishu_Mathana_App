package kkn.vishu.mathana.pager


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.firebase.storage.StorageReference
import kkn.vishu.mathana.FIREBASE_URL
import kkn.vishu.mathana.R
import kkn.vishu.mathana.databinding.ItemImageBinding
import kkn.vishu.mathana.databinding.ItemPagerImageBinding
import kkn.vishu.mathana.view_models.HomeViewModel


class ImageFragment : Fragment() {

    lateinit var binding: ItemPagerImageBinding

    lateinit var homeViewModel: HomeViewModel

    companion object {

        var position :Int? = 0

        fun newInstance(title: Int?): Fragment {

            position = title

            val frag :Fragment = ImageFragment()

            val bundle = Bundle()
            bundle.putInt("key",title!!)
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ItemPagerImageBinding.inflate(layoutInflater, container, false)
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getPath = arguments?.getInt("key")
        println("THE THE THE $getPath")
 

        if(!homeViewModel.coverImages.value?.isEmpty()!!) {

            val createPath = FIREBASE_URL.plus(homeViewModel.coverImages.value?.get(getPath!!)?.path)

            println("THE THE THE $createPath")

            val gsReference = homeViewModel.firebaseReference.value?.getReferenceFromUrl(createPath)

            gsReference?.downloadUrl?.addOnSuccessListener { uri ->
                Glide.with(context!!).load(uri).into(binding.ivImage)
            }
        }
    }

    private fun initViewModel() {

        homeViewModel = ViewModelProviders.of(activity!!)[HomeViewModel::class.java]

        homeViewModel.coverImages.observe(viewLifecycleOwner, Observer {

            it.let {

                if(!homeViewModel.coverImages.value?.isEmpty()!!) {

                    val createPath =
                        FIREBASE_URL.plus(homeViewModel.coverImages.value?.get(position!!)?.path)

                    val gsReference =
                        homeViewModel.firebaseReference.value?.getReferenceFromUrl(createPath)

                    gsReference?.downloadUrl?.addOnSuccessListener { uri ->
                        Glide.with(context!!).load(uri).into(binding.ivImage)
                    }
                }
            }

        })
    }
}