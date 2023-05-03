package io.github.unongmilkumk.plug

import org.bukkit.Location
import org.bukkit.World
import kotlin.math.max
import kotlin.math.min

class CuboidPlug(private val world: World, x1: Int, y1: Int, z1: Int, x2: Int, y2: Int, z2: Int) {
    val minX: Int = min(x1, x2)
    val maxX: Int = max(x1, x2)
    val minY: Int = min(y1, y2)
    val maxY: Int= max(y1, y2)
    val minZ: Int = min(z1, z2)
    val maxZ: Int = max(z1, z2)

    constructor(loc1: Location, loc2: Location) : this(
        loc1.world,
        loc1.blockX,
        loc1.blockY,
        loc1.blockZ,
        loc2.blockX,
        loc2.blockY,
        loc2.blockZ
    )

    private fun getWorld(): World {
        return world
    }

    operator fun contains(cuboid: CuboidPlug): Boolean {
        return cuboid.getWorld() == world && cuboid.minX >= minX && cuboid.maxX <= maxX && cuboid.minY >= minY && cuboid.maxY <= maxY && cuboid.minZ >= minZ && cuboid.maxZ <= maxZ
    }

    operator fun contains(location: Location): Boolean {
        return contains(location.blockX, location.blockY, location.blockZ)
    }

    fun contains(x: Int, y: Int, z: Int): Boolean {
        return x in minX..maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || other !is CuboidPlug) {
            return false
        }
        return (world == other.world
                && minX == other.minX && minY == other.minY && minZ == other.minZ && maxX == other.maxX && maxY == other.maxY && maxZ == other.maxZ)
    }

    override fun toString(): String {
        return "Cuboid[world:" + world.name +
                ", minX:" + minX.toString() +
                ", minY:" + minY.toString() +
                ", minZ:" + minZ.toString() +
                ", maxX:" + maxX.toString() +
                ", maxY:" + maxY.toString() +
                ", maxZ:" + maxZ.toString() + "]"
    }

    override fun hashCode(): Int {
        var result = world.hashCode()
        result = 31 * result + minX
        result = 31 * result + maxX
        result = 31 * result + minY
        result = 31 * result + maxY
        result = 31 * result + minZ
        result = 31 * result + maxZ
        return result
    }
}