package org.sopt.at.viewmodel

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    var loginId by mutableStateOf("")
    var loginPw by mutableStateOf("")
    var signUpId by mutableStateOf("")
    var signUpPw by mutableStateOf("")
    var loginSuccess by mutableStateOf(false)

    fun checkLogin(context: Context) {
        if (loginId == signUpId && loginPw == signUpPw) {
            loginSuccess = true
        } else {
            Toast.makeText(context, "회원정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }
    fun applySignUpResult(intent: Intent?) {
        signUpId = intent?.getStringExtra("id") ?: ""
        signUpPw = intent?.getStringExtra("pw") ?: ""
    }
}
