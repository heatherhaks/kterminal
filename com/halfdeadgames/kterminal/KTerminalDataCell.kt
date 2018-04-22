package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

data class KTerminalDataCell(var data: Char = ' ',
                             var foregroundColor: Color = Color.WHITE,
                             var backgroundColor: Color = Color.BLACK)