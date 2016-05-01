package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOGather extends TODOItem {

	public static String NAME = "Gather";
	
	public TODOGather(){
		
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
		return 3;
	}

	@Override
	public int getY() {
		return 0;
	}

}
