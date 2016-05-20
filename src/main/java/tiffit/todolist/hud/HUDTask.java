package tiffit.todolist.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import tiffit.todolist.References;
import tiffit.todolist.TODOList;
import tiffit.todolist.items.TODOTask;

public class HUDTask {

	public void drawHUD(){
		if(TODOList.hudtask == null) return;
		TODOTask task = TODOList.hudtask;
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(References.ICONS);
		int nameW = mc.fontRendererObj.getStringWidth(task.getName()) + 28;
		int taskW = mc.fontRendererObj.getStringWidth(task.taskName() + " Task") + 28;
		Gui.drawRect(0, 0, nameW > taskW ? nameW : taskW, 40, 0x11ffffff);
		GlStateManager.enableBlend();
		Gui.drawModalRectWithCustomSizedTexture(5, 5, task.getX()*32, task.getY()*32, 32, 32, 256f, 256f);
		mc.fontRendererObj.drawStringWithShadow(task.getName(), 24, 10, 0xffffff);
		int taskY = 20;
		if(task.getClock() != null){
			mc.fontRendererObj.drawStringWithShadow(task.getClock().toString(), 24, 20, task.getClock().getTextColor());
			taskY+=10;
		}
		mc.fontRendererObj.drawStringWithShadow(task.taskName() + " Task", 24, taskY, 0xbbbbbb);
		
	}
	
}
