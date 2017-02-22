package com.ads.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ConfigServer {

	private static Properties properties = null;
	private static HashMap<String, String> valuesMap = null;

	/*************** Class Load Initialisation *******************************/
    /**
    * Output the path of the properties file we are using.
    */
    static {
        try {
            java.net.URL configURL = ClassLoader.getSystemResource("Automation.properties");

            if (configURL==null){
                configURL = new ConfigServer().getClass().getClassLoader().getResource("Automation.properties");
            }

            String filename = "";
            if (configURL==null)
                filename = System.getProperty("java.home") + System.getProperty("file.separator") + "lib" + System.getProperty("file.separator")  + "Automation.properties";
            else
                filename = configURL.getFile();

            if (System.getProperty("os.name").toUpperCase().startsWith("WINDOWS")) {
                // Tidy up Windows filename
                if (filename.startsWith("/")) filename = filename.substring(1);
                filename = filename.replace('/','\\');
            }

            //System.out.println("ConfigServer: " + filename);
        }
        catch(Throwable th){
            //Do nothing
        }

    }

    public static String getApplicationHome(String defaultValue) {

    	String applicationHome = getProperty("Common.ApplicationHome",defaultValue);
        return applicationHome;
    }

    public static int getReloadCount(int defaultValue) {
    	int reloadCount = Integer.parseInt(getProperty("Common.ReloadCount", String.valueOf(defaultValue)));
    	return reloadCount;
    }

    public static int getObjectTimeOut(int defaultValue) {
    	int objectTimeOut = Integer.parseInt(getProperty("Common.ObjectTimeOut", String.valueOf(defaultValue)));
    	return objectTimeOut;
    }

 

    public static String getXMLSourceDir(String defaultValue) {
    	String dir = getProperty("Common.XMLSourceDir", defaultValue);
    	return dir;
    }

    /************************** Get Property **********************************/
    /**
    */
    public static String getProperty(String name,String defaultString)
    {
        String value = getProperty(name);

        // If the read value from file is null, use the default
        if (value == null)
        {
            value = defaultString;
        }

        return value;

    }

    public synchronized static String getProperty(String name) {

    	String value = null;

        if (properties == null){

            InputStream in = null;

            try{
                //try to load the file using the class loader
                //this is to support cases where the properties file
                //cannot be placed within JRE lib directory, for example
                //on handhelds where the JRE structure is read only
                in = ClassLoader.getSystemResourceAsStream("Automation.properties");

                if (in==null){
                    in = new ConfigServer().getClass().getClassLoader().getResourceAsStream("Automation.properties");
                }

                //if we failed with the classloader then try the default
                //which is java home\lib
                if (in==null)
                    in = new FileInputStream(System.getProperty("java.home") + System.getProperty("file.separator") + "lib" + System.getProperty("file.separator")  + "Automation.properties");
                properties = new Properties();
                properties.load(in);
                in.close();

            }catch(FileNotFoundException e){
                System.out.println(e);
            }catch(IOException e){
                System.out.println(e);
            }
        }

        if (properties!=null)
            value = properties.getProperty(name);

        return value;

    }

    public static void updateValue (String key, String value) {
    	if(valuesMap == null)
    		valuesMap = new HashMap<String, String>();
    	
    	valuesMap.put(key, value);
    }
    
    public static void updateValues (String key, String value) {
    	if(valuesMap == null)
    		valuesMap = new HashMap<String, String>();
    	else {
    		if(valuesMap.containsKey(key))
    			valuesMap.remove(key);
    	}
    	
    	valuesMap.put(key, value);
    }
    
    public static void removeValue(String key) {
    	if(valuesMap != null && valuesMap.size() >0)
    		valuesMap.remove(key);
    }
    
    public static HashMap<String, String> getValues() {
    	return valuesMap;
    }
    
    public static String getValue(String key) {
    	return valuesMap == null ? null : valuesMap.get(key);
    }

    
}