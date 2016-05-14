package tiffit.todolist.items;

import net.minecraft.nbt.NBTTagCompound;
import tiffit.todolist.ListLoader;
import tiffit.todolist.TaskClock;

public abstract class TODOTask {
	
	private String name = "";
	private TaskClock clock;
	
	public abstract void onClick();
	
	public abstract String taskName();
	
	public abstract int getX();
	
	public abstract int getY();
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setClock(TaskClock clock){
		this.clock = clock;
	}
	
	public TaskClock getClock(){
		return clock;
	}
	
	public void tick(){
		if(clock != null) clock.tick();
	}
	
	public String getName(){
		if(name == null || name.equals("")) return taskName();
		return name;
	}
	
	public NBTTagCompound getNBT(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("type", taskName());
		tag.setString("name", getName());
		if(clock != null && clock.enabled()) tag.setTag("clock", clock.toNBT());
		return tag;
	}
	
	public static TODOTask getFromNBT(NBTTagCompound nbt){
		if(nbt.getBoolean("custom")){
			TODOTask item = new TODOCustom(nbt.getString("custom_name"));
			item.setName(nbt.getString("name"));
			return item;
		}
		TODOTask item = ListLoader.getFromString(nbt.getString("type"));
		item.setName(nbt.getString("name"));
		if(nbt.hasKey("clock")) item.setClock(TaskClock.fromNBT(nbt.getCompoundTag("clock")));
		return item;
	}
	
}
