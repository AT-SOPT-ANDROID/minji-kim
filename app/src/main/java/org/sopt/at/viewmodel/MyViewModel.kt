package org.sopt.at.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.sopt.at.model.UserInfoResponse
import org.sopt.at.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    var nickname by mutableStateOf("")
        private set

    fun fetchNickname(context: Context) {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        val userId = prefs.getInt("userId", -1).toLong()

        RetrofitInstance.authService.getUserInfo(userId)
            .enqueue(object : Callback<UserInfoResponse> {
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    nickname = if (response.isSuccessful) {
                        response.body()?.data?.nickname ?: "알 수 없음"
                    } else {
                        "불러오기 실패"
                    } }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    nickname = "네트워크 오류"
                }
            })
    }
}
