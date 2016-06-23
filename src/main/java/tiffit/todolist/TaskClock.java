package tiffit.todolist;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

public class TaskClock {

	private int hours;
	private int minutes;
	private int seconds;
	private int stage;
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
			stage++;
			if(stage >= 4) stage = 0;
			calculateReadable();
			lastCheckTime = Minecraft.getSystemTime();
			if(hours == 0 && minutes == 1 && seconds == 0){
				TODOListMod.message.setMessage("Deadline", "1 minute left!", false);
			}
			if(hours == 0 && minutes == 5 && seconds == 0){
				TODOListMod.message.setMessage("Deadline", "5 minutes left!", false);
			}
			if(seconds <= 0 && minutes <= 0 && hours <= 0){
				time_left = false;
				TODOListMod.message.setMessage("Deadline", "Task has reached deadline!", false);
			}
		}
		
	}
	
	public int getStage(){
		return stage;
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
	
	public int getTextColor(){
		if(hours == 0 && minutes == 0 && minutes <= 59){
			return 0xbb0000;
		}
		return 0xdddddd;
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
	
	public boolean hasMoreTime(TaskClock clock){
		if(clock.getHours() > getHours()) return true;
		if(clock.getMinutes() > getMinutes()) return true;
		if(clock.getSeconds() > getSeconds()) return true;
		return false;
		
	}
}
