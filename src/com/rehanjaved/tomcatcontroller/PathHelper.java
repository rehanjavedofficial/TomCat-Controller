package com.rehanjaved.tomcatcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

/**
 * @author Rehan Javed
 * @version 1.0
 */
public class PathHelper {

	// Attributes..
	private String path = "";
	private static final String PATH_FILE_NAME = "path.ser";
	private static PathHelper pathHelperObj;
	
	/**
	 * Singular object for path helper.
	 * 
	 * @return path helper.
	 */
	public static PathHelper getPathHelper() {
		
		if(pathHelperObj == null) {
			
			pathHelperObj = new PathHelper();
			
		}
		
		return pathHelperObj;
		
	}
	
	/**
	 * Constructor initialize and read
	 * path from file.
	 */
	private PathHelper() {
		
		// Reading path from file..
		try {
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(PATH_FILE_NAME)));
			path = (String) ois.readObject();
			ois.close();
		
		} catch (Exception e) {
		}
		
	}
	
	public String getPath() {
		
		return this.path;
		
	}
	
	/**
	 * Updating path into file.
	 * 
	 * @param newPath of TomCat
	 */
	public void updatePath(String newPath) {
	
		try {

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(PATH_FILE_NAME)));
			oos.writeObject(newPath);
			oos.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Updating Path got error: "+e.toString());
		}
		
		this.path = newPath;
		
	}
	
}
