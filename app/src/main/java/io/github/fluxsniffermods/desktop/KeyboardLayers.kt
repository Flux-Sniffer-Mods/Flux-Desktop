package io.github.fluxsniffermods.desktop

import android.content.Context
import io.github.fluxsniffermods.core.FluxApp
import io.github.fluxsniffermods.core.link.FluxLink
import io.github.fluxsniffermods.core.link.LinkMethods

/** Flux Keyboard's Alt and Sym layers, asked of it over the link (null without it). Blocking. */
internal object KeyboardLayers {
    fun fetch(context: Context): KeyLayers? {
        val answer = FluxLink.call(context, FluxApp.KEYBOARD, LinkMethods.KEY_LAYERS) ?: return null
        fun layer(name: String): Map<Int, String> {
            val keys = answer.getIntArray(LinkMethods.layerKeys(name)) ?: return emptyMap()
            val chars = answer.getStringArray(LinkMethods.layerChars(name)) ?: return emptyMap()
            return keys.indices.filter { it < chars.size }.associate { keys[it] to chars[it] }
        }
        return KeyLayers(layer(LinkMethods.LAYER_ALT), layer(LinkMethods.LAYER_SYM), layer(LinkMethods.LAYER_SYM_SHIFTED))
    }
}
