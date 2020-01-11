package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties properties;

	public ReadConfig() {
		try {
			properties=new Properties();
			File file=new File("./Configuration//config.properties");
			FileInputStream fis=new FileInputStream(file);
			properties.load(fis);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getPropertyValue(String strParam) {
		String strParamVal=properties.getProperty(strParam);
		return strParamVal;
	}
}	
