package org.polyfrost.fullbright.config;

import org.polyfrost.fullbright.FullBright;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Dropdown;
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;

public class FullBrightConfig extends Config {
    public FullBrightConfig() {
        super(FullBright.ID + ".json", FullBright.NAME, Category.QOL);

        loadFrom("patcher.toml")
    }

    @Dropdown(
            title = "FullBright Mode",
            options = {"Gamma", "Light Level"}
    )
    public int fullBrightMode = 0;

    @Slider(
            title = "Gamma",
            min = 100f, max = 500f, step = 1f
    )
    public int gamma = 500;

    @Slider(
            title = "Light Level",
            max = 15f, step = 1f
    )
    public int lightLevel = 15;
}
