package com.hanielcota.harvestorbs.api;

import com.hanielcota.harvestorbs.domain.model.Orb;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface IOrbFactory {

    Orb createOrb(Location baseLocation, String title, ItemStack head);
}
