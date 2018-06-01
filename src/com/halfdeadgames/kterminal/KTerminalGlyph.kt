package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalGlyph(var char: Char,
                          var foreground: Color,
                          var background: Color,
                          var rotation: Float = 0f,
                          var isFlipped: Boolean = false) {
    fun set(char: Char, foregroundColor: Color, backgroundColor: Color, rotation: Float = 0f, isFlipped: Boolean = false) {
        this.char = char
        this.foreground = foregroundColor.cpy()
        this.background = backgroundColor.cpy()
        this.rotation = rotation
        this.isFlipped = isFlipped
    }

    fun set(glyph: KTerminalGlyph) {
        set(glyph.char, glyph.foreground, glyph.background, glyph.rotation, glyph.isFlipped)
    }

    fun copy() : KTerminalGlyph {
        return KTerminalGlyph(char,  foreground.cpy(), background.cpy(), rotation, isFlipped)
    }
}
