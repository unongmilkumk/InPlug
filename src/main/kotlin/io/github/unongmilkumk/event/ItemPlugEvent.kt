package io.github.unongmilkumk.event

import io.github.unongmilkumk.plug.itemPlug.MetaDataPlug.getData
import io.github.unongmilkumk.plug.itemPlug.MetaDataPlug.hasData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

object ItemPlugEvent : Listener {
    @Suppress("UNCHECKED_CAST")
    @EventHandler
    fun interaction(event : PlayerInteractEvent) {
        val i = event.player.inventory.itemInMainHand
        if (i.itemMeta.hasData("inplug.interact")) {
            (i.itemMeta.getData("inplug.interact") as (event : PlayerInteractEvent) -> Unit).invoke(event)
        }
    }
}