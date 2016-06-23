package tiffit.todolist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import tiffit.todolist.items.TODOBuild;
import tiffit.todolist.items.TODOCraft;
import tiffit.todolist.items.TODOExplore;
import tiffit.todolist.items.TODOGather;
import tiffit.todolist.items.TODOHunt;
import tiffit.todolist.items.TODOMine;
import tiffit.todolist.items.TODOOther;
import tiffit.todolist.items.TODOTask;

public class ListLoader {

	public static void register(File file){
		TODOListMod.taskRegistry.putObject(TODOCraft.NAME, TODOCraft.class);
		TODOListMod.taskRegistry.putObject(TODOBuild.NAME, TODOBuild.class);
		TODOListMod.taskRegistry.putObject(TODOGather.NAME, TODOGather.class);
		TODOListMod.taskRegistry.putObject(TODOHunt.NAME, TODOHunt.class);
		TODOListMod.taskRegistry.putObject(TODOMine.NAME, TODOMine.class);
		TODOListMod.taskRegistry.putObject(TODOExplore.NAME, TODOExplore.class);
		TODOListMod.taskRegistry.putObject(TODOOther.NAME, TODOOther.class);
		NBTTagCompound base = tagFromFile(file);
		if(base != null){
			NBTTagCompound tag = base.getCompoundTag("custom");
			if(tag.hasKey("i")){
				for(int i = 0; i < tag.getInteger("i"); i++){
					TODOListMod.customTaskRegistry.add(tag.getString("name_" + i));
				}
			}
		}
	}
	
	public static void post_register(){
		
	}
	
	private static NBTTagCompound tagFromFile(File file){
		File folder = new File(file, References.MODID);
		folder.mkdir();
		File data = new File(folder, "list.dat");
		NBTTagCompound tag = null;
		try {
			tag = CompressedStreamTools.read(data);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error while reading TODOList! File = " + file.toPath());
		}
		return tag;
		
	}
	
	public static List<TODOList> getListFromStorage(File file){
		System.out.println("Getting TODO list from storage...");
		List<TODOList> lists = new ArrayList<TODOList>();
		NBTTagCompound tag = tagFromFile(file);
		if(tag == null){
			return lists;
		}
		for(int i = 0; i < tag.getInteger("size"); i++){
			lists.add(TODOList.getFromNBT(tag.getCompoundTag("" + i)));
		}
		return lists;
	}
	
	public static void storeToFile(File file){
		System.out.println("Saving TODO list to storage...");
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("size", TODOListMod.lists.size());
		for(int i = 0; i < TODOListMod.lists.size(); i++){
			tag.setTag("" + i, TODOListMod.lists.get(i).toNBT());
		}
		if(TODOListMod.customTaskRegistry.size() > 0){
			NBTTagCompound custom = new NBTTagCompound();
			custom.setInteger("i", TODOListMod.customTaskRegistry.size());
			for(int i = 0; i < TODOListMod.customTaskRegistry.size(); i++){
				custom.setString("name_" + i, TODOListMod.customTaskRegistry.get(i));
			}
			tag.setTag("custom", custom);
		}
		boolean savedSuccess = true;
		try {
			CompressedStreamTools.write(tag, file);
		} catch (IOException e) {
			System.out.println("Error while saving list!");
			savedSuccess = false;
			e.printStackTrace();
		}
		if(savedSuccess) System.out.println("List has been saved!");
	}
	
}
