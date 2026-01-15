package org.polyfrost.fullbright.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.renderer.LightTexture;
import org.spongepowered.asm.mixin.injection.At;
import org.polyfrost.fullbright.FullBright;

@Mixin(LightTexture.class)
public class LightTextureMixin {
    @ModifyExpressionValue(method = "updateLightTexture", at = @At(value = "INVOKE", target = "Ljava/lang/Double;floatValue()F", ordinal = 1))
    private float modifyGamma(float original) {
        return FullBright.config.enable ? FullBright.config.gamma : original;
    }
}
