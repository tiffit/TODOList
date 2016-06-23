package tiffit.todolist;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import tiffit.todolist.items.TODOTask;

public class TODOList {

	public List<TODOTask> list;
	private String name;
	
	public TODOList(String name){
		list = new LinkedList<TODOTask>();
		this.name = name;
	}
	
	public String name(){
		return name;
	}
	
	public NBTTagCompound toNBT(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("size", list.size());
		for(int i = 0; i < list.size(); i++){
			tag.setTag("" + i, list.get(i).getNBT());
		}
		tag.setString("name", name);
		return tag;
	}
	
	public static TODOList getFromNBT(NBTTagCompound tag){
		TODOList list = new TODOList(tag.getString("name"));
		for(int i = 0; i < tag.getInteger("size"); i++){
			list.list.add(TODOTask.getFromNBT(tag.getCompoundTag("" + i)));
		}
		return list;
	}
	
}
