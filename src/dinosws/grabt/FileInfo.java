package dinosws.grabt;

import java.nio.file.Paths;

// Class providing info on a file
public class FileInfo {
	
	public FileInfo(String inName, long inSize) {
		this.fileName = inName;
		this.fileSize = inSize;
		this.score = 0;
		this.mimeType = "";
		this.mimeSubtype = "";
		this.isCopied = false;
	}
	
	// The filename without the path
	private String fileName;
	
	// The file size
	private long fileSize;
	
	// The file score
	private int score;
	
	// The general mime group
	private String mimeType;
	
	// The specific mime subtype
	private String mimeSubtype;
	
	// Indicates, whether the file has been copied
	private boolean isCopied;
	
	public String getName() {
		return fileName;
	}
	
	// Returns the full path to the file based on the parent directory path
	public String getFullPath(DirectoryInfo parentDir) {
		// Get the path of the parent directory
		String parentPath = parentDir == null ?
				Paths.get(".").toAbsolutePath().toString() :
				parentDir.getFullPath();
				
		// Assemble and return the path
		return Paths.get(parentPath, fileName).toString();
	}
	
	public int getScore() {
		return score;
	}
	
	// Recalculates the score
	public void calculateScore() {
		
	}
}
