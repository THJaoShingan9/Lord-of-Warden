package net.entropy.lordofwarden.procedures;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "lord_of_warden", value = Dist.CLIENT)
public final class SkinProcedure {

    private static final float[] WARDEN_BLUE = {0.0F, 0.1F, 0.5F, 1.0F}; // 预定义颜色

    @SubscribeEvent
    public static void onRenderLivingPre(RenderLivingEvent.Pre<? extends LivingEntity, ?> evt) {
        LivingEntity entity = evt.getEntity();

        // 1. 恢复到默认颜色
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // 2. 跳过监守者
        if (entity instanceof Warden) {
            return;
        }

        // 3. 只对 warden 队染色
        if (entity.getTeam() != null && "warden".equals(entity.getTeam().getName())) {
            RenderSystem.setShaderColor(WARDEN_BLUE[0], WARDEN_BLUE[1], WARDEN_BLUE[2], WARDEN_BLUE[3]);
        }
    }
}