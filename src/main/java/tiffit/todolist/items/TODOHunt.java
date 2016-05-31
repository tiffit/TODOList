package tiffit.todolist.items;

public class TODOHunt extends TODOTask {

	public static final String NAME = "Hunt";
	
	public TODOHunt(){
		
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
		return 4;
	}

	@Override
	public int getY() {
		return 0;
	}

}
