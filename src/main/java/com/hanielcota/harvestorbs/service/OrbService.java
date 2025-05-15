package com.hanielcota.harvestorbs.service;

import com.hanielcota.harvestorbs.domain.OrbType;
import com.hanielcota.harvestorbs.domain.model.Orb;
import com.hanielcota.harvestorbs.domain.model.OrbData;
import com.hanielcota.harvestorbs.factory.OrbFactory;
import com.hanielcota.harvestorbs.manager.OrbManager;
import com.hanielcota.harvestorbs.repository.OrbRepository;
import com.hanielcota.harvestorbs.util.OrbItemUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class OrbService {

    private final OrbRepository repository;
    private final OrbManager orbManager;
    private final OrbFactory orbFactory;
    private final OrbItemUtil orbItemUtil;


    public Optional<String> giveOrb(Player player, OrbType type) {
        if (player == null) return Optional.of("Jogador é nulo");
        if (type == null) return Optional.of("Tipo de orb é nulo");

        UUID id = UUID.randomUUID();
        repository.save(new OrbData(id, type, null));
        player.getInventory().addItem(orbItemUtil.createOrbItem(id, type));
        return Optional.empty();
    }

    public Optional<String> removeClosestOrb(Player player, org.bukkit.Location location, double radius) {
        if (player == null) return Optional.of("Jogador é nulo");
        if (location == null) return Optional.of("Localização é nula");

        List<Orb> orbs = orbManager.getOrbsNear(location, radius);
        if (orbs.isEmpty()) return Optional.of("Nenhuma orb encontrada por perto.");

        Orb orb = orbs.getFirst();
        Optional<UUID> idOpt = orbManager.getIdByLocation(orb.getLocation());
        if (idOpt.isEmpty()) return Optional.of("Erro ao identificar a orb.");

        UUID id = idOpt.get();
        repository.deleteById(id);
        orbManager.remove(id);
        return Optional.empty();
    }

    public boolean placeOrb(ItemStack item, Location location) {
        Optional<UUID> idOpt = orbItemUtil.getOrbId(item);
        Optional<OrbType> typeOpt = orbItemUtil.getOrbType(item);

        if (idOpt.isEmpty() || typeOpt.isEmpty()) {
            return false;
        }
        UUID id = idOpt.get();
        OrbType type = typeOpt.get();

        // Atualiza local no repositório
        repository.save(new OrbData(id, type, location));

        // Cria Orb visual e adiciona ao manager
        Orb orb = orbFactory.createOrb(location, type.getTitle(), orbItemUtil.createOrbItem(id, type));
        orbManager.addOrb(id, orb);

        return true;
    }


    public OrbType parseOrbType(String tipo) {
        try {
            return OrbType.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public String getAvailableTypes() {
        return Arrays.stream(OrbType.values())
                .map(Enum::name)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Nenhum");
    }
}
