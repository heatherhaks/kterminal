package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color

class KTerminalData(width: Int,
                    height: Int,
                    var defaultForeground: Color = Color.WHITE.cpy(),
                    var defaultBackground: Color = Color.BLACK.cpy()
) {
    var width: Int = width
        set(value) {
            if(value > 0) {
                field = value
            } else {
                throw IllegalArgumentException("Width can't be 0 or below.")
            }
        }
    var height: Int = height
        set(value) {
            if(value > 0) {
                field = value
            } else {
                throw IllegalArgumentException("Height can't be 0 or below.")
            }
        }

    var terminal = Array(width, {
        Array(height, {
            KTerminalGlyph(' ', defaultForeground, defaultBackground)
        })
    })

    //will not preserve terminal data
    fun resize(width: Int, height: Int) {
        this.width = width
        this.height = height

        terminal = Array(width, {
            Array(height, {
                KTerminalGlyph(' ', defaultForeground, defaultBackground)
            })
        })
    }

    inner class Cursor(x: Int, y: Int, var foregroundColor: Color, var backgroundColor: Color, var rotation: Float, var scale: Float, var isFlippedX: Boolean, var isFlippedY: Boolean) {
        var x: Int = x
            set(value) {
                var tempValue = value

                while(tempValue < 0 || tempValue >= width) {
                    if(tempValue < 0) {
                        tempValue += width
                    } else if(tempValue >= width) {
                        tempValue -= width
                    }
                }

                field = tempValue
            }
        var y: Int = y
            set(value) {
                var tempValue = value

                while(tempValue < 0 || tempValue >= height) {
                    if(tempValue < 0) {
                        tempValue += height
                    } else if(tempValue >= height) {
                        tempValue -= height
                    }
                }

                field = tempValue
            }

        fun set(x: Int, y: Int, foregroundColor: Color, backgroundColor: Color, rotation: Float, scale: Float, isFlippedX: Boolean, isFlippedY: Boolean) {
            this.x = x
            this.y = y
            this.foregroundColor = foregroundColor.cpy()
            this.backgroundColor = backgroundColor.cpy()
            this.rotation = rotation
            this.scale = scale
            this.isFlippedX = isFlippedX
            this.isFlippedY = isFlippedY
        }
        fun set(x: Int, y: Int) {
            this.x = x
            this.y = y
        }
        fun set(cursor: Cursor) {
            set(cursor.x, cursor.y, cursor.foregroundColor, cursor.backgroundColor, cursor.rotation, cursor.scale, cursor.isFlippedX, cursor.isFlippedY)
        }
    }
    val cursor: Cursor = Cursor(0, 0, defaultForeground, defaultBackground, 0f, 1f, false, false)
    val workingCursor: Cursor = Cursor(0, 0, defaultForeground, defaultBackground, 0f, 1f, false, false)

    @JvmOverloads fun setCursor(x: Int = 0, y: Int = 0, foregroundColor: Color = defaultForeground, backgroundColor: Color = defaultBackground, rotation: Float = 0f, scale: Float = 1f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) {
        cursor.set(x, y, foregroundColor, backgroundColor, rotation, scale,  isFlippedX, isFlippedY)
    }
    fun resetCursor() {
        cursor.set(0, 0, defaultForeground, defaultBackground, 0f, 1f, false, false)
    }

    operator fun get(x: Int, y: Int) : KTerminalData {
        cursor.x = x
        cursor.y = y
        return this
    }
    operator fun get(foregroundColor: Color, backgroundColor: Color) : KTerminalData {
        cursor.foregroundColor = foregroundColor
        cursor.backgroundColor = backgroundColor
        return this
    }
    operator fun get(foregroundColor: Color) : KTerminalData {
        cursor.foregroundColor = foregroundColor
        return this
    }
    operator fun get(rotation: Float, scale: Float) : KTerminalData {
        cursor.rotation = rotation
        cursor.scale = scale
        return this
    }
    operator fun get(isFlippedX: Boolean, isFlippedY: Boolean) : KTerminalData {
        cursor.isFlippedX = isFlippedX
        cursor.isFlippedY = isFlippedY
        return this
    }

    //override any color data given by the cursor
    @JvmOverloads fun write(glyph: KTerminalGlyph, cursor: Cursor? = null) {
        if(cursor == null) {
            workingCursor.set(this.cursor)
        } else {
            workingCursor.set(cursor)
        }

        workingCursor.foregroundColor = glyph.foreground
        workingCursor.backgroundColor = glyph.background
        workingCursor.rotation = glyph.rotation
        workingCursor.scale = glyph.scale
        workingCursor.isFlippedX = glyph.isFlippedX
        workingCursor.isFlippedY = glyph.isFlippedY

        write(glyph.char, workingCursor)
    }

    @JvmOverloads fun write(char: Char, cursor: Cursor? = null) {
        if(cursor == null) {
            workingCursor.set(this.cursor)
        } else {
            workingCursor.set(cursor)
        }

        terminal[workingCursor.x][workingCursor.y].apply {
            this.char = char
            this.foreground = workingCursor.foregroundColor
            this.background = workingCursor.backgroundColor
            this.rotation = workingCursor.rotation
            this.scale = workingCursor.scale
            this.isFlippedX = workingCursor.isFlippedX
            this.isFlippedY = workingCursor.isFlippedY
        }
    }

    @JvmOverloads fun write(string: String, cursor: Cursor? = null) {
        if(cursor == null) {
            workingCursor.set(this.cursor)
        } else {
            workingCursor.set(cursor)
        }

        string.toCharArray().forEach {
            write(it, workingCursor)
            if(workingCursor.x == width - 1) workingCursor.y++
            workingCursor.x++
        }
    }

    @JvmOverloads fun clear(width: Int = 1, height: Int = 1, cursor: Cursor? = null) {
        if(cursor == null) {
            workingCursor.set(this.cursor)
        } else {
            workingCursor.set(cursor)
        }

        workingCursor.foregroundColor = defaultForeground
        workingCursor.backgroundColor = defaultBackground
        workingCursor.rotation = 0f
        workingCursor.scale = 1f

        drawRect(width, height, ' ', true, workingCursor)
    }

    fun clearAll() {
        workingCursor.set(0, 0, defaultForeground, defaultBackground, 0f, 1f, false, false)
        clear(width, height, workingCursor)
    }

    @JvmOverloads fun drawRect(width: Int,
                 height: Int,
                 char: Char = ' ',
                 isFilled: Boolean = true,
                 cursor: Cursor? = null) {
        if(cursor == null) {
            workingCursor.set(this.cursor)
        } else {
            workingCursor.set(cursor)
        }

        val startX = workingCursor.x
        val startY = workingCursor.y

        for(i in startX until startX + width) {
            for(j in startY until startY + height) {
                workingCursor.x = i
                workingCursor.y = j
                if(isFilled) {
                    write(char, workingCursor)
                } else {
                    if(i == startX || i == startX + width - 1) {
                        write(char, workingCursor)
                    } else if(j == startY || j == startY + height - 1) {
                        write(char, workingCursor)
                    }
                }
            }
        }
    }

    @JvmOverloads fun drawLine(endX: Int, endY: Int, char: Char, cursor: Cursor? = null) {
        if(cursor == null) {
            workingCursor.set(this.cursor)
        } else {
            workingCursor.set(cursor)
        }

        fun plotLine(x0: Int, y0: Int, x1: Int, y1: Int) :  List<Pair<Int, Int>> {
            val output: MutableList<Pair<Int, Int>> = mutableListOf(Pair(x0, y0), Pair(x1, y1))

            fun plotLineLow(x0: Int, y0: Int, x1: Int, y1: Int){
                val dx = x1 - x0
                var dy = y1 - y0
                var yi = 1

                if(dy < 0) {
                    yi = -1
                    dy = -dy
                }
                var d = 2*dy - dx
                var y = y0

                for(x in x0 until x1) {
                    output.add(Pair(x, y))
                    if(d > 0) {
                        y += yi
                        d -= 2*dx
                    }
                    d += 2*dy
                }
            }

            fun plotLineHigh(x0: Int, y0: Int, x1: Int, y1: Int) {
                var dx = x1 - x0
                val dy = y1 - y0
                var xi = 1

                if(dx < 0) {
                    xi = -1
                    dx = -dx
                }
                var d = 2*dx - dy
                var x = x0

                for(y in y0 until y1) {
                    output.add(Pair(x, y))
                    if(d > 0) {
                        x += xi
                        d -= 2*dy
                    }
                    d += 2*dx
                }
            }

            if(Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
                if (x0 > x1) {
                    plotLineLow(x1, y1, x0, y0)
                } else {
                    plotLineLow(x0, y0, x1, y1)
                }
            } else {
                if(y0 > y1) {
                    plotLineHigh(x1, y1, x0, y0)
                } else {
                    plotLineHigh(x0, y0, x1, y1)
                }
            }

            return output.toList()
        }

        val linePlot = plotLine(workingCursor.x, workingCursor.y, endX, endY).toList()

        linePlot.forEach{
            workingCursor.x = it.first
            workingCursor.y = it.second
            write(char, workingCursor)
        }
    }

    @JvmOverloads fun drawBox(width: Int, height: Int, topLeft: Char, topRight: Char, bottomLeft: Char, bottomRight: Char, horizontal: Char, vertical: Char, cursor: Cursor? = null) {
        if(cursor == null) {
            workingCursor.set(this.cursor)
        } else {
            workingCursor.set(cursor)
        }
        val startX = workingCursor.x
        val startY = workingCursor.y

        workingCursor.set(startX + 1, startY)
        drawLine(startX + width - 2, startY, horizontal, workingCursor)
        workingCursor.set(startX + 1, startY + height - 1)
        drawLine(startX + width - 2, startY + height - 1, horizontal, workingCursor)
        workingCursor.set(startX, startY + 1)
        drawLine(startX, startY + height -2 , vertical, workingCursor)
        workingCursor.set(startX + width - 1, startY + 1)
        drawLine(startX + width - 1, startY + height - 2 , vertical, workingCursor)
        workingCursor.set(startX, startY)
        write(topLeft, workingCursor)
        workingCursor.set(startX + width - 1, startY)
        write(topRight, workingCursor)
        workingCursor.set(startX, startY + height -1)
        write(bottomLeft, workingCursor)
        workingCursor.set(startX + width - 1, startY + height - 1)
        write(bottomRight, workingCursor)
    }

    @JvmOverloads fun drawDoubleBox(width: Int, height: Int, cursor: Cursor? = null) {
        drawBox(width, height,
                KTerminalData.BOX_DOUBLE_DOWN_RIGHT,
                KTerminalData.BOX_DOUBLE_DOWN_LEFT,
                KTerminalData.BOX_DOUBLE_UP_RIGHT,
                KTerminalData.BOX_DOUBLE_UP_LEFT,
                KTerminalData.BOX_DOUBLE_HORIZONTAL,
                KTerminalData.BOX_DOUBLE_VERTICAL,
                cursor)
    }

    @JvmOverloads fun drawSingleBox(width: Int, height: Int, cursor: Cursor? = null) {
        drawBox(width, height,
                KTerminalData.BOX_SINGLE_DOWN_RIGHT,
                KTerminalData.BOX_SINGLE_DOWN_LEFT,
                KTerminalData.BOX_SINGLE_UP_RIGHT,
                KTerminalData.BOX_SINGLE_UP_LEFT,
                KTerminalData.BOX_SINGLE_HORIZONTAL,
                KTerminalData.BOX_SINGLE_VERTICAL,
                cursor)
    }

    override fun toString(): String {
        var output = "[width=$width,height=$height,defaultForeground=$defaultForeground," +
            "defaultBackground=$defaultBackground,cursor={x=${cursor.x},y=${cursor.y}," +
            "foreground=${cursor.foregroundColor},background=${cursor.backgroundColor}},terminal={\n"

        for(j in 0 until height) {
            for(i in 0 until width) {
                output += "${terminal[i][j].char}"
            }
            output += "\n"
        }

        output += "}]"
        return output
    }

    companion object {
        const val NULL = 0.toChar()
        const val SMILE = 1.toChar()
        const val INVERTED_SMILE = 2.toChar()
        const val HEART = 3.toChar()
        const val DIAMOND = 4.toChar()
        const val CLUB = 5.toChar()
        const val SPADE = 6.toChar()
        const val BULLET = 7.toChar()
        const val INVERTED_BULLET = 8.toChar()
        const val CIRCLE = 9.toChar()
        const val INVERTED_CIRCLE = 10.toChar()
        const val MALE = 11.toChar()
        const val FEMALE = 12.toChar()
        const val PAIR_EIGHTH_NOTE = 13.toChar()
        const val EIGHTH_NOTE = 14.toChar()
        const val SOLAR = 15.toChar()
        const val RIGHT_TRIANGLE = 16.toChar()
        const val LEFT_TRIANGLE = 17.toChar()
        const val UP_DOWN_ARROW = 18.toChar()
        const val DOUBLE_EXCLAMATION = 19.toChar()
        const val PARAGRAPH = 20.toChar()
        const val SECTION = 21.toChar()
        const val LOWER_BLACK_RECTANGLE = 22.toChar()
        const val UP_DOWN_BAR_ARROW = 23.toChar()
        const val UP_ARROW = 24.toChar()
        const val DOWN_ARROW = 25.toChar()
        const val RIGHT_ARROW = 26.toChar()
        const val LEFT_ARROW = 27.toChar()
        const val RIGHT_ANGLE = 28.toChar()
        const val LEFT_RIGHT_ARROW = 29.toChar()
        const val UP_TRIANGLE = 30.toChar()
        const val DOWN_TRIANGLE = 31.toChar()
        const val SPACE = 32.toChar()
        const val HOUSE = 127.toChar()
        const val UPPER_CEDILLA_C = 128.toChar()
        const val LOWER_UMLAUT_U = 129.toChar()
        const val ACUTE_E = 130.toChar()
        const val CIRCUMFLEX_A = 131.toChar()
        const val LOWER_UMLAUT_A = 132.toChar()
        const val GRAVE_A = 133.toChar()
        const val LOWER_RING_A = 134.toChar()
        const val LOWER_CEDILLA_C = 135.toChar()
        const val CIRCUMFLEX_E = 136.toChar()
        const val UMLAUT_E = 137.toChar()
        const val GRAVE_E = 138.toChar()
        const val UMLAUT_I = 139.toChar()
        const val CIRCUMFLEX_I = 140.toChar()
        const val GRAVE_I = 141.toChar()
        const val UPPER_UMLAUT_A = 142.toChar()
        const val UPPER_RING_A = 143.toChar()
        const val UPPER_ACUTE_E = 144.toChar()
        const val LOWER_ASH = 145.toChar()
        const val UPPER_ASH = 146.toChar()
        const val CIRCUMFLEX_O = 147.toChar()
        const val LOWER_UMLAUT_O = 148.toChar()
        const val GRAVE_O = 149.toChar()
        const val CIRCUMFLEX_U = 150.toChar()
        const val GRAVE_U = 151.toChar()
        const val UMLAUT_Y = 152.toChar()
        const val UPPER_UMLAUT_O = 153.toChar()
        const val UPPER_UMLAUT_U = 154.toChar()
        const val CENT = 155.toChar()
        const val POUND = 156.toChar()
        const val YEN = 157.toChar()
        const val PESETA = 158.toChar()
        const val HOOK_F = 159.toChar()
        const val ACUTE_A = 160.toChar()
        const val ACUTE_I = 161.toChar()
        const val ACUTE_O = 162.toChar()
        const val ACUTE_U = 163.toChar()
        const val LOWER_TILDE_N = 164.toChar()
        const val UPPER_TILDE_N = 165.toChar()
        const val ORDINAL_FEMININE = 166.toChar()
        const val ORDINAL_MASCULINE = 167.toChar()
        const val INVERTED_QUESTION = 168.toChar()
        const val INVERTED_NOT = 169.toChar()
        const val NOT = 170.toChar()
        const val ONE_HALF = 171.toChar()
        const val ONE_FOURTH = 172.toChar()
        const val INVERTED_EXCLAMATION = 173.toChar()
        const val ANGLE_LEFT = 174.toChar()
        const val ANGLE_RIGHT = 175.toChar()
        const val SHADE_LIGHT = 176.toChar()
        const val SHADE_MEDIUM = 177.toChar()
        const val SHADE_DARK = 178.toChar()
        const val BOX_SINGLE_VERTICAL = 179.toChar()
        const val BOX_SINGLE_VERTICAL_LEFT = 180.toChar()
        const val BOX_VERTICAL_SINGLE_LEFT_DOUBLE = 181.toChar()
        const val BOX_VERTICAL_DOUBLE_LEFT_SINGLE = 182.toChar()
        const val BOX_DOWN_DOUBLE_LEFT_SINGLE = 183.toChar()
        const val BOX_DOWN_SINGLE_LEFT_DOUBLE = 184.toChar()
        const val BOX_DOUBLE_VERTICAL_LEFT = 185.toChar()
        const val BOX_DOUBLE_VERTICAL = 186.toChar()
        const val BOX_DOUBLE_DOWN_LEFT = 187.toChar()
        const val BOX_DOUBLE_UP_LEFT = 188.toChar()
        const val BOX_UP_DOUBLE_LEFT_SINGLE = 189.toChar()
        const val BOX_UP_SINGLE_LEFT_DOUBLE = 190.toChar()
        const val BOX_SINGLE_DOWN_LEFT = 191.toChar()
        const val BOX_SINGLE_UP_RIGHT = 192.toChar()
        const val BOX_SINGLE_HORIZONTAL_UP = 193.toChar()
        const val BOX_SINGLE_HORIZONTAL_DOWN = 194.toChar()
        const val BOX_SINGLE_VERTICAL_RIGHT = 195.toChar()
        const val BOX_SINGLE_HORIZONTAL = 196.toChar()
        const val BOX_SINGLE_VERTICAL_HORIZONTAL = 197.toChar()
        const val BOX_VERTICAL_SINGLE_RIGHT_DOUBLE = 198.toChar()
        const val BOX_VERTICAL_DOUBLE_RIGHT_SINGLE = 199.toChar()
        const val BOX_DOUBLE_UP_RIGHT = 200.toChar()
        const val BOX_DOUBLE_DOWN_RIGHT = 201.toChar()
        const val BOX_DOUBLE_HORIZONTAL_UP = 202.toChar()
        const val HOX_DOUBLE_HORIZONTAL_DOWN = 203.toChar()
        const val BOX_DOUBLE_VERTICAL_RIGHT = 204.toChar()
        const val BOX_DOUBLE_HORIZONTAL = 205.toChar()
        const val BOX_DOUBLE_VERTICAL_HORIZONTAL = 206.toChar()
        const val BOX_UP_SINGLE_HORIZONTAL_DOUBLE = 207.toChar()
        const val BOX_UP_DOUBLE_HORIZONTAL_SINGLE = 208.toChar()
        const val BOX_DOWN_SINGLE_HORIZONTAL_DOUBLE = 209.toChar()
        const val BOX_DOWN_DOUBLE_HORIZONTAL_SINGLE = 210.toChar()
        const val BOX_UP_DOUBLE_RIGHT_SINGLE = 211.toChar()
        const val BOX_UP_SINGLE_RIGHT_DOUBLE = 212.toChar()
        const val BOX_DOWN_SINGLE_RIGHT_DOUBLE = 213.toChar()
        const val BOX_DOWN_DOUBLE_RIGHT_SINGLE = 214.toChar()
        const val BOX_VERTICAL_DOUBLE_HORIZONTAL_SINGLE = 215.toChar()
        const val BOX_VERTICAL_SINGLE_HORIZONTAL_DOUBLE = 216.toChar()
        const val BOX_SINGLE_UP_LEFT = 217.toChar()
        const val BOX_SINGLE_DOWN_RIGHT = 218.toChar()
        const val BLOCK_FULL = 219.toChar()
        const val BLOCK_LOWER_HALF = 220.toChar()
        const val BLOCK_LEFT_HALF = 221.toChar()
        const val BLOCK_RIGHT_HALF = 222.toChar()
        const val BLOCK_UPPER_HALF = 223.toChar()
        const val ALPHA = 224.toChar()
        const val SHARP_S = 225.toChar()
        const val GAMMA = 226.toChar()
        const val PI = 227.toChar()
        const val UPPER_SIGMA = 228.toChar()
        const val LOWER_SIGMA = 229.toChar()
        const val MU = 230.toChar()
        const val TAU = 231.toChar()
        const val UPPER_PHI = 232.toChar()
        const val THETA = 233.toChar()
        const val OMEGA = 234.toChar()
        const val DELTA = 235.toChar()
        const val INFINITY = 236.toChar()
        const val LOWER_PHI = 237.toChar()
        const val EPSILON = 238.toChar()
        const val INTERSECTION = 239.toChar()
        const val TRIPLE_BAR = 240.toChar()
        const val PLUS_MINUS = 241.toChar()
        const val GREATER_EQUAL = 242.toChar()
        const val LESSER_EQUAL = 243.toChar()
        const val INTEGRAL_TOP = 244.toChar()
        const val INTEGRAL_BOTTOM = 245.toChar()
        const val DIVISION = 246.toChar()
        const val APROXIMATE = 247.toChar()
        const val DEGREE = 248.toChar()
        const val SMALL_BULLET = 249.toChar()
        const val INTERPUNCT = 250.toChar()
        const val RADICAL = 251.toChar()
        const val SUPERSCRIPT = 252.toChar()
        const val SUPERSCRIPT_SQUARE = 253.toChar()
        const val SQUARE = 254.toChar()
        const val NBSP = 255.toChar()
    }
}
