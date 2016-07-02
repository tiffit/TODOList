package tiffit.todolist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import tiffit.todolist.config.Configuration;
import tiffit.todolist.gui.ListSelectionGui;
import tiffit.todolist.gui.TODOListGui;
import tiffit.todolist.hud.HudEvent;
import tiffit.todolist.hud.HudMessage;
import tiffit.todolist.items.TODOTask;
import tiffit.todolist.items.TODOTask.TaskPriority;
import tiffit.todolist.versionchecker.TDLVersion;
import tiffit.todolist.versionchecker.VersionParser;


@Mod(modid = References.MODID, version = References.VERSION, name = References.MODID, clientSideOnly = true)
public class TODOListMod {

	public static List<TODOList> lists;
	public static TODOTask hudtask;
	public static TDLVersion tdlVer;
	public static int current_list = 0;
	private static File configDir;
	public static RegistryNamespaced<String, Class<? extends TODOTask>> taskRegistry = new RegistryNamespaced<String, Class<? extends TODOTask>>();
	public static List<String> customTaskRegistry = new ArrayList<String>();
	public static HudMessage message;
	public static Configuration config;
	
	@EventHandler
    public void preinit(FMLPreInitializationEvent event){
		configDir = new File(event.getModConfigurationDirectory().getParentFile(), "todolist");
		configDir.mkdir();
		message = new HudMessage();
		ListLoader.register(configDir);
		Runtime.getRuntime().addShutdownHook(new ShutdownEvent(configDir));
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new HudEvent());
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event){
		KeyBind.init();
    }
	
	@EventHandler
    public void postinit(FMLPostInitializationEvent event){
		if(config.shouldVersionCheck()) tdlVer = VersionParser.getLatestVersion();
		lists = ListLoader.getListFromStorage(configDir);
		reorganize();
    }
	
	public static void reorganize(){
		for(TODOList list : lists){
			List<TODOTask> lowest = new ArrayList<TODOTask>();
			List<TODOTask> low = new ArrayList<TODOTask>();
			List<TODOTask> medium = new ArrayList<TODOTask>();
			List<TODOTask> high = new ArrayList<TODOTask>();
			List<TODOTask> highest = new ArrayList<TODOTask>();
			for(TODOTask task : list.list){
				if(task.getPriority() == TaskPriority.Lowest) lowest.add(task);
				if(task.getPriority() == TaskPriority.Low) low.add(task);
				if(task.getPriority() == TaskPriority.Medium) medium.add(task);
				if(task.getPriority() == TaskPriority.High) high.add(task);
				if(task.getPriority() == TaskPriority.Highest) highest.add(task);
			}
			list.list.clear();
			list.list.addAll(highest);
			list.list.addAll(high);
			list.list.addAll(medium);
			list.list.addAll(low);
			list.list.addAll(lowest);
		}

	}
	
	@SubscribeEvent
	public void tickEvent(TickEvent.RenderTickEvent e){
		if(e.phase == TickEvent.Phase.END) return;
		for(TODOList list : lists){
			List<TODOTask> removal = new ArrayList<TODOTask>();
			for(TODOTask task : list.list){
				task.tick();
				if(task.getClock() != null && !task.getClock().enabled()) removal.add(task);
			}
			if(removal.size() > 0){
				list.list.removeAll(removal);
				if(Minecraft.getMinecraft().currentScreen instanceof TODOListGui){
					Minecraft.getMinecraft().displayGuiScreen(new TODOListGui());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void event(GuiScreenEvent.DrawScreenEvent.Post e){
		if(e.getGui() instanceof GuiInventory && config.shouldDrawList()){
			Minecraft.getMinecraft().getTextureManager().bindTexture(References.TODOLIST);
			GlStateManager.color(1f, 1f, 1f);
			int width = e.getGui().width;
			int height = e.getGui().height;
			if(e.getMouseX() > width/2+65 && e.getMouseX() < width/2+65 + 16 && e.getMouseY() > height/2 - 75 && e.getMouseY() < height/2 - 75 + 16){
				GlStateManager.color(3f, 3f, 3f);
			}
			Gui.drawModalRectWithCustomSizedTexture(width/2+65, height/2 - 75, 0, 0, 15, 12, 256f, 256f);
		}
	}
	
	@SubscribeEvent
	public void onGuiMouseEvent(GuiScreenEvent.MouseInputEvent.Pre e) {
		if(e.getGui() instanceof GuiInventory && config.shouldDrawList()){
			GuiScreen gui = e.getGui();
			int width = gui.width;
			int height = gui.height;
			int x = Mouse.getEventX()*width/gui.mc.displayWidth;
			int y = height - Mouse.getEventY()*height/gui.mc.displayHeight - 1;
			if(Mouse.isButtonDown(0)){
				if(x > width/2+65 && x < width/2+65 + 16 && y > height/2 - 75 && y < height/2 - 75 + 16){
					Minecraft.getMinecraft().displayGuiScreen(new ListSelectionGui());
				}
			}
		}
	}
	
	public static TODOList getCurrent(){
		return lists.get(current_list);
	}
	
	
}
