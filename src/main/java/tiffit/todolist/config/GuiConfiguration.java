package tiffit.todolist.config;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.gui.EnumTheme;
import tiffit.todolist.utils.GuiUtils;

public class GuiConfiguration extends GuiScreen {

	@Override
	public void initGui() {
		super.initGui();
		addButtons();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GuiUtils.drawBackground(TODOListMod.config.getTheme(), width, height);
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
		if(button.id == 2){
			int next_id = TODOListMod.config.getTheme().ordinal() + 1;
			if(next_id >= EnumTheme.values().length)next_id = 0;
			TODOListMod.config.setTheme(EnumTheme.values()[next_id]);
		}
		addButtons();
	}
	
	private void addButtons(){
		buttonList.clear();
		buttonList.add(new GuiButton(0, width/2 - 150, 50, 150, 20, "Show Inv Button: " + TODOListMod.config.shouldDrawList()));
		buttonList.add(new GuiButton(1, width/2, 50, 150, 20, "Version Checker: " + TODOListMod.config.shouldVersionCheck()));
		buttonList.add(new GuiButton(2, width/2 - 150, 75, 300, 20, "Theme: " + TODOListMod.config.getTheme()));

	}
	
}
