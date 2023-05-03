package io.github.unongmilkumk.plug.commandPlug

import io.github.unongmilkumk.InPlug
import org.bukkit.entity.Player

class CommandPlug(var commandName : String) {
    data class Argu(var text : String, var argf : (player : Player) -> Unit)
    lateinit var run : (player : Player) -> Unit
    lateinit var argus : ArrayList<ArgPlug>
    fun run(function : (player : Player) -> Unit) : CommandPlug {
        run = function
        return this
    }
    fun arg(text : String, function : (player : Player) -> Unit): ArgPlug {
        val b = ArgPlug(Argu(text, function), commandName)
        argus.add(b)
        return b
    }
    fun inplug() {
        InPlug.registerCommand(commandName) { sender, _, _, args ->
            val p = sender as Player
            if (args.isEmpty()) run.invoke(p)
            for (a in argus) {
                if (args[0] == a.argu.text) {
                    a.command(p, args)
                }
            }
            true
        }
    }
}