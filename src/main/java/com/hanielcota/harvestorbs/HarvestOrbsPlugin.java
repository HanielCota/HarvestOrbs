package com.hanielcota.harvestorbs;

import co.aikar.commands.PaperCommandManager;
import com.hanielcota.harvestorbs.command.OrbCommand;
import com.hanielcota.harvestorbs.domain.model.OrbData;
import com.hanielcota.harvestorbs.factory.OrbFactory;
import com.hanielcota.harvestorbs.listener.OrbListener;
import com.hanielcota.harvestorbs.listener.OrbPlaceListener;
import com.hanielcota.harvestorbs.manager.OrbManager;
import com.hanielcota.harvestorbs.manager.ReplantTaskManager;
import com.hanielcota.harvestorbs.manager.TaskManager;
import com.hanielcota.harvestorbs.repository.OrbRepository;
import com.hanielcota.harvestorbs.service.OrbService;
import com.hanielcota.harvestorbs.service.ParticleService;
import com.hanielcota.harvestorbs.task.OrbParticleTask;
import com.hanielcota.harvestorbs.task.ReplantTask;
import com.hanielcota.harvestorbs.util.OrbItemUtil;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public final class HarvestOrbsPlugin extends JavaPlugin {

    @Getter
    private OrbManager orbManager;

    private ReplantTaskManager replantTaskManager;
    private TaskManager taskManager;
    private ParticleService particleService;
    private OrbService orbService;
    private OrbItemUtil orbItemUtil;

    @Override
    public void onEnable() {
        orbManager = new OrbManager();
        taskManager = new TaskManager(this);
        replantTaskManager = new ReplantTaskManager();

        NamespacedKey uuidKey = new NamespacedKey(this, "orb_id");
        NamespacedKey typeKey = new NamespacedKey(this, "orb_type");
        orbItemUtil = new OrbItemUtil(uuidKey, typeKey);

        OrbFactory orbFactory = new OrbFactory(taskManager);
        particleService = new ParticleService(taskManager);

        OrbRepository orbRepository = new OrbRepository(this);

        orbService = new OrbService(orbRepository, orbManager, orbFactory, orbItemUtil);

        Collection<OrbData> dataList = new ArrayList<>();
        orbRepository.loadAll(dataList);

        for (OrbData data : dataList) {

            if (data != null && data.getLocation() != null) {

                orbManager.addOrb(data.getId(), orbFactory.createOrb(data.getLocation(),
                        data.getType().getTitle(),

                        orbItemUtil.createOrbItem(
                                data.getId(),
                                data.getType())
                        )
                );
            }
        }

        taskManager.registerOrbParticleTask(new OrbParticleTask(orbManager), 20L);
        taskManager.registerReplantTask(new ReplantTask(replantTaskManager.getQueue()));

        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        if (taskManager == null) {
            return;
        }

        taskManager.cancelAll();

        if (orbManager == null) {
            return;
        }

        orbManager.removeAll();
    }

    private void registerEvents() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new OrbListener(orbManager, particleService, replantTaskManager), this);
        pluginManager.registerEvents(new OrbPlaceListener(orbService, orbItemUtil), this);
    }

    private void registerCommands() {
        PaperCommandManager acf = new PaperCommandManager(this);
        acf.registerCommand(new OrbCommand(orbService));
    }
}
