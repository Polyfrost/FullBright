package org.polyfrost.fullbright.config;

import org.polyfrost.fullbright.FullBright;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.Property;
import org.polyfrost.oneconfig.api.config.v1.annotations.Dropdown;
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

public class FullBrightConfig extends Config {
    public FullBrightConfig() {
        super(FullBright.ID + ".json", FullBright.NAME, Category.QOL);

        loadFrom("patcher.toml");

        addDependency("fullBrightMode", "fullBrightMode", () -> Property.Display.HIDDEN);
        addDependency("lightLevel", "lightLevel", () -> Property.Display.HIDDEN);
    }

    @Switch(
            title = "Enable FullBright"
    )
    public boolean enable = true;

    @Dropdown(
            title = "FullBright Mode",
            options = {"Gamma", "Light Level"}
    )
    public int fullBrightMode = 0;

    @Slider(
            title = "Gamma",
            max = 15f, step = 1f
    )
    public int gamma = 15;

    @Slider(
            title = "Light Level",
            max = 15f, step = 1f
    )
    public int lightLevel = 15;
}
