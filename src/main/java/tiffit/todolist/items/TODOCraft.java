package tiffit.todolist.items;

public class TODOCraft extends TODOTask {

	public static final String NAME = "Craft";
	
	public TODOCraft(){
		
	}
	
	@Override
	public void onClick() {
	}

	@Override
	public String taskName() {
		return NAME;
	}

	@Override
	public int getX() {
		return 1;
	}

	@Override
	public int getY() {
		return 0;
	}

}
