package com.ads.utility;

import java.io.*;
import java.text.*;
import java.util.*;

public class Logger {
	static String getWorkingdir = System.getProperty("user.dir");

	protected static String defaultLogFile = getWorkingdir + "/src";

	public static void log(String s) {
	}

	public static void log(Object s) {
	}

	public static void log(String f, String s) throws IOException {
		TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy.mm.dd hh:mm:ss ");
		df.setTimeZone(tz);
		String currentTime = df.format(now);

		FileWriter aWriter = new FileWriter(f, true);
		aWriter.write(currentTime + " " + s + "\n");
		aWriter.flush();
		aWriter.close();
	}

	public static void log(String f, Object s) throws IOException {
		TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy.mm.dd hh:mm:ss ");
		df.setTimeZone(tz);
		String currentTime = df.format(now);

		FileWriter aWriter = new FileWriter(f, true);
		aWriter.write(currentTime + " " + s + "\n");
		aWriter.flush();
		aWriter.close();
	}
	
	

	
}
