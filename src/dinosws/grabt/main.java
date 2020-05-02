package dinosws.grabt;

import java.util.List;

public class main {

	public static void main(String[] args) {
		// Default directory
		String TargetDir;
		System.out.println("Please pick directory (for example 'E:\\Files\\')");
		TargetDir = General.getInput("Directory: ");
		
		List<DirectoryInfo> Folders = General.getFolderArray(TargetDir);
		General.Analyze(Folders);
	}
	

}