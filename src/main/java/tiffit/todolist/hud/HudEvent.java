package tiffit.todolist.hud;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tiffit.todolist.TODOListMod;

public class HudEvent {

	HUDTask task;
	
	public HudEvent(){
		task = new HUDTask();
	}
	
	@SubscribeEvent
	public void drawHUD(RenderGameOverlayEvent.Post e){
		task.drawHUD("post");
	}
	
	@SubscribeEvent
	public void drawHUD(RenderGameOverlayEvent.Text e){
		task.drawHUD("text");
		TODOListMod.message.drawHUD();
	}
	
	@SubscribeEvent
	public void drawHUD(RenderGameOverlayEvent.Pre e){
		task.drawHUD("pre");
	}
	
}
