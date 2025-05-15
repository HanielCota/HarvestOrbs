package com.hanielcota.harvestorbs.domain.model;

import com.hanielcota.harvestorbs.domain.OrbType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrbData {

    private final UUID id;
    private final OrbType type;
    private final Location location;

}
