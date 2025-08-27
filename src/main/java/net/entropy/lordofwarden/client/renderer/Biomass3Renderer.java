
package net.entropy.lordofwarden.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import net.entropy.lordofwarden.entity.Biomass3Entity;

public class Biomass3Renderer extends MobRenderer<Biomass3Entity, SilverfishModel<Biomass3Entity>> {
	public Biomass3Renderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel<Biomass3Entity>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(Biomass3Entity entity) {
		return new ResourceLocation("lord_of_warden:textures/entities/biomass.png");
	}
}
