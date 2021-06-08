package dinosws.grabt.graphics;

public class SimpleGraphics {

	/**
	 * Creates an ASCII-Art inspired text-only slider
	 * @param minValue The lower border value for the slider
	 * @param maxValue The higher border value for the slider
	 * @param currentValue The slider's current value
	 * @param name The name tag to be put above the slider
	 * @param width The absolute width of the text output
	 * The resulting slider should look like this:
	 * <p>
	 * [Fancy Name  =65]
	 * <p>
	 * [>===O=========<]
	 * <p>
	 * [20/        \180]
	 */
	public static void slider(int minValue, int maxValue, int currentValue, final String name, int width) {
		// TODO: Move String generation for each line to separate methods which may be individually called
		// sanity check input
		if (name == null) throw new NullPointerException("NULL String was given for slider name");
		if (minValue >= maxValue) throw new IllegalArgumentException("Border values look fishy");
		if (width < 6 + Integer.toString(maxValue).length()) throw new IllegalArgumentException("Given WIDTH parameter is below minimum value of 6 + space required by upper bound.");
		
		// shorten name if it's too long, default is FALSE. The magic number is the amount of foreign letters (not generated spaces nor the name) in the first line
		boolean shortenName = (name.length() > width - 3 - Integer.toString(maxValue).length());
		
		// hide slider if the value is off bounds, default is FALSE
		boolean hideSlider = (minValue > currentValue || currentValue > maxValue);
		
		// Line 1: Name tag
		// Create a String consisting of the given name (possibly shortened to fit in) followed by a number of spaces and '/XX', with X being the given currentValue
	/*	StringBuffer result = new StringBuffer().append(shortenName
				? stringFill(shorten(name, width - Integer.toString(currentValue).length() - 1), ' ', width - Integer.toString(currentValue).length() - 1, true)
				: stringFill(name, ' ', width - Integer.toString(currentValue).length() - 1, true)
				).append('/').append(currentValue).append("\n");
	*/	
		StringBuffer result = new StringBuffer()
				.append('[')
				.append(shortenName
						? shorten(name, width - Integer.toString(currentValue).length() - 3)
						: name)
				.append(repeatChar(' ', shortenName
						? width - Integer.toString(currentValue).length() - 3 - name.length()
						: width - Integer.toString(currentValue).length() - 3 - name.length()))	
				.append('=').append(currentValue).append("]\n");
		
		// Line 2: Graph
		// Create a String representing an ascii-art slider [>====O==<]
		double percentage = (double) (borderValue(minValue, currentValue, maxValue) - minValue) / (maxValue - minValue);
		result.append("[>")
				.append(repeatChar('=', (int) ((width - 4) * percentage)))
				.append('O')
				.append(repeatChar('=', (int) ((width - 4) * (1 - percentage))))
				.append("<]\n");
		
		// Line 3: Subtitle
		// Create a String containing the minimum and maximum border values
		result.append('[')
				.append(minValue)
				.append('/')
				.append(repeatChar(' ', width - 4 - Integer.toString(minValue).length() - Integer.toString(maxValue).length()))
				.append('\\')
				.append(maxValue)
				.append("]\n");
		System.out.println(result);
		
	}


	/**
	 * Limits a value within set minimum and maximum borders
	 * @param minValue the lower border
	 * @param midValue the value to be limited within the borders
	 * @param maxValue the higher border
	 * @return the limited value
	 */
	private static int borderValue(int minValue, int midValue, int maxValue) {
		return Math.max(minValue,  Math.min(midValue, maxValue));
	}


	/**
	 * Cuts a string down to the given maximum length, appending '..' if it had to be shortened.
	 * @param text The Text to be shortened
	 * @param length The intended length
	 * @return A string with the given length
	 */
	private static String shorten(String text, int length) {
		// sanity check input
		if (text == null) throw new NullPointerException("NULL String was given");
		// if no cropping is necessary, just return the given string unchanged
		if (length > text.length())
			return text;
		// Cut down and return the string
		return text.substring(0, length-2) + "..";
	}
	
	
	/**
	 * Concatenates a given character a given number of times
	 * @param character The character to be concatenated
	 * @param amount The finally intended length for the resulting String
	 * @return A String with the intended length, made up only of the given character
	 */
	private static String repeatChar(char character, int amount) {
		StringBuffer result = new StringBuffer();
		for(; amount>0; amount--)
			result.append(character);
		return result.toString();
	}
	
	
	/**
	 * Stretches a string to a given length, by appending a variable number of fixed characters at it's end.
	 * @param text The given text
	 * @param filler The Character to be appended
	 * @param length The intended absolute length for the String
	 * @param isAbsoluteStringLength Determines if the given number is the intended absolute string length, or the amount of characters to be appended. 
	 * 'True' standing for an intended absolute string length of both the given text and the appended characters together
	 * @return A string with the given length
	 */
	/*
	private static String stringFill(String text, char filler, int length, boolean isAbsoluteStringLength) {
		// Sanity check input
		if (isAbsoluteStringLength && length < text.length())
			// Should the given length parameter be lower than the text's length, interpret the length parameter as number of characters to append
			isAbsoluteStringLength = false;
		// Check if an intended absolute length has been given. If yes, adapt the length parameter to the intended amount of characters to be added
		if (isAbsoluteStringLength)
			length -= text.length();
		// Append the intended number of characters to the buffer, then return the resulting string
		StringBuffer result = new StringBuffer().append(text);
		for (int amount = length; amount>0; amount--)
			result.append(filler);
		return result.toString();
	}
	*/
	
	
	/**
	 * Concatenates a given character a given number of times
	 * @param character The character to be concatenated
	 * @param amount The finally intended length for the resulting String
	 * @return A String with the intended length, made up only of the given character
	 */
	/*private static String repeatChar(char character, double amount) {
		StringBuffer result = new StringBuffer();
		int amountInt = (int) amount;
		for(; amountInt>0; amountInt--)
			result.append(character);
		return result.toString();
	}*/
	
}
