package io.github.fluxsniffermods.desktop

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import io.github.fluxsniffermods.core.link.LinkMethods

/**
 * The desktop opened for another Flux app (Flux Streaming: on the TV it streams to), started
 * first if it isn't running. An activity so the desktop starts in front, as Android wants.
 */
class LinuxOpenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screen = intent.getIntExtra(LinkMethods.EXTRA_SCREEN, -1).takeIf { it > 0 }
        val context = applicationContext
        when {
            LinuxDesktop.installedMode(this) == null -> Toast.makeText(this, R.string.tile_desktop_not_installed, Toast.LENGTH_LONG).show()
            LinuxDesktop.state.value == LinuxDesktop.State.Running -> LinuxDesktop.show(this, screen)
            LinuxDesktop.state.value == LinuxDesktop.State.Starting -> Toast.makeText(this, R.string.linux_starting, Toast.LENGTH_SHORT).show()
            else -> {
                @Suppress("DEPRECATION")
                val metrics = android.util.DisplayMetrics().also { windowManager.defaultDisplay.getRealMetrics(it) }
                val (w, h) = if (screen != null) 1920 to 1080 else metrics.widthPixels to metrics.heightPixels
                Toast.makeText(this, R.string.linux_starting, Toast.LENGTH_SHORT).show()
                LinuxDesktop.start(this, w, h) { LinuxDesktop.show(context, screen) }
            }
        }
        finish()
    }
}
