package io.github.unongmilkumk.plug

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class GuiPlug(var name : String, var rows : Int) {
    var defItem = ItemStack(Material.AIR)
    var defInter : (event : InventoryClickEvent) -> Unit = {}
    var inter = HashMap<Int, (InventoryClickEvent) -> Unit>()
    var inventory = Bukkit.createInventory(null, 6 * rows, Component.text(name))
    fun defItem(item : ItemStack) {
        defItem = item
    }
    fun defInteract(inter : (event : InventoryClickEvent) -> Unit) {
        defInter = inter
    }
    fun item(item : ItemStack, vararg slot : Int) {
        slot.forEach {
            inventory.setItem(it, item)
        }
    }
    fun interaction(slot : Int, function : (event : InventoryClickEvent) -> Unit) {
        inter[slot] = function
    }
    fun inplug(): Inventory {
        for (i in (0 until inventory.size - 1)) {
            if (inventory.getItem(i)?.type == null || inventory.getItem(i)!!.type.isAir) {
                inventory.setItem(i, defItem)
            }
        }
        (0..6 * rows).forEach {
            if (!inter.keys.contains(it)) inter[it] = defInter
        }
        return inventory
    }
}