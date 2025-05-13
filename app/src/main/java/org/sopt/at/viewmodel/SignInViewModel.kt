package org.sopt.at.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import org.sopt.at.MainActivity
import org.sopt.at.model.SignInRequest
import org.sopt.at.model.SignInResponse
import org.sopt.at.model.UserInfoResponse
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
                override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        val userId = response.body()?.data?.userId
                        if (userId != null) {
                            val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
                            prefs.edit().putInt("userId", userId).apply()

                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                            if (context is Activity) context.finish()
                        }
                    }

                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    Toast.makeText(context, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }


    }
