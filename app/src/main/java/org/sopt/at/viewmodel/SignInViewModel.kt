package org.sopt.at.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.sopt.at.model.SignInRequest
import org.sopt.at.model.SignInResponse
import org.sopt.at.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    var loginId by mutableStateOf("")
    var loginPw by mutableStateOf("")
    var loginSuccess by mutableStateOf(false)

    fun signIn(context: Context) {
        val request = SignInRequest(loginId, loginPw)

        RetrofitInstance.authService.signIn(request)
            .enqueue(object : Callback<SignInResponse> {
                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body?.success == true) {
                            loginSuccess = true
                            Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, body?.message ?: "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "HTTP 오류: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    Toast.makeText(context, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
