package org.sopt.at.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    var id by mutableStateOf("")
    var pw by mutableStateOf("")
    var isIdError by mutableStateOf(false)
    var isPwError by mutableStateOf(false)
    var pwVisible by mutableStateOf(false)
    var screenState by mutableStateOf(SignUpScreenState.ID)

    fun validateId(): Boolean {
        val isLowerOrNum = id.all { it.isLowerCase() || it.isDigit() }
        val validLength = id.length in 6..12
        isIdError = !(isLowerOrNum && validLength)
        return !isIdError
    }

    fun validatePw(): Boolean {
        val isLower = pw.any { it.isLowerCase() }
        val hasDigit = pw.any { it.isDigit() }
        val specialChars = "~!@#\$%^&*"
        val hasChar = pw.any { it in specialChars }
        val validLength = pw.length in 8..15
        isPwError = !(isLower && hasDigit && hasChar && validLength)
        return !isPwError
    }

    fun goToPwScreen() {
        if (validateId()) screenState = SignUpScreenState.PASSWORD
    }

    fun finishSignUp(context: Context) {
        if (validatePw()) {
            val intent = Intent().apply {
                putExtra("id", id)
                putExtra("pw", pw)
            }
            (context as Activity).setResult(Activity.RESULT_OK, intent)
            context.finish()
        } else {
            Toast.makeText(context, "비밀번호 조건에 맞지 않습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

enum class SignUpScreenState {
    ID, PASSWORD
}
