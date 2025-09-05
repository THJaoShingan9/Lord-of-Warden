package net.entropy.lordofwarden.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.entropy.lordofwarden.init.LordOfWardenModItems;

@Mod.EventBusSubscriber
public class PurifyOtherMobsProcedure {

	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		if (event.getHand() != event.getEntity().getUsedItemHand()) return;
		execute(event.getLevel(),
				event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(),
				event.getTarget(), event.getEntity());
	}

	private static void execute(LevelAccessor world, double x, double y, double z,
								Entity target, Entity player) {
		if (target == null || player == null || !(player instanceof LivingEntity living))
			return;

		ItemStack main = living.getMainHandItem();
		if (main.getItem() != LordOfWardenModItems.PURIFY_POTION.get() || !player.isShiftKeyDown())
			return;

		if (target instanceof Warden) return;

		Scoreboard board = target.level().getScoreboard();
		String name = target instanceof Player pl ? pl.getGameProfile().getName() : target.getStringUUID();
		PlayerTeam team = board.getPlayersTeam(name);

		if (team == null || !"warden".equals(team.getName())) return;

		if (player instanceof Player p) {
			p.getInventory().clearOrCountMatchingItems(
					st -> st.getItem() == LordOfWardenModItems.PURIFY_POTION.get(),
					1, p.inventoryMenu.getCraftSlots());
		}

		PlayerTeam wardenTeam = board.getPlayerTeam("warden");
		if (wardenTeam != null) board.removePlayerFromTeam(name, wardenTeam);

		playSound(world, x, y, z, "entity.generic.drink");
		playSound(world, x, y, z, "block.sculk_shrieker.shriek");
	}

	private static void playSound(LevelAccessor world, double x, double y, double z, String sound) {
		if (world instanceof Level lvl) {
			if (!lvl.isClientSide()) {
				lvl.playSound(null, BlockPos.containing(x, y, z),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sound)),
						SoundSource.PLAYERS, 1, 1);
			} else {
				lvl.playLocalSound(x, y, z,
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sound)),
						SoundSource.PLAYERS, 1, 1, false);
			}
		}
	}
}