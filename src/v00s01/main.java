package v00s01;

public class main {

	public static void main(String[] args) {
		String TargetDir;						// Default directory
		// String result;							// General return String, used in various places
		System.out.println("Please pick directory (for example 'E:\\Files\\')");
		TargetDir = General.getInput("Directory: ");
		
		String[][] names = General.getFileNames(TargetDir);
	}
	
	

}