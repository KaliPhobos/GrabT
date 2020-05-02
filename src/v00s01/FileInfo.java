package v00s01;

// Class providing info on a file
public class FileInfo {
	// The filename without the path
	private String fileName;
	
	// The file size
	private long fileSize;
	
	// The file score.
	private double score;
	
	// The general mime group
	private String mimeType;
	
	// The specific mime subtype
	private String mimeSubtype;
	
	// Indicates, whether the file has been copied
	private boolean isCopied;
	
	public String getName() {
		return fileName;
	}
	
	public double getScore() {
		return score;
	}
}
