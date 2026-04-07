package net.tw25.azurefey.feywild.behavior

import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BedBlock
import net.minecraft.world.level.levelgen.Heightmap
import net.tw25.azurefey.feywild.FeywildUtilMod

object MirrorTravel {
    fun useMirror(player: Player, position: Vec3) {
        if (player !is ServerPlayer) {
            return
        }

        val mirrorDimension = ResourceKey.create(Registries.DIMENSION,FeywildUtilMod.id("mirror"))

        val dim: ServerLevel? = if (player.level().dimension() == Level.OVERWORLD) {
            player.server.getLevel(mirrorDimension)
        }  else if (player.level().dimension() == mirrorDimension) {
            player.server.overworld()
        } else {
            null
        }

        if (dim == null) return

        val positionAdjusted = BedBlock.findStandUpPosition(EntityType.PLAYER, dim, BlockPos.containing(position), player.direction, player.yRot)

        val safePosition = if (positionAdjusted.isPresent) {
            positionAdjusted.get()
        } else {
            val safeBlockPos = BlockPos.containing(position).mutable()

            do safeBlockPos.y++
            while (dim.getBlockState(safeBlockPos).isSuffocating(dim, safeBlockPos))

            val heightmapPos = dim.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, safeBlockPos)

            Vec3.atBottomCenterOf(if (safeBlockPos.y > heightmapPos.y) heightmapPos else safeBlockPos)
        }

        player.teleportTo(dim, safePosition.x, safePosition.y, safePosition.z, player.yRot, player.xRot)
    }
}