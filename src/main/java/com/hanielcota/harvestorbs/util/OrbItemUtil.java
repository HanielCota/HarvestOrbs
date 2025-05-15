package com.hanielcota.harvestorbs.util;

import com.hanielcota.harvestorbs.domain.OrbType;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class OrbItemUtil {

    private final NamespacedKey uuidKey;
    private final NamespacedKey typeKey;

    public ItemStack createOrbItem(UUID id, OrbType type) {
        if (id == null || type == null)
            throw new IllegalArgumentException("UUID e OrbType não podem ser nulos.");

        ItemStack base = HeadUtil.getCustomHeadFromBase64(type.getTextureUrl());
        ItemBuilder builder = new ItemBuilder(base);

        return builder
                .name(Component.text(type.getTitle()))
                .lore(List.of(Component.text(type.getLore())))
                .persistent(uuidKey, id.toString())
                .persistent(typeKey, type.name())
                .build();
    }

    public boolean isOrbItem(ItemStack item) {
        if (item == null) return false;
        if (item.getType() != Material.PLAYER_HEAD) return false;
        if (!item.hasItemMeta()) return false;

        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        return container.has(uuidKey, PersistentDataType.STRING)
                && container.has(typeKey, PersistentDataType.STRING);
    }

    public Optional<UUID> getOrbId(ItemStack item) {
        if (!isOrbItem(item)) return Optional.empty();

        String uuidStr = item.getItemMeta()
                .getPersistentDataContainer()
                .get(uuidKey, PersistentDataType.STRING);

        if (uuidStr == null || uuidStr.isBlank()) return Optional.empty();

        try {
            return Optional.of(UUID.fromString(uuidStr));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    public Optional<OrbType> getOrbType(ItemStack item) {
        if (!isOrbItem(item)) return Optional.empty();

        String typeStr = item.getItemMeta()
                .getPersistentDataContainer()
                .get(typeKey, PersistentDataType.STRING);

        if (typeStr == null || typeStr.isBlank()) return Optional.empty();

        try {
            return Optional.of(OrbType.valueOf(typeStr));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
