package tiffit.todolist.config;

import net.minecraft.nbt.NBTTagCompound;
import tiffit.todolist.gui.EnumTheme;

public class Configuration {
	
	private boolean drawList;
	private boolean versionCheck;
	private EnumTheme theme;
	
	public Configuration(){
		drawList = true;
		versionCheck = true;
		theme = EnumTheme.Default;
	}
	
	public boolean shouldDrawList(){
		return drawList;
	}
	
	public void setShouldDrawList(boolean shouldDrawList){
		drawList = shouldDrawList;
	}

	
	public EnumTheme getTheme(){
		return theme;
	}
	
	public void setTheme(EnumTheme theme){
		this.theme = theme;
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
		tag.setInteger("theme", theme.ordinal());
		return tag;
	}
	
	public static Configuration getFromNBT(NBTTagCompound tag){
		Configuration config = new Configuration();
		config.setShouldDrawList(tag.getBoolean("drawList"));
		config.setShouldVersionCheck(tag.getBoolean("versionCheck"));
		config.setTheme(EnumTheme.values()[tag.getInteger("theme")]);
		return config;
	}
	
}
