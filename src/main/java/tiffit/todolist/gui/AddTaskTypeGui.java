package tiffit.todolist.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.utils.GuiUtils;

public class AddTaskTypeGui extends GuiScreen {

	NewTaskSelectionGui behind;
	GuiTextField nameField;
	
	public AddTaskTypeGui(NewTaskSelectionGui behind){
		this.behind = behind;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GuiUtils.drawBackground(TODOListMod.config.getTheme(), width, height);
		super.drawScreen(mouseX, mouseY, partialTicks);
		fontRenderer.drawStringWithShadow("Task Type Name:", this.width / 2 - 100, height/3 - fontRenderer.FONT_HEIGHT, 0xffffff);
		nameField.drawTextBox();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		nameField = new GuiTextField(0, fontRenderer, this.width / 2 - 100, height/3, 200, 20);
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
		TODOListMod.customTaskRegistry.add(nameField.getText());
		Minecraft.getMinecraft().displayGuiScreen(behind);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		nameField.textboxKeyTyped(typedChar, keyCode);
	}
}
