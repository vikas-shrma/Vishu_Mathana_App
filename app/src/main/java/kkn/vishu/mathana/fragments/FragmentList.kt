package kkn.vishu.mathana.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kkn.vishu.mathana.*
import kkn.vishu.mathana.adapters.HomeItemAdapter
import kkn.vishu.mathana.adapters.ListAdapter
import kkn.vishu.mathana.databinding.FragmentListBinding
import kkn.vishu.mathana.databinding.ItemImageBinding
import kkn.vishu.mathana.databinding.ItemPagerImageBinding
import kkn.vishu.mathana.ui.HomeScreen
import kkn.vishu.mathana.utils.GridSpacing
import kkn.vishu.mathana.view_models.HomeViewModel


class FragmentList : Fragment() {

    lateinit var binding: FragmentListBinding

    lateinit var homeViewModel: HomeViewModel

    lateinit var homeAdapter : ListAdapter

    private var imagesList = mutableListOf<StorageReference>()

 private var getIndexValue = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        initRecyclerView()

        getDataFromArguments()
    }

    private fun initRecyclerView() {

       val gridLayoutManager = GridLayoutManager(activity!!,2)
          homeAdapter = ListAdapter(requireActivity())

        binding.recyclerView.adapter = homeAdapter

        val gridSpacing = GridSpacing(10)
        binding.recyclerView.addItemDecoration(gridSpacing)

        binding.recyclerView.layoutManager = gridLayoutManager

        homeAdapter.setFirebase(homeViewModel.firebaseReference.value!!)

        homeAdapter.setListener(object : ListAdapter.OnDrawerClick{
            override fun onDrawerItemClick(item: String,position : Int) {

                homeViewModel.selectedPosition = position

                homeViewModel.allFirebaseList.value = imagesList

                (activity as HomeScreen).changeFragment(9,item)

            }
        })
    }

    private fun getDataFromArguments() {

        if(arguments!=null){

            getIndexValue = arguments?.getInt("index")!!

            val path :String = when(getIndexValue) {

                0->""
                1-> PATH_POSTERS
                2-> PATH_LOGOS
                3-> PATH_FLYER
                4-> PATH_DIGITAL_PAINTINGS
                5-> PATH_ARTIST
                else->""
            }

            callFirebaseApi(path)

        }

        (activity as HomeScreen).showVisible(true)

    }

    private fun initViewModel () {

        homeViewModel = ViewModelProviders.of(activity!!)[HomeViewModel::class.java]

        homeViewModel.allListImages.observe(viewLifecycleOwner, Observer {

            it.let {

                homeAdapter.setData(imagesList)
            }

        })
    }

    private fun callFirebaseApi(path : String) {


        val islandRef = homeViewModel.storageReference.value?.child(path)

        val listAllTask: Task<ListResult> = islandRef?.listAll()!!

        listAllTask.addOnCompleteListener { result ->

            binding.progressBar.visibility = View.GONE

            println("The data length is ${result.result?.items?.size}")

            imagesList = result.result?.items!!
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.setItemViewCacheSize(result.result?.items?.size!!)
           // binding.recyclerView.setDrawingCacheEnabled(true);

            if(getIndexValue==2) {
                while (binding.recyclerView.itemDecorationCount > 0) {
                    binding.recyclerView.removeItemDecorationAt(0);
                }
            }
            homeAdapter.setData(result.result?.items!!)
        }
    }

}