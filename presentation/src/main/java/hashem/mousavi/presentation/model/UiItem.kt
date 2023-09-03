package hashem.mousavi.presentation.model

import androidx.compose.ui.graphics.Color

data class UiItem(
    val key: Int, // for LazyColumn animation we need unique key
    val number: Int,
    val color: Color,
    val showStroke: Boolean = false,
    val comparing: Boolean = false
)
