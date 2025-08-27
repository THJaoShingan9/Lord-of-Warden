package net.entropy.lordofwarden.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.entropy.lordofwarden.LordOfWardenMod;
import net.entropy.lordofwarden.init.LordOfWardenModEntities;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = LordOfWardenMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SculkCatalystinfectProcedure {

   @SubscribeEvent
public static void onLivingDeath(LivingDeathEvent event) {
    LivingEntity victim = event.getEntity();
    Level level = victim.level();
    if (level.isClientSide()) return;

    //排除 BIOMASS、BIOMASS_2、BIOMASS_3
    EntityType<?> type = victim.getType();
    if (type == LordOfWardenModEntities.BIOMASS.get() ||
        type == LordOfWardenModEntities.BIOMASS_2.get() ||
        type == LordOfWardenModEntities.BIOMASS_3.get()) {
        return;
    }

    BlockPos deathPos = victim.blockPosition();

        // 30 格内找幽匿催发体
        BlockPos catalystPos = findSculkCatalystNearby(level, deathPos, 30);
        if (catalystPos == null) return;

        // 根据最大生命值选择实体
        float maxHp = victim.getMaxHealth();
        Supplier<EntityType<? extends LivingEntity>> entitySupplier;

        if (maxHp <= 100) {
            entitySupplier = () -> LordOfWardenModEntities.BIOMASS.get();
        } else if (maxHp <= 499) {
            entitySupplier = ()-> LordOfWardenModEntities.BIOMASS_2.get();
        } else {
            entitySupplier = () -> LordOfWardenModEntities.BIOMASS_3.get();
        }

        // 在催发体 8 格内生成
        spawnEntityNear(level, catalystPos, 4, entitySupplier);
    }

    private static BlockPos findSculkCatalystNearby(Level level, BlockPos center, int radius) {
        for (BlockPos p : BlockPos.withinManhattan(center, radius, radius, radius)) {
            if (level.getBlockState(p).is(Blocks.SCULK_CATALYST)) {
                return p;
            }
        }
        return null;
    }

    private static void spawnEntityNear(Level level,
                                        BlockPos center,
                                        int radius,
                                        Supplier<EntityType<? extends LivingEntity>> supplier) {

        BlockPos pos = findSafeSpawnPos(level, center, radius);
        if (pos == null) return;

        Entity entity = supplier.get().create(level);
        if (entity != null) {
            entity.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0, 0);
            level.addFreshEntity(entity);
        }
    }

    private static BlockPos findSafeSpawnPos(Level level, BlockPos center, int radius) {
        for (int i = 0; i < 10; i++) {
            BlockPos p = center.offset(
                    level.random.nextIntBetweenInclusive(-radius, radius),
                    level.random.nextIntBetweenInclusive(-2, 2),
                    level.random.nextIntBetweenInclusive(-radius, radius)
            );
            if (level.getBlockState(p).isAir()
                    && level.getBlockState(p.below()).isSolidRender(level, p.below())) {
                return p;
            }
        }
        return null;
    }
}