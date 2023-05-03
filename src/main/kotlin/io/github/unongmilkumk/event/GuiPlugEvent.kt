package io.github.unongmilkumk.event

import io.github.unongmilkumk.plug.GuiPlug
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

object GuiPlugEvent : Listener {
    var inventories = arrayListOf<GuiPlug>()
    @Suppress("DEPRECATION")
    @EventHandler
    fun invenclick(event : InventoryClickEvent) {
        inventories.forEach {
            if (it.name == event.view.title) {
                it.inter[event.slot]!!.invoke(event)
            }
        }
    }
}