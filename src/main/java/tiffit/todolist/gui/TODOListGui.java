package tiffit.todolist.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import tiffit.todolist.References;
import tiffit.todolist.TODOList;
import tiffit.todolist.TaskClock;
import tiffit.todolist.items.TODOTask;
import tiffit.todolist.items.TODOTask.TaskPriority;

public class TODOListGui extends GuiScreen{

    public static final ResourceLocation mc_widgets = new ResourceLocation("textures/gui/widgets.png");
	
	private TaskSelectionList taskSelectionList;
	private int updateSlide = 20;
	private int updateSlideHold = 100;
	
    public void handleMouseInput() throws IOException{
        super.handleMouseInput();
        this.taskSelectionList.handleMouseInput();
    }
	
	 public void initGui(){
		 Keyboard.enableRepeatEvents(true);
		 this.taskSelectionList = new TaskSelectionList(this.mc, this.width, this.height, 32, this.height - 64, 32);
	     this.buttonList.clear();
	     this.buttonList.add(new GuiButton(0, this.width / 2, this.height - 52, 100, 20, "Add Task"));
	     this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 52, 50, 20, "Finish"));
	     this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height - 52, 50, 20, "Remove"));
	     this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height - 28, 100, 20, "Edit"));
	     this.buttonList.add(new GuiButton(4, this.width / 2, this.height - 28, 100, 20, "Cancel"));
	     buttonList.get(1).enabled = false;
	     buttonList.get(2).enabled = false;
	     buttonList.get(3).enabled = false;
	 }
	 
	 public void drawScreen(int mouseX, int mouseY, float partialTicks){
		 validateButtons();
		 this.drawDefaultBackground();
		 taskSelectionList.drawScreen(mouseX, mouseY, partialTicks);
		 this.drawCenteredString(this.fontRendererObj, "TODO List", this.width / 2, 20, 16777215);
		 drawAuthor(mouseX, mouseY);
		 if(TODOList.tdlVer.isGreaterVersion(References.VERSION)) drawUpdate(mouseX, mouseY);
		 
		 super.drawScreen(mouseX, mouseY, partialTicks);
	 }
	 
	 private void drawUpdate(int x, int y){
		 drawString(fontRendererObj, "An update is available!", updateSlide, 4, 0xffffff);
		 Minecraft.getMinecraft().getTextureManager().bindTexture(mc_widgets);
		 drawScaledCustomSizeModalRect(0, 0, 238, 22, 8, 8, 16, 16, 256f, 256f);
		 if(updateSlideHold <= 0){
			 if(x >= 0 && y >= 0 && x <= 16 && y <= 16) updateSlide++;
			 else updateSlide--;
			 if(updateSlide < -120) updateSlide = -120;
			 if(updateSlide > 20) updateSlide = 20;
		 }else{
			 updateSlideHold--;
		 }
	 }
	 
	 private void drawAuthor(int x, int y){
		 int color = 0xffffff;
		 String drawString = "by tiffit";
		 if(isInside(x, y)) color = 0xFFEA00;
		 this.drawString(fontRendererObj, drawString, this.width - 43, this.height - 15, color);
	 }
	 
	 private boolean isInside(int x, int y){
		 if(!Desktop.isDesktopSupported()) return false;
		 if(x > this.width - 43 && y > this.height - 15 && x < this.width - 43 + fontRendererObj.getStringWidth("by tiffit") && y < this.height - 15 + fontRendererObj.FONT_HEIGHT) return true;
		 return false;
	 }
	 
	 private void validateButtons(){
		if(taskSelectionList.getSelected() != null){
			buttonList.get(1).enabled = true;
		    buttonList.get(2).enabled = true;
		    buttonList.get(3).enabled = true;
		}else{
			buttonList.get(1).enabled = false;
		    buttonList.get(2).enabled = false;
		    buttonList.get(3).enabled = false;
		}
	 }
	 
	 @Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button.id == 0){
			Minecraft.getMinecraft().displayGuiScreen(new NewTaskSelectionGui(this));
		}
		if(taskSelectionList.getSelected() != null){
			TaskEntry selected = taskSelectionList.getSelected();
			if(button.id == 1 || button.id == 2){
				TODOList.list.remove(selected.index);
				taskSelectionList.init();
			}
			if(button.id == 3){
				TODOTask task = selected.item;
				Minecraft.getMinecraft().displayGuiScreen(new EditTaskGui(selected.index, task.getName(), task.getClock() != null ? task.getClock().toString() : "", task.getPriority().ordinal(), this));
			}
		}
		if(button.id == 4){
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		
	}
	 
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		taskSelectionList.mouseClicked(mouseX, mouseY, mouseButton);
		if(isInside(mouseX, mouseY)){
			Desktop.getDesktop().browse(URI.create("http://minecraft.curseforge.com/members/tiffit"));
		}
		if(mouseY >= 0 && mouseX >= 0 && mouseX <= 16 && mouseX <= 16){
			if(TODOList.tdlVer.isGreaterVersion(References.VERSION)) TODOList.tdlVer.launchDllSite();
		}
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		taskSelectionList.mouseReleased(mouseX, mouseY, state);
	}
	
	public void add(TODOTask item){
		TODOList.list.add(item);
		taskSelectionList.init();
	}
	
	public void setText(int index, String text){
		TODOList.list.get(index).setName(text);
		taskSelectionList.init();
	}
	
	public void setTime(int index, String time){
		TODOList.list.get(index).setClock(TaskClock.fromString(time));
		taskSelectionList.init();
	}
	
	public void setPriority(int index, TaskPriority priority){
		TODOList.list.get(index).setPriority(priority);
		taskSelectionList.init();
	}
	
	public TaskSelectionList getTSL(){
		return taskSelectionList;
	}

}
