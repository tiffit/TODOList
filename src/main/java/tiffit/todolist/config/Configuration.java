package tiffit.todolist.config;

import net.minecraft.nbt.NBTTagCompound;

public class Configuration {
	
	private boolean drawList;
	private boolean versionCheck;
	
	public Configuration(){
		drawList = true;
		versionCheck = true;
	}
	
	public boolean shouldDrawList(){
		return drawList;
	}
	
	public void setShouldDrawList(boolean shouldDrawList){
		drawList = shouldDrawList;
	}
	
	public boolean shouldVersionCheck(){
		return versionCheck;
	}
	
	public void setShouldVersionCheck(boolean shouldVersionCheck){
		versionCheck = shouldVersionCheck;
	}
	
	public NBTTagCompound getNBT(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("drawList", drawList);
		tag.setBoolean("versionCheck", versionCheck);
		return tag;
	}
	
	public static Configuration getFromNBT(NBTTagCompound tag){
		Configuration config = new Configuration();
		config.setShouldDrawList(tag.getBoolean("drawList"));
		config.setShouldVersionCheck(tag.getBoolean("versionCheck"));
		return config;
	}
	
}
