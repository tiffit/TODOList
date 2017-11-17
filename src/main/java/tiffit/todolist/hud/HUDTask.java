package tiffit.todolist.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tiffit.todolist.References;
import tiffit.todolist.TODOListMod;
import tiffit.todolist.items.TODOTask;

public class HUDTask {

	
	
	public void drawHUD(String type){
		if(TODOListMod.hudtask == null) return;
		TODOTask task = TODOListMod.hudtask;
		Minecraft mc = Minecraft.getMinecraft();
		if(type.equals("post")){
			mc.getTextureManager().bindTexture(References.ICONS);
			int nameW = mc.fontRenderer.getStringWidth(task.getName()) + 28;
			int taskW = mc.fontRenderer.getStringWidth(task.taskName() + " Task") + 28;
			Gui.drawRect(0, 0, nameW > taskW ? nameW : taskW, 40, 0x11ffffff);
			GlStateManager.enableBlend();
			Gui.drawModalRectWithCustomSizedTexture(5, 5, task.getX()*32, task.getY()*32, 32, 32, 256f, 256f);
			GlStateManager.disableBlend();
			GlStateManager.color(1f, 1f, 1f, 1f);
			mc.getTextureManager().bindTexture(References.MC_ICONS);
			
		}
		if(type.equals("text")){
			mc.fontRenderer.drawStringWithShadow(task.getName(), 24, 10, 0xffffff);
			int taskY = 20;
			if(task.getClock() != null){
				mc.fontRenderer.drawStringWithShadow(task.getClock().toString(), 24, 20, task.getClock().getTextColor());
				taskY+=10;
			}
			mc.fontRenderer.drawStringWithShadow(task.taskName() + " Task", 24, taskY, 0xbbbbbb);
		}
		
	}
	
}
