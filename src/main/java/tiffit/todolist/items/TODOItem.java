package tiffit.todolist.items;

import net.minecraft.nbt.NBTTagCompound;
import tiffit.todolist.ListLoader;

public abstract class TODOItem {
	
	private String name = "";
	
	public abstract void onClick();
	
	public abstract String name();
	
	public abstract int getX();
	
	public abstract int getY();
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		if(name == null || name.equals("")) return name();
		return name;
	}
	
	public NBTTagCompound getNBT(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("type", name());
		tag.setString("name", getName());
		return tag;
	}
	
	public static TODOItem getFromNBT(NBTTagCompound nbt){
		TODOItem item = ListLoader.getFromString(nbt.getString("type"));
		item.setName(nbt.getString("name"));
		return item;
	}
	
}
