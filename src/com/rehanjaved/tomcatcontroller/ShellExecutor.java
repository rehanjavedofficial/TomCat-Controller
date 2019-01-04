package com.rehanjaved.tomcatcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Rehan Javed
 * @version 1.0
 */
public class ShellExecutor {

	// Shell files to control TomCat.
	public static final String TOMCAT_START = "/startup.sh";
	public static final String TOMCAT_SHUTDOWN = "/shutdown.sh";
	
	/**
	 * It take path through parameter and 
	 * run that shell file and return back
	 * the output of the program.
	 * 
	 * @param path Shell File Path
	 * @return output of execution
	 */
	public static String executeShellFile(String path) throws IOException {

		// Starting a process..
		ProcessBuilder pb = new ProcessBuilder(path);
		Process p = pb.start();
		
		// Reading output from process.
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String output = null;
		String result = "";
		
		while ((output = reader.readLine()) != null) {
			result += output;
		}
		
		return result;

	}
	
}
