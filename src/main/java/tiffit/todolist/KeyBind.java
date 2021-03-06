package tiffit.todolist;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import tiffit.todolist.config.GuiConfiguration;
import tiffit.todolist.gui.ListSelectionGui;
import tiffit.todolist.gui.TODOListGui;

public class KeyBind {

	public static KeyBinding openTodoList;
	public static KeyBinding openTodoListConfig;
	
	private static String category = "key.categories." + References.MODID;
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new KeyBind());
		openTodoList = new KeyBinding("openTodoList", KeyConflictContext.IN_GAME, Keyboard.KEY_COMMA, category);
		ClientRegistry.registerKeyBinding(openTodoList);
		openTodoListConfig = new KeyBinding("openTodoListConfig", KeyConflictContext.IN_GAME, KeyModifier.ALT, Keyboard.KEY_COMMA, category);
		ClientRegistry.registerKeyBinding(openTodoListConfig);
	}
	
	@SubscribeEvent
	public void keyPress(KeyInputEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		if(openTodoListConfig.isPressed() && openTodoListConfig.isKeyDown()){
			mc.displayGuiScreen(new GuiConfiguration());
			Minecraft.getMinecraft().player.playSound(SoundEvents.UI_BUTTON_CLICK, 4F, 1F);
		}else
		if(openTodoList.isPressed() && openTodoList.isKeyDown()){
			mc.displayGuiScreen(new ListSelectionGui());
			Minecraft.getMinecraft().player.playSound(SoundEvents.UI_BUTTON_CLICK, 4F, 1F);
			TODOListMod.message.clearMessage();
		}
		
	}
	
}
