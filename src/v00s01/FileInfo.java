package v00s01;

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
	
	public static FileInfo createFile(String inName, long inSize) {
		// Constructor for DirectoryInfo
		return new FileInfo(inName, inSize);
	}
	
	// The filename without the path
	private String fileName;
	
	// The file size
	private long fileSize;
	
	// The file score.
	private int score;
	
	// The general mime group
	private String mimeType;
	
	// The specific mime subtype
	private String mimeSubtype;
	
	// Indicates, whether the file has been copied
	private boolean isCopied;
	
	// Recalculates the score
	public void calculateScore() {
		
	}
	
	public String getName() {
		return fileName;
	}
	
	public int getScore() {
		return score;
	}
}
