package net.entropy.lordofwarden.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class PurifyPotionDangWanJiaWanChengShiYongWuPinShiProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null || !(entity instanceof LivingEntity living))
			return;

		Scoreboard board = living.level().getScoreboard();
		String playerName = living instanceof Player pl ? pl.getGameProfile().getName() : living.getStringUUID();
		PlayerTeam currentTeam = board.getPlayersTeam(playerName);

		if (currentTeam != null && "warden".equals(currentTeam.getName())) {
			PlayerTeam wardenTeam = board.getPlayerTeam("warden");
			if (wardenTeam != null) {
				board.removePlayerFromTeam(playerName, wardenTeam);
			}

			if (world instanceof Level lvl) {
				if (!lvl.isClientSide()) {
					lvl.playSound(null, BlockPos.containing(x, y, z),
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_shrieker.shriek")),
						SoundSource.PLAYERS, 1, 1);
				} else {
					lvl.playLocalSound(x, y, z,
						ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sculk_shrieker.shriek")),
						SoundSource.PLAYERS, 1, 1, false);
				}
			}
		}
	}
}