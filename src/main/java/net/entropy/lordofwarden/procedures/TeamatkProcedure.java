package net.entropy.lordofwarden.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;

@Mod.EventBusSubscriber
public class TeamatkProcedure {

    private static final String TEAM_NAME = "warden";

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Mob mob)) return;

        LivingEntity target = mob.getTarget();
        if (target == null) return;

        if (isInTeam(entity, TEAM_NAME) && isInTeam(target, TEAM_NAME)) {
            mob.setTarget(null);
        }
    }

    private static boolean isInTeam(LivingEntity entity, String teamName) {
        String name = entity instanceof Player player
                ? player.getGameProfile().getName()
                : entity.getStringUUID();
        PlayerTeam team = entity.level().getScoreboard().getPlayersTeam(name);
        return team != null && teamName.equals(team.getName());
    }
}