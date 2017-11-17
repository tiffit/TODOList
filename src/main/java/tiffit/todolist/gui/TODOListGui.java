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
import tiffit.todolist.TODOListMod;
import tiffit.todolist.TaskClock;
import tiffit.todolist.config.GuiConfiguration;
import tiffit.todolist.items.TODOTask;
import tiffit.todolist.items.TODOTask.TaskPriority;
import tiffit.todolist.utils.GuiUtils;

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
	     this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 52, 100, 20, "Display on HUD"));
	     this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height - 28, 100, 20, "Edit"));
	     this.buttonList.add(new GuiButton(3, this.width / 2, this.height - 28, 100, 20, "Cancel"));
	     buttonList.get(2).enabled = false;
	 }
	 
	 public void drawScreen(int mouseX, int mouseY, float partialTicks){
		 validateButtons();
		GuiUtils.drawBackground(TODOListMod.config.getTheme(), width, height);
		 taskSelectionList.drawScreen(mouseX, mouseY, partialTicks);
		 this.drawCenteredString(this.fontRenderer, "TODO List" + " (" + TODOListMod.lists.get(TODOListMod.current_list).name() + ")", this.width / 2, 20, 16777215);
		 drawAuthor(mouseX, mouseY);
		 if(TODOListMod.config.shouldVersionCheck()){
			 if(TODOListMod.tdlVer != null){
				 if(TODOListMod.tdlVer.isGreaterVersion(References.VERSION)) drawUpdate(mouseX, mouseY);
			 }
		 }
		 
		 super.drawScreen(mouseX, mouseY, partialTicks);
	 }
	 
	 private void drawUpdate(int x, int y){
		 if(!TODOListMod.config.shouldVersionCheck()) return;
		 drawString(fontRenderer, "An update is available!", updateSlide, 4, 0xffffff);
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
		 this.drawString(fontRenderer, drawString, this.width - 43, this.height - 15, color);
	 }
	 
	 private boolean isInside(int x, int y){
		 if(!Desktop.isDesktopSupported()) return false;
		 if(x > this.width - 43 && y > this.height - 15 && x < this.width - 43 + fontRenderer.getStringWidth("by tiffit") && y < this.height - 15 + fontRenderer.FONT_HEIGHT) return true;
		 return false;
	 }
	 
	 private void validateButtons(){
		if(taskSelectionList.getSelected() != null) buttonList.get(2).enabled = true;
		else buttonList.get(2).enabled = false;
		
		GuiButton hud_disp_button = buttonList.get(1);
		if(taskSelectionList.getSelected() == null && TODOListMod.hudtask == null) hud_disp_button.enabled = false;
		else hud_disp_button.enabled = true;
		if(taskSelectionList.getSelected() != null) hud_disp_button.displayString = "Display on HUD";
		else if(TODOListMod.hudtask != null) hud_disp_button.displayString = "Clear HUD";
		else hud_disp_button.displayString = "Display on HUD";;
	 }
	 
	 @Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button.id == 0){
			Minecraft.getMinecraft().displayGuiScreen(new NewTaskSelectionGui(this));
		}
		if(button.id == 1){
			TaskEntry entry = taskSelectionList.getSelected();
			if(entry == null) TODOListMod.hudtask = null;
			else TODOListMod.hudtask = entry.item;
		}
		if(taskSelectionList.getSelected() != null){
			TaskEntry selected = taskSelectionList.getSelected();
			if(button.id == 2){
				TODOTask task = selected.item;
				Minecraft.getMinecraft().displayGuiScreen(new EditTaskGui(selected.index, task.getName(), task.getClock() != null ? task.getClock().toString() : "", task.getPriority().ordinal(), this));
			}
		}
		if(button.id == 3){
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
		if(TODOListMod.config.shouldVersionCheck()){
			if(mouseY >= 0 && mouseX >= 0 && mouseX <= 16 && mouseX <= 16){
				if(TODOListMod.tdlVer.isGreaterVersion(References.VERSION)) TODOListMod.tdlVer.launchDllSite();
			}
		}
		
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		taskSelectionList.mouseReleased(mouseX, mouseY, state);
	}
	
	public void add(TODOTask item){
		TODOListMod.getCurrent().list.add(item);
		taskSelectionList.init();
	}
	
	public void setText(int index, String text){
		TODOListMod.getCurrent().list.get(index).setName(text);
		taskSelectionList.init();
	}
	
	public void setTime(int index, String time){
		TODOListMod.getCurrent().list.get(index).setClock(TaskClock.fromString(time));
		taskSelectionList.init();
	}
	
	public void setPriority(int index, TaskPriority priority){
		TODOListMod.getCurrent().list.get(index).setPriority(priority);
		taskSelectionList.init();
	}
	
	public TaskSelectionList getTSL(){
		return taskSelectionList;
	}

}
