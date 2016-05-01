package tiffit.todolist.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tiffit.todolist.References;
import tiffit.todolist.TODOList;
import tiffit.todolist.items.TODOItem;

public class TaskEntry implements IGuiListEntry {

	ResourceLocation background = new ResourceLocation(References.MODID + ":textures/gui/todolist.png");
	ResourceLocation image = new ResourceLocation(References.MODID + ":textures/gui/icons.png");
	TODOItem item;
	boolean selected;
	boolean highlighted;
	int index;
	TaskSelectionList list;
	
	public TaskEntry(int index, TaskSelectionList list){
		this.index = index;
		this.item = TODOList.list.get(index);
		this.list = list;
	}
	
	@Override
	public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
		
	}

	@Override
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
		highlighted = isSelected;
		if(selected){
	        GlStateManager.color(1f, 1f, .6f, 1f);
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(background);
		Gui.drawModalRectWithCustomSizedTexture(x + listWidth/4, y, 0, 193, 151, 32, 256.0F, 256.0F);
		GlStateManager.color(1, 1, 1, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x + 16, y, item.getX()*32, item.getY()*32, 32, 32, 256.0F, 256.0F);
        String text = item.name() + " Task";
        FontRenderer fro = Minecraft.getMinecraft().fontRendererObj;
        fro.drawSplitString(item.getName(), x + listWidth/4 + 2, y + 2, 150, 0xffffff);
        int width = fro.getStringWidth(text);
        fro.drawStringWithShadow(item.name() + " Task", x + listWidth/4 + 147 - width, y + 22, 0xbbbbbb);
        GlStateManager.color(1, 1, 1, 1);
	}

	@Override
	public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
		return false;
	}

	@Override
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
		if(highlighted){
			if(relativeX > 54 && relativeY > 0 && relativeX < 151 + 54 && relativeY < 32){
				selected = true;
				return;
			}
		}
		selected = false;
	}

}
