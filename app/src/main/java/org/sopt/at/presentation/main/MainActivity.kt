package org.sopt.at.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.at.ATSOPTANDROIDTheme
import org.sopt.at.presentation.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val nickname = intent.getStringExtra("nickname") ?: "티빙"
        setContent {
            ATSOPTANDROIDTheme {
                MainScreen(nickname = nickname)

            }
        }
    }
}