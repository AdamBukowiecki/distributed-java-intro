package main.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ConfigurationTool {

	private static HashMap<String, String> parameterToValue;
	
	public static void loadConfiguration(String configFileName) {
		parameterToValue = new HashMap<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(configFileName));
			String line = new String();
			while( (line = bufferedReader.readLine()) != null ) {
				if(line.startsWith("//")) continue;
				String[] splitLine = line.split(":");
				parameterToValue.put(splitLine[0], splitLine[1]);
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("-------- Can't find configuration file! ----------");
			e.printStackTrace();
		}
	}
	
	public static String getParamter(String parameter) {
		if(parameterToValue != null)
			return parameterToValue.get(parameter);
		else {
			System.out.println("-------- Configuration has not been loaded! ----------");
			return "0";
		}
	}
	
}
