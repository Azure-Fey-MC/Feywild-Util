package net.tw25.azurefey.feywild

import gay.asoji.fmw.FMW
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import eu.midnightdust.lib.config.MidnightConfig

/**
 * This is your mod's main entrypoint class, this runs on both Client and Server.
 * What you put in here will be loaded on mod initialization time
 */
object FeywildUtilMod : ModInitializer {
    val MOD_ID: String = "feywild"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    val MOD_NAME: String = FMW.getName(MOD_ID)

    /**
     * This code runs as soon as Minecraft is in a mod-load-ready state.
     * However, some things (like resources) may still be uninitialized.
     * Proceed with mild caution.
     */
    override fun onInitialize() {
        LOGGER.info("[${MOD_NAME}] Hello Fabric world from $MOD_NAME/$MOD_ID")
        MidnightConfig.init("feywild", FeywildUtilConfig::class.java)
    }
}