package io.github.unongmilkumk.plug

import io.github.unongmilkumk.InPlug
import org.bukkit.Particle
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable

class AbilityPlug(var player : Player) {
    data class AbilityArg(var caster : Player, var target : LivingEntity?)
    var throwititem : () -> Unit = {}
    var throwitpart : () -> Unit = {}
    var near : () -> Unit = {}
    var caster : () -> Unit = {}
    fun throwit(item : ItemStack, function : (AbilityArg) -> Unit) : AbilityPlug {
        throwititem =  {
            val v = player.location.add(player.location.direction.multiply(10)).subtract(player.location).toVector().normalize()
            val amo = player.world.spawnEntity(player.location.add(0.0, 0.5, 0.0).add(v), EntityType.ARMOR_STAND) as ArmorStand
            amo.isVisible = false
            amo.setArms(true)
            amo.setGravity(false)
            amo.isSmall = true
            amo.isMarker = true
            amo.setItem(EquipmentSlot.HEAD, item)

            object : BukkitRunnable() {
                var d = 50
                var i = 0

                override fun run() {
                    amo.teleport(amo.location.add(v.normalize().multiply(2)))
                    if (!amo.location.add(0.0, 1.0, 0.0).block.type.isAir && !amo.location.add(0.0, 1.0, 0.0).block.isPassable) {
                        if (!amo.isDead) {
                            amo.remove()
                        }
                        cancel()
                    }
                    for (e in amo.location.chunk.entities) {
                        if (!amo.isDead) {
                            if (amo.location.distanceSquared(e.location) <= 1.0) {
                                if (e != player && e != amo) {
                                    if (e is LivingEntity) {
                                        function.invoke(AbilityArg(player, e))
                                        amo.remove()
                                        cancel()
                                    }
                                }
                            }
                        }
                    }
                    if (i > d && !amo.isDead) {
                        amo.remove()
                        cancel()
                    }
                    i++
                }
            }.runTaskTimer(InPlug.pl, 0L, 1L)
        }
        return this
    }
    fun throwit(particle : Particle, function : (AbilityArg) -> Unit) : AbilityPlug {
        throwitpart = {
            val v = player.location.add(player.location.direction.multiply(10)).subtract(player.location).toVector().normalize()
            val amo = player.world.spawnEntity(player.location.add(0.0, 0.5, 0.0).add(v), EntityType.ARMOR_STAND) as ArmorStand
            amo.isVisible = false
            amo.setArms(true)
            amo.setGravity(false)
            amo.isSmall = true
            amo.isMarker = true

            object : BukkitRunnable() {
                var d = 50
                var i = 0

                override fun run() {
                    amo.teleport(amo.location.add(v.normalize().multiply(2)))
                    amo.location.world.spawnParticle(particle, amo.location, 3)
                    if (!amo.location.add(0.0, 1.0, 0.0).block.type.isAir && !amo.location.add(0.0, 1.0, 0.0).block.isPassable) {
                        if (!amo.isDead) {
                            amo.remove()
                        }
                        cancel()
                    }
                    for (e in amo.location.chunk.entities) {
                        if (!amo.isDead) {
                            if (amo.location.distanceSquared(e.location) <= 1.0) {
                                if (e != player && e != amo) {
                                    if (e is LivingEntity) {
                                        function.invoke(AbilityArg(player, e))
                                        amo.remove()
                                        cancel()
                                    }
                                }
                            }
                        }
                    }
                    if (i > d && !amo.isDead) {
                        amo.remove()
                        cancel()
                    }
                    i++
                }
            }.runTaskTimer(InPlug.pl, 0L, 1L)
        }
        return this
    }
    fun near(radius : Int, function : (AbilityArg) -> Unit) : AbilityPlug {
        near = {
            for (nearbyLivingEntity in player.location.getNearbyLivingEntities(radius.toDouble())) {
                function.invoke(AbilityArg(player, nearbyLivingEntity))
            }
        }
        return this
    }
    fun caster(function : (Player) -> Unit) : AbilityPlug {
        caster = {
            function.invoke(player)
        }
        return this
    }
    fun inplug() {
        throwititem()
        throwitpart()
        near()
        caster()
    }
}