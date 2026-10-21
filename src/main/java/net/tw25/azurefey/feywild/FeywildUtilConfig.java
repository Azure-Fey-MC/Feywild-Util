package net.tw25.azurefey.feywild;

import eu.midnightdust.lib.config.MidnightConfig;

public class FeywildUtilConfig extends MidnightConfig {
    public static final String TEXT = "text";
    public static final String NUMBERS = "numbers";
    public static final String SLIDERS = "sliders";
    public static final String LISTS = "lists";
    public static final String FILES = "files";
    public static final String CONDITIONS = "conditions";

    @Entry(category = TEXT) public static boolean debug = false;
}
