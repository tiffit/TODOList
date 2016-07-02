package tiffit.todolist.versionchecker;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class TDLVersion {
	String name;
	String download;
	public TDLVersion(String name, String download){
		this.name = name;
		this.download = download;
	}
	
	public String getVersion(){
		return name.replace("TODOList-v", "");
	}
	
	public void launchDllSite(){
		if(Desktop.isDesktopSupported()){
			try {
				Desktop.getDesktop().browse(URI.create(download));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String[] getVersionNumbers(){
		return getVersion().split("\\.");
	}
	
	public int getMajor(){
		return Integer.parseInt(getVersionNumbers()[0]);
	}
	
	public int getMiddle(){
		return Integer.parseInt(getVersionNumbers()[1]);
	}
	
	public int getMinor(){
		return Integer.parseInt(getVersionNumbers()[2]);
	}
	
	public boolean isGreaterVersion(String currentVer){
		String[] current = currentVer.split("\\.");
		
		int major = Integer.parseInt(current[0]);
		int middle = Integer.parseInt(current[1]);
		int minor = Integer.parseInt(current[2]);
		
		if(getMajor() > major) return true;
		else if(getMajor() < major)return false;
		if(getMiddle() > middle) return true;
		else if(getMiddle() < middle) return false;
		if(getMinor() > minor) return true;
		else return false;
	}
}