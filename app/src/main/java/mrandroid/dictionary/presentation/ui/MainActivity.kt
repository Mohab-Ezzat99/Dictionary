package mrandroid.dictionary.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import mrandroid.dictionary.R
import mrandroid.dictionary.databinding.ActivityMainBinding
import mrandroid.dictionary.util.Constants.TAG
import mrandroid.dictionary.util.showToast
import mrandroid.dictionary.util.state.UiState

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoadingDialog.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivSearch.setOnClickListener {
                viewModel.searchWord(etSearch.text.toString())
            }
        }
        fetchWordState()
    }

    private fun fetchWordState() {
        lifecycleScope.launchWhenCreated {
            viewModel.wordState.collect {
                when (it) {
                    is UiState.Loading -> LoadingDialog.showDialog()
                    is UiState.Error -> {
                        LoadingDialog.dismissDialog()
                        binding.tvResult.text = it.data?.get(0)?.word
                    }
                    is UiState.Success -> {
                        LoadingDialog.dismissDialog()
                        binding.tvResult.text = it.data?.get(0)?.word
                    }
                    else -> Unit
                }
            }
        }
    }
}