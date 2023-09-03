package hashem.mousavi.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hashem.mousavi.presentation.model.UiItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    items: List<UiItem>,
    buttonEnabled: Boolean,
    onSortClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onSortClick, enabled = buttonEnabled) {
            Text(text = "Generate List and Sort", fontSize = 20.sp)
        }
        var itemHeight by remember {
            mutableStateOf(0.dp)
        }
        val density = LocalDensity.current
        if (items.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .onSizeChanged {
                        itemHeight = with(density) { (it.height / items.size - 10).toDp() }
                    },
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = items, key = { it.key }) { item ->
                    ItemUI(
                        item = item,
                        itemHeight = itemHeight,
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            }
        }
    }
}

@Composable
fun ItemUI(item: UiItem, itemHeight: Dp, modifier: Modifier) {
    val stroke = if (item.comparing) {
        BorderStroke(width = 4.dp, color = Color.Red)
    } else {
        BorderStroke(width = 0.dp, color = Color.Transparent)
    }
    Text(
        text = item.number.toString(),
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(shape = CircleShape)
            .size(itemHeight)
            .border(border = stroke, shape = CircleShape)
            .background(color = item.color, shape = CircleShape)
            .wrapContentHeight()
    )
}

