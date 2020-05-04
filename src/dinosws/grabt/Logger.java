package dinosws.grabt;

// Contains functions for simplifying logging to the console 
public class Logger {
	// Output options
	// Show info messages
	public static boolean ShowInfo = true;
	// Show debug messages
	public static boolean ShowDebug = true;
	// Show warning messages
	public static boolean ShowWarning = true;
	// Show error messages
	public static boolean ShowError = true;
	// Show assertion failed messages
	public static boolean ShowAssert = true;
	
	// Formatting options
	// Show exact trace (CallInfo) for every debug line
	public static boolean ShowCallInfo = true;
	// Show a full timestamp for every debug line
	public static boolean ShowFullTimestamp = true;
	
	// Other options
	// Show debug messages concerning critical input errors, causing an abort
	public static boolean DebugShowCritical = true;
	// Show live results of initial directory scan (basically a flat file tree)
	public static boolean DebugShowDirScanFindings = true;
	// Shows short statistics about files and folders found during initial directory scan
	public static boolean DebugShowDirScanResults = true;
	// Show all found files + their paths after initial directory scan is done
	public static boolean DebugShowDirScanAllFiles = false;
	// Shows creation and manipulation of Directory objects (#DirectoryInfo)
	public static boolean DebugShowFolderObjectLog = false;
	
	// The last timestamp
	private static long firstTimestamp, lastTimestamp;
	
	// Static initialization
	static {
		// Initialize the timestamp
		firstTimestamp = System.currentTimeMillis();
		lastTimestamp = firstTimestamp;
	}
	
	// Shows a formatted info message
	public static void info(String format, Object... args) {
		// Check, if the message should be displayed
		if (!ShowInfo)
			return;
		
		// Call the print function accordingly
		doPrint("Debug", String.format(format, args), false);
	}
	
	// Shows a formatted error message and the call stack
	public static void error(String format, Object... args) {
		// Check, if the message should be displayed
		if (!ShowError)
			return;
				
		// Call the print function accordingly
		doPrint("Error", String.format(format, args), true);
	}
	
	// Conditionally shows a formatted warning message
	public static void warning(boolean condition, String format, Object... args) {
		// Only do the assertion if the condition does not match and the message is enabled
		if (condition || !ShowWarning)
			return;
				
		// Call the print function accordingly
		doPrint("Warning", String.format(format, args), false);
	}
	
	// Conditionally shows a formatted debug message and the call stack
	public static void debug(boolean condition, String format, Object... args) {
		// Only print the debug message if the condition matches and the message is enabled
		if (!condition || !ShowDebug)
			return;
		
		// Call the print function accordingly
		doPrint("Debug", String.format(format, args), true);
	}
	
	// Perform a non-fatal assertion check and print a message and the call stack on failure
	public static void assertion(boolean condition, String format, Object... args) {
		// Only do the assertion if the condition does not match and the message is enabled
		if (condition || !ShowAssert)
			return;
		
		// Call the print function accordingly
		doPrint("Assert", String.format(format, args), true);
	}
	
	// Handles all the printing and formatting for the above functions
	private static void doPrint(String function, String text, boolean showCallInfo) {
		// Sanitize the input (especially during COVID-19)
		if (function == null)
			function = "";
		if (text == null)
			text = "";
		
		// Handle the call info if desired
		String callInfo = "";
		if (showCallInfo && ShowCallInfo)
			callInfo = getPreviousCaller(" (in %s in %s)");
		
		// Handle the time
		long currentTimestamp = System.currentTimeMillis(),
				lastDelta = getDelta(currentTimestamp),
				totalDelta = getTotalDelta(currentTimestamp);
		
		// Format the time string
		String timingInfo = ShowFullTimestamp ?
				String.format("[%x,%d,+%d]", currentTimestamp, totalDelta, lastDelta) :
				String.format("[%d]", totalDelta);
				
		// Print the formatted message
		System.out.printf("%s %s: %s%s", timingInfo, function, text, callInfo);
		System.out.println();
	}
	
	// Calculate the millisecond delta and update the timestamp
	private static long getDelta(long currentTimestamp) {
		long delta = currentTimestamp - lastTimestamp;
		lastTimestamp = currentTimestamp;
		return delta;
	}
	
	// Calculates the millisecond delta since the first logging entry
	private static long getTotalDelta(long currentTimestamp) {
		return currentTimestamp - firstTimestamp;
	}
	
	// Determines and formats the second most recent calling function
	private static String getPreviousCaller(String format) {
		// Allocate the result variables
		String callingFunction = "null", callingFile = "null";
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (stackTrace.length > 1) {
			// Fetch the second most recent stack entry
			StackTraceElement stackElement = stackTrace[stackTrace.length - 2]; 
			callingFunction = String.format("%s:%s()",
					stackElement.getClassName(), stackElement.getMethodName());
			callingFile = String.format("%s:%d",
					stackElement.getFileName(), stackElement.getLineNumber());
		}
		
		// Assemble and return the formatted result
		// FIXME: note that this is not entirely safe due to external format string input
		return String.format(format, callingFunction, callingFile);
	}
}
