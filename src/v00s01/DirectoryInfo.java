package v00s01;

// Class providing info on a directory
public class DirectoryInfo {
	
	public DirectoryInfo(String inPath, String inName, int inDimension) {
		this.basePath = inPath;
		this.folderName = inName;
		this.children = new FileInfo[inDimension];
		this.score = 0.0;
		this.topScore = 0.0;
	}
	
	public static DirectoryInfo createDirectory(String inPath, String inName, int inDimension) {
		// Constructor for DirectoryInfo
		return new DirectoryInfo(inPath, inName, inDimension);
	}
	
	// The path to the folder without the path or folder name
	public String basePath;
	
	// The name of the folder
	public String folderName;
	
	// The children file objects
	private FileInfo[] children;
	
	// The average score in this folder
	public double score;
	
	// The highest file score in this folder 
	public double topScore;
		
	// Recalculates the scores
	public void CalculateScores()
	{
		// Allocate the working variables
		long scoreCounter = 0;
		
		// Reset the top score
		topScore = 0;
		
		// Do the statistics
		for (int i = 0; i < children.length; i++)
		{
			// Fetch the child score
			double childScore = children[i].getScore();
			
			// Update the average counter
			scoreCounter += childScore;
			
			// Update the top score
			if (topScore < childScore)
				topScore = childScore;
		}
		
		// Calculate the average
		score = (int)(scoreCounter / children.length);
	}
	
	public FileInfo[] getChildren() {
		return children;
	}
	
	public FileInfo getChild(int inID) {
		return children[inID];
	}
	
	public String getName() {
		return folderName;
	}
	
	public String getFullName() {
		return basePath+"\\"+folderName;
	}
	
	// Adding a File at a certain position in the array
	public void addFile(FileInfo inFile, int inPos) {
		children[inPos] = inFile;
	}
}
