package tiffit.todolist.items;

public class TODOBuild extends TODOTask {

	public static final String NAME = "Build";
	
	public TODOBuild(){
		
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
		return 2;
	}

	@Override
	public int getY() {
		return 0;
	}

}
