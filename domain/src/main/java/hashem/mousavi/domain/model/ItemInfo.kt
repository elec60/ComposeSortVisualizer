package hashem.mousavi.domain.model

data class ItemInfo(
    val index: Int,
    val swapWithNextItem: Boolean,
    val comparing: Boolean,
    val finished: Boolean = false
)
