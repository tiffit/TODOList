package tiffit.todolist.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.items.TODOTask;

public class NewTaskSelectionList extends GuiListExtended {
	
	List<NewTaskEntry> tasks = new ArrayList<NewTaskEntry>();
	
	public NewTaskSelectionList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
		super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
		update();
	}
	
	private void update(){
		tasks.clear();
		for(String str : TODOListMod.taskRegistry.getKeys()){
			Class<? extends TODOTask> clazz = TODOListMod.taskRegistry.getObject(str);
			tasks.add(new NewTaskEntry(clazz, this));
		}
		for(String str : TODOListMod.customTaskRegistry){
			tasks.add(new CustomNewTaskEntry(str, this));
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
	
	public NewTaskEntry getSelected(){
		for(int i = 0; i < tasks.size(); i++){
			NewTaskEntry entry = tasks.get(i);
			if(entry.selected){
				return entry;
			}
		}
		return null;
	}
	
	public void remove(String name){
		TODOListMod.customTaskRegistry.remove(name);
		update();
	}

}
