package com.hanielcota.harvestorbs.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeadUtil {

    private static final Map<String, ItemStack> HEAD_CACHE = new HashMap<>();

    public static ItemStack getCustomHeadFromBase64(String base64) {
        if (HEAD_CACHE.containsKey(base64)) return HEAD_CACHE.get(base64);

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
        profile.setProperty(new ProfileProperty("textures", base64));
        profile.complete();

        meta.setPlayerProfile(profile);
        head.setItemMeta(meta);

        HEAD_CACHE.put(base64, head);
        return head;
    }
}
