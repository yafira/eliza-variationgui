package sortgui;

public interface FileActionsIO {

	public void createNewFile(String fileName) ;
	public void writeToNewFile(String fileName, String text) ;
	public void appendToFile(String fileName, String text) ;
	public String readFromFile(String fileName);
	public String readDelimitedFile(String fileName);
	public String readDelimitedFile(String fileName, String delimeter);
	public void findAndReplaceInFile(String fileName, String textOld, String textNew);
}
