package tiffit.todolist.items;

public class TODOMine extends TODOTask {

	public static final String NAME = "Mine";
	
	public TODOMine(){
		
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
		return 5;
	}

	@Override
	public int getY() {
		return 0;
	}

}
