package tiffit.todolist;

import tiffit.todolist.items.TODOOther;
import tiffit.todolist.items.TODOTask;

public class Util {
	
	public static TODOTask getFromString(String str){
		try {
			return TODOListMod.taskRegistry.getObject(str).newInstance();
		} catch (Exception e){
			e.printStackTrace();
			System.err.println("Error while reading TODOList! String = " + str);
		}
		return new TODOOther();
	}

}
