package io.github.unongmilkumk.event

import io.github.unongmilkumk.plug.MetaDataPlug.getData
import io.github.unongmilkumk.plug.MetaDataPlug.hasData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

object ItemPlugEvent : Listener {
    @Suppress("UNCHECKED_CAST")
    @EventHandler
    fun interaction(event : PlayerInteractEvent) {
        val i = event.player.inventory.itemInMainHand
        if (i.itemMeta.hasData("interact")) {
            (i.itemMeta.getData("interact") as (event : PlayerInteractEvent) -> Unit).invoke(event)
        }
    }
}