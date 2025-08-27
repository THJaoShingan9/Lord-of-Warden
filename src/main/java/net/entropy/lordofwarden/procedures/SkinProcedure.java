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

    // 颜色
    private static final float R = 0.00F;
    private static final float G = 0.45F;
    private static final float B = 0.60F;
    private static final float A = 1.00F;

    @SubscribeEvent
    public static void onRenderLivingPre(RenderLivingEvent.Pre<? extends LivingEntity, ?> evt) {
        LivingEntity entity = evt.getEntity();

        // 恢复默认
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // 跳过监守者
        if (entity instanceof Warden) {
            return;
        }

        // 只对 warden 队染色
        if (entity.getTeam() != null && "warden".equals(entity.getTeam().getName())) {
            RenderSystem.setShaderColor(R, G, B, A);
        }
    }
}
