package com.hanielcota.harvestorbs.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.TextDisplay;

@Getter
@AllArgsConstructor
public class Orb {

    private final Location location;
    private final ArmorStand display;
    private final TextDisplay textDisplay;

    public boolean isValid() {
        return display != null && display.isValid();
    }

    public void remove() {
        if (display != null && !display.isDead()) display.remove();
        if (textDisplay != null && !textDisplay.isDead()) textDisplay.remove();
    }
}
