package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalGlyph @JvmOverloads constructor(var char: Char,
                          var foreground: Color,
                          var background: Color,
                          var rotation: Float = 0f,
                          var scale: Float = 1f,
                          var isFlippedX: Boolean = false,
                          var isFlippedY: Boolean = false) {

    @JvmOverloads fun set(char: Char, foregroundColor: Color, backgroundColor: Color, rotation: Float = 0f, scale: Float = 1f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) {
        this.char = char
        this.foreground = foregroundColor.cpy()
        this.background = backgroundColor.cpy()
        this.rotation = rotation
        this.scale = scale
        this.isFlippedX = isFlippedX
        this.isFlippedY = isFlippedY
    }

    fun set(glyph: KTerminalGlyph) {
        set(glyph.char, glyph.foreground, glyph.background, glyph.rotation, glyph.scale, glyph.isFlippedX, glyph.isFlippedY)
    }

    fun copy() : KTerminalGlyph {
        return KTerminalGlyph(char,  foreground.cpy(), background.cpy(), rotation, scale, isFlippedX, isFlippedY)
    }
}
