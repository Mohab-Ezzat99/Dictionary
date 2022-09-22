package mrandroid.dictionary.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mrandroid.dictionary.domain.model.WordModel
import mrandroid.dictionary.domain.repository.WordRepository
import mrandroid.dictionary.util.state.Resource
import mrandroid.dictionary.util.state.UiState
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _wordState = MutableStateFlow<UiState<List<WordModel>>?>(null)
    val wordState: StateFlow<UiState<List<WordModel>>?> = _wordState

    private var wordJob: Job? = null

    fun cancelRequest() {
        wordJob?.cancel()
    }

    fun searchWord(word: String) {
        if(word.isEmpty()) return
        wordJob?.cancel()
        wordJob = viewModelScope.launch {
            withContext(coroutineContext) {
                wordRepository.getWordList(word).collect {
                    when (it) {
                        is Resource.Loading -> _wordState.value = UiState.Loading()
                        is Resource.Error -> _wordState.value = UiState.Error(it.message!!)
                        is Resource.Success -> {
                            _wordState.value = UiState.Success(it.data)
                        }
                    }
                }
            }
        }
    }

}