package com.halfdeadgames.kterminal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import com.halfdeadgames.kterminal.KTerminalDataCell
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

    lateinit var texture: Texture

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

        terminal[x][y] = characterData.copy()
        terminal[x][y].needsUpdate = true
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
    }

    override fun dispose() {
        glyphTexture.dispose()
        backgroundTexture.dispose()
        texture.dispose()
        offScreenRenderer.dispose()
    }
}
