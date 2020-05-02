package dinosws.grabt;

// Represents a score attached to a word found in a filename
public class WordScore {
	// The word to search for
	public String word;
	
	// The position that the word must be in
	public WordPosition position;
	
	// Indicates, whether the word is case sensitive
	public boolean exact;
	
	// The number that the file score is multiplied with before dividing 
	public int multiplier;
	
	// The number that the file score is divided by after multiplying
	public int divider;
	
	// The number of points added to the score after multiplying and dividing.
	public int points;
	
	// Constructor
	public WordScore(String word, WordPosition position, boolean exact, int multiplier, int divider, int points) {
		this.word = word;
		this.position = position;
		this.exact = exact;
		this.multiplier = multiplier;
		this.divider = divider;
		this.points = points;
	}
}
