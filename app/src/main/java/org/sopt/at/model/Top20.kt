package org.sopt.at.model

import androidx.compose.runtime.Immutable

@Immutable
data class Top20(
    val rank: Int,
    val imageId: Int
)