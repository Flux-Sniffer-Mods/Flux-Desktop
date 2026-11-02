package io.github.fluxsniffermods.desktop

import android.os.Bundle
import io.github.fluxsniffermods.core.link.FluxLinkProvider
import io.github.fluxsniffermods.core.link.LinkMethods

/** What the other Flux apps ask of the desktop (Flux Streaming lists it among its apps). */
class DesktopLink : FluxLinkProvider() {
    override fun answer(method: String, arg: String?, extras: Bundle?): Bundle? {
        val context = context ?: return null
        return when (method) {
            LinkMethods.DESKTOP_INFO -> Bundle().apply {
                putBoolean("installed", LinuxDesktop.installedMode(context) != null)
                putString("label", LinuxDesktop.distro(context).label)
                putBoolean("running", LinuxDesktop.state.value == LinuxDesktop.State.Running)
                putBoolean("starting", LinuxDesktop.state.value == LinuxDesktop.State.Starting)
            }
            else -> null
        }
    }
}
