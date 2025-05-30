package org.sopt.at.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("HOME", Icons.Default.Home, "home")
    object Shorts : BottomNavItem("SHORTS", Icons.Default.PlayArrow, "shorts")
    object Live : BottomNavItem("LIVE", Icons.Default.LiveTv, "live")
    object Search : BottomNavItem("SEARCH", Icons.Default.Search, "search")
    object History : BottomNavItem("HISTORY", Icons.Default.History, "history")
}