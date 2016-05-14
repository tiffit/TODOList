package tiffit.todolist.items;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;

public class TODOCustom extends TODOTask {

	private String name;
	
	public TODOCustom(){
		
	}
	
	public void setCustomName(String name){
		this.name = name;
	}
	
	public TODOCustom(String name){
		this.name = name;
	}
	
	@Override
	public void onClick() {
	}

	@Override
	public String taskName() {
		return name;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}
	
	@Override
	public NBTTagCompound getNBT(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("type", taskName());
		tag.setString("name", getName());
		tag.setBoolean("custom", true);
		tag.setString("custom_name", name);
		return tag;
	}

}
