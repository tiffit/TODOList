package tiffit.todolist.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import tiffit.todolist.TODOList;
import tiffit.todolist.items.TODOItem;

public class TaskSelectionList extends GuiListExtended {
	
	List<TaskEntry> tasks = new ArrayList<TaskEntry>();
	
	public TaskSelectionList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
		super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
		init();
	}
	
	public void init(){
		tasks.clear();
		for(int i = 0; i < TODOList.list.size(); i++){
			tasks.add(new TaskEntry(i, this));
		}
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return tasks.get(index);
	}

	@Override
	protected int getSize() {
		return tasks.size();
	}
	
	protected void remove(int index){
		TODOList.list.remove(index);
		init();
	}
	
	public TaskEntry getSelected(){
		for(int i = 0; i < tasks.size(); i++){
			TaskEntry entry = tasks.get(i);
			if(entry.selected) return entry;
		}
		return null;
	}

}
