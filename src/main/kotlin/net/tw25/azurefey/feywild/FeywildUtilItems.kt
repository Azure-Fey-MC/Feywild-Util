package net.tw25.azurefey.feywild

import net.minecraft.world.item.Item
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.tw25.azurefey.feywild.items.MagicMirrorItem

object FeywildUtilItems {
    @JvmStatic
    val MAGIC_MIRROR: Item = registerItem("magic_mirror", MagicMirrorItem)

    fun registerItem(name: String, item: Item): Item {
        return Registry.register(BuiltInRegistries.ITEM, FeywildUtilMod.id(name), item)
    }

    fun init() { }
}