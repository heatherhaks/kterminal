package com.halfdeadgames.offscreenrenderer

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.utils.Disposable
import ktx.app.use

class OffScreenRenderer(width: Int, height: Int, private val batch: Batch) : Disposable {
    private val buffer = FrameBuffer(Pixmap.Format.RGBA8888, width, height, false)
    val transformMatrix: Matrix4 = Matrix4().scl(1f, -1f, 1f).translate(0f, -buffer.height.toFloat(), 0f)
    val projectionMatrix: Matrix4 = Matrix4().setToOrtho2D(0f, 0f, buffer.width.toFloat(), buffer.height.toFloat())

    fun render(batchColor: Color = Color.WHITE, clearColor: Color = Color.CLEAR, block: (Batch) -> Unit): Texture {
        buffer.use {
            // clearing the color here to due a bug where fading a modal in or out changes the color of the batch and can affect the background opacity
            // flip y axis to match normal libgdx orientation
            batch.use(batchColor, transformMatrix, projectionMatrix) {
                clearScreen(clearColor)
                block(batch)
            }
        }
        return buffer.colorBufferTexture
    }

    fun getBufferTexture() : Texture{
        return buffer.colorBufferTexture
    }

    fun FrameBuffer.use(action: (FrameBuffer) -> Unit) {
        begin()
        action(this)
        end()
    }

    private fun Batch.use(color: Color? = null, transform: Matrix4? = null, projection: Matrix4? = null, action: (Batch) -> Unit) {
        val originalColor = this.color
        val originalTransform = this.transformMatrix.cpy()
        val originalProjection = this.projectionMatrix.cpy()

        this.color = color ?: this.color
        this.transformMatrix = transform ?: this.transformMatrix
        this.projectionMatrix = projection ?: this.projectionMatrix

        use(action)

        this.color = originalColor ?: this.color
        this.transformMatrix = originalTransform ?: this.transformMatrix
        this.projectionMatrix = originalProjection ?: this.projectionMatrix
    }

    private fun clearScreen(clearColor: Color = Color.BLACK) {
        ktx.app.clearScreen(clearColor.r, clearColor.g, clearColor.b, clearColor.a)
    }

    override fun dispose() = buffer.dispose()
}