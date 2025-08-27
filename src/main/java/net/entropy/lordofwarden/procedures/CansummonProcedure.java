package net.entropy.lordofwarden.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.world.level.block.SculkShriekerBlock.CAN_SUMMON;

@Mod.EventBusSubscriber(modid = "lord_of_warden")
public class CansummonProcedure {

    @SubscribeEvent
    public static void onNeighborChanged(BlockEvent.NeighborNotifyEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (!(event.getLevel() instanceof ServerLevel level)) return;

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        if (!(state.getBlock() instanceof SculkShriekerBlock))
            return;

        // 若当前不能召唤，立即设为可召唤
        if (!state.getValue(CAN_SUMMON)) {
            level.setBlock(pos, state.setValue(CAN_SUMMON, true), 3);
        }
    }
}