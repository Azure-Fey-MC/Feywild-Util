package net.tw25.azurefey.feywild.items

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.Level
import net.tw25.azurefey.feywild.behavior.MirrorTravel

object MagicMirrorItem : Item(Properties().stacksTo(1).rarity(Rarity.RARE)) {
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack?>? {
        val stack = player.getItemInHand(usedHand)
        val location = player.position()
        if (level.isClientSide) {
            return InteractionResultHolder.consume(stack)
        }
        MirrorTravel.useMirror(player, location)
        return InteractionResultHolder.success(stack)
    }
}