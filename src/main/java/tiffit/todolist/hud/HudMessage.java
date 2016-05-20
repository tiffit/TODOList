package tiffit.todolist.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import tiffit.todolist.References;

public class HudMessage {

	private static final ResourceLocation widgets = new ResourceLocation(References.MODID + ":textures/gui/widgets.png");
	
	private String message;
	private String title;
	private int timing;
	private int x;
	private int original_x;
	private Stage stage;
	private int update_delay;
	private final int update_delay_max = 10;
	
	public HudMessage(){
		stage = Stage.None;
	}
	
	public void drawHUD(){
		if(stage == Stage.None) return;
		Minecraft mc = Minecraft.getMinecraft();
		GlStateManager.enableBlend();
		mc.renderEngine.bindTexture(widgets);
		for(int i = 0; i <= mc.fontRendererObj.getStringWidth(message) + x; i++){
			Gui.drawScaledCustomSizeModalRect(i, 48, 0, 0, 1, 22, 1, 22, 256.0F, 256.0F);
		}
		Gui.drawScaledCustomSizeModalRect(mc.fontRendererObj.getStringWidth(message) + x + 1, 48, 1, 0, 2, 22, 2, 22, 256.0F, 256.0F);
		mc.fontRendererObj.drawStringWithShadow(title, x, 50, 0xffffff);
		mc.fontRendererObj.drawStringWithShadow(message, x, 60, 0xbfbfbf);
		if(update_delay <= 0){
			update();
			update_delay = update_delay_max;
		}else{
			update_delay--;
		}
	}
	
	private void update(){
		if(stage == Stage.Open){
			x++;
			if(x >= 0){
				x = 0;
				stage = Stage.Stay;
			}
		}
		if(stage == Stage.Stay){
			timing--;
			if(timing <= 0){
				stage = Stage.Close;
			}
		}
		if(stage == Stage.Close){
			x--;
			if(x <= original_x){
				stage = Stage.None;
			}
		}
	}
		
	public void setMessage(String title, String message){
		this.title = title;
		this.message = message;
		timing = 300;
		x = original_x = -Minecraft.getMinecraft().fontRendererObj.getStringWidth(message);
		stage = Stage.Open;
		try{
			Minecraft.getMinecraft().thePlayer.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 4F, 1F);
		}catch(NullPointerException e){
			System.err.println("Error while sending message! message = " + message);
		}catch(Exception e){
			System.err.println("Unknown (sound) error while sending message! message = " + message);
		}
	}
	
	private enum Stage{
		Open, Stay, Close, None;
	}
	
}
