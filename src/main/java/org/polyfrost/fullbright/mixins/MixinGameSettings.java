package org.polyfrost.fullbright.mixins;

// legacy only
/*
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;
import org.polyfrost.fullbright.FullBright;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("RedundantCast")
@Mixin(EntityRenderer.class)
public class MixinGameSettings {
    @Redirect(
            method = "updateLightmap", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;gammaSetting:F")
    )
    private float updateGamma(GameSettings instance) {
        return FullBright.config.fullBrightMode == 0 ? FullBright.config.gamma : (float) instance.gammaSetting;
    }
}
*/