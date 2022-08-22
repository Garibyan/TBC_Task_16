package com.garibyan.armen.tbc_task_16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_task_16.adapter.LoaderStateAdapter
import com.garibyan.armen.tbc_task_16.adapter.RvAdapter
import com.garibyan.armen.tbc_task_16.databinding.ActivityMainBinding
import com.garibyan.armen.tbc_task_16.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val rvAdapter: RvAdapter by lazy { RvAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView(){
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvAdapter.withLoadStateHeaderAndFooter(
                header = LoaderStateAdapter{rvAdapter :: retry},
                footer = LoaderStateAdapter{rvAdapter :: retry}
            )
            setHasFixedSize(true)
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.listData.collectLatest {
                binding.apply {
                    progressBar.isVisible=false
                    recyclerview.isVisible=true
                }
                rvAdapter.submitData(it)
            }
        }
    }
}