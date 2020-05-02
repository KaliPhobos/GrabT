package v00s01;

import java.nio.file.Paths;
import java.util.ArrayList;

// Class providing info on a directory
public class DirectoryInfo {
	// DirectoryInfo constructor
	public DirectoryInfo(String folderName, String basePath, int dimension) {
		// Determine the absolute base path
		basePath = Paths.get(basePath).toAbsolutePath().toString();
		
		// Copy the values
		this.folderName = folderName;
		this.basePath = basePath;
		this.fullPath = Paths.get(basePath, folderName).toString();
		this.children = new ArrayList<FileInfo>(dimension);
		this.score = 0;
		this.topScore = 0;
	}
	
	// The name of the folder
	private String folderName;
	
	// The path to the folder without the path or folder name
	private String basePath;
	
	// The full path to the folder
	private String fullPath;
	
	// The children file objects
	private ArrayList<FileInfo> children;
	
	// The average score in this folder
	private int score;
	
	// The highest file score in this folder 
	private int topScore;
	
	// Sorts the child list, highest score first, based on the score of the files
	public void sortList() {
		sortList(children.size());
	}
	
	// Sorts the child list, highest score first, based on the score of the files and limits the number of items
	public void sortList(int maxEntries) {
		// Validate and sanitize the input first
		if (maxEntries <= 0)
			throw new IllegalArgumentException("maxEntries is out of bounds");
		if (maxEntries > children.size())
			maxEntries = children.size();
		
		// Calculate the scores next
		calculateScores();
		
		// Allocate a new linked list and two top counters
		ArrayList<FileInfo> targetList = new ArrayList<FileInfo>(maxEntries);
		int currentTopScore = topScore, nextTopScore = topScore - 1;
		
		// Iterate through the list and keep searching for the highest
		for (int i = 0; targetList.size() < maxEntries; i++) {
			for (int j = 0; i < children.size(); i++) {
				// Fetch the item and its score
				FileInfo item = children.get(j);
				int itemScore = item.getScore();
				
				// Eventually skip all scores unequal to the current top score
				if (itemScore != currentTopScore) {
					// Check, if this might be the next top score
					if (itemScore < currentTopScore && itemScore > nextTopScore)
						nextTopScore = itemScore;
					// And continue
					continue;
				}
				
				// Append the current item to the top of the list
				targetList.add(item);
			}
			
			// After each round, update the top score counters
			currentTopScore = nextTopScore;
			nextTopScore--;
		}
		
		// Update the internal list
		children = targetList;
	}
		
	// Recalculates the scores
	public void calculateScores()
	{
		// Allocate the working variables
		long scoreCounter = 0;
		
		// Reset the top score
		topScore = 0;
		
		// Do the statistics
		for (int i = 0; i < children.size(); i++)
		{
			// Fetch the child item and update its score
			FileInfo child = children.get(i);
			child.calculateScore();
			
			// Get the new score of the child
			int childScore = child.getScore();
			
			// Update the average counter
			scoreCounter += childScore;
			
			// Update the top score
			if (topScore < childScore)
				topScore = childScore;
		}
		
		// Calculate the average
		score = (int)(scoreCounter / children.size());
	}
	
	// Appends a child FileInfo object to the end of the internal list 
	public void addChild(FileInfo info)
	{
		// Add the item
		children.add(info);
	}
	
	// Fetches a FileInfo child object from the internal list 
	public FileInfo getChild(int index) {
		// Validate the input
		if (!childExists(index))
			throw new IllegalArgumentException("index is out of bounds");
		
		// Return the item
		return children.get(index);
	}
	
	// Returns the number of child elements in the internal list
	public int getChildCount() {
		return children.size();
	}
	
	// Fetches a FileInfo child object, if it exists, from the internal list
	public void removeChild(int index) {
		// Validate the input
		if (!childExists(index))
			return;
		
		// Remove the item
		children.remove(index);
	}
	
	// Clears the internal list
	public void removeChilds()
	{
		children.clear();
	}
	
	// Returns, whether the internal list contains a specific index
	public boolean childExists(int index) {
		return index > 0 && index < children.size();
	}
	
	// Returns the name of the directory
	public String getName() {
		return folderName;
	}
	
	// Returns the full path to the parent directory
	public String getFullParentPath() {
		return basePath;
	}
	
	// Returns the full path to the directory
	public String getFullPath() {
		return fullPath;
	}
	
	// Returns the average score inside the directory
	public int getScore() {
		return score;
	}
	
	// Returns the top score inside the directory
	public int getTopScore() {
		return topScore;
	}
}
