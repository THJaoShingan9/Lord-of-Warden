package net.entropy.lordofwarden.procedures;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.entropy.lordofwarden.LordOfWardenMod;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = LordOfWardenMod.MODID)
public class SculkSensorProcedure {

    private static final Map<ServerLevel, Map<Entity, BlockPos>> lastPos = new WeakHashMap<>();

    public static void execute() {  }

    @SubscribeEvent
    public static void onTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        event.getServer().getAllLevels().forEach(level -> {
            Map<Entity, BlockPos> map = lastPos.computeIfAbsent(level, k -> new HashMap<>());

            level.getAllEntities().forEach(entity -> {
                if (entity instanceof Player) return;
                if ("warden".equals(entity.getTeam() == null ? "" : entity.getTeam().getName())) return;

                BlockPos current = entity.blockPosition();
                BlockPos prev = map.put(entity, current);
                if (prev != null && prev.equals(current)) return;

                if (!level.players().isEmpty()) {
                    level.gameEvent(level.players().get(0), GameEvent.STEP, current);
                }
            });
        });
    }
}