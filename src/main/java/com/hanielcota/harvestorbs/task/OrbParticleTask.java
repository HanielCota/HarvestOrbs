package com.hanielcota.harvestorbs.task;

import com.hanielcota.harvestorbs.domain.model.Orb;
import com.hanielcota.harvestorbs.manager.OrbManager;
import lombok.AllArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class OrbParticleTask extends BukkitRunnable {

    private final OrbManager orbManager;

    @Override
    public void run() {
        for (Orb orb : orbManager.getOrbs()) {

            if (shouldSkip(orb)) continue;
            emitParticles(orb.getLocation());
        }
    }

    private boolean shouldSkip(Orb orb) {
        return orb == null || !orb.isValid()
                || orb.getLocation() == null
                || orb.getLocation().getWorld() == null;
    }

    private void emitParticles(Location location) {
        location.getWorld().spawnParticle(
                Particle.FIREWORK,
                location.clone().add(
                        0,
                        1.5,
                        0),
                        10,
                        0.1,
                        0.1,
                        0.1,
                        0.01);
    }
}
