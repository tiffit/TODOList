package tiffit.todolist.config;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tiffit.todolist.TODOListMod;

public class GuiConfiguration extends GuiScreen {

	@Override
	public void initGui() {
		super.initGui();
		addButtons();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground(0);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawCenteredString(fontRendererObj, "Configuration", this.width/2, 5, 0xffffff);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button.id == 0){
			TODOListMod.config.setShouldDrawList(!TODOListMod.config.shouldDrawList());
		}
		if(button.id == 1){
			TODOListMod.config.setShouldVersionCheck(!TODOListMod.config.shouldVersionCheck());
		}
		addButtons();
	}
	
	private void addButtons(){
		buttonList.clear();
		buttonList.add(new GuiButton(0, width/2 - 150, 50, 150, 20, "Show Inv Button: " + TODOListMod.config.shouldDrawList()));
		buttonList.add(new GuiButton(1, width/2, 50, 150, 20, "Version Checker: " + TODOListMod.config.shouldVersionCheck()));
	}
	
}
