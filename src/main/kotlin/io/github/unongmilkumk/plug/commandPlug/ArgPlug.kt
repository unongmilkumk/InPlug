package io.github.unongmilkumk.plug.commandPlug

import io.github.unongmilkumk.plug.commandPlug.CommandPlug.Argu
import org.bukkit.entity.Player

class ArgPlug(var argu : Argu, var commandName : String) {
    lateinit var run : (player : Player) -> Unit
    lateinit var argus : ArrayList<ArguPlug>
    fun run(function : (player : Player) -> Unit): ArgPlug {
        run = function
        return this
    }
    fun arg(text : String, function : (player : Player) -> Unit): ArguPlug {
        val b = ArguPlug(Argu(text, function), argu, commandName)
        argus.add(b)
        return b
    }
    fun finishArg(): CommandPlug {
        return CommandPlug(commandName)
    }
    @Suppress("SENSELESS_COMPARISON")
    fun command(player: Player, args: Array<String>) {
        if (argus == null || argus.size == 0) {
            run.invoke(player)
        } else {
            for (a in argus) {
                if (args[1] == a.argu.text) {
                    a.command(player, args, 2)
                }
            }
        }
    }
}