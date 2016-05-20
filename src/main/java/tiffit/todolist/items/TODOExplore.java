package tiffit.todolist.items;

public class TODOExplore extends TODOTask {

	public static final String NAME = "Explore";
	
	public TODOExplore(){
		
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
		return 6;
	}

	@Override
	public int getY() {
		return 0;
	}

}
