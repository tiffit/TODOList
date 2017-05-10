package tiffit.todolist.gui;

import net.minecraft.util.ResourceLocation;

public enum EnumTheme {

	Default("textures/gui/options_background.png"),
	Netherrack("textures/blocks/netherrack.png"),
	Brick("textures/blocks/brick.png"),
	Bookshelf("textures/blocks/bookshelf.png"),
	Wood("textures/blocks/planks_oak.png");
	
	public final ResourceLocation RESOURCE;
	
	private EnumTheme(String loc) {
		RESOURCE = new ResourceLocation(loc);
	}
	
}
