package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color
import ktx.graphics.copy


class SubCellGlyph() {

    constructor(topLeft: SubCellData, topRight: SubCellData, bottomLeft: SubCellData, bottomRight: SubCellData): this() {
        this.topLeft = topLeft
        this.topRight = topRight
        this.bottomLeft = bottomLeft
        this.bottomRight = bottomRight
    }

    var resetColor: Color = Color.CLEAR.copy()
    var defaultTopLeftValue = 257
    var defaultTopRightValue = 258
    var defaultBottomLeftValue = 260
    var defaultBottomRightValue = 259

    var topLeft: SubCellData = SubCellData(resetColor, defaultTopLeftValue)
    var topRight: SubCellData = SubCellData(resetColor, defaultTopRightValue)
    var bottomLeft: SubCellData = SubCellData(resetColor, defaultBottomLeftValue)
    var bottomRight: SubCellData = SubCellData(resetColor, defaultBottomRightValue)

    inner class SubCellData(var color: Color = Color.CLEAR.copy(), var value: Int = 0) {
        fun set(color: Color, value: Int) {
            this.color = color
            this.value = value
        }

        fun copy() : SubCellData {
            return SubCellData(color.copy(), value)
        }
    }

    fun reset() {
        topLeft.set(resetColor, defaultTopLeftValue)
        topRight.set(resetColor, defaultTopRightValue)
        bottomLeft.set(resetColor, defaultBottomLeftValue)
        bottomRight.set(resetColor, defaultBottomRightValue)
    }

    fun copy() : SubCellGlyph {
        val output = SubCellGlyph(topLeft.copy(), topRight.copy(), bottomLeft.copy(), bottomRight.copy())

        output.resetColor = resetColor.copy()
        output.defaultTopLeftValue = defaultTopLeftValue
        output.defaultTopRightValue = defaultTopRightValue
        output.defaultBottomLeftValue = defaultBottomLeftValue
        output.defaultBottomRightValue = defaultBottomRightValue

        return output
    }
}