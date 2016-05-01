package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOBMAltar extends TODOItem {

	public static String NAME = "Build/Upgrade Altar";
	
	public TODOBMAltar(){
		
	}
	
	@Override
	public void onClick() {
	}

	@Override
	public String name() {
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
