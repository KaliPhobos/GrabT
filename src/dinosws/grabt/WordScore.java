package dinosws.grabt;

// Represents a score attached to a word found in a filename
public class WordScore {
	// The word to search for
	public String word;
	
	// The position that the word must be in
	public WordPosition position;
	
	// Indicates, whether the word is case sensitive
	public boolean exact;
	
	// Indicates, whether the match condition is inverted
	public boolean invert;
	
	// The number that the file score is multiplied with before dividing 
	public int multiplier;
	
	// The number that the file score is divided by after multiplying
	public int divider;
	
	// The number of points added to the score after multiplying and dividing.
	public int points;
	
	// Full constructor
	public WordScore(String word, WordPosition position, boolean exact, boolean invert,
			int multiplier, int divider, int points) {
		this.word = word;
		this.position = position;
		this.exact = exact;
		this.invert = invert;
		this.multiplier = multiplier;
		this.divider = divider;
		this.points = points;
	}
	
	// Constructor with invert = false
	public WordScore(String word, WordPosition position, boolean exact,
			int multiplier, int divider, int points) {
		this(word, position, exact, false, multiplier, divider, points);
	}
	
	// Constructor with invert = false and exact = false
	public WordScore(String word, WordPosition position, int multiplier, int divider, int points) {
		this(word, position, false, false, multiplier, divider, points);
	}
	
	// Constructor with invert = false, exact = false and points = 0
	public WordScore(String word, WordPosition position, int multiplier, int divider) {
		this(word, position, false, false, multiplier, divider, 0);
	}
	
	// Constructor with invert = false, exact = false, multiplier = 1 and divider = 1
	public WordScore(String word, WordPosition position, int points) {
		this(word, position, false, false, 1, 1, points);
	}
}
