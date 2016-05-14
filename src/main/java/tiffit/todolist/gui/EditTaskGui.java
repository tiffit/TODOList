package tiffit.todolist.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPageButtonList.GuiResponder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiSlider.FormatHelper;
import net.minecraft.client.gui.GuiTextField;
import tiffit.todolist.TODOList;
import tiffit.todolist.TaskClock;
import tiffit.todolist.items.TODOTask;

public class EditTaskGui extends GuiScreen {

	private int index;
	private final String name;
	private final String time;
	private final int priority;
	private TODOListGui behind;
	private GuiSlider prioritySlider;
	private GuiTextField nameField;
	private GuiTextField timeField;
	
	public EditTaskGui(int index, String name, String time, int priority, TODOListGui behind){
		this.index = index;
		this.name = name;
		this.behind = behind;
		this.time = time;
		this.priority = priority;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawBackground(0);
		super.drawScreen(mouseX, mouseY, partialTicks);
		fontRendererObj.drawStringWithShadow("Task Name:", this.width / 2 - 100, height/3 - fontRendererObj.FONT_HEIGHT, 0xffffff);
		fontRendererObj.drawStringWithShadow("Deadline (Leave blank if none):", this.width / 2 - 100, height/2 - fontRendererObj.FONT_HEIGHT, 0xffffff);
		fontRendererObj.drawStringWithShadow("HH:MM:SS", this.width / 2 - 100, height/2 - fontRendererObj.FONT_HEIGHT + 32, 0xbbbbbb);
		if(!TaskClock.validate(timeField.getText())){
			fontRendererObj.drawStringWithShadow("Invalid Format!", this.width / 2, height/2 - fontRendererObj.FONT_HEIGHT + 32, 0xff0000);
		}
		nameField.drawTextBox();
		timeField.drawTextBox();
		prioritySlider.drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		nameField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, height/3, 200, 20);
		nameField.setText(name);
		timeField = new GuiTextField(1, fontRendererObj, this.width / 2 - 100, height/2, 200, 20);
		timeField.setText(time);
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 40, 200, 20, "Done"));
		prioritySlider = new GuiSlider(new GuiResponder(){
			@Override
			public void func_175321_a(int value, boolean p_175321_2_) {}
			@Override
			public void onTick(int id, float value) {}
			@Override
			public void func_175319_a(int p_175319_1_, String p_175319_2_) {}
		}, 1, this.width / 2 - 100 + 25, (this.height / 3) * 2, "Value", 0, 4, priority, new FormatHelper() {
			@Override
			public String getText(int id, String name, float value) {
				return "Priority: " + TODOTask.TaskPriority.values()[(int) value];
			}
		});
		this.buttonList.add(prioritySlider);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		nameField.mouseClicked(mouseX, mouseY, mouseButton);
		timeField.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button.id == 0){
			behind.setText(index, nameField.getText());
			String time = timeField.getText();
			if(TaskClock.validate(time))behind.setTime(index, time);
			behind.setPriority(index, TODOTask.TaskPriority.values()[(int) prioritySlider.getSliderValue()]);
			TODOList.reorganize();
			Minecraft.getMinecraft().displayGuiScreen(behind);
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		nameField.textboxKeyTyped(typedChar, keyCode);
		timeField.textboxKeyTyped(typedChar, keyCode);
	}
}
