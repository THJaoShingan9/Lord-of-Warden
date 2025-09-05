package net.entropy.lordofwarden.procedures;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "lord_of_warden", value = Dist.CLIENT)
public final class SkinProcedure {

    private SkinProcedure() {}

    @SubscribeEvent
    public static void onRenderLivingPost(RenderLivingEvent.Post<? extends LivingEntity, ?> evt) {
        LivingEntity entity = evt.getEntity();
        if (entity instanceof Warden) return;
        if (entity.getTeam() == null || !"warden".equals(entity.getTeam().getName())) return;

        PoseStack pose = evt.getPoseStack();
        MultiBufferSource buffer = evt.getMultiBufferSource();

        Minecraft mc = Minecraft.getInstance();
        ItemRenderer itemRenderer = mc.getItemRenderer();

        pose.pushPose();
        // 头顶 0.25 格
        pose.translate(0, entity.getBbHeight() + 0.25F, 0);
        // 不朝向玩家
        // 方块一半大小
        pose.scale(0.5F, 0.5F, 0.5F);

        BakedModel model = itemRenderer.getModel(
                Items.SCULK_SHRIEKER.getDefaultInstance(),
                entity.level(), null, 0);

        itemRenderer.render(
                Items.SCULK_SHRIEKER.getDefaultInstance(),
                ItemDisplayContext.NONE,   // 按方块模型渲染
                false,
                pose,
                buffer,
                0xF000F0,
                OverlayTexture.NO_OVERLAY,
                model);

        pose.popPose();
    }
}