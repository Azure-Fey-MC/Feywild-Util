package net.tw25.azurefey.feywild

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object FeywildUtilCreativeTab {
    val FEYWILD_UTIL_GROUP: CreativeModeTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FeywildUtilMod.id("feywild"), FabricItemGroup.builder().icon { ItemStack(FeywildUtilItems.MAGIC_MIRROR) }.title(
        Component.translatable("itemGroup.feywild.feywild")).displayItems { parameters, output ->
        output.accept(FeywildUtilItems.HAND_MIRROR)
        output.accept(FeywildUtilItems.MAGIC_MIRROR)
        output.accept(FeywildUtilBlocks.TALL_MAGIC_MIRROR.asItem())
    }.build())

    fun init() {}
}