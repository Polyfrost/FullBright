package org.polyfrost.fullbright.mixins;

//#if MC>=12001
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import net.minecraft.client.renderer.LightTexture;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
//$$ import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
//$$ import org.polyfrost.fullbright.FullBright;

//$$ @Mixin(LightTexture.class)
public class LightTextureMixin {
//$$ @WrapOperation(method = "updateLightTexture", at = @At(value = "INVOKE", target = "Ljava/lang/Double;floatValue()F"))
//$$ private float modifyGamma(Double instance, Operation<Float> original) {
//$$     return FullBright.config.fullBrightMode == 0 ? FullBright.config.gamma : original.call(instance);
//$$ }
}
//#endif