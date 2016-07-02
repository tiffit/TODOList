 package tiffit.todolist.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.items.TODOTask;
import tiffit.todolist.items.TODOTask.TaskPriority;

public class ListSelectionGui extends GuiScreen {

	private ListSelectionList taskSelectionList;
	public ListSelectionGui(){
	}
	
    public void handleMouseInput() throws IOException{
        super.handleMouseInput();
        this.taskSelectionList.handleMouseInput();
    }
	
	 public void initGui(){
		 Keyboard.enableRepeatEvents(true);
		 this.taskSelectionList = new ListSelectionList(this.mc, this.width, this.height, 32, this.height - 64, 32);
	     this.buttonList.clear();
	     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 52, 100, 20, "Add"));
	     this.buttonList.add(new GuiButton(1, this.width / 2, this.height - 52, 100, 20, "Open"));
	 }
	 
	 public void drawScreen(int mouseX, int mouseY, float partialTicks){
		 this.drawDefaultBackground();
		 taskSelectionList.drawScreen(mouseX, mouseY, partialTicks);
		 this.drawCenteredString(this.fontRendererObj, "List Selection", this.width / 2, 20, 16777215);
		 super.drawScreen(mouseX, mouseY, partialTicks);
	 }
	 
	 @Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button.id == 0){
			Minecraft.getMinecraft().displayGuiScreen(new AddListGui(this));
		}
		if(button.id == 1){
			if(taskSelectionList.getSelected() != null){
				TODOListMod.current_list = taskSelectionList.getSelected().index;
				Minecraft.getMinecraft().displayGuiScreen(new TODOListGui());
			}
		}
	}
	 
	public void update(){
		taskSelectionList.update();
	}
	 
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		taskSelectionList.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		taskSelectionList.mouseReleased(mouseX, mouseY, state);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}

}
