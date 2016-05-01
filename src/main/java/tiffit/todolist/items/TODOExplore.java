package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOExplore extends TODOItem {

	public static String NAME = "Explore";
	
	public TODOExplore(){
		
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
		return 6;
	}

	@Override
	public int getY() {
		return 0;
	}

}
