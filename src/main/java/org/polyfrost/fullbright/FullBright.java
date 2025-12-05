package org.polyfrost.fullbright;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#endif

import org.polyfrost.fullbright.config.FullBrightConfig;

//#if FORGE-LIKE
//#if MC <= 1.12.2
@net.minecraftforge.fml.common.Mod(modid = FullBright.ID, name = FullBright.NAME, version = FullBright.VERSION)
//#else
//#if NEOFORGE
//$$ @net.neoforged.fml.common.Mod(FullBright.ID)
//#else
//$$ @net.minecraftforge.fml.common.Mod(FullBright.ID)
//#endif
//#endif
//#endif
public class FullBright
        //#if FABRIC
        //$$ implements ModInitializer
        //#endif
{
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static final FullBright INSTANCE = new FullBright();
    public static FullBrightConfig config;

    //#if FORGE-LIKE
    //#if MC <= 1.12.2
    @net.minecraftforge.fml.common.Mod.EventHandler
    private void onInit(net.minecraftforge.fml.common.event.FMLPostInitializationEvent ev) {
        init();
    }
    //#else
    //$$ static {
    //$$     INSTANCE.init();
    //$$ }
    //#endif
    //#else
    //$$ @Override
    //$$ public void onInitialize() {
    //$$     init();
    //$$ }
    //#endif

    public void init() {
        config = new FullBrightConfig();
    }
}