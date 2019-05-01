package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color


class SubCellGlyph() {

    constructor(topLeft: SubCellData, topRight: SubCellData, bottomLeft: SubCellData, bottomRight: SubCellData): this() {
        subCells[0][0] = topLeft
        subCells[1][0] = topRight
        subCells[0][1] = bottomLeft
        subCells[1][1] = bottomRight
    }

    var resetColor = Color.CLEAR.toFloatBits()
    var defaultTopLeftValue = 257
    var defaultTopRightValue = 258
    var defaultBottomLeftValue = 260
    var defaultBottomRightValue = 259

    val subCells = Array(2) { Array(2) {SubCellData(resetColor, defaultTopLeftValue) }}

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
        subCells[0][0].set(resetColor, defaultTopLeftValue)
        subCells[1][0].set(resetColor, defaultTopRightValue)
        subCells[0][1].set(resetColor, defaultBottomLeftValue)
        subCells[1][1].set(resetColor, defaultBottomRightValue)
    }

    fun copy() : SubCellGlyph {
        val output = SubCellGlyph(subCells[0][0].copy(), subCells[0][1].copy(), subCells[1][0].copy(), subCells[1][1].copy())

        output.resetColor = resetColor
        output.defaultTopLeftValue = defaultTopLeftValue
        output.defaultTopRightValue = defaultTopRightValue
        output.defaultBottomLeftValue = defaultBottomLeftValue
        output.defaultBottomRightValue = defaultBottomRightValue

        return output
    }
}