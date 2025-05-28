package org.sopt.at.presentation.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var input by mutableStateOf("")
        private set

    var nicknameList by mutableStateOf<List<String>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onInputChange(newInput: String) {
        input = newInput
        viewModelScope.launch {
            searchNickname(newInput)
        }
    }

    fun searchNickname(keyword: String) {
        viewModelScope.launch {
            val result = userRepository.getNicknames(keyword)
            if (result.isSuccess) {
                val dto = result.getOrNull()
                nicknameList = dto?.nicknameList ?: emptyList()
                errorMessage = null
            } else {
                nicknameList = emptyList()
                errorMessage = result.exceptionOrNull()?.message
            }
        }
    }

}