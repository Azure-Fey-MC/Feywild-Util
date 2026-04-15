package net.tw25.azurefey.feywild.blocks

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult
import net.tw25.azurefey.feywild.FeywildUtilMod
import net.tw25.azurefey.feywild.behavior.MirrorTravel
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

object MagicMirrorBlock : HorizontalDirectionalBlock(Properties.of().destroyTime(4.0f).strength(2.0f).sound(SoundType.METAL)) {
    init {
        registerDefaultState(defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH))
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING)
    }

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        var facing: Direction = context.nearestLookingDirection.opposite
        if (facing != Direction.NORTH && facing != Direction.SOUTH && facing != Direction.EAST && facing != Direction.WEST) {
            facing = Direction.NORTH
        }
        return super.getStateForPlacement(context)!!.setValue(BlockStateProperties.HORIZONTAL_FACING, facing)
    }

    @Deprecated("Deprecated in Java")
    override fun getShape(
        state: BlockState,
        level: BlockGetter,
        pos: BlockPos,
        context: CollisionContext
    ): VoxelShape {
        return Shapes.box(0.0,0.0,0.0,1.0,2.0,1.0)
    }

    @Deprecated("Deprecated in Java")
    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        usedHand: InteractionHand,
        hit: BlockHitResult
    ): InteractionResult {
        val location = player.position()
        if (level.isClientSide) {
            return InteractionResult.CONSUME
        }
        MirrorTravel.useMirror(player, location)
        val mirrorDimension = ResourceKey.create(Registries.DIMENSION,FeywildUtilMod.id("mirror"))
        if (level.dimension() == Level.OVERWORLD) {
            player.server?.getLevel(mirrorDimension)?.setBlock(pos, state, 0)
        }  else if (level.dimension() == mirrorDimension) {
            player.server?.overworld()?.setBlock(pos, state, 0)
        }

        return InteractionResult.SUCCESS
    }
}