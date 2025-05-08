package org.sopt.at

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.viewmodel.SignInViewModel
import org.sopt.at.viewmodel.SignUpViewModel

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                val signInViewModel: SignInViewModel = viewModel()
                val signUpViewModel: SignUpViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    Tving(signInViewModel = signInViewModel, signUpViewModel = signUpViewModel, modifier = Modifier.padding(padding))
                }
            }
        }
    }
}


@Composable
fun Tving(signInViewModel: SignInViewModel = viewModel(), signUpViewModel: SignUpViewModel = viewModel(),  modifier: Modifier) {
    val context = LocalContext.current
    val loginId = signInViewModel.loginId
    val loginPw = signInViewModel.loginPw
    val loginSuccess = signInViewModel.loginSuccess
    var pwVisible by remember { mutableStateOf(false) }
    val nickName = signUpViewModel.nickName

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            val intent = Intent(context, MyActivity::class.java)
            intent.putExtra("nickName", nickName)
            context.startActivity(intent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 30.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start

    ){
        Icon(
            modifier = Modifier
                .size(35.dp),
            imageVector = Icons.Default.ChevronLeft,
            contentDescription = null,
            tint = Color.White,

        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "TVING ID 로그인",
            style = TextStyle(
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(28.dp))

        TextField(
            value = loginId,
            onValueChange = { signInViewModel.loginId = it },
            placeholder = { Text("아이디", color = Color(0xFFAAAAAA)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2C2C2C),
                unfocusedContainerColor = Color(0xFF2C2C2C),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        Spacer(modifier = Modifier.height(13.dp))
        TextField(
            value = loginPw,
            onValueChange = { signInViewModel.loginPw = it },
            visualTransformation = if (pwVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (pwVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { pwVisible = !pwVisible }) {
                    Icon(imageVector = icon, contentDescription = null, tint = Color.LightGray)
                }
            },
            placeholder = { Text("비밀번호", color = Color(0xFFAAAAAA)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF2C2C2C),
                unfocusedContainerColor = Color(0xFF2C2C2C),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(22.dp))

        Button(
            onClick = { signInViewModel.signIn(context) },
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF444444))
        ) {
            Text("로그인하기", color = Color(0xFFAAAAAA))
        }


        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ){
            Text("아이디 찾기",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.LightGray,
                ))
            Spacer(modifier = Modifier.width(10.dp))
            Text("|",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF444444),
                    fontWeight = FontWeight.Bold
                ))
            Spacer(modifier = Modifier.width(10.dp))
            Text("비밀번호 찾기",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.LightGray,

                ))
            Spacer(modifier = Modifier.width(10.dp))
            Text("|",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0xFF444444),
                    fontWeight = FontWeight.Bold

                ))
            Spacer(modifier = Modifier.width(10.dp))
            Text("회원가입", color = Color.LightGray, modifier = Modifier.clickable {
                context.startActivity(Intent(context, SignUpActivity::class.java))
            })
        }
        Spacer(modifier = Modifier.height(25.dp))

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                buildAnnotatedString {
                    append("이 사이트는 Google reCAPTCHA로 보호되며, ")

                },
                color = Color.Gray,
                fontSize = 11.sp,
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Google 개인정보 처리방침")
                    }
                    append("과 ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("서비스 약관")
                    }
                    append("이 적용됩니다.")
                },
                color = Color.Gray,
                fontSize = 11.sp,

            )

        }
    }
}
