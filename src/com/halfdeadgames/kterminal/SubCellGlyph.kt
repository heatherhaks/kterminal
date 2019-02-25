package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color


class SubCellGlyph() {

    constructor(topLeft: SubCellData, topRight: SubCellData, bottomLeft: SubCellData, bottomRight: SubCellData): this() {
        this.topLeft = topLeft
        this.topRight = topRight
        this.bottomLeft = bottomLeft
        this.bottomRight = bottomRight
    }

    var resetColor = Color.CLEAR.toFloatBits()
    var defaultTopLeftValue = 257
    var defaultTopRightValue = 258
    var defaultBottomLeftValue = 260
    var defaultBottomRightValue = 259

    var topLeft: SubCellData = SubCellData(resetColor, defaultTopLeftValue)
    var topRight: SubCellData = SubCellData(resetColor, defaultTopRightValue)
    var bottomLeft: SubCellData = SubCellData(resetColor, defaultBottomLeftValue)
    var bottomRight: SubCellData = SubCellData(resetColor, defaultBottomRightValue)

    inner class SubCellData(var color: Float = Color.CLEAR.toFloatBits(), var value: Int = 0) {
        fun set(color: Float, value: Int) {
            this.color = color
            this.value = value
        }

        fun copy() : SubCellData {
            return SubCellData(color, value)
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

        output.resetColor = resetColor
        output.defaultTopLeftValue = defaultTopLeftValue
        output.defaultTopRightValue = defaultTopRightValue
        output.defaultBottomLeftValue = defaultBottomLeftValue
        output.defaultBottomRightValue = defaultBottomRightValue

        return output
    }
}