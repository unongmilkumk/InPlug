package io.github.unongmilkumk

import io.github.unongmilkumk.event.ItemPlugEvent
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin

object InPlug {
    lateinit var pl : JavaPlugin
    fun register(jp : JavaPlugin) {
        pl = jp
        if (!jp.dataFolder.exists()) jp.dataFolder.mkdir()
        Bukkit.getPluginManager().registerEvents(ItemPlugEvent, jp)
    }
    fun registerCommand(name : String, ce : CommandExecutor) {
        pl.getCommand(name)?.setExecutor(ce)
    }
}