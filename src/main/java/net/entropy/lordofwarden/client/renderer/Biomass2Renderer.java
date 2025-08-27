
package net.entropy.lordofwarden.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import net.entropy.lordofwarden.entity.Biomass2Entity;

public class Biomass2Renderer extends MobRenderer<Biomass2Entity, SilverfishModel<Biomass2Entity>> {
	public Biomass2Renderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel<Biomass2Entity>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(Biomass2Entity entity) {
		return new ResourceLocation("lord_of_warden:textures/entities/biomass.png");
	}
}
