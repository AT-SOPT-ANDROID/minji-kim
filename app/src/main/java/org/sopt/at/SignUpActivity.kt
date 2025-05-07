package org.sopt.at

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
// ✅ 올바른 import
import androidx.compose.material3.OutlinedTextField


import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.viewmodel.SignUpScreenState
import org.sopt.at.viewmodel.SignUpViewModel

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                val viewModel: SignUpViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUp(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun SignUp(viewModel: SignUpViewModel = viewModel(), modifier: Modifier = Modifier) {
    val context = LocalContext.current

    when (viewModel.screenState) {
        SignUpScreenState.ID -> {
            SignUpIdScreen(
                id = viewModel.id,
                isError = viewModel.isIdError,
                onIdChange = { viewModel.id = it },
                onNext = {
                    if (!viewModel.validateId()) {
                        Toast.makeText(context, "아이디 조건에 맞지 않습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.goToPwScreen()
                    }
                }
            )
        }
        SignUpScreenState.PASSWORD -> {
            SignUpPwScreen(
                pw = viewModel.pw,
                isError = viewModel.isPwError,
                pwVisible = viewModel.pwVisible,
                onPwChange = { viewModel.pw = it },
                onTogglePwVisible = { viewModel.pwVisible = !viewModel.pwVisible },
                onComplete = { viewModel.finishSignUp(context) }
            )
        }
    }
}

@Composable
fun SignUpIdScreen(
    id: String,
    isError: Boolean,
    onIdChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column() {
            Text(
                "아이디를 입력해주세요.",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = id,
                onValueChange = onIdChange,
                isError = isError,
                placeholder = { Text("아이디", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onSurfaceVariant),
                textStyle = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "영문 소문자 또는 영문 소문자, 숫자 조합 6~12 자리",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurfaceVariant)) {
            Text("다음", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@Composable
fun SignUpPwScreen(
    pw: String,
    isError: Boolean,
    pwVisible: Boolean,
    onPwChange: (String) -> Unit,
    onTogglePwVisible: () -> Unit,
    onComplete: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                "비밀번호를 입력해주세요.",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = pw,
                onValueChange = onPwChange,
                isError = isError,
                visualTransformation = if (pwVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = onTogglePwVisible) {
                        Icon(
                            imageVector = if (pwVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                placeholder = { Text("비밀번호", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onComplete,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(4.dp)
                ),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)) {
            Text("회원가입 완료", color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewSignUpIdScreen() {
    org.sopt.at.screen.ui.theme.ATSOPTANDROIDTheme {
        SignUpPwScreen(
            pw = "exampleId",
            isError = false,
            pwVisible = false,
            onPwChange = {},
            onTogglePwVisible = {},
            onComplete = {}
        )
    }
}

