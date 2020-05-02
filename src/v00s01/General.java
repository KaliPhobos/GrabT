package v00s01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class General {
	
	// Asks for a string input and returns it
	public static String getInput(String inMessage) {
		String result = "ERROR";									// Default result, if this gets returned something went wrong
		Scanner in = new Scanner(System.in);						// Create instance of Scanner
		System.out.print(inMessage);								// Message output (given String)
		if (in.hasNext()) {											// Ask for input from Scanner
			result = in.next();										// Get String
			in.close();												// Close Scanner instance
		}
		return result;
	}
	
	// Loops through separate files linked in a given folder array, analyzing them
	public static void Analyze(List<DirectoryInfo> inFolders) {
		// retrieve file list
		DirectoryInfo Folder;
		FileInfo[] Files;
		FileInfo File;
		// Going through all folder elements
		for (int c_Folder = 0; c_Folder<inFolders.size(); c_Folder++) {
			Folder = inFolders.get(c_Folder);
			Files = Folder.getChildren();
			// Going through all file elements
			for (int c_File = 0; c_File<Files.length; c_File++) {
				// Get the current file
				File = Files[c_File];
				System.out.println("File: " + File.getName() + " in folder: " + Folder.getName());
				// DO FANCY STUFF HERE
			}

		}
	}
	
	
	// Returns a list of any sub-folders inside a directory, each having child elements for contained files.
	// Scans any file & folder, adding found sub-folders to a ToDo-List.
	// Recursively repeats the process with any ToDo-List item until no more sub-folders remain untouched.
	public static List<DirectoryInfo> getFolderArray(String inPath) {
		// counter for the currently processed item in the ToDo-List
		int c_folders = 0;
		// Protocol of any file that was found
		List<File> allFiles = new ArrayList<File>();
		// ToDo-List
		List<String> folders = new ArrayList<String>();
		
		// List holding all results
		List<DirectoryInfo> result = new ArrayList<DirectoryInfo>();
		
		// Select directory as first item on ToDo-List
		folders.add(FixBackslashes(inPath));
		// currently processed path
		String path = inPath;
		System.out.println("----------");
		// Loops through ToDo-List items
		while (c_folders < folders.size()) {
			path = folders.get(c_folders);
			System.out.println("+-- " + path + " (item "+(c_folders+1) + "/" + folders.size() + ")");
			File folder = new File(path);
			// Scan current folder
			File[] listOfFiles = folder.listFiles();
			// Start working through contents of the folder (if not empty)
			if(listOfFiles!=null) {
				
				// CREATE FOLDER ELEMENT (only if folder is not empty)
				DirectoryInfo Folder = DirectoryInfo.createDirectory(path, "abc", listOfFiles.length);					// TODO: SPLIT FOLDER NAME FROM PATH			
				// Scan through content
				for (int c_subFile = 0; c_subFile < listOfFiles.length; c_subFile++) {
					// Found Sub-Folder
					if (listOfFiles[c_subFile].isDirectory()) {
						// Add optional '\\' to path
						path = FixBackslashes(path);
						System.out.println("|+"+listOfFiles[c_subFile].getPath() + "(folder #" + (folders.size()+1) + ")");
						// Add folder to ToDo-List for later
						folders.add(listOfFiles[c_subFile].getPath());
					} else {
						// Found File - add it to list
				    	allFiles.add(listOfFiles[c_subFile]);
				    	
				    	// APPEND FILEINFO CHILD
				    	FileInfo File = FileInfo.createFile(allFiles.get(c_subFile).getName(), allFiles.get(c_subFile).length());
						System.out.println("|-"+listOfFiles[c_subFile].getPath());
						// Add file to mother folder at position c_subFile
						Folder.addFile(File, c_subFile);
				   	}
				}
				//Append newly created & filled DirectoryInfo element to results list
				result.add(Folder);

			}
			// jump to next item on the list
			c_folders++;
		}
		if(c_folders!=allFiles.size()) { System.out.println("ERROR, File count does not match");}
		System.out.println("\nScanned a total of "+c_folders+" folders and files");
		if (allFiles.size()==0) {
			System.out.println("ERROR - No files were found, please check input parameters again");
		}
		return result;
	}
	
	// Adds '\\' at the end of a String in case it'S not already there (fixing file paths)
	public static String FixBackslashes(String inPath) {
		inPath = (inPath.endsWith("\\")) ? inPath : inPath+"\\";
		return inPath;
	}
	
	// Formats file sizes in a nice way you can actually work with
	public static String FormatSize(double size) {
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
