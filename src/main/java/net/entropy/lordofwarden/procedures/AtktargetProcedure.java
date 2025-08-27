package net.entropy.lordofwarden.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import java.util.Comparator;

@Mod.EventBusSubscriber(modid = "lord_of_warden") 
public final class AtktargetProcedure {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Mob mob && !(entity instanceof Warden) && isOnWardenTeam(entity)) {
            findTarget(entity).ifPresent(target -> mob.setTarget(target));
        }
    }

    private static boolean isOnWardenTeam(Entity e) {
        return e.getTeam() != null && "warden".equals(e.getTeam().getName());
    }

    private static java.util.Optional<LivingEntity> findTarget(Entity self) {
        LevelAccessor world = self.level();
        Vec3 center = self.position();
        return world.getEntitiesOfClass(LivingEntity.class,
                        new AABB(center, center).inflate(32),
                        e -> !isOnWardenTeam(e) && isAttackable(e))
                .stream()
                .min(Comparator.comparingDouble(e -> e.distanceToSqr(center)));
    }

    private static boolean isAttackable(LivingEntity e) {
        if (e instanceof Player p) {
            if (p.level().isClientSide()) return false;
            if (p instanceof ServerPlayer sp) {
                GameType gm = sp.gameMode.getGameModeForPlayer();
                return gm != GameType.CREATIVE && gm != GameType.SPECTATOR;
            }
            return false;
        }
        return true;
    }

    private AtktargetProcedure() {}
}
