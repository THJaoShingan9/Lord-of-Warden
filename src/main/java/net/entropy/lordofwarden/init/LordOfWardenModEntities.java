
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.entropy.lordofwarden.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.entropy.lordofwarden.entity.BiomassEntity;
import net.entropy.lordofwarden.entity.Biomass3Entity;
import net.entropy.lordofwarden.entity.Biomass2Entity;
import net.entropy.lordofwarden.LordOfWardenMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LordOfWardenModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LordOfWardenMod.MODID);
	public static final RegistryObject<EntityType<BiomassEntity>> BIOMASS = register("biomass",
			EntityType.Builder.<BiomassEntity>of(BiomassEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BiomassEntity::new).fireImmune().sized(0.4f, 0.3f));
	public static final RegistryObject<EntityType<Biomass2Entity>> BIOMASS_2 = register("biomass_2",
			EntityType.Builder.<Biomass2Entity>of(Biomass2Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Biomass2Entity::new).fireImmune().sized(0.4f, 0.3f));
	public static final RegistryObject<EntityType<Biomass3Entity>> BIOMASS_3 = register("biomass_3",
			EntityType.Builder.<Biomass3Entity>of(Biomass3Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Biomass3Entity::new).fireImmune().sized(0.4f, 0.3f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			BiomassEntity.init();
			Biomass2Entity.init();
			Biomass3Entity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(BIOMASS.get(), BiomassEntity.createAttributes().build());
		event.put(BIOMASS_2.get(), Biomass2Entity.createAttributes().build());
		event.put(BIOMASS_3.get(), Biomass3Entity.createAttributes().build());
	}
}
