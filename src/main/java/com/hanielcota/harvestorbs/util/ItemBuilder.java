package com.hanielcota.harvestorbs.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material não pode ser nulo.");
        }

        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();

        if (this.meta == null) {
            throw new IllegalStateException("ItemMeta não pode ser nulo para o material: " + material);
        }
    }

    public ItemBuilder(ItemStack base) {
        if (base == null) throw new IllegalArgumentException("ItemStack base não pode ser nulo.");
        this.item = base.clone();
        this.meta = item.getItemMeta();
        if (this.meta == null) throw new IllegalStateException("ItemMeta não pode ser nulo no item base.");
    }

    public ItemBuilder name(Component name) {
        if (name == null) {
            return this;
        }

        meta.displayName(name);
        return this;
    }

    public ItemBuilder lore(List<Component> lore) {
        if (lore == null || lore.isEmpty()) {
            return this;
        }

        meta.lore(lore);
        return this;
    }

    public ItemBuilder persistent(NamespacedKey key, String value) {
        if (key == null || value == null || value.isBlank()) {
            return this;
        }
        
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
