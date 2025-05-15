package com.hanielcota.harvestorbs.manager;

import com.hanielcota.harvestorbs.task.ReplantTask;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public class ReplantTaskManager {

    private final Queue<ReplantTask.ReplantAction> queue = new LinkedList<>();

    public void queueReplant(Location location, Material material) {
        if (location == null || material == null) return;
        queue.offer(new ReplantTask.ReplantAction(location, material));
    }

}
