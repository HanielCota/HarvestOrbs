package com.hanielcota.harvestorbs.repository;

import com.hanielcota.harvestorbs.domain.model.OrbData;
import com.hanielcota.harvestorbs.repository.helper.OrbDataParser;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class OrbRepository {

    private final Plugin plugin;
    private final File file;
    private final YamlConfiguration config;
    private final Map<UUID, OrbData> orbs = new HashMap<>();

    public OrbRepository(Plugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "orbs.yml");
        File dir = file.getParentFile();

        if (!dir.exists()) {
            boolean created = dir.mkdirs();
          
            if (!created) {
                plugin.getLogger().severe("Não foi possível criar a pasta de dados para orbs.yml em: " + dir.getAbsolutePath());
                throw new IllegalStateException("Falha ao criar diretório de dados para orbs!");
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }


    public void save(OrbData data) {
        if (data == null || data.getId() == null) {
            return;
        }

        orbs.put(data.getId(), data);
        saveAsync();
    }

    public void deleteById(UUID id) {
        orbs.remove(id);
        config.set(id.toString(), null);
        saveAsync();
    }

    public Optional<OrbData> findById(UUID id) {
        return Optional.ofNullable(orbs.get(id));
    }

    public Map<UUID, OrbData> findAll() {
        return Collections.unmodifiableMap(orbs);
    }

    public void loadAll(Collection<OrbData> output) {
        orbs.clear();
        for (String key : config.getKeys(false)) {
            OrbData data = OrbDataParser.fromYaml(config, key);

            if (data == null) {
                continue;
            }

            orbs.put(data.getId(), data);

            if (output != null) {
                output.add(data);
            }
        }
    }

    private void saveAsync() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (OrbData data : orbs.values()) {
                OrbDataParser.toYaml(config, data);
            }
            try {
                config.save(file);
            } catch (IOException e) {
                plugin.getLogger().severe("Erro ao salvar orbs.yml: " + e.getMessage());
            }
        });
    }
}
