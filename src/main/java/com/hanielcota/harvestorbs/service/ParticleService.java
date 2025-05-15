package com.hanielcota.harvestorbs.service;

import com.hanielcota.harvestorbs.manager.TaskManager;
import com.hanielcota.harvestorbs.task.ParticleShootTask;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;

@RequiredArgsConstructor
public class ParticleService {

    private final TaskManager taskManager;

    public void shootParticle(Location from, Location to) {
        taskManager.registerParticleShootTask(new ParticleShootTask(from, to, 0.25, 30));
    }
}
