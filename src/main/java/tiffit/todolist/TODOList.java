package tiffit.todolist;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.util.registry.RegistryNamespacedDefaultedByKey;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tiffit.todolist.items.TODOItem;
import tiffit.todolist.versionchecker.TDLVersion;
import tiffit.todolist.versionchecker.VersionParser;


@Mod(modid = References.MODID, version = References.VERSION, clientSideOnly = true)
public class TODOList {

	public static List<TODOItem> list;
	public static TDLVersion tdlVer;
	private static File configDir;
	public static RegistryNamespaced<String, Class<? extends TODOItem>> taskRegistry = new RegistryNamespaced<String, Class<? extends TODOItem>>();
	
	@EventHandler
    public void preinit(FMLPreInitializationEvent event){
		ListLoader.register();
		configDir = event.getModConfigurationDirectory();
		Runtime.getRuntime().addShutdownHook(new ShutdownEvent(configDir));
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
	
}
