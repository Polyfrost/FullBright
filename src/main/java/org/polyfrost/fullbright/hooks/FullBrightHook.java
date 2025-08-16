package org.polyfrost.fullbright.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import org.polyfrost.fullbright.FullBright;

public class FullBrightHook {
    public static boolean shouldUpdateLightLevel() {
        IntegratedServer server = Minecraft.getMinecraft().getIntegratedServer();
        return (server == null || !server.isCallingFromMinecraftThread()) && FullBright.config.fullBrightMode == 1;
    }
}
