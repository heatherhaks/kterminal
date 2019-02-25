package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalGlyph @JvmOverloads constructor(var value: Int,
                          var foregroundColor: Float,
                          var backgroundColor: Float,
                          var rotation: Float = 0f,
                          var scale: Float = 1f,
                          var offsetX: Float = 0f,
                          var offsetY: Float = 0f,
                          var isFlippedX: Boolean = false,
                          var isFlippedY: Boolean = false) {

    @JvmOverloads constructor(char: Char,
                              foregroundColor: Float,
                              backgroundColor: Float,
                              rotation: Float = 0f,
                              scale: Float = 1f,
                              offsetX: Float = 0f,
                              offsetY: Float = 0f,
                              isFlippedX: Boolean = false,
                              isFlippedY: Boolean = false) : this(char.toInt(), foregroundColor, backgroundColor, rotation, scale, offsetX, offsetY, isFlippedX, isFlippedY)

    @JvmOverloads constructor(topLeftColor: Float,
                topRightColor: Float,
                bottomLeftColor: Float,
                bottomRightColor: Float,
                rotation: Float = 0f,
                scale: Float = 1f,
                offsetX: Float = 0f,
                offsetY: Float = 0f,
                isFlippedX: Boolean = false,
                isFlippedY: Boolean = false) : this(0, Color.CLEAR.toFloatBits(), Color.CLEAR.toFloatBits(), rotation, scale, offsetX, offsetY, isFlippedX, isFlippedY) {
        subCellGlyph.topLeft.color = topLeftColor
        subCellGlyph.topRight.color = topRightColor
        subCellGlyph.bottomLeft.color = bottomLeftColor
        subCellGlyph.bottomRight.color = bottomRightColor
        isSubCellEnabled = true
    }

    var char: Char
        set(input) {
            value = input.toInt()
        }
        get() = value.toChar()

    var isSubCellEnabled = false
    var subCellGlyph = SubCellGlyph()

    @JvmOverloads fun set(value: Int, foregroundColor: Float, backgroundColor: Float, rotation: Float = 0f, scale: Float = 1f, offsetX: Float = 0f, offsetY: Float = 0f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) {
        this.value = value
        this.foregroundColor = foregroundColor
        this.backgroundColor = backgroundColor
        this.rotation = rotation
        this.scale = scale
        this.offsetX = offsetX
        this.offsetY = offsetY
        this.isFlippedX = isFlippedX
        this.isFlippedY = isFlippedY
    }

    @JvmOverloads fun set(char: Char, foregroundColor: Float, backgroundColor: Float, rotation: Float = 0f, scale: Float = 1f, offsetX: Float = 0f, offsetY: Float = 0f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) {
        set(char.toInt(), foregroundColor, backgroundColor, rotation, scale, offsetX, offsetY, isFlippedX, isFlippedY)
    }


    fun set(glyph: KTerminalGlyph) {
        set(glyph.value, glyph.foregroundColor, glyph.backgroundColor, glyph.rotation, glyph.scale, glyph.offsetX, glyph.offsetY, glyph.isFlippedX, glyph.isFlippedY)
    }

    fun copy() : KTerminalGlyph {
        val output = KTerminalGlyph(value, foregroundColor, backgroundColor, rotation, scale, offsetX, offsetY, isFlippedX, isFlippedY)
        output.isSubCellEnabled = isSubCellEnabled
        output.subCellGlyph = subCellGlyph.copy()
        return output
    }
}
