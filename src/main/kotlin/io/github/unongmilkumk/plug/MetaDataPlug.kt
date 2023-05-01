package io.github.unongmilkumk.plug

import org.bukkit.inventory.meta.ItemMeta

object MetaDataPlug {
    private var mcmap = HashMap<String, Any>()
    data class MetaClass(var im : ItemMeta, var source: String){
        override fun toString(): String {
            return (im.toString() + source)
        }
    }
    fun ItemMeta.hasData(source: String) : Boolean {
        return mcmap[MetaClass(this, source).toString()] != null
    }
    fun ItemMeta.setData(source : String, key : Any) {
        mcmap[MetaClass(this, source).toString()] = key
    }
    fun ItemMeta.getData(source: String) : Any? {
        return mcmap[MetaClass(this, source).toString()]
    }
}