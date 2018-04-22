package com.halfdeadgames.kterminalexample.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.halfdeadgames.kterminalexample.KTerminalExample

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()

        config.title = "${KTerminalExample.TITLE} v${KTerminalExample.VERSION}"
        config.width = KTerminalExample.V_WIDTH
        config.height = KTerminalExample.V_HEIGHT
        config.backgroundFPS = 60
        config.foregroundFPS = 60
        config.resizable = false

        LwjglApplication(KTerminalExample(), config)
    }
}
