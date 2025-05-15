package com.hanielcota.harvestorbs.factory;

import com.hanielcota.harvestorbs.api.IOrbFactory;
import com.hanielcota.harvestorbs.domain.model.Orb;
import com.hanielcota.harvestorbs.manager.TaskManager;
import com.hanielcota.harvestorbs.task.OrbFluctuateTask;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class OrbFactory implements IOrbFactory {

    private final TaskManager taskManager;

    @Override
    public Orb createOrb(Location baseLocation, String title, ItemStack head) {
        World world = baseLocation.getWorld();
        if (world == null) throw new IllegalArgumentException("Localização inválida para criar Orb.");

        Location center = baseLocation.clone();

        ArmorStand stand = world.spawn(center, ArmorStand.class, s -> {
            s.setInvisible(true);
            s.setGravity(false);
            s.setMarker(true);
            s.getEquipment().setHelmet(head);
        });

        TextDisplay textDisplay = world.spawn(center.clone().add(0, 0.5, 0), TextDisplay.class, t -> {
            t.text(Component.text(title).color(NamedTextColor.GOLD));
            t.setBillboard(Display.Billboard.CENTER);
            t.setSeeThrough(true);
            t.setDefaultBackground(false);
            t.setShadowed(true);
            t.setViewRange(32f);
        });

        taskManager.registerOrbFluctuateTask(new OrbFluctuateTask(center, stand, textDisplay));

        return new Orb(center, stand, textDisplay);
    }
}
