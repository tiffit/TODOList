package tiffit.todolist.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tiffit.todolist.References;
import tiffit.todolist.TODOList;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.items.TODOTask;

public class ListEntry implements IGuiListEntry {
	
	public final TODOList item;
	boolean selected;
	private boolean highlighted;
	public final int index;
	public final ListSelectionList list;
	
	public ListEntry(int index, ListSelectionList list){
		this.index = index;
		this.item = TODOListMod.lists.get(index);
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(References.TODOLIST);
		Gui.drawModalRectWithCustomSizedTexture(x + listWidth/4, y, 0, 193, 151, 32, 256.0F, 256.0F);
		GlStateManager.color(1, 1, 1, 1);
		
        if(highlighted){
        	if(item.list.size() > 0){
        		GlStateManager.color(0f, 0f, 0f);
        	}
        	Minecraft.getMinecraft().getTextureManager().bindTexture(References.MC_WIDGETS);
        	Gui.drawModalRectWithCustomSizedTexture(x + listWidth - 34, y + slotHeight - 16, 128, 0, 16, 16, 256.0F, 256.0F);
    		GlStateManager.color(1, 1, 1, 1);
        }
        
        FontRenderer fro = Minecraft.getMinecraft().fontRendererObj;
        fro.drawSplitString(item.name(), x + listWidth/4 + 2, y + 2, 150, 0xffffff);
        
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
			}else selected = false;
		}else selected = false;
		if(selected){
			if(relativeX > 187 && relativeY > 13 && relativeX < 187 + 16 && relativeY < 13+16){
				if(item.list.size() == 0){
					list.remove(index);
				}
			}
		}
	}

}
