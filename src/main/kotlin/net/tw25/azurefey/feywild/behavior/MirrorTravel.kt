package net.tw25.azurefey.feywild.behavior

import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.phys.Vec3
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.RelativeMovement
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.BedBlock
import net.minecraft.world.level.levelgen.Heightmap
import net.tw25.azurefey.feywild.FeywildUtilConfig
import net.tw25.azurefey.feywild.FeywildUtilMod

object MirrorTravel {
    fun useMirror(entity: LivingEntity, position: Vec3) {
        if (FeywildUtilConfig.debug) {
            FeywildUtilMod.LOGGER.info("Input Position: {}", position)
        }

        val mirrorDimension = ResourceKey.create(Registries.DIMENSION,FeywildUtilMod.id("mirror"))

        val dim: ServerLevel? = if (entity.level().dimension() == Level.OVERWORLD) {
            entity.server!!.getLevel(mirrorDimension)
        }  else if (entity.level().dimension() == mirrorDimension) {
            entity.server!!.overworld()
        } else {
            null
        }

        if (dim == null) return

        val positionAdjusted = BedBlock.findStandUpPosition(entity.type, dim, BlockPos.containing(position).offset(-1,0,-1), entity.direction, entity.yRot)

        val safePosition = if (positionAdjusted.isPresent) {
            positionAdjusted.get()
        } else {
            val safeBlockPos = BlockPos.containing(position).mutable()

            do safeBlockPos.y++
            while (dim.getBlockState(safeBlockPos).isSuffocating(dim, safeBlockPos))

            val heightmapPos = dim.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, safeBlockPos)

            Vec3.atBottomCenterOf(if (safeBlockPos.y > heightmapPos.y) heightmapPos else safeBlockPos)
        }

        if (FeywildUtilConfig.debug) {
            FeywildUtilMod.LOGGER.info("Output Position: {}", safePosition)
        }
        entity.teleportTo(dim, safePosition.x, safePosition.y, safePosition.z, RelativeMovement.ALL, entity.yRot, entity.xRot)
    }
}