package com.hanielcota.harvestorbs.task;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ParticleShootTask extends BukkitRunnable {

    private final Location end;
    private final double speed;
    private final int maxSteps;
    private int steps = 0;
    private final Location current;

    public ParticleShootTask(Location start, Location end, double speed, int maxSteps) {
        this.end = end;
        this.speed = speed;
        this.maxSteps = maxSteps;
        this.current = start.clone();
    }

    @Override
    public void run() {
        if (steps++ >= maxSteps) {
            cancel();
            return;
        }

        World world = current.getWorld();
        if (world == null) {
            cancel();
            return;
        }

        Vector direction = end.toVector().subtract(current.toVector()).normalize().multiply(speed);
        world.spawnParticle(Particle.SCRAPE, current, 5, 0, 0, 0, 0);
        current.add(direction);
    }
}
