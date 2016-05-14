package tiffit.todolist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import tiffit.todolist.items.TODOBMAltar;
import tiffit.todolist.items.TODOBuild;
import tiffit.todolist.items.TODOCraft;
import tiffit.todolist.items.TODOExplore;
import tiffit.todolist.items.TODOGather;
import tiffit.todolist.items.TODOHunt;
import tiffit.todolist.items.TODOTask;
import tiffit.todolist.items.TODOMine;
import tiffit.todolist.items.TODOOther;

public class ListLoader {

	public static void register(File file){
		TODOList.taskRegistry.putObject(TODOCraft.NAME, TODOCraft.class);
		TODOList.taskRegistry.putObject(TODOBuild.NAME, TODOBuild.class);
		TODOList.taskRegistry.putObject(TODOGather.NAME, TODOGather.class);
		TODOList.taskRegistry.putObject(TODOHunt.NAME, TODOHunt.class);
		TODOList.taskRegistry.putObject(TODOMine.NAME, TODOMine.class);
		TODOList.taskRegistry.putObject(TODOExplore.NAME, TODOExplore.class);
		TODOList.taskRegistry.putObject(TODOOther.NAME, TODOOther.class);
		if(Loader.isModLoaded("BloodMagic")) {
			TODOList.taskRegistry.putObject(TODOBMAltar.NAME, TODOBMAltar.class);
		}
		NBTTagCompound base = tagFromFile(file);
		if(base != null){
			NBTTagCompound tag = tagFromFile(file).getCompoundTag("custom");
			if(tag.hasKey("i")){
				for(int i = 0; i < tag.getInteger("i"); i++){
					TODOList.customTaskRegistry.add(tag.getString("name_" + i));
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
		if(tag != null){
			if(tag.hasKey("enableButtonMessage")) TODOList.enableButtonMessage = tag.getBoolean("enableButtonMessage");
			else TODOList.enableButtonMessage = true;
		}else{
			TODOList.enableButtonMessage = true;
		}
		return tag;
		
	}
	
	public static List<TODOTask> getListFromStorage(File file){
		System.out.println("Getting TODO list from storage...");
		ArrayList<TODOTask> list = new ArrayList<TODOTask>();
		NBTTagCompound tag = tagFromFile(file);
		if(tag == null){
			return list;
		}
		for(int i = 0; i < tag.getInteger("size"); i++){
			list.add(TODOTask.getFromNBT(tag.getCompoundTag("" + i)));
		}
		return list;
	}
	
	public static TODOTask getFromString(String str){
		try {
			return TODOList.taskRegistry.getObject(str).newInstance();
		} catch (Exception e){
			e.printStackTrace();
			System.err.println("Error while reading TODOList! String = " + str);
		}
		
		return null;
	}
	
	public static void storeToFile(File file, List<TODOTask> list){
		System.out.println("Saving TODO list to storage...");
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("size", list.size());
		for(int i = 0; i < list.size(); i++){
			tag.setTag("" + i, list.get(i).getNBT());
		}
		if(TODOList.customTaskRegistry.size() > 0){
			NBTTagCompound custom = new NBTTagCompound();
			custom.setInteger("i", TODOList.customTaskRegistry.size());
			for(int i = 0; i < TODOList.customTaskRegistry.size(); i++){
				custom.setString("name_" + i, TODOList.customTaskRegistry.get(i));
			}
			tag.setTag("custom", custom);
		}
		tag.setBoolean("enableButtonMessage", TODOList.enableButtonMessage);	
		try {
			CompressedStreamTools.write(tag, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
