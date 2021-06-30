package com.santoshpillai.projectone.ui.state

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppBottomSheetState {
    data class HomeScreenBS(
        val delete: String = "Delete",
        val deleteIcon: ImageVector = Icons.Filled.Delete,
    ): AppBottomSheetState()
}