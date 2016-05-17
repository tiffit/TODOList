package tiffit.todolist.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tiffit.todolist.References;
import tiffit.todolist.TODOList;
import tiffit.todolist.items.TODOTask;

public class NewTaskEntry implements IGuiListEntry {
	protected boolean selected;
	protected boolean highlighted;
	protected TODOTask item;
	protected Class<? extends TODOTask> clazz;
	protected NewTaskSelectionList list;
	
	public NewTaskEntry(Class<? extends TODOTask> clazz, NewTaskSelectionList list){
		this.clazz = clazz;
		try {
			item = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		this.list = list;
	}
	
	@Override
	public void setSelected(int par1, int par2, int par3) {
	}

	@Override
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
		highlighted = isSelected;
		if(selected){
	        GlStateManager.color(1f, 1f, .6f, 1f);
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(References.TODOLIST);
		Gui.drawModalRectWithCustomSizedTexture(x + listWidth/4, y, 0, 193, 151, 32, 256.0F, 256.0F);
		GlStateManager.color(1, 1, 1, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(References.ICONS);
        Gui.drawModalRectWithCustomSizedTexture(x + 16, y, item.getX()*32, item.getY()*32, 32, 32, 256.0F, 256.0F);
        
		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(item.getName() + " Task", x + listWidth/4 + 2, y + 2, 0xffffff);
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
