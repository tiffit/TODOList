package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOBuild extends TODOItem {

	public static String NAME = "Build";
	
	public TODOBuild(){
		
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
		return 2;
	}

	@Override
	public int getY() {
		return 0;
	}

}
