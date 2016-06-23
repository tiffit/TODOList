package tiffit.todolist.items;

import net.minecraft.nbt.NBTTagCompound;
import tiffit.todolist.ListLoader;
import tiffit.todolist.TaskClock;
import tiffit.todolist.Util;

public abstract class TODOTask {
	
	private String name = "";
	private int listIndex;
	private TaskClock clock;
	private TaskPriority priority;
	
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
	
	public void setPriority(TaskPriority priority){
		this.priority = priority;
	}
	
	public TaskPriority getPriority(){
		return priority;
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
	
	public int getListIndex(){
		return listIndex;
	}
	
	public NBTTagCompound getNBT(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("type", taskName());
		tag.setString("name", getName());
		tag.setInteger("priority", priority.ordinal());
		tag.setInteger("listIndex", listIndex);
		if(clock != null && clock.enabled()) tag.setTag("clock", clock.toNBT());
		return tag;
	}
	
	public static TODOTask getFromNBT(NBTTagCompound nbt){
		if(nbt.getBoolean("custom")){
			TODOTask item = new TODOCustom(nbt.getString("custom_name"));
			item.setName(nbt.getString("name"));
			return item;
		}
		TODOTask item = Util.getFromString(nbt.getString("type"));
		item.setName(nbt.getString("name"));
		item.setClock(TaskClock.fromNBT(nbt.getCompoundTag("clock")));
		if(nbt.hasKey("priority"))item.setPriority(TaskPriority.values()[nbt.getInteger("priority")]);
		else item.setPriority(TaskPriority.Medium);
		return item;
	}
	
	public static enum TaskPriority{
		Lowest(0xFFA2FF00), Low(0xFFE5FF00), Medium(0xFFFFD900), High(0xFFFF9100), Highest(0xFFFF0000);
		
		private int color;
		
		TaskPriority(int color){
			this.color = color;
		}
		
		public int getColor(){
			return color;
		}
	}
	
}
