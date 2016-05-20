package tiffit.todolist.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.resources.I18n;
import tiffit.todolist.TODOList;
import tiffit.todolist.items.TODOCraft;
import tiffit.todolist.items.TODOTask;
import tiffit.todolist.items.TODOTask.TaskPriority;

public class NewTaskSelectionGui extends GuiScreen implements GuiYesNoCallback {

	private NewTaskSelectionList taskSelectionList;
	TODOListGui behind;
	public NewTaskSelectionGui(TODOListGui behind){
		this.behind = behind;
	}
	
    public void handleMouseInput() throws IOException{
        super.handleMouseInput();
        this.taskSelectionList.handleMouseInput();
    }
	
	 public void initGui(){
		 Keyboard.enableRepeatEvents(true);
		 this.taskSelectionList = new NewTaskSelectionList(this.mc, this.width, this.height, 32, this.height - 64, 32);
	     this.buttonList.clear();
	     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 52, 100, 20, "Add"));
	     this.buttonList.add(new GuiButton(1, this.width / 2, this.height - 52, 100, 20, "Custom Task"));
	     this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height - 28, 200, 20, "Cancel"));
	 }
	 
	 public void drawScreen(int mouseX, int mouseY, float partialTicks){
		 this.drawDefaultBackground();
		 taskSelectionList.drawScreen(mouseX, mouseY, partialTicks);
		 this.drawCenteredString(this.fontRendererObj, "New Task", this.width / 2, 20, 16777215);
		 super.drawScreen(mouseX, mouseY, partialTicks);
	 }
	 
	 @Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button.id == 0 && taskSelectionList.getSelected() != null){
			TODOTask item = taskSelectionList.getSelected().item;
			behind.add(item);
			Minecraft.getMinecraft().displayGuiScreen(new EditTaskGui(behind.getTSL().getSize() - 1, item.getName(), "", TaskPriority.Medium.ordinal(), behind));
		}
		if(button.id == 1){
			Minecraft.getMinecraft().displayGuiScreen(new AddTaskTypeGui(this));
		}
		if(button.id == 2){
			Minecraft.getMinecraft().displayGuiScreen(behind);
		}
		
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
