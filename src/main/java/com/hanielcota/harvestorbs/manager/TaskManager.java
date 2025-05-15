package com.hanielcota.harvestorbs.manager;

import com.hanielcota.harvestorbs.task.OrbFluctuateTask;
import com.hanielcota.harvestorbs.task.OrbParticleTask;
import com.hanielcota.harvestorbs.task.ParticleShootTask;
import com.hanielcota.harvestorbs.task.ReplantTask;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class TaskManager {

    private final Plugin plugin;
    private final Set<BukkitTask> tasks = new HashSet<>();

    public void registerOrbFluctuateTask(OrbFluctuateTask task) {
        BukkitTask bukkitTask = task.runTaskTimer(plugin, 0L, 1L);
        tasks.add(bukkitTask);
    }

    public void registerOrbParticleTask(OrbParticleTask task, long interval) {
        BukkitTask bukkitTask = task.runTaskTimer(plugin, 0L, interval);
        tasks.add(bukkitTask);
    }

    public void registerParticleShootTask(ParticleShootTask task) {
        BukkitTask bukkitTask = task.runTaskTimer(plugin, 0L, 1L);
        tasks.add(bukkitTask);
    }

    public void registerReplantTask(ReplantTask task) {
        BukkitTask bukkitTask = task.runTaskTimer(plugin, 40L, 40L);
        tasks.add(bukkitTask);
    }

    public void cancelAll() {
        for (BukkitTask task : tasks) {
            if (task != null) task.cancel();
        }
        tasks.clear();
    }
}
