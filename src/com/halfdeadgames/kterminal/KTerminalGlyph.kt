package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalGlyph(var char: Char,
                          var foreground: Color,
                          var background: Color,
                          var rotation: Float = 0f) {
    fun set(char: Char, foregroundColor: Color, backgroundColor: Color, rotation: Float = 0f) {
        this.char = char
        this.foreground = foregroundColor.cpy()
        this.background = backgroundColor.cpy()
        this.rotation = rotation
    }

    fun set(glyph: KTerminalGlyph) {
        set(glyph.char, glyph.foreground, glyph.background, glyph.rotation)
    }

    fun copy() : KTerminalGlyph {
        return KTerminalGlyph(char,  foreground.cpy(), background.cpy(), rotation)
    }
}
