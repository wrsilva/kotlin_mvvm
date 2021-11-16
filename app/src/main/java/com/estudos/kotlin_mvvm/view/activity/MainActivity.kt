package com.estudos.kotlin_mvvm.view.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.estudos.kotlin_mvvm.R
import com.estudos.kotlin_mvvm.view.adapters.MainAdapter
import com.estudos.kotlin_mvvm.databinding.ActivityMainBinding
import com.estudos.kotlin_mvvm.data.webservice.repositories.MainRepository
import com.estudos.kotlin_mvvm.data.webservice.rest.RetrofitService
import com.estudos.kotlin_mvvm.viewmodel.live.LiveViewModel
import com.estudos.kotlin_mvvm.viewmodel.live.LiveViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: LiveViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter { live ->
        openLink(live.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, LiveViewModelFactory(MainRepository(retrofitService))).get(
                LiveViewModel::class.java
            )

        binding.recyclerview.adapter = adapter

    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setLiveList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()

    }

    private fun openLink(link: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }

}