package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class TODOMine extends TODOItem {

	public static String NAME = "Mine";
	
	public TODOMine(){
		
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
		return 5;
	}

	@Override
	public int getY() {
		return 0;
	}

}
