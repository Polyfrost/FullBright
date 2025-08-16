package org.polyfrost.fullbright.hooks;

import net.minecraft.server.MinecraftServer;
import org.polyfrost.fullbright.FullBright;

public class FullBrightHook {
    public static boolean shouldUpdateLightLevel() {
        MinecraftServer server = MinecraftServer.getServer();
        return server == null || !server.isCallingFromMinecraftThread() && FullBright.config.fullBrightMode == 1;
    }
}
