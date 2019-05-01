package com.halfdeadgames.kterminal

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Disposable
import java.nio.ByteBuffer

@Suppress("unused", "MemberVisibilityCanBePrivate")
class KTerminalRenderer(val batch: SpriteBatch,
                        var tilesetFile: String? = null,
                        var columns: Int = 16,
                        var rows: Int = 16,
                        scale: Float = 1f
) : Disposable {

    private lateinit var glyphTexture: Texture
    private lateinit var glyphs: Array<TextureRegion?>
    private lateinit var backgroundTexture: Texture
    private lateinit var backgroundTextureRegion: TextureRegion
    private var glyphMaxSize: Float = 0f

    var glyphWidth: Int = 0
        private set
    var glyphHeight: Int = 0
        private set
    private var scaledGlyphHeight: Float = 0f
    private var scaledGlyphWidth: Float = 0f

    init{
        init(tilesetFile, scale)
    }

    private fun init(tilesetFile: String?, scale: Float = 1f) {
        if(tilesetFile == null) {
            columns = 16
            rows = 17
        }

        val tempTilesetFile = tilesetFile ?: "defaultKTerminalFontSheet.png"


        val pixmap = Pixmap(Gdx.files.internal(tempTilesetFile))


        glyphWidth = pixmap.width / columns
        glyphHeight = pixmap.height / rows
        scaledGlyphWidth = glyphWidth * scale
        scaledGlyphHeight = glyphHeight * scale
        glyphMaxSize = Math.sqrt(((scaledGlyphWidth * scaledGlyphWidth) + (scaledGlyphHeight * scaledGlyphHeight)).toDouble()).toFloat()

        val resultPixmap = Pixmap(pixmap.width, pixmap.height, Pixmap.Format.RGBA8888)
        val whitePixmap = Pixmap(pixmap.width, pixmap.height, Pixmap.Format.RGBA8888)
        whitePixmap.setColor(Color.WHITE)
        whitePixmap.fill()
        backgroundTexture = Texture(whitePixmap)
        backgroundTextureRegion = TextureRegion(backgroundTexture)
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
        val glyphOutput: Array<TextureRegion?> = arrayOfNulls(columns * rows)

        var counter = 0
        for(i in 0 until rows) {
            for(j in 0 until columns) {
                glyphOutput[counter] = TextureRegion(glyphTexture, j * glyphWidth, i * glyphHeight, glyphWidth, glyphHeight)
                counter++
            }
        }

        return glyphOutput
    }

    fun render(x: Float, y: Float, kTerminalData: KTerminalData) {
        val originalColor = batch.packedColor

        fun draw(textureRegion: TextureRegion?, glyphColor: Float, glyph: KTerminalGlyph, posX: Int, posY: Int, scaleX: Float, scaleY: Float) {
            batch.packedColor = glyphColor

            batch.draw( textureRegion,
                    x + (posX * scaledGlyphWidth) + (glyph.offsetX * scaledGlyphWidth),
                    y + ((kTerminalData.height - posY - 1) * scaledGlyphHeight) + (-glyph.offsetY * scaledGlyphHeight),
                    scaledGlyphWidth / 2,
                    scaledGlyphHeight / 2,
                    scaledGlyphWidth,
                    scaledGlyphHeight,
                    scaleX,
                    scaleY,
                    (-glyph.rotation + 90) % 360, //0 is no rotation, clockwise
                    true)
        }

        for(j in 0 until kTerminalData.height) {
            for(i in 0 until kTerminalData.width) {
                val drawColor = when(kTerminalData.terminal[i][j].isSubCellEnabled) {
                    true -> Color.CLEAR.toFloatBits()
                    false -> kTerminalData.terminal[i][j].backgroundColor
                }

                val glyph = kTerminalData.terminal[i][j]
                val scaleX = if(glyph.isFlippedY) -glyph.scale else glyph.scale
                val scaleY = if(glyph.isFlippedX) -glyph.scale else glyph.scale

                draw(TextureRegion(backgroundTexture), drawColor, glyph, i, j, scaleX, scaleY)

            }
        }

        for(j in 0 until kTerminalData.height) {
            for(i in 0 until kTerminalData.width) {
                val glyph = kTerminalData.terminal[i][j]
                val scaleX = if(glyph.isFlippedY) -glyph.scale else glyph.scale
                val scaleY = if(glyph.isFlippedX) -glyph.scale else glyph.scale

                if(glyph.value >= columns * rows) {
                    throw IllegalArgumentException("glyph value [${glyph.value}] exceeds found glyph count [${(columns * rows) - 1}]")
                } else {
                    if(glyph.isSubCellEnabled) {

                        val topLeft = glyph.subCellGlyph.subCells[0][0]
                        val topRight = glyph.subCellGlyph.subCells[1][0]
                        val bottomLeft = glyph.subCellGlyph.subCells[0][1]
                        val bottomRight = glyph.subCellGlyph.subCells[1][1]

                        draw(glyphs[topLeft.value], topLeft.color, glyph, i, j, scaleX, scaleY)
                        draw(glyphs[topRight.value], topRight.color, glyph, i, j, scaleX, scaleY)
                        draw(glyphs[bottomLeft.value], bottomLeft.color, glyph, i, j, scaleX, scaleY)
                        draw(glyphs[bottomRight.value], bottomRight.color, glyph, i, j, scaleX, scaleY)
                    }

                    draw(glyphs[glyph.value], kTerminalData.terminal[i][j].foregroundColor, glyph, i, j, scaleX, scaleY)
                }
            }
        }

        batch.packedColor = originalColor
    }

    override fun dispose() {
        glyphTexture.dispose()
        backgroundTexture.dispose()
    }
}
