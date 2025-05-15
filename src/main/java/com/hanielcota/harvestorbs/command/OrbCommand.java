package com.hanielcota.harvestorbs.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.hanielcota.harvestorbs.domain.OrbType;
import com.hanielcota.harvestorbs.service.OrbService;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Optional;

@CommandAlias("orb")
@CommandPermission("harvestorbs.command")
@RequiredArgsConstructor
public class OrbCommand extends BaseCommand {

    private final OrbService orbService;

    @Subcommand("give")
    @Description("Recebe uma orb do tipo desejado")
    @Syntax("<tipo>")
    public void onGive(Player player, String tipo) {
        if (tipo == null || tipo.isBlank()) {
            player.sendMessage("§cUso correto: /orb give <tipo>");
            return;
        }

        OrbType type = orbService.parseOrbType(tipo);
        if (type == null) {
            player.sendMessage("§cTipo de orb inválido: §e" + tipo);
            player.sendMessage("§7Tipos disponíveis: §f" + orbService.getAvailableTypes());
            return;
        }

        Optional<String> error = orbService.giveOrb(player, type);
        if (error.isPresent()) {
            player.sendMessage("§cErro: " + error.get());
            return;
        }

        player.sendMessage("§aVocê recebeu a orb: §e" + type.getTitle());
    }

    @Subcommand("remove")
    @Description("Remove a orb mais próxima de você (raio de 5 blocos)")
    public void onRemove(Player player) {

        Optional<String> error = orbService.removeClosestOrb(player, player.getLocation(), 5);
        if (error.isPresent()) {
            player.sendMessage("§c" + error.get());
            return;
        }

        player.sendMessage("§aOrb removida com sucesso!");
    }
}
