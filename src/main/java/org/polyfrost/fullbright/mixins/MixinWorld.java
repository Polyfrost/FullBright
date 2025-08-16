package org.polyfrost.fullbright.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.World;
import org.polyfrost.fullbright.FullBright;
import org.polyfrost.fullbright.hooks.FullBrightHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(World.class)
public class MixinWorld {
    @ModifyReturnValue(
            method = {
                    "getLight(Lnet/minecraft/util/BlockPos;)I", "getLight(Lnet/minecraft/util/BlockPos;Z)I",
                    "getLightFromNeighbors", "getLightFromNeighborsFor", "getRawLight"
            },
            at = @At("RETURN")
    )
    private int getLight(int original) {
        return FullBrightHook.shouldUpdateLightLevel() ? FullBright.config.lightLevel : original;
    }

    @ModifyReturnValue(method = "checkLightFor", at = @At("RETURN"))
    private boolean updateLight(boolean original) {
        return FullBrightHook.shouldUpdateLightLevel() || original;
    }
}
