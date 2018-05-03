package com.halfdeadgames.offscreenrenderer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.HdpiUtils.glViewport
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.utils.Disposable
import ktx.app.clearScreen
import ktx.app.use

class OffScreenRenderer(var width: Int, var height: Int, private val batch: Batch, var clearScreen: Boolean = false) : Disposable {
    private val buffer = FrameBuffer(Pixmap.Format.RGBA8888, width, height, false)

    val textureRegion: TextureRegion

    init {
        textureRegion = TextureRegion(buffer.colorBufferTexture)
        textureRegion.flip(false, true)
    }

    fun render(batchColor: Color = Color.WHITE, clearColor: Color = Color.CLEAR, scissor: Boolean = false, block: (Batch) -> Unit): Texture {
        buffer.use {
            // clearing the color here to due a bug where fading a modal in or out changes the color of the batch and can affect the background opacity
            if(clearScreen) clearScreen()
            if(scissor) glViewport(0, 0, width, height)
            batch.use(batchColor) {
                block(batch)
            }
        }
        return buffer.colorBufferTexture
    }

    private fun FrameBuffer.use(action: (FrameBuffer) -> Unit) {
        begin()
        action(this)
        end()
    }

    private fun Batch.use(color: Color? = null, action: (Batch) -> Unit) {
        val originalColor = this.color
        this.color = color ?: this.color

        use(action)

        this.color = originalColor ?: this.color
    }

    private fun clearScreen(clearColor: Color = Color.BLACK) {
        clearScreen(clearColor.r, clearColor.g, clearColor.b, clearColor.a)
    }

    override fun dispose() = buffer.dispose()
}
