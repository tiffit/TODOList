package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOCraft extends TODOTask {

	public static String NAME = "Craft";
	
	public TODOCraft(){
		
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
		return 1;
	}

	@Override
	public int getY() {
		return 0;
	}

}
