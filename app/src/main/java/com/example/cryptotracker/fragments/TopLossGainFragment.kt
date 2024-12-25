package com.example.cryptotracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.cryptotracker.adapters.MarketAdapter
import com.example.cryptotracker.api.ApiInterface
import com.example.cryptotracker.api.ApiUtils
import com.example.cryptotracker.databinding.FragmentTopLossGainBinding
import com.example.cryptotracker.models.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections

class TopLossGainFragment : Fragment() {

    lateinit var binding: FragmentTopLossGainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopLossGainBinding.inflate(layoutInflater)

        getMarketData()


        return binding.root

    }

    private fun getMarketData() {
        val position = requireArguments().getInt("position")
        lifecycleScope.launch(Dispatchers.IO) {
            val result = ApiUtils.getInstance().create(ApiInterface::class.java).getMarketData()

            if (result.body() != null) {
                withContext(Dispatchers.Main) {
                    val dataItem = result.body()!!.data.cryptoCurrencyList

                    Collections.sort(dataItem) { o1, o2 ->
                        (o2.quotes[0].percentChange24h.toInt())
                            .compareTo(o1.quotes[0].percentChange24h.toInt())
                    }

                    binding.spinKitView.visibility = GONE

                    val list = ArrayList<CryptoCurrency>()

                    if (position == 0) {
                        list.clear()
                        for (i in 0..9) {
                            list.add(dataItem[i])
                        }

                        binding.topGainLoseRecyclerView.adapter =
                            MarketAdapter(requireContext(), list, "home")

                    } else {
                        list.clear()
                        for (i in 0..9) {
                            list.add(dataItem[dataItem.size - 1 - i])
                        }

                        binding.topGainLoseRecyclerView.adapter =
                            MarketAdapter(requireContext(), list, "home")
                    }

                }
            }
        }


    }


}