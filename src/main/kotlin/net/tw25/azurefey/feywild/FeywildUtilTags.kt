package net.tw25.azurefey.feywild

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object FeywildUtilTags {
    val VOIDLIKE: TagKey<Block> = TagKey.create(
        Registries.BLOCK,
        FeywildUtilMod.id("voidlike")
    )
}