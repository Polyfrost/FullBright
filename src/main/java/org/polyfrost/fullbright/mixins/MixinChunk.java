package org.polyfrost.fullbright.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.chunk.Chunk;
import org.polyfrost.fullbright.FullBright;
import org.polyfrost.fullbright.hooks.FullBrightHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//#if MC<12001
@Mixin(Chunk.class)
public class MixinChunk {
    @ModifyReturnValue(method = {"getLightFor", "getLightSubtracted"}, at = @At("RETURN"))
    private int getLight(int original) {
        return FullBrightHook.shouldUpdateLightLevel() ? FullBright.config.lightLevel : original;
    }
}
//#endif