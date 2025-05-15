package com.hanielcota.harvestorbs.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Utilitário para criação e cache de cabeças customizadas (PLAYER_HEAD) com texturas personalizadas via base64.
 * Permite reutilizar instâncias para melhor performance e economia de recursos.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeadUtil {

    /** Cache estático para evitar recriação de itens de cabeça com a mesma textura */
    private static final Map<String, ItemStack> HEAD_CACHE = new HashMap<>();

    /**
     * Cria um {@link ItemStack} do tipo PLAYER_HEAD customizado com a textura fornecida em base64.
     * Utiliza cache interno para evitar processamento repetido.
     *
     * @param base64 String base64 da textura a ser aplicada
     * @return ItemStack do tipo PLAYER_HEAD com textura customizada
     */
    public static ItemStack getCustomHeadFromBase64(@NotNull String base64) {
        if (HEAD_CACHE.containsKey(base64)) {
            return HEAD_CACHE.get(base64);
        }

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
