package tiffit.todolist;

import java.io.File;
import java.io.IOException;

public class ShutdownEvent extends Thread {
	
	File configDir;
	
	public ShutdownEvent(File configDir){
		this.configDir = configDir;
	}
	
	@Override
	public void run(){
		saveList();
	}
	
	private void saveList(){
		File f = new File(configDir, References.MODID);
		f.mkdir();
		File data = new File(f, "list.dat");
		if(!data.exists()){
			try {
				data.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ListLoader.storeToFile(data, TODOList.list);
	}
}
