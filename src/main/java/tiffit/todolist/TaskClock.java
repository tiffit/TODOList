package tiffit.todolist;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import scala.tools.nsc.backend.icode.Primitives.Primitive;

public class TaskClock {

	private int hours;
	private int minutes;
	private int seconds;
	private long lastCheckTime;
	private boolean time_left = true;
	
	public TaskClock(int hours, int minutes, int seconds){
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		lastCheckTime = Minecraft.getSystemTime();
	}
	
	public void tick(){
		if(Minecraft.getSystemTime() - lastCheckTime >= 1000){
			seconds--;
			calculateReadable();
			lastCheckTime = Minecraft.getSystemTime();
			if(hours == 0 && minutes == 1 && seconds == 0){
				TODOList.message.setMessage("Deadline", "1 minute left!");
			}
			if(hours == 0 && minutes == 5 && seconds == 0){
				TODOList.message.setMessage("Deadline", "5 minutes left!");
			}
			if(seconds <= 0){
				time_left = false;
				TODOList.message.setMessage("Deadline", "Task has reached deadline!");
			}
		}
		
	}
	
	public boolean enabled(){
		return time_left;
	}
	
	private void calculateReadable(){
		int seconds = this.seconds + (((this.hours*60) + minutes)*60);
		int minutes = 0;
		int hours = 0;
		while(seconds > 59){
			seconds-=60;
			minutes++;
		}
		while(minutes > 59){
			minutes-=60;
			hours++;
		}
		this.seconds = seconds;
		this.minutes = minutes;
		this.hours = hours;
	}
	
	public int getHours(){ return hours; }
	public int getMinutes(){ return minutes; }
	public int getSeconds(){ return seconds; }
	
	public NBTTagCompound toNBT(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("hours", hours);
		tag.setInteger("minutes", minutes);
		tag.setInteger("seconds", seconds);
		return tag;
	}
	
	public static TaskClock fromNBT(NBTTagCompound tag){
		int hours = tag.getInteger("hours");
		int minutes = tag.getInteger("minutes");
		int seconds = tag.getInteger("seconds");
		return new TaskClock(hours, minutes, seconds);
	}
	
	@Override
	public String toString(){
		calculateReadable();
		return hours + ":" + minutes + ":" + seconds;
	}
	
	public static TaskClock fromString(String str){
		if(str.equals("")) return null;
		String[] times = str.split(":");
		int hours = Integer.valueOf(times[0]);
		int minutes = Integer.valueOf(times[1]);
		int seconds = Integer.valueOf(times[2]);
		return new TaskClock(hours, minutes, seconds);
	}
	
	public static boolean validate(String str){
		if(str.equals("")) return true;
		String[] times = str.split(":");
		if(times.length != 3) return false;
		for(String time : times){
			try{
				Integer.valueOf(time);
			}catch(NumberFormatException e){
				return false;
			}
		}
		return true;
	}
}
