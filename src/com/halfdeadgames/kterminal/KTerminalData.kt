package com.halfdeadgames.kterminal

import com.badlogic.gdx.graphics.Color
import kotlin.math.abs

class KTerminalData(width: Int,
                    height: Int,
                    var defaultForegroundColor: Float = Color.WHITE.toFloatBits(),
                    var defaultBackgroundColor: Float = Color.BLACK.toFloatBits()
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

    var terminal = Array(width) {
        Array(height) {
            KTerminalGlyph(' ', defaultForegroundColor, defaultBackgroundColor)
        }
    }

    //will not preserve terminal data
    fun resize(width: Int, height: Int) {
        this.width = width
        this.height = height

        terminal = Array(width) {
            Array(height) {
                KTerminalGlyph(' ', defaultForegroundColor, defaultBackgroundColor)
            }
        }
    }

    inner class Cursor(x: Int, y: Int, var foregroundColor: Float, var backgroundColor: Float, var rotation: Float, var scale: Float, var isFlippedX: Boolean, var isFlippedY: Boolean) {
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

        fun set(x: Int, y: Int, foregroundColor: Float, backgroundColor: Float, rotation: Float, scale: Float, isFlippedX: Boolean, isFlippedY: Boolean) {
            this.x = x
            this.y = y
            this.foregroundColor = foregroundColor
            this.backgroundColor = backgroundColor
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

    private val cursor: Cursor = Cursor(0, 0, defaultForegroundColor, defaultBackgroundColor, 0f, 1f, false, false)
    private val workingCursor: Cursor = Cursor(0, 0, defaultForegroundColor, defaultBackgroundColor, 0f, 1f, false, false)

    @JvmOverloads fun setCursor(x: Int = 0, y: Int = 0, foregroundColor: Float = defaultForegroundColor, backgroundColor: Float = defaultBackgroundColor, rotation: Float = 0f, scale: Float = 1f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) {
        cursor.set(x, y, foregroundColor, backgroundColor, rotation, scale,  isFlippedX, isFlippedY)
        workingCursor.set(cursor)
    }

    @JvmOverloads fun setCursor(x: Int = 0, y: Int = 0, foregroundColor: Color, backgroundColor: Color, rotation: Float = 0f, scale: Float = 1f, isFlippedX: Boolean = false, isFlippedY: Boolean = false) {
        setCursor(x, y, foregroundColor.toFloatBits(), backgroundColor.toFloatBits(), rotation, scale, isFlippedX, isFlippedY)
    }

    fun resetCursor() {
        setCursor(0, 0, defaultForegroundColor, defaultBackgroundColor, 0f, 1f, false, false)
    }

    fun setCursorPosition(x: Int, y: Int) : KTerminalData {
        cursor.x = x
        cursor.y = y
        workingCursor.set(cursor)

        return this
    }

    fun setCursorColor(foregroundColor: Float, backgroundColor: Float) : KTerminalData {
        cursor.foregroundColor = foregroundColor
        cursor.backgroundColor = backgroundColor

        workingCursor.foregroundColor = foregroundColor
        workingCursor.backgroundColor = backgroundColor
        return this
    }

    fun setCursorColor(foregroundColor: Color, backgroundColor: Color) : KTerminalData {
        return setCursorColor(foregroundColor.toFloatBits(), backgroundColor.toFloatBits())
    }

    fun setCursorScale(scale: Float) : KTerminalData {
        cursor.scale = scale
        workingCursor.scale = scale

        return this
    }

    fun setCursorRotation(rotation: Float) : KTerminalData {
        cursor.rotation = rotation
        workingCursor.rotation = rotation

        return this
    }

    fun setCursorFlip(isFlippedX: Boolean, isFlippedY: Boolean) : KTerminalData {
        cursor.isFlippedX = isFlippedX
        cursor.isFlippedY = isFlippedY
        workingCursor.isFlippedX = isFlippedX
        workingCursor.isFlippedY = isFlippedY

        return this
    }

    //position brackets
    operator fun get(x: Int, y: Int) : KTerminalData {
        return setCursorPosition(x, y)
    }

    //color brackets, can't do floats because rotation and scale use floats
    operator fun get(foregroundColor: Color, backgroundColor: Color) : KTerminalData {
        return setCursorColor(foregroundColor, backgroundColor)
    }

    //rotation and scale brackets
    operator fun get(rotation: Float, scale: Float) : KTerminalData {
        return setCursorRotation(rotation).setCursorScale(scale)
    }

    //flip status brackets
    operator fun get(isFlippedX: Boolean, isFlippedY: Boolean) : KTerminalData {
        return setCursorFlip(isFlippedX, isFlippedY)
    }

    //override any cursor data given by the glyph
    fun write(glyph: KTerminalGlyph) {
        workingCursor.foregroundColor = glyph.foregroundColor
        workingCursor.backgroundColor = glyph.backgroundColor
        workingCursor.rotation = glyph.rotation
        workingCursor.scale = glyph.scale
        workingCursor.isFlippedX = glyph.isFlippedX
        workingCursor.isFlippedY = glyph.isFlippedY

        write(glyph.value)
    }

    fun write(value: Int) {
        terminal[workingCursor.x][workingCursor.y].apply {
            this.value = value
            this.foregroundColor = workingCursor.foregroundColor
            this.backgroundColor = workingCursor.backgroundColor
            this.rotation = workingCursor.rotation
            this.scale = workingCursor.scale
            this.isFlippedX = workingCursor.isFlippedX
            this.isFlippedY = workingCursor.isFlippedY
        }

        workingCursor.set(cursor)
    }

    fun write(char: Char) {
        write(char.toInt())
    }

    @JvmOverloads fun write(string: String, direction: Int = WRITE_LEFT_TO_RIGHT, wrapping: Int = WRAP_NONE) {
        var posX = cursor.x
        var posY = cursor.y
        var isWriting = true

        string.toCharArray().forEach {
            if(isWriting) {
                workingCursor.set(posX, posY)
                write(it)

                when(direction) {
                    WRITE_LEFT_TO_RIGHT -> posX++
                    WRITE_TOP_TO_BOTTOM -> posY++
                    WRITE_RIGHT_TO_LEFT -> posX--
                    WRITE_BOTTOM_TO_TOP -> posY--
                }

                when(direction) {
                    WRITE_LEFT_TO_RIGHT, WRITE_RIGHT_TO_LEFT -> {
                        if(posX >= width) {
                            posX = 0

                            when(wrapping) {
                                WRAP_NONE -> isWriting = false
                                WRAP_POSITIVE_SHIFT -> posY++
                                WRAP_NEGATIVE_SHIFT -> posY--
                            }
                        } else if(posX < 0) {
                            posX = width - 1

                            when(wrapping) {
                                WRAP_NONE -> isWriting = false
                                WRAP_POSITIVE_SHIFT -> posY++
                                WRAP_NEGATIVE_SHIFT -> posY--
                            }
                        }
                    }
                    WRITE_TOP_TO_BOTTOM, WRITE_BOTTOM_TO_TOP -> {
                        if(posY >= height) {
                            posY = 0

                            when(wrapping) {
                                WRAP_NONE -> isWriting = false
                                WRAP_POSITIVE_SHIFT -> posX++
                                WRAP_NEGATIVE_SHIFT -> posX--
                            }
                        } else if(posY < 0) {
                            posY = height - 1

                            when(wrapping) {
                                WRAP_NONE -> isWriting = false
                                WRAP_POSITIVE_SHIFT -> posX++
                                WRAP_NEGATIVE_SHIFT -> posX--
                            }
                        }
                    }
                }
            }
        }
    }

    @JvmOverloads fun clear(width: Int = 1, height: Int = 1) {
        workingCursor.foregroundColor = defaultForegroundColor
        workingCursor.backgroundColor = defaultBackgroundColor
        workingCursor.rotation = 0f
        workingCursor.scale = 1f

        drawRect(width, height, 0, true)
    }

    fun clearAll() {
        workingCursor.set(0, 0, defaultForegroundColor, defaultBackgroundColor, 0f, 1f, false, false)
        clear(width, height)
    }

    private fun plotRect(width: Int, height: Int) : List<Pair<Int, Int>> {
        val output: MutableList<Pair<Int, Int>> = mutableListOf()



        return output
    }

    @JvmOverloads fun drawRect(width: Int,
                               height: Int,
                               char: Char = ' ',
                               isFilled: Boolean = false) {
        drawRect(width, height, char.toInt(), isFilled)
    }

    @JvmOverloads fun drawRect(width: Int,
                 height: Int,
                 value: Int,
                 isFilled: Boolean = false) {

        val startX = workingCursor.x
        val startY = workingCursor.y

        for(i in startX until startX + width) {
            for(j in startY until startY + height) {
                workingCursor.x = i
                workingCursor.y = j
                if(isFilled) {
                    write(value)
                } else {
                    if(i == startX || i == startX + width - 1) {
                        write(value)
                    } else if(j == startY || j == startY + height - 1) {
                        write(value)
                    }
                }
            }
        }
    }

    private fun fillShape(shapeList: List<Pair<Int, Int>>, value: Int) {
        val shapeListSorted = shapeList.sortedWith(compareBy<Pair<Int, Int>>{it.second}.thenBy { it.first })

        val firstY = shapeListSorted.first().second
        val lastY = shapeListSorted.last().second

        shapeListSorted.forEachIndexed { index, pair ->
            if(index + 1 < shapeListSorted.size) {
                if(pair.second != firstY && pair.second != lastY) {
                    if( pair.second == shapeListSorted[index + 1].second
                        && pair.first <= shapeListSorted[index + 1].first - 2) {
                        for(i in pair.first + 1 until shapeListSorted[index + 1].first) {
                            workingCursor.x = i
                            workingCursor.y = pair.second
                            write(value)
                        }
                    }
                }
            }
        }
    }

    private fun drawShape(shapeList: List<Pair<Int, Int>>, value: Int) {
        shapeList.forEach {
            workingCursor.x = it.first
            workingCursor.y = it.second
            write(value)
        }
    }

    private fun plotLine(x0: Int, y0: Int, x1: Int, y1: Int) :  List<Pair<Int, Int>> {
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

    fun drawLine(endX: Int, endY: Int, char: Char) {
        drawLine(endX, endY, char.toInt())
    }
    fun drawLine(endX: Int, endY: Int, value: Int) {
        val linePlot = plotLine(workingCursor.x, workingCursor.y, endX, endY).toList()
        drawShape(linePlot, value)
    }

    private fun plotEllipseRect(x0: Int, y0: Int, x1: Int, y1: Int) :  List<Pair<Int, Int>> {
        val output: MutableList<Pair<Int, Int>> = mutableListOf()

        var x0 = x0
        var y0 = y0
        var x1 = x1
        var y1 = y1
        var a = abs(x1 - x0)
        val b = abs(y1 - y0)
        var b1 = b and 1 /* values of diameter */
        var dx = (4 * (1 - a) * b * b).toLong()
        var dy = (4 * (b1 + 1) * a * a).toLong() /* error increment */
        var err = dx + dy + (b1 * a * a).toLong()
        var e2: Long /* error of 1.step */

        if (x0 > x1) {
            x0 = x1
            x1 += a
        } /* if called with swapped points */
        if (y0 > y1) y0 = y1 /* .. exchange them */
        y0 += (b + 1) / 2
        y1 = y0 - b1   /* starting pixel */
        a *= 8 * a
        b1 = 8 * b * b

        do {
            output.add(Pair(x1, y0)) /*   I. Quadrant */
            output.add(Pair(x0, y0)) /*  II. Quadrant */
            output.add(Pair(x0, y1)) /* III. Quadrant */
            output.add(Pair(x1, y1)) /*  IV. Quadrant */
            e2 = 2 * err
            if (e2 <= dy) {
                y0++
                y1--
                dy += a.toLong()
                err += dy
            }  /* y step */
            if (e2 >= dx || 2 * err > dy) {
                x0++
                x1--
                dx += b1.toLong()
                err += dx
            } /* x step */
        } while (x0 <= x1)

        while (y0 - y1 < b) {  /* too early stop of flat ellipses a=1 */
            output.add(Pair(x0 - 1, y0)) /* -> finish tip of ellipse */
            output.add(Pair(x1 + 1, y0++))
            output.add(Pair(x0 - 1, y1))
            output.add(Pair(x1 + 1, y1--))
        }

        return output
    }

    @JvmOverloads fun drawEllipse(width: Int, height: Int, char: Char, isFilled: Boolean = false, fillValue: Char) {
        drawEllipse(width, height, char.toInt(), isFilled, fillValue.toInt())
    }

    @JvmOverloads fun drawEllipse(width: Int, height: Int, value: Int, isFilled: Boolean = false, fillValue: Int = value) {
        val ellipsePlot = plotEllipseRect(cursor.x - 1, cursor.y - 1, cursor.x + width - 2, cursor.y + height - 2)

        drawShape(ellipsePlot, value)
        if(isFilled) fillShape(ellipsePlot, fillValue)
    }

    private fun plotCircle(centerX: Int, centerY: Int, radius: Int) : List<Pair<Int, Int>> {
        val output: MutableList<Pair<Int, Int>> = mutableListOf()

        var r = radius
        var x = -r
        var y = 0
        var err = 2 - (2 * r)

        do {
            output.add(Pair(centerX - x, centerY + y))
            output.add(Pair(centerX -y, centerY - x))
            output.add(Pair(centerX + x, centerY - y))
            output.add(Pair(centerX + y, centerY + x))
            r = err
            if(r <= y) err += ++y * 2 + 1
            if(r > x || err > y) err += ++x * 2 + 1
        } while(x < 0)

        return output.toList()
    }

    @JvmOverloads fun drawCircle(radius: Int, char: Char, isFilled: Boolean = false, fillValue: Char = char) {
        drawCircle(radius, char.toInt(), isFilled, fillValue.toInt())
    }

    @JvmOverloads fun drawCircle(radius: Int, value: Int, isFilled: Boolean = false, fillValue: Int = value) {
        val circlePlot = plotCircle(cursor.x - 1, cursor.y - 1, radius - 1)

        drawShape(circlePlot, value)
        if(isFilled) fillShape(circlePlot, fillValue)
    }

    fun drawBox(width: Int, height: Int, topLeft: Int, topRight: Int, bottomLeft: Int, bottomRight: Int, horizontal: Int, vertical: Int) {
        val startX = workingCursor.x
        val startY = workingCursor.y

        workingCursor.set(startX + 1, startY)
        drawLine(startX + width - 2, startY, horizontal)
        workingCursor.set(startX + 1, startY + height - 1)
        drawLine(startX + width - 2, startY + height - 1, horizontal)
        workingCursor.set(startX, startY + 1)
        drawLine(startX, startY + height -2 , vertical)
        workingCursor.set(startX + width - 1, startY + 1)
        drawLine(startX + width - 1, startY + height - 2 , vertical)
        workingCursor.set(startX, startY)
        write(topLeft)
        workingCursor.set(startX + width - 1, startY)
        write(topRight)
        workingCursor.set(startX, startY + height -1)
        write(bottomLeft)
        workingCursor.set(startX + width - 1, startY + height - 1)
        write(bottomRight)
    }

    fun drawBox(width: Int, height: Int, topLeft: Char, topRight: Char, bottomLeft: Char, bottomRight: Char, horizontal: Char, vertical: Char) {
        drawBox(width, height, topLeft.toInt(), topRight.toInt(), bottomLeft.toInt(), bottomRight.toInt(), horizontal.toInt(), vertical.toInt())
    }

    fun drawDoubleBox(width: Int, height: Int) {
        drawBox(width, height,
                KTerminalData.BOX_DOUBLE_DOWN_RIGHT,
                KTerminalData.BOX_DOUBLE_DOWN_LEFT,
                KTerminalData.BOX_DOUBLE_UP_RIGHT,
                KTerminalData.BOX_DOUBLE_UP_LEFT,
                KTerminalData.BOX_DOUBLE_HORIZONTAL,
                KTerminalData.BOX_DOUBLE_VERTICAL)
    }

    fun drawSingleBox(width: Int, height: Int) {
        drawBox(width, height,
                KTerminalData.BOX_SINGLE_DOWN_RIGHT,
                KTerminalData.BOX_SINGLE_DOWN_LEFT,
                KTerminalData.BOX_SINGLE_UP_RIGHT,
                KTerminalData.BOX_SINGLE_UP_LEFT,
                KTerminalData.BOX_SINGLE_HORIZONTAL,
                KTerminalData.BOX_SINGLE_VERTICAL)
    }

    override fun toString(): String {
        var output = "[width=$width,height=$height,defaultForegroundColor=$defaultForegroundColor," +
            "defaultBackgroundColor=$defaultBackgroundColor,cursor={x=${cursor.x},y=${cursor.y}," +
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
        const val WRITE_LEFT_TO_RIGHT = 0
        const val WRITE_TOP_TO_BOTTOM = 1
        const val WRITE_RIGHT_TO_LEFT = 2
        const val WRITE_BOTTOM_TO_TOP = 3

        const val WRAP_NONE = -1
        const val WRAP_NO_SHIFT = 0
        const val WRAP_POSITIVE_SHIFT = 1
        const val WRAP_NEGATIVE_SHIFT = 2

        const val NULL = 0
        const val SMILE = 1
        const val INVERTED_SMILE = 2
        const val HEART = 3
        const val DIAMOND = 4
        const val CLUB = 5
        const val SPADE = 6
        const val BULLET = 7
        const val INVERTED_BULLET = 8
        const val CIRCLE = 9
        const val INVERTED_CIRCLE = 10
        const val MALE = 11
        const val FEMALE = 12
        const val PAIR_EIGHTH_NOTE = 13
        const val EIGHTH_NOTE = 14
        const val SOLAR = 15
        const val RIGHT_TRIANGLE = 16
        const val LEFT_TRIANGLE = 17
        const val UP_DOWN_ARROW = 18
        const val DOUBLE_EXCLAMATION = 19
        const val PARAGRAPH = 20
        const val SECTION = 21
        const val LOWER_BLACK_RECTANGLE = 22
        const val UP_DOWN_BAR_ARROW = 23
        const val UP_ARROW = 24
        const val DOWN_ARROW = 25
        const val RIGHT_ARROW = 26
        const val LEFT_ARROW = 27
        const val RIGHT_ANGLE = 28
        const val LEFT_RIGHT_ARROW = 29
        const val UP_TRIANGLE = 30
        const val DOWN_TRIANGLE = 31
        const val SPACE = 32
        const val HOUSE = 127
        const val UPPER_CEDILLA_C = 128
        const val LOWER_UMLAUT_U = 129
        const val ACUTE_E = 130
        const val CIRCUMFLEX_A = 131
        const val LOWER_UMLAUT_A = 132
        const val GRAVE_A = 133
        const val LOWER_RING_A = 134
        const val LOWER_CEDILLA_C = 135
        const val CIRCUMFLEX_E = 136
        const val UMLAUT_E = 137
        const val GRAVE_E = 138
        const val UMLAUT_I = 139
        const val CIRCUMFLEX_I = 140
        const val GRAVE_I = 141
        const val UPPER_UMLAUT_A = 142
        const val UPPER_RING_A = 143
        const val UPPER_ACUTE_E = 144
        const val LOWER_ASH = 145
        const val UPPER_ASH = 146
        const val CIRCUMFLEX_O = 147
        const val LOWER_UMLAUT_O = 148
        const val GRAVE_O = 149
        const val CIRCUMFLEX_U = 150
        const val GRAVE_U = 151
        const val UMLAUT_Y = 152
        const val UPPER_UMLAUT_O = 153
        const val UPPER_UMLAUT_U = 154
        const val CENT = 155
        const val POUND = 156
        const val YEN = 157
        const val PESETA = 158
        const val HOOK_F = 159
        const val ACUTE_A = 160
        const val ACUTE_I = 161
        const val ACUTE_O = 162
        const val ACUTE_U = 163
        const val LOWER_TILDE_N = 164
        const val UPPER_TILDE_N = 165
        const val ORDINAL_FEMININE = 166
        const val ORDINAL_MASCULINE = 167
        const val INVERTED_QUESTION = 168
        const val INVERTED_NOT = 169
        const val NOT = 170
        const val ONE_HALF = 171
        const val ONE_FOURTH = 172
        const val INVERTED_EXCLAMATION = 173
        const val ANGLE_LEFT = 174
        const val ANGLE_RIGHT = 175
        const val SHADE_LIGHT = 176
        const val SHADE_MEDIUM = 177
        const val SHADE_DARK = 178
        const val BOX_SINGLE_VERTICAL = 179
        const val BOX_SINGLE_VERTICAL_LEFT = 180
        const val BOX_VERTICAL_SINGLE_LEFT_DOUBLE = 181
        const val BOX_VERTICAL_DOUBLE_LEFT_SINGLE = 182
        const val BOX_DOWN_DOUBLE_LEFT_SINGLE = 183
        const val BOX_DOWN_SINGLE_LEFT_DOUBLE = 184
        const val BOX_DOUBLE_VERTICAL_LEFT = 185
        const val BOX_DOUBLE_VERTICAL = 186
        const val BOX_DOUBLE_DOWN_LEFT = 187
        const val BOX_DOUBLE_UP_LEFT = 188
        const val BOX_UP_DOUBLE_LEFT_SINGLE = 189
        const val BOX_UP_SINGLE_LEFT_DOUBLE = 190
        const val BOX_SINGLE_DOWN_LEFT = 191
        const val BOX_SINGLE_UP_RIGHT = 192
        const val BOX_SINGLE_HORIZONTAL_UP = 193
        const val BOX_SINGLE_HORIZONTAL_DOWN = 194
        const val BOX_SINGLE_VERTICAL_RIGHT = 195
        const val BOX_SINGLE_HORIZONTAL = 196
        const val BOX_SINGLE_VERTICAL_HORIZONTAL = 197
        const val BOX_VERTICAL_SINGLE_RIGHT_DOUBLE = 198
        const val BOX_VERTICAL_DOUBLE_RIGHT_SINGLE = 199
        const val BOX_DOUBLE_UP_RIGHT = 200
        const val BOX_DOUBLE_DOWN_RIGHT = 201
        const val BOX_DOUBLE_HORIZONTAL_UP = 202
        const val HOX_DOUBLE_HORIZONTAL_DOWN = 203
        const val BOX_DOUBLE_VERTICAL_RIGHT = 204
        const val BOX_DOUBLE_HORIZONTAL = 205
        const val BOX_DOUBLE_VERTICAL_HORIZONTAL = 206
        const val BOX_UP_SINGLE_HORIZONTAL_DOUBLE = 207
        const val BOX_UP_DOUBLE_HORIZONTAL_SINGLE = 208
        const val BOX_DOWN_SINGLE_HORIZONTAL_DOUBLE = 209
        const val BOX_DOWN_DOUBLE_HORIZONTAL_SINGLE = 210
        const val BOX_UP_DOUBLE_RIGHT_SINGLE = 211
        const val BOX_UP_SINGLE_RIGHT_DOUBLE = 212
        const val BOX_DOWN_SINGLE_RIGHT_DOUBLE = 213
        const val BOX_DOWN_DOUBLE_RIGHT_SINGLE = 214
        const val BOX_VERTICAL_DOUBLE_HORIZONTAL_SINGLE = 215
        const val BOX_VERTICAL_SINGLE_HORIZONTAL_DOUBLE = 216
        const val BOX_SINGLE_UP_LEFT = 217
        const val BOX_SINGLE_DOWN_RIGHT = 218
        const val BLOCK_FULL = 219
        const val BLOCK_LOWER_HALF = 220
        const val BLOCK_LEFT_HALF = 221
        const val BLOCK_RIGHT_HALF = 222
        const val BLOCK_UPPER_HALF = 223
        const val ALPHA = 224
        const val SHARP_S = 225
        const val GAMMA = 226
        const val PI = 227
        const val UPPER_SIGMA = 228
        const val LOWER_SIGMA = 229
        const val MU = 230
        const val TAU = 231
        const val UPPER_PHI = 232
        const val THETA = 233
        const val OMEGA = 234
        const val DELTA = 235
        const val INFINITY = 236
        const val LOWER_PHI = 237
        const val EPSILON = 238
        const val INTERSECTION = 239
        const val TRIPLE_BAR = 240
        const val PLUS_MINUS = 241
        const val GREATER_EQUAL = 242
        const val LESSER_EQUAL = 243
        const val INTEGRAL_TOP = 244
        const val INTEGRAL_BOTTOM = 245
        const val DIVISION = 246
        const val APROXIMATE = 247
        const val DEGREE = 248
        const val SMALL_BULLET = 249
        const val INTERPUNCT = 250
        const val RADICAL = 251
        const val SUPERSCRIPT = 252
        const val SUPERSCRIPT_SQUARE = 253
        const val SQUARE = 254
        const val NBSP = 255
    }
}
