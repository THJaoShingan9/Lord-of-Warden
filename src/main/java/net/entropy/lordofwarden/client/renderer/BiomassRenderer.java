
package net.entropy.lordofwarden.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.SilverfishModel;

import net.entropy.lordofwarden.entity.BiomassEntity;

public class BiomassRenderer extends MobRenderer<BiomassEntity, SilverfishModel<BiomassEntity>> {
	public BiomassRenderer(EntityRendererProvider.Context context) {
		super(context, new SilverfishModel<BiomassEntity>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(BiomassEntity entity) {
		return new ResourceLocation("lord_of_warden:textures/entities/biomass.png");
	}
}
