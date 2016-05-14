package tiffit.todolist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import tiffit.todolist.gui.TODOListGui;
import tiffit.todolist.hudmessage.HudMessage;
import tiffit.todolist.items.TODOTask;
import tiffit.todolist.versionchecker.TDLVersion;
import tiffit.todolist.versionchecker.VersionParser;


@Mod(modid = References.MODID, version = References.VERSION, name = References.MODID, clientSideOnly = true)
public class TODOList {

	public static List<TODOTask> list;
	public static TDLVersion tdlVer;
	private static File configDir;
	public static RegistryNamespaced<String, Class<? extends TODOTask>> taskRegistry = new RegistryNamespaced<String, Class<? extends TODOTask>>();
	public static List<String> customTaskRegistry = new ArrayList<String>();
	public static HudMessage message;
	public static boolean enableButtonMessage;
	
	@EventHandler
    public void preinit(FMLPreInitializationEvent event){
		configDir = event.getModConfigurationDirectory();
		message = new HudMessage();
		ListLoader.register(configDir);
		Runtime.getRuntime().addShutdownHook(new ShutdownEvent(configDir));
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(message);
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event){
		KeyBind.init();
    }
	
	@EventHandler
    public void postinit(FMLPostInitializationEvent event){
		tdlVer = VersionParser.getLatestVersion();
		list = ListLoader.getListFromStorage(configDir);
    }
	
	@SubscribeEvent
	public void tickEvent(TickEvent.RenderTickEvent e){
		if(e.phase == TickEvent.Phase.END) return;
		List<TODOTask> removal = new ArrayList<TODOTask>();
		for(TODOTask task : list){
			task.tick();
			if(task.getClock() != null && !task.getClock().enabled()) removal.add(task);
		}
		if(removal.size() > 0){
			list.removeAll(removal);
			if(Minecraft.getMinecraft().currentScreen instanceof TODOListGui){
				Minecraft.getMinecraft().displayGuiScreen(new TODOListGui());
			}
		}
	}
	
	@SubscribeEvent
	public void joinWorld(PlayerLoggedInEvent e){
		if(enableButtonMessage){
			message.setMessage("TODOList", "Press "+Keyboard.getKeyName(KeyBind.openTodoList.getKeyCode())+" to open");
			enableButtonMessage = false;
		}
	}
	
}
