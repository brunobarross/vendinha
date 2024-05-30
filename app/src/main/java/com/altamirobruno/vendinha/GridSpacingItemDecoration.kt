package com.altamirobruno.vendinha
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        // Add item offset to left, right, top and bottom
        if (includeEdge) {
            outRect.apply {
                left = spacing - (position % spanCount) * spacing / spanCount
                right = (position % spanCount + 1) * spacing / spanCount
                if (position < spanCount) { // Top edge
                    top = spacing
                }
                bottom = if (position >= spanCount) spacing else 0 // Item is below top edge
            }
        } else {
            outRect.apply {
                left = position % spanCount * spacing / spanCount
                right = spacing - (position % spanCount + 1) * spacing / spanCount
                if (position >= spanCount) {
                    top = spacing
                }
                bottom = 0
            }
        }
    }
}
