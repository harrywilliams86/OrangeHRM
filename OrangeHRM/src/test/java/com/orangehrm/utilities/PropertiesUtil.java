package com.orangehrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil 
{
	public static String getValueForKey(String key) throws Exception, IOException
	{
		Properties configprop = new Properties();
		configprop.load(new FileInputStream(new File("./PropertiesFile/Environment.properties")));
		return configprop.getProperty(key);
	}
}
