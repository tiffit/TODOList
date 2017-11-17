package tiffit.todolist.gui;

import net.minecraft.util.ResourceLocation;

public enum EnumTheme {

	Default("textures/gui/options_background.png"),
	Netherrack("textures/blocks/netherrack.png"),
	Brick("textures/blocks/brick.png"),
	Bookshelf("textures/blocks/bookshelf.png"),
	Wood("textures/blocks/planks_oak.png"),
	Obsidian("textures/blocks/obsidian.png"),
	Quartz("textures/blocks/quartz_block_bottom.png"),
	Red_Nether_Brick("textures/blocks/red_nether_brick.png"),
	Purpur("textures/blocks/purpur_block.png");
	
	public final ResourceLocation RESOURCE;
	
	private EnumTheme(String loc) {
		RESOURCE = new ResourceLocation(loc);
	}
	
	@Override
	public String toString() {
		return super.toString().replaceAll("_", " ");
	}
	
}
