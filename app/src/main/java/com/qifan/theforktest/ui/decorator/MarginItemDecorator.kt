package com.qifan.theforktest.ui.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorator(
    private val space: Space,
    private val decorator: Position = Position.ALL
) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            when (decorator) {
                Position.VERTICAL, Position.ALL -> {
                    top = space.top
                    bottom = space.bottom
                }
                Position.HORIZONTAL -> {
                    left = space.left
                    right = space.right
                }
            }
        }
    }

    data class Space(
        val space: Int,
        val top: Int = space,
        val right: Int = space,
        val bottom: Int = space,
        val left: Int = space
    )

    enum class Position {
        VERTICAL,
        HORIZONTAL,
        ALL
    }

}