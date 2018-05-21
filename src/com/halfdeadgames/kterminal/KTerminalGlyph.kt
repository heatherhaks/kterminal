package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalGlyph(var char: Char,
                          var foreground: Color,
                          var background: Color) {
    fun set(char: Char, foregroundColor: Color, backgroundColor: Color) {
        this.char = char
        this.foreground = foregroundColor
        this.background = backgroundColor
    }

    fun set(glyph: KTerminalGlyph) {
        set(glyph.char, glyph.foreground, glyph.background)
    }
}
