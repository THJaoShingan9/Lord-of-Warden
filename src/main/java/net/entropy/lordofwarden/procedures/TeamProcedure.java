package net.entropy.lordofwarden.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TeamProcedure {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        execute(event, event.getEntity().level());
    }

    public static void execute(LevelAccessor world) {
        execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        if (!(world instanceof Level _level)) return;

        var scoreboard = _level.getScoreboard();
        PlayerTeam team = scoreboard.getPlayerTeam("warden");

        if (team == null) {
            team = scoreboard.addPlayerTeam("warden");
        }

        team.setAllowFriendlyFire(false);
    }
}