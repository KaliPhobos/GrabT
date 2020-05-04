package dinosws.grabt;

public class FileScore {
	String path;
	String name;
	String ending;
	double size;
	double FileSizeScore = 0.0;			// How attractive is the file's size?
	double FileTypeScore = 0.0;			// How interesting is the file's type (determined by file ending)
	double FileChangedScore = 0.0;		// Was the file changed >24h after it's creation?
	double FileRecencyScore = 0.0;		// How recently was the file changed?
	double FilePathScore = 0.0;			// Does the file path contain keywords?
	double FileNameScore = 0.0;			// Does the file name contain keywords?
	double FileFakeEndingScore = 0.0;	// Added if file ending does not match file content (DEEP SCAN ONLY)
	double FileFolderScore = 0.0;		// Average Score of other files in folder
	double FileAmountScore = 0.0;		// Amount of files of same type compared to total amount of files
	
	// FileScore Item which will be created for each file
	public FileScore(String inPath, String inName, String inEnding, double inSize) {
		this.path = inPath;
		this.name = inName;
		this.ending = inEnding;
		this.size = inSize;
	}
	
	public void calculateFileSizeScore() {
		double result = 0.0;
		if(size==0.0) {
			// empty file
			result = 0.0;
		}
		if (General.isBetween(1.0, size, 500000.0)) {
			// 0 - 0.5 MB
			result = (size/500000.0)*40.0+60.0;
		}
		if (General.isBetween(500000.0, size, 15000000.0)) {
			// 0.5 - 15 MB
			result = 99.9;
		}
		if (General.isBetween(15000000.0, size, 100000000.0)) {
			// 15 - 100 MB
			result = 100.0-(((size-15000000.0)/85000000.0)*70.0);
		}
		if (General.isBetween(100000000.0, size, 1000000000)) {
			// 100 MB - 1 GB
			result = 30.0-(((size-100000000.0)/900000000.0)*20.0);
		}
		if (General.isBetween(1000000000.0, size, 5000000000.0)) {
			// 1 - 5 GB
			result = 10.0;
		}
		System.out.println(size+"	 -->	"+result+"% interesting");
		FileSizeScore = result;
	}
	public void calculateFileTypeScore() {
	}
	public void calculateFileChangedScore() {
	}
	public void calculateFileRecencyScore() {
	}
	public void calculateFileNameScore() {
	}
	public void calculateFileFakeEndingScore() {
	}
	public void calculateFileFolderScore() {
	}
	public void calculateTotalScore() {
	}
	
	

}
