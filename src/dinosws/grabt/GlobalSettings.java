package dinosws.grabt;

public class GlobalSettings {	
	public static int FileSizeWeight;
	public static int FileTypeWeight;
	public static int FileChangedWeight;
	public static int FileRecencyWeight;
	public static int FilePathWeight;
	public static int FileNameWeight;
	public static int FileFakeEndingWeight;
	public static int FileFolderWeight;
	public static int FileAmountWeight;

	private static int getTotalWeight() {
		int TotalWeight = FileSizeWeight + FileTypeWeight + FileChangedWeight;
		TotalWeight += FileRecencyWeight + FilePathWeight + FileNameWeight;
		TotalWeight += FileFakeEndingWeight + FileFolderWeight + FileAmountWeight;
		return TotalWeight;
	}
}
