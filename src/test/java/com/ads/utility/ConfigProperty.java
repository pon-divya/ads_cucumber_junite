package com.ads.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class ConfigProperty {

	private Properties prop = new Properties();
	private String propFileName = "config.properties";

	public ConfigProperty() {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				//throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			//System.out.println(e.toString());
		}
	}

	public String getLogURL(){
		return prop.getProperty("logURL");
	}
	
	public String getUserName(String userNameType) {
		return prop.getProperty(userNameType);
	}

	public String getPassword(String pwType) {
		return prop.getProperty(pwType);
	}

	public String getBaseURL() {
		return prop.getProperty("baseURL");
	}
	
	public String getCSQURL() {
		return prop.getProperty("csqURL");
	}
	
	public String getMFAURL() {
		return prop.getProperty("mfaURL");
	}

	public String getMFAAnswer() {
		return prop.getProperty("MFAanswer");
	}
	
	public String funStartDefaultMsg() {
		return prop.getProperty("dataStartDefaultMsg");
	}
	
	public String funEndDefaultMsg() {
		return prop.getProperty("dataEndDefaultMsg");
	}
	
	public String getEaseManUrl() {
		return prop.getProperty("easeMan");
	}
	
	public String getdevURL() {
		return prop.getProperty("devURL");
	}


	// this function is created for splitting the strings
	public String Spliteme(int beginIndex, int endIndex, String getmesubstring) {

		String subString = getmesubstring.substring(beginIndex, endIndex);
		return subString;

	}

	public static int ReloadCount() {

		return 70000;
	}

	public static void main(String toConvertTest) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a sentence: ");
		String input = scanner.nextLine();
		String result = "";
		char firstChar = input.charAt(0);
		result = result + Character.toUpperCase(firstChar);
		for (int i = 1; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			char previousChar = input.charAt(i - 1);
			if (previousChar == ' ') {
				result = result + Character.toUpperCase(currentChar);
			} else {
				result = result + currentChar;
			}
		}
		System.out.println(result);
	}

	public static String getMethodName() {
	    return Thread.currentThread().getStackTrace()[2].getMethodName();
	} 

}
