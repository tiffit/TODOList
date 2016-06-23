package tiffit.todolist.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import tiffit.todolist.TODOList;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.items.TODOTask;

public class ListSelectionList extends GuiListExtended {
	
	List<ListEntry> lists = new ArrayList<ListEntry>();
	
	public ListSelectionList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
		super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
		update();
	}
	
	public void update(){
		lists.clear();
		for(int i = 0; i < TODOListMod.lists.size(); i++){
			lists.add(new ListEntry(i, this));
		}
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return lists.get(index);
	}

	@Override
	protected int getSize() {
		return lists.size();
	}
	
	public ListEntry getSelected(){
		for(int i = 0; i < lists.size(); i++){
			ListEntry entry = lists.get(i);
			if(entry.selected){
				return entry;
			}
		}
		return null;
	}
	
	public void remove(int index){
		TODOListMod.lists.remove(index);
		update();
	}

}
