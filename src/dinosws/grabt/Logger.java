package dinosws.grabt;

// Contains functions for simplifying logging to the console 
public class Logger {
	// The public debug condition fields
	// Show debug messages concerning critical input errors, causing an abort
	public static boolean DebugShowCritical = true;
	// Show live results of initial directory scan (basically a flat file tree)
	public static boolean DebugShowDirScanFindings = true;
	// Shows short statistics about files and folders found during initial directory scan
	public static boolean DebugShowDirScanResults = true;
	// Show all found files + their paths after initial directory scan is done
	public static boolean DebugShowDirScanAllFiles = false;
	// Shows creation and manipulation of Directory objects (#DirectoryInfo)
	public static boolean DebugShowFolderObjectLog = true;
	
	// The last timestamp
	private static long lastTimestamp;
	
	// Static initialization
	static {
		// Initialize the timestamp
		lastTimestamp = System.currentTimeMillis();
	}
	
	// Conditionally shows a formatted debug message and call stack
	public static void debug(boolean condition, String format, Object... args) {
		// Only print the debug message if the condition matches
		if (!condition)
			return;
		
		// Get the previous caller and delta
		String callInfo = getPreviousCaller("%s in %s");
		long delta = getDelta();
		
		// Print the formatted message
		System.out.printf("[%d] Debug: %s (in %s)", delta, String.format(format, args), callInfo);
	}
	
	// Perform a non-fatal assertion check and print a message and call stack on failure
	public static void assertion(boolean condition, String format, Object... args) {
		// Only do the assertion if the condition does not match
		if (condition)
			return;
		
		// Get the previous caller and delta
		String callInfo = getPreviousCaller("%s in %s");
		long delta = getDelta();
		
		// Print the formatted message
		System.out.printf("[%d] Assert: %s (in %s)", delta, String.format(format, args), callInfo);
	}
	
	// Calculate the millisecond delta and update the timestamp
	private static long getDelta() {
		long currentTimestamp = System.currentTimeMillis();
		long delta = currentTimestamp - lastTimestamp;
		lastTimestamp = currentTimestamp;
		return delta;
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
