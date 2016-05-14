package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOGather extends TODOTask {

	public static String NAME = "Gather";
	
	public TODOGather(){
		
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
		return 3;
	}

	@Override
	public int getY() {
		return 0;
	}

}
