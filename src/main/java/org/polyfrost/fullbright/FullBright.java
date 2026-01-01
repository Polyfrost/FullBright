package org.polyfrost.fullbright;

import net.fabricmc.api.ModInitializer;

import org.polyfrost.fullbright.config.FullBrightConfig;

public class FullBright implements ModInitializer {
//    public static final String ID = "@MOD_ID@";
//    public static final String NAME = "@MOD_NAME@";
//    public static final String VERSION = "@MOD_VERSION@";

    public static final String ID = "fullbright";
    public static final String NAME = "FullBright";
    public static final String VERSION = "1.0.0";

    public static final FullBright INSTANCE = new FullBright();
    public static FullBrightConfig config;

    @Override
    public void onInitialize() {
        init();
    }

    public void init() {
        config = new FullBrightConfig();
    }
}