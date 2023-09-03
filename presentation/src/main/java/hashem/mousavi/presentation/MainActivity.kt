package hashem.mousavi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hashem.mousavi.presentation.ui.MainScreen
import hashem.mousavi.presentation.ui.theme.ComposeSortVisualizerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSortVisualizerTheme {
                MainScreen(
                    items = viewModel.uiList,
                    buttonEnabled = viewModel.buttonEnabled.value,
                    onSortClick = viewModel::onSortClick
                )
            }
        }
    }
}
