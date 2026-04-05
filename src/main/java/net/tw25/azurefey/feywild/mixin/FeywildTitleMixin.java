package net.tw25.azurefey.feywild.mixin;

import net.minecraft.client.gui.screens.TitleScreen;
import net.tw25.azurefey.feywild.FeywildUtilMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This is an Example mixin that prints out `This line is printed by the Nautical feywild mod mixin` on initialization
 * of the TitleScreen, but this mixin can be replaced with any other thing you want to mixin.
 * <p>
 * Mixins **must** be in Java! They **cannot** be in Kotlin!
 */
@Mixin(TitleScreen.class)
public class FeywildTitleMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        FeywildUtilMod.INSTANCE.getLOGGER().info("Minecraft loaded with Feywild Util, yay!");
    }
}