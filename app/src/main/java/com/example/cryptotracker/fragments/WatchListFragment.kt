package com.example.cryptotracker.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import com.example.cryptotracker.Login_SignUp
import com.example.cryptotracker.adapters.MarketAdapter
import com.example.cryptotracker.api.ApiInterface
import com.example.cryptotracker.api.ApiUtils
import com.example.cryptotracker.databinding.FragmentWatchList2Binding
import com.example.cryptotracker.models.CryptoCurrency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchlistFragment : Fragment() {

    private lateinit var binding: FragmentWatchList2Binding
    private lateinit var watchList : ArrayList<String>
    private lateinit var watchlistItem : ArrayList<CryptoCurrency>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWatchList2Binding.inflate(layoutInflater)

        watchlistItem = ArrayList()
        watchList = ArrayList()

        readData()

        binding.logoutButton.setOnClickListener {
            var sharedPreferences: SharedPreferences =
                requireContext().getSharedPreferences(LogInFragement::PREFS_NAME.toString(), 0)

            // declaring the editor object to edit the booleand data
            var editor: SharedPreferences.Editor = sharedPreferences.edit()

            // changing the boolean data of key hasLoggedIn to false because user clicked on logout
            editor.putBoolean("hasLoggedIn", false)
            editor.commit()
            val intent = Intent(this@WatchlistFragment.requireContext(),Login_SignUp::class.java)
            startActivity(intent)

        }


        lifecycleScope.launch(Dispatchers.IO){
            val result = ApiUtils.getInstance().create(ApiInterface::class.java).getMarketData()

            if(result.body()!=null){

                withContext(Dispatchers.Main){
                    watchlistItem.clear()
                    for(watchData in watchList){
                        for(item in result.body()!!.data.cryptoCurrencyList){
                            if(watchData == item.symbol)
                            {
                                watchlistItem.add(item)
                            }
                        }
                    }
                    binding.spinKitView.visibility = GONE
                    binding.watchlistRecyclerView.adapter = MarketAdapter(requireContext(),watchlistItem,"watchlist")
                }
            }
        }


        return binding.root
    }
    private fun readData() {
        val sharedPreferences =
            requireContext().getSharedPreferences("watchlist", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchlist", ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>() {}.type
        watchList = gson.fromJson(json, type)
    }


}