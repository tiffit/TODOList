package tiffit.todolist.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import tiffit.todolist.TODOList;
import tiffit.todolist.TODOListMod;

public class AddListGui extends GuiScreen {

	ListSelectionGui behind;
	GuiTextField nameField;
	
	public AddListGui(ListSelectionGui behind){
		this.behind = behind;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawBackground(0);
		super.drawScreen(mouseX, mouseY, partialTicks);
		fontRendererObj.drawStringWithShadow("List Name:", this.width / 2 - 100, height/3 - fontRendererObj.FONT_HEIGHT, 0xffffff);
		nameField.drawTextBox();
	}
	
	@Override
	public void initGui() {
		super.initGui();
		nameField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, height/3, 200, 20);
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
		TODOListMod.lists.add(new TODOList(nameField.getText()));
		behind.update();
		Minecraft.getMinecraft().displayGuiScreen(behind);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		nameField.textboxKeyTyped(typedChar, keyCode);
	}
}
