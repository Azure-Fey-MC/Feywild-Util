package net.tw25.azurefey.feywild

import gay.asoji.fmw.FMW
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import eu.midnightdust.lib.config.MidnightConfig
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.ChatFormatting
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

/**
 * This is your mod's main entrypoint class, this runs on both Client and Server.
 * What you put in here will be loaded on mod initialization time
 */
object FeywildUtilMod : ModInitializer {
    val MOD_ID: String = "feywild"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    val MOD_NAME: String = FMW.getName(MOD_ID)
    val OLD_MOD_ID: String = "shattered_reality"
    @JvmStatic fun id(string: String) = ResourceLocation(MOD_ID, string)
    @JvmStatic fun oldId(string: String) = ResourceLocation(OLD_MOD_ID, string)

    /**
     * This code runs as soon as Minecraft is in a mod-load-ready state.
     * However, some things (like resources) may still be uninitialized.
     * Proceed with mild caution.
     */
    override fun onInitialize() {
        LOGGER.info("[${MOD_NAME}] Hello Fabric world from $MOD_NAME/$MOD_ID")
        MidnightConfig.init(MOD_ID, FeywildUtilConfig::class.java)
        FeywildUtilItems.init()
        FeywildUtilBlocks.init()
        FeywildUtilCreativeTab.init()

        ServerTickEvents.EndTick { server: MinecraftServer ->
            if (FeywildUtilConfig.compatibilityMode && server.tickCount % 20 == 0 && FabricLoader.getInstance().isModLoaded("shattered_reality")) {4
                fun replaceOldItem(player: Player, slot: Int, oldItem: ResourceKey<Item>, newItem: Item) {
                    val stackCount = player.inventory.getItem(slot).count
                    if (player.inventory.getItem(slot).item == oldItem) {
                        player.inventory.setItem(slot, ItemStack(newItem, stackCount))
                        player.displayClientMessage(
                            Component.translatable(
                                "feyfild.compatibility",
                                newItem.getName(player.inventory.getItem(slot))).withStyle(
                            ChatFormatting.RED
                                ),
                            false)
                    }
                }
                val playerListings = server.playerList.players
                val slots: List<Int> = (0..35).toMutableList()+(200..226).toMutableList()+(98..103).toMutableList()
                for (player in playerListings) {
                    for (slot in slots) {
                        replaceOldItem(player, slot, ResourceKey.create(Registries.ITEM,oldId("hand_mirror")), FeywildUtilItems.HAND_MIRROR)
                        replaceOldItem(player, slot, ResourceKey.create(Registries.ITEM,oldId("magic_mirror_new")), FeywildUtilItems.MAGIC_MIRROR)
                    }
                }
            }
        }

    }
}