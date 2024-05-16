package com.badbones69.crazyenvoys.api.objects.misc.v2;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class TierHologram {

    private final boolean enabled;
    private final double height;
    private final int range;
    private final List<String> messages;
    private final String backgroundColor;

    /**
     * Empty constructor
     */
    public TierHologram() {
        this.enabled = false;
        this.height = 0.0;
        this.range = 8;
        this.messages = new ArrayList<>();
        this.backgroundColor = "transparent";
    }

    /**
     * A secondary constructor to build a hologram.
     *
     * @param enabled if the hologram enabled or not
     * @param height height of the hologram from the ground
     * @param range range the hologram can be seen
     * @param color the background color
     * @param messages the hologram will display
     */
    public TierHologram(final boolean enabled, final double height, final int range, @NotNull final String color, @NotNull final List<String> messages) {
        this.enabled = enabled;
        this.height = height;
        this.range = range;
        this.backgroundColor = color;
        this.messages = messages;
    }

    /**
     * Check if the hologram is enabled or not.
     *
     * @return true if yes otherwise false
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Gets the range at which a hologram can be seen.
     *
     * @return the range
     */
    public int getRange() {
        return this.range;
    }

    /**
     * Get the height of the hologram from the ground.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the background color of the hologram.
     *
     * @return the color
     */
    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Get the messages the hologram will display.
     *
     * @return the list of messages
     */
    public List<String> getMessages() {
        return this.messages;
    }

}