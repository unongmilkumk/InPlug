package io.github.unongmilkumk

import io.github.unongmilkumk.event.ItemPlugEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

object InPlug {
    lateinit var pl : JavaPlugin
    fun register(jp : JavaPlugin) {
        pl = jp
        Bukkit.getPluginManager().registerEvents(ItemPlugEvent, jp)
    }
}