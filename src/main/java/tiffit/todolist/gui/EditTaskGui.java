package tiffit.todolist.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import tiffit.todolist.TODOList;
import tiffit.todolist.items.TODOItem;

public class EditTaskGui extends GuiScreen {

	int index;
	String name;
	TODOListGui behind;
	GuiTextField nameField;
	
	public EditTaskGui(int index, String name, TODOListGui behind){
		this.index = index;
		this.name = name;
		this.behind = behind;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawBackground(0);
		super.drawScreen(mouseX, mouseY, partialTicks);
		nameField.drawTextBox();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		nameField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, this.height / 3, 200, 20);
		nameField.setText(name);
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, (this.height / 3) * 2, 200, 20, "Done"));
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		nameField.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		behind.setText(index, nameField.getText());
		Minecraft.getMinecraft().displayGuiScreen(behind);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		nameField.textboxKeyTyped(typedChar, keyCode);
	}
}
