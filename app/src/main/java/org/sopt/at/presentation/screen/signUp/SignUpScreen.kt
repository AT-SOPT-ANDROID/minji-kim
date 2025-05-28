package org.sopt.at.presentation.screen.signUp

import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.at.ATSOPTANDROIDTheme



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
                    .background(MaterialTheme.colorScheme.background),
                textStyle = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "영문 소문자 또는 영문 소문자, 숫자 조합 6~12 자리",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Button(onClick = onNext, modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(4.dp)
            ),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)) {
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
    onNext2: () -> Unit
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
        Button(onClick = onNext2,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(4.dp)
                ),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)) {
            Text("다음", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}
@Composable
fun SignUpNicknameScreen(
    nickName: String,
    isError: Boolean,
    onNickNameChange: (String) -> Unit,
    onComplete: () -> Unit
){
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                "닉네임을 입력해주세요.",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = nickName,
                onValueChange = onNickNameChange,
                isError = isError,
                placeholder = { Text("닉네임", color = MaterialTheme.colorScheme.onSurfaceVariant) },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
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
            Text("회원가입 완료", color = MaterialTheme.colorScheme.onBackground)
        }
    }


}
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewSignUpIdScreen() {
    ATSOPTANDROIDTheme {
        SignUpPwScreen(
            pw = "exampleId",
            isError = false,
            pwVisible = false,
            onPwChange = {},
            onTogglePwVisible = {},
            onNext2 = {}
        )
    }
}

