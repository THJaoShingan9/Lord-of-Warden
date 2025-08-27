
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.entropy.lordofwarden.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.entropy.lordofwarden.client.renderer.BiomassRenderer;
import net.entropy.lordofwarden.client.renderer.Biomass3Renderer;
import net.entropy.lordofwarden.client.renderer.Biomass2Renderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class LordOfWardenModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(LordOfWardenModEntities.BIOMASS.get(), BiomassRenderer::new);
		event.registerEntityRenderer(LordOfWardenModEntities.BIOMASS_2.get(), Biomass2Renderer::new);
		event.registerEntityRenderer(LordOfWardenModEntities.BIOMASS_3.get(), Biomass3Renderer::new);
	}
}
