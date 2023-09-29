package comBase;

import java.io.InputStream;
import java.util.Properties;


public class Base_Properties {
	
	public Properties loadproperties() {

		try
		{
			InputStream istream = getClass().getClassLoader().getResourceAsStream("Config.properties");
			Properties prop = new Properties();
			prop.load(istream);
			return prop;
		}
		catch(Exception e) 
		{
			System.out.println("Throw Exception for config as file not found");
			return null;
		}
	}
}
