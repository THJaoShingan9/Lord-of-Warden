
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.entropy.lordofwarden.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.entropy.lordofwarden.item.PurifyPotionItem;
import net.entropy.lordofwarden.LordOfWardenMod;

public class LordOfWardenModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, LordOfWardenMod.MODID);
	public static final RegistryObject<Item> PURIFY_POTION = REGISTRY.register("purify_potion", () -> new PurifyPotionItem());
	// Start of user code block custom items
	// End of user code block custom items
}
