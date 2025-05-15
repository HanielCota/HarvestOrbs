package com.hanielcota.harvestorbs.repository.helper;

import com.hanielcota.harvestorbs.domain.OrbType;
import com.hanielcota.harvestorbs.domain.model.OrbData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrbDataParser {

    public static OrbData fromYaml(YamlConfiguration config, String key) {
        UUID id = parseUUID(key);
        if (id == null) return null;

        OrbType type = parseOrbType(config.getString(key + ".type"));
        if (type == null) return null;

        Location location = parseLocation(
                config.getString(key + ".world"),
                config.getDouble(key + ".x"),
                config.getDouble(key + ".y"),
                config.getDouble(key + ".z")
        );

        return new OrbData(id, type, location);
    }

    public static void toYaml(YamlConfiguration config, OrbData data) {
        config.set(data.getId() + ".type", data.getType().name());
        Location loc = data.getLocation();

        if (loc != null && loc.getWorld() != null) {
            config.set(data.getId() + ".world", loc.getWorld().getName());
            config.set(data.getId() + ".x", loc.getX());
            config.set(data.getId() + ".y", loc.getY());
            config.set(data.getId() + ".z", loc.getZ());
        }
    }

    private static UUID parseUUID(String key) {
        try {
            return UUID.fromString(key);
        } catch (Exception ignored) {
            return null;
        }
    }

    private static OrbType parseOrbType(String typeName) {
        try {
            return OrbType.valueOf(typeName);
        } catch (Exception ignored) {
            return null;
        }
    }

    private static Location parseLocation(String worldName, double x, double y, double z) {
        if (worldName == null) {
            return null;
        }

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return null;
        }

        return new Location(world, x, y, z);
    }
}
