package com.hanielcota.harvestorbs.task;

import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Queue;

@RequiredArgsConstructor
public class ReplantTask extends BukkitRunnable {

    private final Queue<ReplantAction> queue;

    @Override
    public void run() {
        int limit = 10;

        while (!queue.isEmpty() && limit-- > 0) {
            ReplantAction action = queue.poll();

            if (shouldSkip(action)){
                continue;
            }

            applyReplant(action);
        }
    }

    private boolean shouldSkip(ReplantAction action) {
        if (action == null) {
            return true;
        }

        Block block = action.location().getBlock();
        return !block.getType().isAir();
    }

    private void applyReplant(ReplantAction action) {
        Block block = action.location().getBlock();
        block.setType(action.material());

        if (block.getBlockData() instanceof Ageable ageable) {
            ageable.setAge(ageable.getMaximumAge());
            block.setBlockData(ageable);
        }
    }

    public record ReplantAction(Location location, Material material) {}
}
