package com.hanielcota.harvestorbs.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class OrbFluctuateTask extends BukkitRunnable {

    private final Location baseLocation;
    private final ArmorStand stand;
    private final TextDisplay display;
    private int ticks = 0;

    @Override
    public void run() {
        if (!stand.isValid() || !display.isValid()) {
            cancel();
            return;
        }

        double offsetY = Math.sin(ticks * 0.1) * 0.3;
        Location newLoc = baseLocation.clone().add(0, offsetY, 0);

        stand.teleport(newLoc);
        display.teleport(newLoc.clone().add(0, 2.3, 0));
        ticks++;
    }
}
