package kkn.vishu.mathana.fragments

import android.content.Intent
import android.net.Uri
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
import kkn.vishu.mathana.*
import kkn.vishu.mathana.adapters.AboutAdapter
import kkn.vishu.mathana.adapters.BookLinkAdapter
import kkn.vishu.mathana.adapters.ListAdapter
import kkn.vishu.mathana.data.HomeData
import kkn.vishu.mathana.data.ImageData
import kkn.vishu.mathana.databinding.FragmentAboutBinding
import kkn.vishu.mathana.databinding.FragmentBookBinding
import kkn.vishu.mathana.databinding.FragmentHomeBinding
import kkn.vishu.mathana.databinding.FragmentSingleImageBinding
import kkn.vishu.mathana.ui.HomeScreen
import kkn.vishu.mathana.utils.GridSpacing
import kkn.vishu.mathana.view_models.HomeViewModel

class FragmentBook : Fragment() {

    lateinit var binding: FragmentBookBinding

    lateinit var homeViewModel: HomeViewModel

    lateinit var bookAdapter: BookLinkAdapter

    companion object {
      var allList = mutableListOf<ImageData>()

        var position : Int = 0
    }
    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBookBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {

        initViewPagers()

        binding.tvDescription.text = getString(R.string.book_about)

        (activity as HomeScreen).showVisible(true)
        (activity as HomeScreen).showClose(false)

        initViewModel()


    }

        private fun initViewPagers() {

            val gridLayoutManager = GridLayoutManager(activity!!,4)
            bookAdapter = BookLinkAdapter(requireActivity())

            binding.recyclerView.adapter = bookAdapter

            val gridSpacing = GridSpacing(10)
            gridSpacing.isOnlyRightSpacing(true)
            //binding.recyclerView.addItemDecoration(gridSpacing)

            binding.recyclerView.layoutManager = gridLayoutManager

            bookAdapter.setListener(object : BookLinkAdapter.OnAboutClick{
                override fun onItemClick(item: String,position : Int) {

                    val link =  when(position) {
                        0-> LINK_AMAZON
                        1-> LINK_FLIPKART;
                        2-> LINK_GOOGLE;
                        3-> LINK_BLUEROSE
                        else->""
                    }

                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))

                    startActivity(browserIntent)
                    (activity as HomeScreen).changeFragment(7)
                }
            })
        }


    private fun initViewModel() {

        homeViewModel = ViewModelProviders.of(activity!!)[HomeViewModel::class.java]

        homeViewModel.stringTitle.postValue(" MY BOOK")

    }



}