package io.github.unongmilkumk.plug.commandPlug

import io.github.unongmilkumk.plug.commandPlug.CommandPlug.Argu
import org.bukkit.entity.Player

class ArguPlug(var argu : Argu, var back : Argu, var commandName : String) {
    lateinit var run : (player : Player) -> Unit
    lateinit var argus : ArrayList<ArguPlug>
    fun run(function : (player : Player) -> Unit) : ArguPlug {
        run = function
        return this
    }
    fun arg(text : String, function : (player : Player) -> Unit): ArguPlug {
        val b = ArguPlug(Argu(text, function), argu, commandName)
        argus.add(b)
        return b
    }
    fun finishArg(): ArgPlug {
        return ArgPlug(back, commandName)
    }
    @Suppress("SENSELESS_COMPARISON")
    fun command(player: Player, args: Array<String>, size : Int) {
        if (argus == null || argus.size == 0) {
            run.invoke(player)
        } else {
            for (a in argus) {
                if (args[size] == a.argu.text) {
                    a.command(player, args, size + 1)
                }
            }
        }
    }
}