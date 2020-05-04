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
	
	public static double ApplyParameterWeight(double inScore, String paramName) {
		// Early sketch, will probably rewrite this function along the way to increase efficiency
		int paramValue = 0;
		switch (paramName) {
			case "FileSize" :
				paramValue = FileSizeWeight;
				break;
			case "FileType" :
				paramValue = FileTypeWeight;
				break;
			case "FileChanged" :
				paramValue = FileChangedWeight;
				break;
			case "FileRecency" :
				paramValue = FileRecencyWeight;
				break;
			case "FilePath" :
				paramValue = FilePathWeight;
				break;
			case "FileName" :
				paramValue = FileNameWeight;
				break;
			case "FileFakeEnding" :
				paramValue = FileFakeEndingWeight;
				break;
			case "FileFolder" :
				paramValue = FileFolderWeight;
				break;
			case "FileAmount" :
				paramValue = FileAmountWeight;
				break;
			default :
				Logger.debug(Logger.DebugShowCritical,
						"Wrong 'paramName' value given ('%s'), unable to comply. See '#GlobalSettings>ApplyParameterWeight'",
						paramName);
		}
		return inScore * (paramValue / getTotalWeight());
	}
}
