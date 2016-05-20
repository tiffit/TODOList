package tiffit.todolist.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tiffit.todolist.References;
import tiffit.todolist.TODOList;
import tiffit.todolist.items.TODOTask;

public class TaskEntry implements IGuiListEntry {
	
	private static final ResourceLocation clock = new ResourceLocation("textures/items/clock_00.png");
	public final TODOTask item;
	boolean selected;
	private boolean highlighted;
	public final int index;
	public final TaskSelectionList list;
	
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(References.TODOLIST);
		Gui.drawModalRectWithCustomSizedTexture(x + listWidth/4, y, 0, 193, 151, 32, 256.0F, 256.0F);
		GlStateManager.color(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(References.ICONS);
        Gui.drawModalRectWithCustomSizedTexture(x + 16, y, item.getX()*32, item.getY()*32, 32, 32, 256.0F, 256.0F);
        
        if(item.getClock() != null){
        	Minecraft.getMinecraft().getTextureManager().bindTexture(clock);
        	Gui.drawModalRectWithCustomSizedTexture(x + 32, y + 16, 0, 0, 16, 16, 16.0F, 16.0F);
        }
        if(TODOList.hudtask == item){
        	Minecraft.getMinecraft().getTextureManager().bindTexture(References.WIDGETS);
        	Gui.drawModalRectWithCustomSizedTexture(x + 16, y + 16, 0, 22, 16, 16, 256.0F, 256.0F);
        }
        
        if(highlighted){
        	Minecraft.getMinecraft().getTextureManager().bindTexture(References.MC_WIDGETS);
        	Gui.drawModalRectWithCustomSizedTexture(x + listWidth - 34, y + slotHeight - 16, 128, 0, 16, 16, 256.0F, 256.0F);
        }
        
        String text = item.taskName() + " Task";
        String time = item.getClock() != null ? item.getClock().toString() : "";
        FontRenderer fro = Minecraft.getMinecraft().fontRendererObj;
        fro.drawSplitString(item.getName(), x + listWidth/4 + 2, y + 2, 150, 0xffffff);
        
        int width = fro.getStringWidth(text);
        fro.drawString(time, x + listWidth/4 + 2, y + 22, item.getClock() != null ? item.getClock().getTextColor() : 0);
        if(!highlighted) fro.drawStringWithShadow(item.taskName() + " Task", x + listWidth/4 + 147 - width, y + 22, 0xbbbbbb);
        
        Gui.drawRect(x + listWidth - 14, y, x + listWidth - 12, y + slotHeight + 4, item.getPriority().getColor());
        
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
				list.remove(index);
			}
		}
	}

}
