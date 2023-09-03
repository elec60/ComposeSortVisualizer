package hashem.mousavi.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hashem.mousavi.domain.usecase.SortUseCase
import hashem.mousavi.presentation.model.UiItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val sortUseCase: SortUseCase,
) : ViewModel() {

    private var job: Job? = null

    private val _uiList = mutableStateListOf<UiItem>()
    val uiList: MutableList<UiItem> = _uiList

    private val _buttonEnabled = mutableStateOf(true)
    val buttonEnabled: State<Boolean> = _buttonEnabled


    private fun generateData() {
        _uiList.clear()
        for (i in 0 until 6) {
            _uiList.add(
                UiItem(
                    key = i,
                    number = (1..100).random(),
                    color = Color(
                        red = (50..100).random(),
                        green = (0..200).random(),
                        blue = (0..200).random(),
                    )
                )
            )
        }
    }


    fun onSortClick() {
        job?.cancel()
        job = viewModelScope.launch {
            _buttonEnabled.value = false
            generateData()
            var previousIndex = -1
            sortUseCase(uiList.map { it.number }).collect { itemInfo ->
                if (itemInfo.finished) {
                    val newList = _uiList.map { it.copy(comparing = false, showStroke = false) }
                    _uiList.clear()
                    _uiList.addAll(newList)

                    _buttonEnabled.value = true

                    return@collect
                }
                if (previousIndex != -1) {
                    _uiList[previousIndex] = _uiList[previousIndex].copy(comparing = false)
                    _uiList[previousIndex + 1] = _uiList[previousIndex + 1].copy(comparing = false)
                }
                val currIndex = itemInfo.index
                _uiList[currIndex] = _uiList[currIndex].copy(comparing = itemInfo.comparing)
                _uiList[currIndex + 1] = _uiList[currIndex + 1].copy(comparing = itemInfo.comparing)

                if (itemInfo.swapWithNextItem) {
                    val item1 = _uiList[currIndex].copy(comparing = itemInfo.comparing)
                    _uiList[currIndex] = _uiList[currIndex + 1].copy(comparing = itemInfo.comparing)
                    _uiList[currIndex + 1] = item1
                }
                previousIndex = currIndex
            }
        }
    }

}