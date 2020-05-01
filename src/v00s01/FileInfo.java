package v00s01;

// Class providing info on a file
public class FileInfo {
	// The filename without the path
	public String fileName;
	
	// The file size
	public long fileSize;
	
	// The file score.
	public int score;
	
	// The general mime group
	public String mimeType;
	
	// The specific mime subtype
	public String mimeSubtype;
	
	// Indicates, whether the file has been copied
	public boolean isCopied;
}
