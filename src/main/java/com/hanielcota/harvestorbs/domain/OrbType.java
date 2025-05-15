package com.hanielcota.harvestorbs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrbType {

    COLHEITA("§eOrb da Colheita",
            "§7Replantações mágicas",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWMzYTRiNTFhNDgwMDI4OTBmYjI3MWNkYzE1MTdlYWY0MWFiMDAyNTNjMWI3ZDgyNDI3MjczNmJiNDE2NjNkNSJ9fX0="),

    CENOURA("§aOrb de Cenoura",
            "§7Replantações mágicas",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQzYTZiZDk4YWMxODMzYzY2NGM0OTA5ZmY4ZDJkYzYyY2U4ODdiZGNmM2NjNWIzODQ4NjUxYWU1YWY2YiJ9fX0="),

    TRIGO("§6Orb de Trigo",
            "§7Replantações mágicas",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTBkYWUwNjk3YTg4NTQ1MzJlYTM1NmFjMzg4Y2ExYTBmOWNhZmMyNTgyYjAxNDgyZjI3MWRlNDExZWEwYzU1YSJ9fX0=");


    private final String title;
    private final String lore;
    private final String textureUrl;
}