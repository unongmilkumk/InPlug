package io.github.unongmilkumk.plug

import io.github.unongmilkumk.plug.MetaDataPlug.setData
import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class ItemPlug(mat : Material) {
    private var item = ItemStack(mat)
    private var im: ItemMeta = item.itemMeta!!
    private var interact: (PlayerInteractEvent) -> Unit = {}
    fun name(name : String) : ItemPlug {
        item.itemMeta = item.itemMeta.apply {
            this.displayName(text(name))
        }
        return this
    }
    fun lore(vararg description : String) : ItemPlug {
        item.itemMeta = item.itemMeta.apply {
            this.lore(description.toMutableList().map { text(it) })
        }
        return this
    }
    fun lore(description : MutableList<String>) : ItemPlug {
        item.itemMeta = item.itemMeta.apply {
            this.lore(description.map { text(it) })
        }
        return this
    }
    fun amount(amount: Int) : ItemPlug {
        item.amount = amount
        return this
    }
    fun interact(func: (PlayerInteractEvent) -> Unit) {
        interact = func
        item.itemMeta = item.itemMeta?.apply {
            this.setData("interact", func)
        }
    }
    fun inplug() : ItemStack = item
}