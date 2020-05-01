package v00s01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class General {
	
	public static String getInput(String inMessage) {
		// Asks for a string input and returns it
		String result = "ERROR";									// Default result, if this gets returned something went wrong
		Scanner in = new Scanner(System.in);						// Create instance of Scanner
		System.out.print(inMessage);								// Message output (given String)
		if (in.hasNext()) {											// Ask for input from Scanner
			result = in.next();										// Get String
			in.close();												// Close Scanner instance
		}
		return result;
	}
	
	public static String[][] getFileNames(String inPath) {
		// Creates an array containing absolute path and name for every file in a given directory
		List<File> allFiles = loadFiles(inPath);					// retrieve file list
		String[][] FullNames = new String[allFiles.size()][2];		// create array 2x?
		for (int i = 0; i < allFiles.size(); i++) {					// Fill array
			FullNames[i][0] = allFiles.get(i).getPath();			// Extract absolute path
			FullNames[i][1] = allFiles.get(i).getName();			// Extract actual name & ending
		}
		return FullNames;
	}
	
	public static List<File> loadFiles(String path) {
		// Returns a list of any files and folders inside a directory.
		// Scans any file & folder, adding found sub-folders to a ToDo-List.
		// Recursively repeats the process with any ToDo-List item until no more sub-folders remain untouched.
		
		// counter for the currently processed item in the ToDo-List
		int c_folders = 0;
		// Protocol of any file that was found
		List<File> allFiles = new ArrayList<File>();
		// ToDo-List
		List<String> folders = new ArrayList<String>();
		// Select directory as first item on ToDo-List
		folders.add(FixBackslashes(path));
		System.out.println("----------");
		// Loops through ToDo-List items
		while (c_folders < folders.size()) {
			path = folders.get(c_folders);
			System.out.println("+-- " + path + " (item "+(c_folders+1) + "/" + folders.size() + ")");
			File folder = new File(path);
			// Scan top level folder
			File[] listOfFiles = folder.listFiles();
			// Start working through contents of top level folder
			if(listOfFiles!=null) {
				// Scan through content
				for (int i = 0; i < listOfFiles.length; i++) {
					// Found Folder
					if (listOfFiles[i].isDirectory()) {
						// Add optional '\\' to path
						path = FixBackslashes(path);
						System.out.println("|+"+listOfFiles[i].getPath() + "(folder #" + (folders.size()+1) + ")");
						// Add folder to ToDo-List for later
						folders.add(listOfFiles[i].getPath());
					} else {
						// Found File - add it to list
				    	allFiles.add(listOfFiles[i]);
						System.out.println("|-"+listOfFiles[i].getPath());
				   	}
				}
			}
			// jump to next item on the list
			c_folders++;
		}
		if(c_folders!=allFiles.size()) { System.out.println("ERROR, File count does not match");}
		System.out.println("\nScanned a total of "+c_folders+" folders and files");
		if (allFiles.size()==0) {
			System.out.println("ERROR - No files were found, please check input parameters again");
		}
		return allFiles;
	}
	
	public static String FixBackslashes(String inPath) {
		// Adds '\\' at the end of a String in case it'S not already there (fixing file paths)
		inPath = (inPath.endsWith("\\")) ? inPath : inPath+"\\";
		return inPath;
	}
	
	public static String FormatSize(double size) {
		// Formats file sizes in a nice way you can actually work with
		String result;
		if (size<1000) {
			result = (int) size+" Byte";
		} else if(size<1000000) {
			result = (((double) Math.round(size/10))/100)+"KB";
		} else if(size<1000000000) {
			result = (((double) Math.round(size/10000))/100)+"MB";
		} else if(size<1000000000000.0) {
			result = (((double) Math.round(size/10000000))/100)+"GB";
		} else if(size<1000000000000000.0) {
			result = (((double) Math.round(size/10000000000.0))/100)+"TB";
		} else if(size<1000000000000000000.0) {
			result = (((double) Math.round(size/10000000000000.0))/100)+"PB";
		} else {
			// No man should ever go there...
			result = "fuck you";
		}
		return result;
	}
}
