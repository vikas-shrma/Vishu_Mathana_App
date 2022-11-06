package kkn.vishu.mathana.pager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter.POSITION_NONE
import androidx.viewpager2.adapter.FragmentStateAdapter


class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return ImageFragment.newInstance(position)
    }

    fun getItemPosition(data : Any?): Int {
        return POSITION_NONE
    }


}