package kkn.vishu.mathana.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kkn.vishu.mathana.R
import kkn.vishu.mathana.adapters.AboutAdapter
import kkn.vishu.mathana.adapters.ListAdapter
import kkn.vishu.mathana.data.HomeData
import kkn.vishu.mathana.data.ImageData
import kkn.vishu.mathana.databinding.FragmentAboutBinding
import kkn.vishu.mathana.databinding.FragmentHomeBinding
import kkn.vishu.mathana.databinding.FragmentSingleImageBinding
import kkn.vishu.mathana.ui.HomeScreen
import kkn.vishu.mathana.utils.GridSpacing
import kkn.vishu.mathana.view_models.HomeViewModel

class FragmentAbout : Fragment() {

    lateinit var binding: FragmentAboutBinding

    lateinit var homeViewModel: HomeViewModel

    lateinit var aboutAdapter: AboutAdapter

    companion object {
      var allList = mutableListOf<ImageData>()

        var position : Int = 0
    }
    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {

        initViewPagers()

        binding.tvDescription.text = getString(R.string.step_8)

        (activity as HomeScreen).showVisible(true)
        (activity as HomeScreen).showClose(false)

        initViewModel()

        getImages()

    }

        private fun initViewPagers() {

            val gridLayoutManager = GridLayoutManager(activity!!,2)
            aboutAdapter = AboutAdapter(requireActivity())

            binding.recyclerView.adapter = aboutAdapter

            val gridSpacing = GridSpacing(10)
            gridSpacing.isOnlyRightSpacing(true)
            //binding.recyclerView.addItemDecoration(gridSpacing)

            binding.recyclerView.layoutManager = gridLayoutManager

            aboutAdapter.setListener(object : AboutAdapter.OnAboutClick{
                override fun onItemClick(item: String,position : Int) {

                    FragmentAbout.position = position

                    (activity as HomeScreen).changeFragment(7)
                }
            })
        }


    private fun initViewModel() {

        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]

    }

    private fun getImages() {

          allList = arrayListOf()

            val mDatabase = FirebaseDatabase.getInstance().reference;

            mDatabase.child("profile").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (data  in snapshot.children) {

                        val homeData = data.getValue(ImageData::class.java)

                        allList.add(homeData!!)
                    }

                    aboutAdapter.setData(allList)

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

}