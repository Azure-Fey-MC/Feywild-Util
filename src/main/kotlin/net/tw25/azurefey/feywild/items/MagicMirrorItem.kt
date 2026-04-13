package net.tw25.azurefey.feywild.items

import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.tw25.azurefey.feywild.FeywildUtilItems
import net.tw25.azurefey.feywild.behavior.MirrorTravel
import net.tw25.azurefey.feywild.client.FeywildUtilModClient

object MagicMirrorItem : Item(Properties().stacksTo(1).rarity(Rarity.RARE).craftRemainder(FeywildUtilItems.HAND_MIRROR)) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack?>? {
        val stack = player.getItemInHand(usedHand)
        val location = player.position()
        if (level.isClientSide) {
            return InteractionResultHolder.consume(stack)
        }
        MirrorTravel.useMirror(player, location)
        return InteractionResultHolder.success(stack)
    }

    override fun hurtEnemy(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        MirrorTravel.useMirror(target, target.position())
        return true
    }

    override fun isFoil(stack: ItemStack): Boolean {
        return true
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: List<Component?>,
        isAdvanced: TooltipFlag
    ) {
        (tooltipComponents as MutableList).add(Component.translatable("item.feywild.magic_mirror.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC))
        tooltipComponents.add(Component.translatable("item.feywild.description.action.use").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD))
        tooltipComponents.add(Component.translatable("item.feywild.magic_mirror.description.use").append(Component.translatable("dimension.feywild.mirror")))
        tooltipComponents.add(Component.translatable("item.feywild.description.action.hit").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.BOLD))
        tooltipComponents.add(Component.translatable("item.feywild.magic_mirror.description.hit").append(Component.translatable("dimension.feywild.mirror")))
    }
}