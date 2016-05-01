package tiffit.todolist.versionchecker;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class VersionParser {

	public static TDLVersion getLatestVersion(){
		Gson gson = new Gson();
		try {
			InputStreamReader r = new InputStreamReader(new URL("https://widget.mcf.li/mc-mods/minecraft/244443-todolist.json").openStream());
			JsonParser jsonParser = new JsonParser();
			JsonObject tc = (JsonObject) jsonParser.parse(r);
			JsonObject versiondetails = tc.getAsJsonObject("download");
			String name = versiondetails.getAsJsonPrimitive("name").getAsString();
			String download = versiondetails.getAsJsonPrimitive("url").getAsString();
			TDLVersion tcv = new TDLVersion(name, download);
			return tcv;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}