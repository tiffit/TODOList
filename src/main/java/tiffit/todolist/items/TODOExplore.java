package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOExplore extends TODOTask {

	public static String NAME = "Explore";
	
	public TODOExplore(){
		
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
		return 6;
	}

	@Override
	public int getY() {
		return 0;
	}

}
