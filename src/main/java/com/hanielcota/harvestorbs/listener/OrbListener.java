package com.hanielcota.harvestorbs.listener;

import com.hanielcota.harvestorbs.domain.model.Orb;
import com.hanielcota.harvestorbs.manager.OrbManager;
import com.hanielcota.harvestorbs.manager.ReplantTaskManager;
import com.hanielcota.harvestorbs.service.ParticleService;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Optional;

@RequiredArgsConstructor
public class OrbListener implements Listener {

    private final OrbManager orbManager;
    private final ParticleService particleService;
    private final ReplantTaskManager replantTaskManager;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();
        if (!isReplantableCrop(type)) return;

        Location location = block.getLocation();
        Optional<Orb> orbOpt = orbManager.getOrbsNear(location, 10).stream().findFirst();
        if (orbOpt.isEmpty()) return;

        Orb orb = orbOpt.get();
        particleService.shootParticle(orb.getLocation(), location);
        replantTaskManager.queueReplant(location, type);
    }

    private boolean isReplantableCrop(Material type) {
        return switch (type) {
            case WHEAT, CARROTS, POTATOES, BEETROOTS, NETHER_WART, MELON_STEM, PUMPKIN_STEM -> true;
            default -> false;
        };
    }
}
