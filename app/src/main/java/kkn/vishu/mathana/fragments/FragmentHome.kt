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
import kkn.vishu.mathana.databinding.FragmentHomeBinding
import kkn.vishu.mathana.pager.PagerAdapter
import kkn.vishu.mathana.ui.HomeScreen
import kkn.vishu.mathana.utils.GridSpacing
import kkn.vishu.mathana.view_models.HomeViewModel
import java.util.*

class FragmentHome : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var homeViewModel: HomeViewModel

    private var homeItemAdapter: HomeItemAdapter? = null

    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 5000 //delay in milliseconds before task is to be executed


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

        //setAdapter()
    }

    private fun initView() {

        initViewPager()

        initRecyclerView()

        initViewModel()

        autoScroll()

        binding.bottomBanner.setOnClickListener {
            (activity as HomeScreen).getUrlFromIntent(LINK_WEBSITE)
        }
    }

    private fun initRecyclerView() {

        binding.ivAbout.setOnClickListener {
            (activity as HomeScreen).changeFragment(0)
        }
        binding.ivPoster.setOnClickListener {
            (activity as HomeScreen).changeFragment(1)
        }
        binding.ivLogo.setOnClickListener {
            (activity as HomeScreen).changeFragment(2)
        }
        binding.ivFlyer.setOnClickListener {
            (activity as HomeScreen).changeFragment(3)
        }
        binding.ivPainting.setOnClickListener {
            (activity as HomeScreen).changeFragment(4)
        }
        binding.ivArtist.setOnClickListener {
            (activity as HomeScreen).changeFragment(5)
        }
    }

    private fun initViewPager() {

        binding.pager.adapter = PagerAdapter(activity as AppCompatActivity)

        binding.pagerIndicator.attachToPager(binding.pager)
    }


    private fun initViewModel() {

        homeViewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]

        homeViewModel.coverImages.observe(viewLifecycleOwner, Observer {

            it.let {

                binding.pager.adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun autoScroll() {

        val handler = Handler()
        val update = Runnable {
            if (binding.pager.currentItem == 2) {
                currentPage = 0
                binding.pager.setCurrentItem(0, false)
            } else {
                currentPage = currentPage.plus(1)
                binding.pager.setCurrentItem(currentPage, true)
            }
        }

        timer = Timer() // This will create a new Thread

        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, DELAY_MS)
    }


}