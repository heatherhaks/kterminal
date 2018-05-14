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
                        val batch: SpriteBatch
) : Disposable {

    private var glyphTexture: Texture
    private var glyphs: Array<TextureRegion?>
    private var backgroundTexture: Texture
    var characterSize: Int = 0
    var scaledCharacterSize: Int = 0
    var scale: Float = 1f
        set(value) {
            if(value > 0f) {
                field = value
            } else {
                throw IllegalArgumentException("Scale can't be 0 or below.")
            }
        }

    init{
        this.scale = scale
        val pixmap = Pixmap(Gdx.files.internal(tilesetFile))
        characterSize = pixmap.width / 16
        scaledCharacterSize = (characterSize * this.scale).toInt()
        println(scaledCharacterSize)
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

    fun render(x: Int, y: Int, kTerminalData: KTerminalData) {
        val originalColor = batch.color
        for(i in 0 until kTerminalData.width) {
            for(j in 0 until kTerminalData.height) {
                batch.color = kTerminalData.terminal[i][j].backgroundColor
                batch.draw( backgroundTexture,
                        x + (i * scaledCharacterSize).toFloat(),
                        y + ((kTerminalData.height - j - 1) * scaledCharacterSize).toFloat(),
                        scaledCharacterSize.toFloat(),
                        scaledCharacterSize.toFloat())

                batch.color = kTerminalData.terminal[i][j].foregroundColor
                batch.draw( glyphs[kTerminalData.terminal[i][j].char.toInt()],
                        x + (i * scaledCharacterSize).toFloat(),
                        y + ((kTerminalData.height - j - 1) * scaledCharacterSize.toFloat()),
                        scaledCharacterSize.toFloat(),
                        scaledCharacterSize.toFloat())
            }
        }
        batch.color = originalColor
    }

    override fun dispose() {
        glyphTexture.dispose()
        backgroundTexture.dispose()
    }
}