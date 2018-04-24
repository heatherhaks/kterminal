package com.halfdeadgames.kterminal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import com.halfdeadgames.offscreenrenderer.OffScreenRenderer
import java.nio.ByteBuffer

class KTerminal(width: Int,
                height: Int,
                var tilesetFile: String,
                scale: Float = 1f,
                var defaultForegroundColor: Color = Color.WHITE,
                var defaultBackgroundColor: Color = Color.BLACK,
                inputBatch: SpriteBatch
) : Disposable{

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


    private var glyphTexture: Texture
    private var glyphs: Array<TextureRegion?>
    private var backgroundTexture: Texture
    private var terminal = Array(width, {
        Array(height, {
            KTerminalDataCell()
        })
    })
    private var offScreenRenderer: OffScreenRenderer
    var characterSize: Int = 0
    var scaledCharacterSize: Int = 0
    var width: Int = 0
        set(value) {
            if(value > 0) {
                field = value
            } else {
                throw IllegalArgumentException("Width can't be 0 or below.")
            }
        }
    var height: Int = 0
        set(value) {
            if(value > 0) {
                field = value
            } else {
                throw IllegalArgumentException("Height can't be 0 or below.")
            }
        }
    var scale: Float = 1f
        set(value) {
            if(value > 0f) {
                field = value
            } else {
                throw IllegalArgumentException("Scale can't be 0 or below.")
            }
        }
    private var needsUpdate = true
    var texture: Texture

    init {
        this.scale = scale
        this.width = width
        this.height = height
        val pixmap = Pixmap(Gdx.files.internal(tilesetFile))
        characterSize = pixmap.width / 16
        scaledCharacterSize = (characterSize * this.scale).toInt()
        val resultPixmap = Pixmap(pixmap.width, pixmap.height, Pixmap.Format.RGBA8888)
        val whitePixmap = Pixmap(pixmap.width, pixmap.height, Pixmap.Format.RGBA8888)
        whitePixmap.setColor(Color.WHITE)
        whitePixmap.fill()
        backgroundTexture = Texture(whitePixmap)
        whitePixmap.dispose()
        val buffer: ByteBuffer = pixmap.pixels
        val resultBuffer: ByteBuffer = resultPixmap.pixels
        fillBuffers(buffer, resultBuffer, pixmap)
        pixmap.dispose()

        glyphTexture = Texture(resultPixmap)
        resultPixmap.dispose()

        glyphs = getGlyphs()


        offScreenRenderer = OffScreenRenderer(width = width * scaledCharacterSize,
                height = height * scaledCharacterSize, batch = inputBatch)

        texture = offScreenRenderer.getBufferTexture()
        clear()
        update()
    }

    private fun fillBuffers(buffer: ByteBuffer, resultBuffer: ByteBuffer, pixmap: Pixmap) {
        buffer.rewind()
        resultBuffer.rewind()

        var start = true
        var rBackground: Byte = 0
        var gBackground: Byte = 0
        var bBackground: Byte = 0
        var aBackground: Byte = 1

        while(buffer.hasRemaining()) {
            val r: Byte = buffer.get()
            val g: Byte = buffer.get()
            val b: Byte = buffer.get()
            var a: Byte = -1

            if(pixmap.format == Pixmap.Format.RGBA8888) {
                a = buffer.get()
            }

            if(start) {
                start = false
                rBackground = r
                gBackground = g
                bBackground = b
                aBackground = a
            }

            if(r == rBackground && g == gBackground && b == bBackground && a == aBackground) {
                resultBuffer.put(0.toByte())
                resultBuffer.put(0.toByte())
                resultBuffer.put(0.toByte())
                resultBuffer.put(0.toByte())
            } else {
                resultBuffer.put(r)
                resultBuffer.put(g)
                resultBuffer.put(b)
                resultBuffer.put(a)
            }
        }

        buffer.rewind()
        resultBuffer.rewind()
    }

    private fun getGlyphs(): Array<TextureRegion?> {
        val glyphOutput: Array<TextureRegion?> = arrayOfNulls(256)
        for (i in 0..255) {
            val x = i % 16 * characterSize
            val y = i / 16 * characterSize

            glyphOutput[i] = TextureRegion(glyphTexture, x, y, characterSize, characterSize)
        }

        return glyphOutput
    }

    fun write(x: Int, y: Int, characterData: KTerminalDataCell) {
        if(x < 0 || x > width - 1) {
            throw IllegalArgumentException("X position should be between [0 and ${width-1}]")
        }
        if(y < 0 || y > height - 1) {
            throw IllegalArgumentException("Y position should be between [0 and ${height-1}]")
        }
        if(terminal[x][y].data != characterData.data ||
                terminal[x][y].foregroundColor != characterData.foregroundColor || 
                terminal[x][y].backgroundColor != characterData.backgroundColor) {
            terminal[x][y] = characterData.copy()
            needsUpdate = true
        }
    }

    fun write(x: Int, y: Int, character: Char = ' ', foregroundColor: Color = defaultForegroundColor, backgroundColor: Color = defaultBackgroundColor) {
        write(x, y, KTerminalDataCell(character, foregroundColor, backgroundColor))
    }

    fun clear(x: Int, y: Int, width: Int = 1, height: Int = 1) {
        if(x < 0 || x > this.width - 1) {
            throw IllegalArgumentException("X position should be between [0 and ${this.width-1}]")
        }
        if(y < 0 || y > this.height - 1) {
            throw IllegalArgumentException("Y position should be between [0 and ${this.height-1}]")
        }
        if(width < 1){
            throw IllegalArgumentException("Width can't be under 1")
        }
        if(height < 1){
            throw IllegalArgumentException("Height can't be under 1")
        }
        if(width+x > this.width || height+y > this.height) {
            throw IllegalArgumentException("Clear request exceeds terminal size")
        }

        for(i in y until height+y) {
            for(j in x until width+x) {
                write(j, i)
            }
        }
    }

    fun clear() {
        clear(0, 0, width, height)
    }

    fun update() {
        if(needsUpdate) {
            offScreenRenderer.render {
                for(x in 0 until width) {
                    for(y in 0 until height) {
                        if(terminal[x][y].needsUpdate){
                            it.color = terminal[x][y].backgroundColor
                            it.draw( backgroundTexture,
                                    (x * scaledCharacterSize).toFloat(),
                                    ((height - y - 1) * scaledCharacterSize).toFloat(),
                                    scaledCharacterSize.toFloat(),
                                    scaledCharacterSize.toFloat())

                            it.color = terminal[x][y].foregroundColor
                            it.draw( glyphs[terminal[x][y].data.toInt()],
                                    (x * scaledCharacterSize).toFloat(),
                                    ((height - y - 1) * scaledCharacterSize.toFloat()),
                                    scaledCharacterSize.toFloat(),
                                    scaledCharacterSize.toFloat())
                        }
                    }
                }
            }

            needsUpdate = false
        }
    }

    override fun dispose() {
        glyphTexture.dispose()
        backgroundTexture.dispose()
        texture.dispose()
        offScreenRenderer.dispose()
    }
}
