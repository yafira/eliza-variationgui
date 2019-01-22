package sortgui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class TextFileHandler implements FileActionsIO{

	public void readFromJFileChooser(){
		JFileChooser jfc = new JFileChooser();
		int yesNo = jfc.showDialog(null, null);
		if(yesNo == JFileChooser.APPROVE_OPTION){
			File theFile = jfc.getSelectedFile();
			System.out.println("The file you chose is "+theFile.getAbsolutePath());
			System.out.println("You have write access to it = "+theFile.canWrite());
			//readFromFileWriteToConsole(theFile.getAbsolutePath());
		}
	}
	
	public void writeToFileFromKeyboard(String fileName){
		System.out.println("Will write to "+fileName);
		Scanner kb = new Scanner(System.in);//connect to keyboard
		PrintWriter outStr = null;
		
		String lineIn = "-------beginning of logging in "+fileName+"--------";
		String secretPass = "stop";
		try{
			outStr = new PrintWriter(new FileOutputStream(fileName, true));//use true to append to the end of an existing file
			do{
				outStr.println(lineIn);//write to outStr
				lineIn = kb.nextLine();//read from kb
			}while( !lineIn.equalsIgnoreCase(secretPass));
		}catch(FileNotFoundException e){
			System.out.println("COULD NOT RECORD USER INPUT IN FILE "+e.getMessage());
		}
		finally{
			if(kb !=null){
				kb.close();
			}
			if(outStr !=null){
				outStr.close();
			}
			System.out.println("Go check the file "+fileName);
		}
		
	}
	
	
	
	
	public void recursivelyPrintAllFilesAndDirs(String fileName){
		File theFile = new File(fileName);
		if(theFile.exists()){
			//if it is a Boring File just print the name... base case
			if(theFile.isFile()){
				System.out.println("File:\t"+fileName);
			}
			else{ //it is a directory 
				System.out.println("DIR:\t"+fileName);
				File [] theFiles = theFile.listFiles();//find the names of all the Files it contains
				for(int i=0; i<theFiles.length; i++){//for each File in the array call ourselves passing in that fileName
					recursivelyPrintAllFilesAndDirs(theFiles[i].getAbsolutePath());
				}
				
			}
		}
		else{
			System.out.println("ALERT! No such file or dir "+fileName);
		}
	}
	
	
	
	@Override
	public void createNewFile(String fileName) {
		//System.out.println("inside createNewFile fileName= "+fileName);
		PrintWriter outStr = null;
		try {
			outStr = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			if(outStr !=null){
				outStr.close();
			}
			//System.out.println("end of createNewFile fileName= "+fileName);
		}
	}

	@Override
	public void writeToNewFile(String fileName, String text) {
		//System.out.println("inside writeToNewFile fileName= "+fileName);
		PrintWriter outStr = null;
		try {
			outStr = new PrintWriter(fileName);//create a new empty file
			outStr.println(text);//write the text to the file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			if(outStr !=null){
				outStr.close();
			}
			//System.out.println("end of writeToNewFile fileName= "+fileName);
		}
	}

	@Override
	public void appendToFile(String fileName, String text) {
		//System.out.println("inside appendToFile fileName= "+fileName);
		PrintWriter outStr = null;
		try {
			//outStr = new PrintWriter(fileName);//create a new empty file
			outStr = new PrintWriter(new FileOutputStream(fileName, true));//create a new file if necessary
			outStr.println(text);//write the text to the file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			if(outStr !=null){
				outStr.close();
			}
			//System.out.println("end of appendToFile fileName= "+fileName);
		}
	}

	
	public void readFromFileWriteToConsole(String fileName) {
		System.out.println("Start of readFromFileWriteToConsole method file name is "+fileName);
		Scanner inStr = null;
		//String fileContent = "";
		try{
		File theFile = new File(fileName);
			if(theFile.exists() && theFile.canRead()){
				inStr = new Scanner(theFile);//connect my stream to the file
				int lineNum = 0;
				while(inStr.hasNextLine()){//as long as the file has another line
					lineNum++;
					//fileContent +=  lineNum+" "+ inStr.nextLine() +"\n";//read line by line
					System.out.println(lineNum+" "+ inStr.nextLine());
				}
			}
		}
		catch(FileNotFoundException fnfe){
			System.err.println("PROBLEM! inside readFromFile method "+fileName+
					"Message is: "+fnfe.getMessage());
		}
		finally{
			if(inStr != null){
				inStr.close();
			}
			System.out.println("END of readFromFileWriteToConsole method file name was "+fileName);
		}
		//return fileContent;
	}
	public void readFrom_CSV_FileWriteToConsole(String fileName) {
		System.out.println("Start of readFromFileWriteToConsole method file name is "+fileName);
		Scanner inStr = null;
		//String fileContent = "";
		try{
		File theFile = new File(fileName);
			if(theFile.exists() && theFile.canRead()){
				inStr = new Scanner(theFile);//connect my stream to the file
				inStr.useDelimiter(",");
				//int lineNum = 0;
				while(inStr.hasNext()){//as long as the file has another line
					String token = inStr.next();
					System.out.print(token +"\t");
					//lineNum++;
					//fileContent +=  lineNum+" "+ inStr.nextLine() +"\n";//read line by line
					//System.out.println(lineNum+" "+ inStr.nextLine());
				}
			}
		}
		catch(FileNotFoundException fnfe){
			System.err.println("PROBLEM! inside readFromFile method "+fileName+
					"Message is: "+fnfe.getMessage());
		}
		finally{
			if(inStr != null){
				inStr.close();
			}
			System.out.println("END of readFromFileWriteToConsole method file name was "+fileName);
		}
		//return fileContent;
	}

	public boolean deleteFile(String fileName){
		File theFile = new File(fileName);
		if(theFile.exists()){
			if(theFile.delete()){
				System.out.println("The file was successfully deleted "+fileName);
				return true;
			}
				System.out.println("The file was NOT deleted "+fileName);
		}
		return false;//problema
	}
	
	@Override
	public String readFromFile(String fileName) {
		System.out.println("Start of readFromFile method file name is "+fileName);
		Scanner inStr = null;
		String fileContent = "";
		try{
		File theFile = new File(fileName);
			if(theFile.exists() && theFile.canRead()){
				inStr = new Scanner(theFile);//connect my stream to the file
				int lineNum = 0;
				while(inStr.hasNextLine()){//as long as the file has another line
					lineNum++;
					fileContent +=  lineNum+" "+ inStr.nextLine() +"\n";//read line by line
				}
			}
		}
		catch(FileNotFoundException fnfe){
			System.err.println("PROBLEM! inside readFromFile method "+fileName+
					"Message is: "+fnfe.getMessage());
		}
		finally{
			if(inStr != null){
				inStr.close();
			}
			System.out.println("END of readFromFile method file name was "+fileName);
		}
		return fileContent;
	}
	
	
	public void copyFileFromOneToOther(String fileOrig, String fileCopied){
		Scanner inStr = null;
		PrintWriter outStr = null;
		String lineIn = "\nCOPY OF\n"+fileOrig+"\nWOOHOO\n";
		try {
			inStr = new Scanner(new File(fileOrig));
			outStr = new PrintWriter(fileCopied); //create exact copy of original
			outStr.println(lineIn);
			while(inStr.hasNextLine()){
				lineIn = inStr.nextLine();
				outStr.println(lineIn);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not copy file "+e.getMessage());
		}
		finally{
			if(inStr !=null){
				inStr.close();
			}
			if(outStr !=null){
				outStr.close();
			}
			System.out.println("DONE copyFileFromOneToOther ");
		}
	}

	@Override
	public String readDelimitedFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readDelimitedFile(String fileName, String delimeter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findAndReplaceInFile(String fileName, String textOld,
			String textNew) {
		// TODO Auto-generated method stub
		
	}

}
