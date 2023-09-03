package hashem.mousavi.domain.usecase

import hashem.mousavi.domain.model.ItemInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SortUseCase {
    operator fun invoke(items: List<Int>): Flow<ItemInfo> = flow {
        val mutableList = items.toMutableList()
        var size = mutableList.size - 1
        while (size > 1) {
            var index = 0
            while (index < size) {
                val currentNumber = mutableList[index]
                val nextNumber = mutableList[index + 1]
                val itemInfo = ItemInfo(
                    index = index,
                    swapWithNextItem = false,
                    comparing = true
                )
                emit(itemInfo)
                delay(600)
                if (currentNumber > nextNumber) {
                    mutableList.swap(index, index + 1)
                    emit(
                        itemInfo.copy(
                            swapWithNextItem = true,
                            comparing = false
                        )
                    )
                }
                delay(300)
                emit(
                    itemInfo.copy(
                        swapWithNextItem = false,
                        comparing = false
                    )
                )
                delay(600)
                index++
            }
            size--
        }
        emit(
            ItemInfo(
                index = -1,
                swapWithNextItem = false,
                comparing = false,
                finished = true
            )
        )
    }
}

private fun <E> MutableList<E>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}
