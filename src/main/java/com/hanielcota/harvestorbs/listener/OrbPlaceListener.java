package com.hanielcota.harvestorbs.listener;

import com.hanielcota.harvestorbs.service.OrbService;
import com.hanielcota.harvestorbs.util.OrbItemUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class OrbPlaceListener implements Listener {

    private final OrbService orbService;
    private final OrbItemUtil orbItemUtil;

    @EventHandler
    public void onPlace(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || !orbItemUtil.isOrbItem(item)) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        event.setCancelled(true);

        boolean success = orbService.placeOrb(item, block.getLocation().add(0.5, 1.5, 0.5));
        if (!success) {
            player.sendMessage("§cErro ao identificar ou posicionar a Orb.");
            return;
        }

        player.sendMessage("§aOrb posicionada!");
        item.setAmount(item.getAmount() - 1);
    }
}
