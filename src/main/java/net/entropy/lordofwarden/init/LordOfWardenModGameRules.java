
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.entropy.lordofwarden.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LordOfWardenModGameRules {
	public static final GameRules.Key<GameRules.IntegerValue> WARDENINFECTIONCHANCE = GameRules.register("wardeninfectionchance", GameRules.Category.MOBS, GameRules.IntegerValue.create(2));
	public static final GameRules.Key<GameRules.IntegerValue> WARDENINFECTIONBLOCK = GameRules.register("wardeninfectionblock", GameRules.Category.MOBS, GameRules.IntegerValue.create(30));
}
