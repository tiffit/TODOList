package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOCraft extends TODOItem {

	public static String NAME = "Craft";
	
	public TODOCraft(){
		
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
		return 1;
	}

	@Override
	public int getY() {
		return 0;
	}

}
