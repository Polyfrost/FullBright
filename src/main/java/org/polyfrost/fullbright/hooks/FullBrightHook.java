package org.polyfrost.fullbright.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;
import org.polyfrost.fullbright.FullBright;

public class FullBrightHook {
    public static boolean shouldUpdateLightLevel() {
        IntegratedServer server = Minecraft.getMinecraft().getIntegratedServer();
        //#if MC < 11605
        boolean sameThread = server.isCallingFromMinecraftThread();
        //#else
        //$$ boolean sameThread = Minecraft.getInstance().isSameThread();
        //#endif
        return (server == null || !sameThread) && FullBright.config.fullBrightMode == 1;
    }
}
