package com.example.cryptotracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptotracker.adapters.TopLossGainPagerAdapter
import com.example.cryptotracker.api.ApiInterface
import com.example.cryptotracker.api.ApiUtils
import com.example.cryptotracker.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setTabLayout()
        getTopCurrencyList()

        return binding.root
    }

    private fun getTopCurrencyList() {
       lifecycleScope.launch(Dispatchers.IO){
           var res= ApiUtils.getInstance().create(ApiInterface::class.java).getMarketData()

       }
    }

    private fun setTabLayout()
    {
        val adapter = TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter = adapter
        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position==0)
                {
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topLoseIndicator.visibility = GONE
                }
                else
                {
                    binding.topGainIndicator.visibility = GONE
                    binding.topLoseIndicator.visibility = VISIBLE
                }
            }
        })
        TabLayoutMediator(binding.tabLayout,binding.contentViewPager)
        {
                tab, position ->
            var title = if(position == 0)
            {
                "Top Gainers"
            }
            else{
                "Top Losers"
            }
            tab.text = title
        }.attach()
        }

}