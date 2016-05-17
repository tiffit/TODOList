package tiffit.todolist.hud;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tiffit.todolist.TODOList;

public class HudEvent {

	HUDTask task;
	
	public HudEvent(){
		task = new HUDTask();
	}
	
	@SubscribeEvent
	public void drawHUD(RenderGameOverlayEvent.Post e){
		task.drawHUD();
		TODOList.message.drawHUD();
	}
	
}
