package v00s01;

public class main {

	public static void main(String[] args) {
		// Default directory
		String TargetDir;
		System.out.println("Please pick directory (for example 'E:\\Files\\')");
		TargetDir = General.getInput("Directory: ");
		
		String[][] names = General.getFileNames(TargetDir);
	}
	
	

}