package io.github.unongmilkumk.plug

import io.github.unongmilkumk.InPlug
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

class ConfigPlug(fileName: String) {
    private var file : File
    private var config: FileConfiguration

    init {
        file = File(InPlug.pl.dataFolder, fileName)
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e : IOException) {
                e.printStackTrace()
            }
        }
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun has(source : String) = config.get(source) == null

    fun get(source : String) = config.get(source)

    fun set(source : String, data : Any) { config.set(source, data) }

    fun inplug() {
        try {
            config.save(file)
        } catch (e : IOException) {
            e.printStackTrace()
        }
    }
}