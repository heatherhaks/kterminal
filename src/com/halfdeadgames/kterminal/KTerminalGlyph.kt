package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalGlyph @JvmOverloads constructor(var value: Int = 0,
                          var foregroundColor: Float = Color.CLEAR.toFloatBits(),
                          var backgroundColor: Float = Color.CLEAR.toFloatBits(),
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
        subCellGlyph.subCells[0][0].color = topLeftColor
        subCellGlyph.subCells[1][0].color = topRightColor
        subCellGlyph.subCells[0][1].color = bottomLeftColor
        subCellGlyph.subCells[1][1].color = bottomRightColor

        isSubCellEnabled = true
    }

    var char: Char
        set(input) {
            value = input.toInt()
        }
        get() = value.toChar()

    var isSubCellEnabled = false
    var subCellGlyph = SubCellGlyph()

    @JvmOverloads fun set(value: Int, foregroundColor: Float, backgroundColor: Float, rotation: Float = 0f, scale: Float = 1f, offsetX: Float = 0f, offsetY: Float = 0f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) : KTerminalGlyph{
        this.value = value
        this.foregroundColor = foregroundColor
        this.backgroundColor = backgroundColor
        this.rotation = rotation
        this.scale = scale
        this.offsetX = offsetX
        this.offsetY = offsetY
        this.isFlippedX = isFlippedX
        this.isFlippedY = isFlippedY
        
        return this
    }

    @JvmOverloads fun set(char: Char, foregroundColor: Float, backgroundColor: Float, rotation: Float = 0f, scale: Float = 1f, offsetX: Float = 0f, offsetY: Float = 0f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) : KTerminalGlyph {
        return set(char.toInt(), foregroundColor, backgroundColor, rotation, scale, offsetX, offsetY, isFlippedX, isFlippedY)
    }


    fun set(glyph: KTerminalGlyph) : KTerminalGlyph {
        return set(glyph.value, glyph.foregroundColor, glyph.backgroundColor, glyph.rotation, glyph.scale, glyph.offsetX, glyph.offsetY, glyph.isFlippedX, glyph.isFlippedY)
    }

    fun copy() : KTerminalGlyph {
        val output = KTerminalGlyph(value, foregroundColor, backgroundColor, rotation, scale, offsetX, offsetY, isFlippedX, isFlippedY)
        output.isSubCellEnabled = isSubCellEnabled
        output.subCellGlyph = subCellGlyph.copy()
        return output
    }
  
    fun reset() : KTerminalGlyph {
        value = 0
        foregroundColor = Color.CLEAR.toFloatBits()
        backgroundColor = Color.CLEAR.toFloatBits()
        rotation = 0f
        scale = 1f
        offsetX = 0f
        offsetY = 0f
        isFlippedX = false
        isFlippedY = false
        isSubCellEnabled = false
        subCellGlyph = SubCellGlyph()
        
        return this
    }
}
