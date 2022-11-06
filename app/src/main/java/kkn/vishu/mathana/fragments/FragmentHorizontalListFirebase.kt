package kkn.vishu.mathana.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import kkn.vishu.mathana.adapters.ListAdapterHorizontal
import kkn.vishu.mathana.databinding.FragmentListBinding
import kkn.vishu.mathana.view_models.HomeViewModel


class FragmentHorizontalListFirebase : Fragment() {

    lateinit var binding: FragmentListBinding

    lateinit var homeAdapter : ListAdapterHorizontal

    lateinit var homeViewModel: HomeViewModel



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

        binding.progressBar.visibility = View.GONE

        initRecyclerView()

        initViewModel()

        homeAdapter.setFirebase(homeViewModel.firebaseReference.value!!)
    }

    private fun initRecyclerView() {

       val gridLayoutManager = LinearLayoutManager(
           activity!!,
           LinearLayoutManager.HORIZONTAL,
           false
       )
          homeAdapter = ListAdapterHorizontal(requireActivity())

        binding.recyclerView.adapter = homeAdapter

        binding.recyclerView.layoutManager = gridLayoutManager
        val snapHelper: SnapHelper = PagerSnapHelper()

        snapHelper.attachToRecyclerView(binding.recyclerView)

    }

    private fun initViewModel () {

        homeViewModel = ViewModelProviders.of(activity!!)[HomeViewModel::class.java]

        homeAdapter.setData(homeViewModel.allFirebaseList.value!!)

        binding.recyclerView.scrollToPosition(homeViewModel.selectedPosition)

    }
}