package net.tw25.azurefey.feywild

import net.minecraft.world.item.Item
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block
import net.tw25.azurefey.feywild.blocks.MagicMirrorBlock
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Rarity


object FeywildUtilBlocks {
    @JvmStatic
    val TALL_MAGIC_MIRROR: Block = registerBlock("tall_magic_mirror", MagicMirrorBlock, Item.Properties().rarity(Rarity.RARE))

    fun registerBlock(name: String, block: Block, item: Item.Properties): Block {
        Registry.register(BuiltInRegistries.ITEM, FeywildUtilMod.id(name), BlockItem(block, item))
        return Registry.register(BuiltInRegistries.BLOCK, FeywildUtilMod.id(name), block)
    }

    fun init() { }
}