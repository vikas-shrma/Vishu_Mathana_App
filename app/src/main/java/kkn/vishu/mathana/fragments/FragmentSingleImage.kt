package kkn.vishu.mathana.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kkn.vishu.mathana.databinding.FragmentSingleImageBinding
import kkn.vishu.mathana.ui.HomeScreen

class FragmentSingleImage : Fragment() {

    lateinit var binding: FragmentSingleImageBinding

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSingleImageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {

        if(arguments!= null) {

            val createPath = arguments?.getString("path")

            Glide.with(this).load(createPath).into(binding.ivImage)

        }

        (activity as HomeScreen).showVisible(false)
        (activity as HomeScreen).showClose(true)


    }

}