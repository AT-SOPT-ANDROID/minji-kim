package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("HOME", Icons.Default.Home, "home")
    object Shorts : BottomNavItem("SHORTS", Icons.Default.PlayArrow, "shorts")
    object Live : BottomNavItem("LIVE", Icons.Default.LiveTv, "live")
    object Search : BottomNavItem("SEARCH", Icons.Default.Search, "search")
    object History : BottomNavItem("HISTORY", Icons.Default.History, "history")
}
