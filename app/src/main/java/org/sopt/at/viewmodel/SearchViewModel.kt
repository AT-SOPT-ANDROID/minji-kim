package org.sopt.at.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.sopt.at.model.NicknameCheckResponse
import org.sopt.at.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    var nicknameList by mutableStateOf<List<String>>(emptyList())
        private set

    fun searchNickname(keyword: String) {
        RetrofitInstance.userService.checkNickname(keyword)
            .enqueue(object : Callback<NicknameCheckResponse> {
                override fun onResponse(
                    call: Call<NicknameCheckResponse>,
                    response: Response<NicknameCheckResponse>
                ) {
                    if (response.isSuccessful) {
                        nicknameList = response.body()?.data?.nicknameList ?: emptyList()
                    } else {
                        nicknameList = listOf("불러오기 실패")
                    }
                }

                override fun onFailure(call: Call<NicknameCheckResponse>, t: Throwable) {
                    nicknameList = listOf("네트워크 오류")
                }
            })
    }
}
