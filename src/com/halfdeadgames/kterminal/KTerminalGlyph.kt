package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalGlyph(var char: Char,
                          var foregroundColor: Color,
                          var backgroundColor: Color) {
    fun set(char: Char, foregroundColor: Color, backgroundColor: Color) {
        this.char = char
        this.foregroundColor = foregroundColor
        this.backgroundColor = backgroundColor
    }
}
