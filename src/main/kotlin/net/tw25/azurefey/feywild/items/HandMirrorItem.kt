package net.tw25.azurefey.feywild.items

import net.minecraft.ChatFormatting
import net.minecraft.client.KeyMapping
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.tw25.azurefey.feywild.FeywildUtilConfig
import net.tw25.azurefey.feywild.FeywildUtilItems
import kotlin.random.Random

object HandMirrorItem : Item(Properties().stacksTo(1).rarity(Rarity.COMMON)) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack?>? {
        val stack = player.getItemInHand(usedHand)
        val selection = Random.nextInt(1,5)
        if (Random.nextInt(1,10) <= 2) {
            player.displayClientMessage(Component.translatable("item.feywild.hand_mirror.reflection.bad.$selection"), true)
        } else {
            player.displayClientMessage(Component.translatable("item.feywild.hand_mirror.reflection.good.$selection"), true)
        }
        return InteractionResultHolder.success(stack)
    }

    override fun useOn(context: UseOnContext): InteractionResult {
        if (FeywildUtilConfig.debug) {
            context.player!!.displayClientMessage(Component.literal(" hand: "+context.hand.toString()), false)
        }
        val voidlikeBlocks: Array<Block> = arrayOf(Blocks.END_PORTAL, Blocks.END_GATEWAY) // TODO: Replace with block tag
        if (context.player!!.level().getBlockState(context.clickedPos).block in voidlikeBlocks) {
            context.player!!.setItemInHand(context.hand, ItemStack(FeywildUtilItems.MAGIC_MIRROR, context.itemInHand.count))
            return InteractionResult.SUCCESS
        } else {
            use(context.level, context.player!!, context.hand)
            return InteractionResult.FAIL
        }
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: List<Component?>,
        isAdvanced: TooltipFlag
    ) {
        (tooltipComponents as MutableList).add(Component.translatable("item.feywild.description.action.use.target", "Void like blocks").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD))
        tooltipComponents.add(Component.translatable("item.feywild.hand_mirror.description.use").append(Component.translatable("item.feywild.magic_mirror")))
    }
}