package com.hanielcota.harvestorbs.service;

import com.hanielcota.harvestorbs.manager.TaskManager;
import com.hanielcota.harvestorbs.task.ParticleShootTask;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ParticleService {

    private final TaskManager taskManager;

    public void shootParticle(@NotNull Location from, @NotNull Location to) {
        taskManager.registerParticleShootTask(new ParticleShootTask(from, to, 0.25, 150));
    }
}
