package org.sopt.at.presentation.screen.nickname

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.sopt.at.presentation.screen.my.NicknameChangeViewModel

@Composable
fun NicknameScreen(
    navController: NavController,
    viewModel: NicknameChangeViewModel = viewModel()
) {
    val context = LocalContext.current
    val nickname = viewModel.newNickname
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly
        ) {
        Text("닉네임 변경", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nickname,
            onValueChange = {
                viewModel.newNickname = it
            },
            label = {Text("새 닉네임")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if(errorMessage != null) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                viewModel.changeNickname(context) {
                    navController.popBackStack()
                }
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onBackground)
        ) {
            Text(
                text = "변경하기"
            )
        }
    }

}