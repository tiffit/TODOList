package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOBMAltar extends TODOTask {

	public static String NAME = "Build/Upgrade Altar";
	
	public TODOBMAltar(){
		
	}
	
	@Override
	public void onClick() {
	}

	@Override
	public String taskName() {
		return NAME;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 1;
	}

}
