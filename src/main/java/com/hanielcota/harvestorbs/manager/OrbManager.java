package com.hanielcota.harvestorbs.manager;

import com.hanielcota.harvestorbs.domain.model.Orb;
import org.bukkit.Location;

import java.util.*;

public class OrbManager {

    private final Map<UUID, Orb> orbs = new HashMap<>();

    public void addOrb(UUID id, Orb orb) {
        if (id == null || orb == null) return;
        orbs.put(id, orb);
    }

    public void remove(UUID id) {
        Orb orb = orbs.remove(id);
        if (orb != null) orb.remove();
    }

    public void removeAll() {
        orbs.values().forEach(Orb::remove);
        orbs.clear();
    }

    public Collection<Orb> getOrbs() {
        return Collections.unmodifiableCollection(orbs.values());
    }

    public List<Orb> getOrbsNear(Location location, double radius) {
        if (location == null || radius <= 0) return Collections.emptyList();
        return orbs.values().stream()
                .filter(orb -> orb.getLocation().distance(location) <= radius)
                .toList();
    }

    public Optional<UUID> getIdByLocation(Location loc) {
        return orbs.entrySet().stream()
                .filter(entry -> entry.getValue().getLocation().distance(loc) < 0.5)
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
