package tiffit.todolist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import tiffit.todolist.items.TODOBuild;
import tiffit.todolist.items.TODOCraft;
import tiffit.todolist.items.TODOExplore;
import tiffit.todolist.items.TODOGather;
import tiffit.todolist.items.TODOHunt;
import tiffit.todolist.items.TODOItem;
import tiffit.todolist.items.TODOMine;
import tiffit.todolist.items.TODOOther;

public class ListLoader {

	public static void register(){
		TODOList.taskRegistry.putObject(TODOCraft.NAME, TODOCraft.class);
		TODOList.taskRegistry.putObject(TODOBuild.NAME, TODOBuild.class);
		TODOList.taskRegistry.putObject(TODOGather.NAME, TODOGather.class);
		TODOList.taskRegistry.putObject(TODOHunt.NAME, TODOHunt.class);
		TODOList.taskRegistry.putObject(TODOMine.NAME, TODOMine.class);
		TODOList.taskRegistry.putObject(TODOExplore.NAME, TODOExplore.class);
		TODOList.taskRegistry.putObject(TODOOther.NAME, TODOOther.class);
	}
	
	public static void post_register(){
		
	}
	
	public static List<TODOItem> getListFromStorage(File file){
		System.out.println("Getting TODO list from storage...");
		ArrayList<TODOItem> list = new ArrayList<TODOItem>();
		File folder = new File(file, References.MODID);
		folder.mkdir();
		File data = new File(folder, "list.dat");
		NBTTagCompound tag = null;
		try {
			tag = CompressedStreamTools.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(tag == null){
			return list;
		}
		for(int i = 0; i < tag.getInteger("size"); i++){
			list.add(TODOItem.getFromNBT(tag.getCompoundTag("" + i)));
		}
		return list;
	}
	
	public static TODOItem getFromString(String str){
		try {
			return TODOList.taskRegistry.getObject(str).newInstance();
		} catch (Exception e){
			e.printStackTrace();
		}
		System.err.println("Error while reading TODOList! String = " + str);
		return null;
	}
	
	public static void storeToFile(File file, List<TODOItem> list){
		System.out.println("Saving TODO list to storage...");
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("size", list.size());
		for(int i = 0; i < list.size(); i++){
			tag.setTag("" + i, list.get(i).getNBT());
		}
		try {
			CompressedStreamTools.write(tag, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
