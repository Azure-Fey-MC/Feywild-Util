package net.tw25.azurefey.feywild;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FeywildUtilConfig extends MidnightConfig {
    public static final String TEXT = "text";
    public static final String NUMBERS = "numbers";
    public static final String SLIDERS = "sliders";
    public static final String LISTS = "lists";
    public static final String FILES = "files";
    public static final String CONDITIONS = "conditions";

    @Entry(category = TEXT) public static boolean compatibilityMode = true;
    @Entry(category = TEXT) public static boolean debug = false;
}
