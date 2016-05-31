package tiffit.todolist;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AchievmentHandler {

	public static Achievement first_start;
	
	public static void init(){
		first_start = new Achievement("achievement.TODOList.first_start", "first_start", 0, 0, Blocks.COMMAND_BLOCK, (Achievement)null).initIndependentStat();
		AchievementPage.registerAchievementPage(new AchievementPage("TODOList", first_start));
	}
	
}
