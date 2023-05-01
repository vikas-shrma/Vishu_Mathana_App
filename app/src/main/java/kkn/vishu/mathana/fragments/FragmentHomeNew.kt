package kkn.vishu.mathana.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kkn.vishu.mathana.LINK_WEBSITE
import kkn.vishu.mathana.adapters.HomeItemAdapter
import kkn.vishu.mathana.adapters.HomeItemAdapterNew
import kkn.vishu.mathana.databinding.FragmentHomeBinding
import kkn.vishu.mathana.databinding.FragmentHomeNewBinding
import kkn.vishu.mathana.pager.PagerAdapter
import kkn.vishu.mathana.ui.HomeScreen
import kkn.vishu.mathana.utils.GridSpacing
import kkn.vishu.mathana.view_models.HomeViewModel
import java.util.*

class FragmentHomeNew : Fragment() {

    lateinit var binding: FragmentHomeNewBinding

    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeNewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        (activity as HomeScreen).showVisible(false)

    }

    private fun initView() {

        initViewPager()

        initViewModel()

    }

    private fun initViewPager() {


        var layoutManager = GridLayoutManager(context,2)

        binding.homePager.layoutManager = layoutManager

        binding.homePager.adapter = HomeItemAdapterNew(activity as AppCompatActivity)
    }

    private fun initViewModel() {

        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]


    }



}