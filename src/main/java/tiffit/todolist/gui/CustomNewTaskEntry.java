package tiffit.todolist.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import tiffit.todolist.References;
import tiffit.todolist.items.TODOCustom;

public class CustomNewTaskEntry extends NewTaskEntry {
	
	private String name;
	
	public CustomNewTaskEntry(String name, NewTaskSelectionList list) {
		super(TODOCustom.class, list);
		this.name = name;
		((TODOCustom)item).setCustomName(name);
	}
	
	@Override
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
		super.drawEntry(slotIndex, x, y, listWidth, slotHeight, mouseX, mouseY, isSelected);
		if(highlighted){
        	Minecraft.getMinecraft().getTextureManager().bindTexture(References.MC_WIDGETS);
        	Gui.drawModalRectWithCustomSizedTexture(x + listWidth - 34, y + slotHeight - 16, 128, 0, 16, 16, 256.0F, 256.0F);
        }
	}
	
	@Override
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
		super.mouseReleased(slotIndex, x, y, mouseEvent, relativeX, relativeY);
		if(selected){
			if(relativeX > 187 && relativeY > 13 && relativeX < 187 + 16 && relativeY < 13+16){
				list.remove(name);
			}
		}
	}

}
