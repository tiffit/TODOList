package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOHunt extends TODOItem {

	public static String NAME = "Hunt";
	
	public TODOHunt(){
		
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
		return 4;
	}

	@Override
	public int getY() {
		return 0;
	}

}
