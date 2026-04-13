package net.tw25.azurefey.feywild.client

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.Minecraft
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.minecraft.world.phys.HitResult

/**
 * This entrypoint is suitable for setting up client-specific logic, such as rendering.
 */
object FeywildUtilModClient : ClientModInitializer {

    /**
     * This code runs on the Minecraft Client as soon as it's in a mod-load-ready state.
     * Just like with the Main entrypoint, some things may be still uninitialized, so proceed with caution.
     */
    override fun onInitializeClient() {}
}