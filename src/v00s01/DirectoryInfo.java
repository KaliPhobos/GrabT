package v00s01;

// Class providing info on a directory
public class DirectoryInfo {
	// The path to the folder without the path or folder name
	public String basePath;
	
	// The name of the folder
	public String folderName;
	
	// The children file objects
	public FileInfo[] children;
	
	// The average score in this folder
	public int score;
	
	// The highest file score in this folder 
	public int topScore;
	
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
			int childScore = children[i].score;
			
			// Update the average counter
			scoreCounter += childScore;
			
			// Update the top score
			if (topScore < childScore)
				topScore = childScore;
		}
		
		// Calculate the average
		score = (int)(scoreCounter / children.length);
	}
}
