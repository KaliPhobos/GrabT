package dinosws.grabt;

import java.nio.file.Paths;

// Contains the black- and whitelist for certain words
public class WordLists {
	// Order of arguments for the lists:
	// Word, Position, Case sensitive, First multiply with, Then divide by, Finally add
	
	// The words whitelist adding points
	private static final WordScore whiteList[] = {
		new WordScore("password", WordPosition.Contained, false, 2, 1, 0),
		new WordScore("Thumbs.db", WordPosition.FullFile, true, 1, 1, 100)
	};
	
	// The words blacklist subtracting points
	private static final WordScore blackList[] = {
		new WordScore(".gitignore", WordPosition.FullFile, false, 1, 2, -10),
		new WordScore(".lnk", WordPosition.EndFile, false, 0, 1, 0) // Zero the score for any shortcuts
	};
	
	// Adjusts the given score based on the installed wordlists
	public static int adjustScore(int score, String basePath, String fileName) {
		return adjustScore(score, basePath, fileName, new WordScore[][] { whiteList, blackList });
	}
	
	// Adjusts the given score based on the supplied list
	public static int adjustScore(int score, String basePath, String fileName, WordScore[] list) {
		return adjustScore(score, basePath, fileName, new WordScore[][] { list });
	}
	
	// Adjusts the given score based on the supplied lists
	public static int adjustScore(int score, String basePath, String fileName, WordScore[][] lists) {
		// Sanitize the input
		if (basePath == null)
			basePath = "";
		if (fileName == null)
			fileName = "";
		
		// Validate the lists array
		if (lists == null)
			throw new IllegalArgumentException("lists may not be null!");
		
		// Transform the base path and file name ahead of time to increase speed for big lists
		String basePathLower = basePath.toLowerCase(), fileNameLower = fileName.toLowerCase(),
				fullPath = Paths.get(basePath, fileName).toString(), fullPathLower = fullPath.toLowerCase();
		
		// Iterate through all lists, ignoring null ones
		for (int listsIndex = 0; listsIndex < lists.length; listsIndex++) {
			// Fetch the current list
			WordScore[] currentList = lists[listsIndex];
			
			// Skip any null lists
			if (currentList == null)
				continue;
			
			// Iterate through all words
			for (int listIndex = 0; listIndex < currentList.length; listIndex++) {
				// Fetch the current word
				WordScore currentWord = currentList[listIndex];
				
				// Create a modification flag and choose the right strings
				boolean modify = false;
				String needleString = currentWord.exact ? currentWord.word : currentWord.word.toLowerCase(),
						haystackString = "";
				
				// Switch the source first
				switch (currentWord.position) {
				case Contained:
				case End:
				case Full:
				case Middle:
				case Start:
					// Full path
					haystackString = currentWord.exact ? fullPath : fullPathLower;
					break;
					
				case ContainedDirectory:
				case EndDirectory:
				case FullDirectory:
				case MiddleDirectory:
				case StartDirectory:
					// Base path only
					haystackString = currentWord.exact ? basePath : basePathLower;
					break;
					
				case ContainedFile:
				case EndFile:
				case FullFile:
				case MiddleFile:
				case StartFile:
					// Filename only
					haystackString = currentWord.exact ? fileName : fileNameLower;
					break;
					
				default:
					// Skip invalid entries
					continue;
				}
				
				// Switch the operation type next
				switch(currentWord.position) {
				case Contained:
				case ContainedDirectory:
				case ContainedFile:
					// Haystack must contain needle
					if (!haystackString.contains(needleString))
						continue;
					break;
					
				case End:
				case EndDirectory:
				case EndFile:
					// Haystack must end in needle
					if (!haystackString.endsWith(needleString))
						continue;
					break;
					
				case Full:
				case FullDirectory:
				case FullFile:
					// Haystack must be equal to needle
					if (!haystackString.equals(needleString))
						continue;
					break;
					
				case Middle:
				case MiddleDirectory:
				case MiddleFile:
					// Haystack must contain needle (not touching the first or last character) 
					if (!haystackString.contains(needleString) ||
						haystackString.startsWith(needleString) ||
						haystackString.endsWith(needleString))
						continue;
					break;
					
				case Start:
				case StartDirectory:
				case StartFile:
					// Haystack must start with needle
					if (!haystackString.startsWith(needleString))
						continue;
					break;
					
				default:
					// Skip invalid entries (should never be here)
					continue;
				}
				
				// Fallthrough is a match, so apply the score
				score *= currentWord.multiplier;
				score /= currentWord.divider == 0 ? 1 : currentWord.divider;
				score += currentWord.points;
			}
		}
		
		// Return the adjusted score
		return score;
	}
}
