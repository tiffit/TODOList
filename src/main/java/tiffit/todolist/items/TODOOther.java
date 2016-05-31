package tiffit.todolist.items;

public class TODOOther extends TODOTask {

	public static final String NAME = "Other";
	
	public TODOOther(){
		
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
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

}
