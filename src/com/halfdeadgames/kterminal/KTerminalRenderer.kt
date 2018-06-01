package com.halfdeadgames.kterminal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import java.nio.ByteBuffer

class KTerminalRenderer(tilesetFile: String,
                        scale: Float = 1f,
                        private val batch: SpriteBatch
) : Disposable {

    private lateinit var glyphTexture: Texture
    private lateinit var glyphs: Array<TextureRegion?>
    private lateinit var backgroundTexture: Texture

    var glyphWidth: Int = 0
        private set
    var glyphHeight: Int = 0
        private set
    private var scaledGlyphHeight: Float = 0f
    private var scaledGlyphWidth: Float = 0f

    init{
        init(tilesetFile, scale)
    }

    private fun init(tilesetFile: String, scale: Float = 1f) {
        val pixmap = Pixmap(Gdx.files.internal(tilesetFile))

        glyphWidth = pixmap.width / 16
        glyphHeight = pixmap.height / 16
        scaledGlyphWidth = glyphWidth * scale
        scaledGlyphHeight = glyphHeight * scale

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
    }

    fun set(tilesetFile: String, scale: Float = 1f) {
        dispose()
        init(tilesetFile, scale)
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
            val x = i % 16 * glyphWidth
            val y = i / 16 * glyphHeight

            glyphOutput[i] = TextureRegion(glyphTexture, x, y, glyphWidth, glyphHeight)
        }

        return glyphOutput
    }

    fun render(x: Float, y: Float, kTerminalData: KTerminalData) {
        val originalColor = batch.color

        for(j in 0 until kTerminalData.height) {
            for(i in 0 until kTerminalData.width) {
                batch.color = kTerminalData.terminal[i][j].background
                batch.draw( backgroundTexture,
                        x + (i * scaledGlyphWidth),
                        y + ((kTerminalData.height - j - 1) * scaledGlyphHeight),
                        scaledGlyphWidth,
                        scaledGlyphHeight)
            }
        }

        for(j in 0 until kTerminalData.height) {
            for(i in 0 until kTerminalData.width) {
                batch.color = kTerminalData.terminal[i][j].foreground
                batch.draw( glyphs[kTerminalData.terminal[i][j].char.toInt()],
                        x + (i * scaledGlyphWidth),
                        y + ((kTerminalData.height - j - 1) * scaledGlyphHeight),
                        scaledGlyphWidth / 2,
                        scaledGlyphHeight / 2,
                        scaledGlyphWidth,
                        scaledGlyphHeight,
                        1f,
                        1f,
                        (-kTerminalData.terminal[i][j].rotation + 90) % 360, //0 is no rotation, clockwise
                        true)
            }
        }
        batch.color = originalColor
    }

    override fun dispose() {
        glyphTexture.dispose()
        backgroundTexture.dispose()
    }
}
