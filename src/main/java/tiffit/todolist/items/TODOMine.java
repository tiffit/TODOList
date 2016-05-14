package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOMine extends TODOTask {

	public static String NAME = "Mine";
	
	public TODOMine(){
		
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
		return 5;
	}

	@Override
	public int getY() {
		return 0;
	}

}
