package com.nc.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAndaParser {
	
	static Matcher headerMatcher = Pattern.compile("Serialnmber\\|Sortorder\\|PartNumber\\|Partname\\|level1\\|serialized\\|Source\\|Lastupdateddate").matcher("");
	static Matcher dataMatcher = Pattern.compile("8TK00204|-99101725|1061657|WIRING GP-REAR|10732256C76004E8467||E|30-AUG-12|").matcher("");
	
	static String newFileHeader = "Serialnmber|Sortorder|PartNumber|Partname|level1|serialized|Source|Lastupdateddate|Indent";
	static List<String> newFileContent = new ArrayList<String>();
	static Set<String> newContentwithoutLevel1 = new TreeSet<String>();
	static String [] headerArr = null;
	
	
	public static void main(String[] args) {
		boolean headerFound = false;
		
		TreeMap<String,String> parentMap = new TreeMap<String, String>();
		String fileName="C:\\Users\\biswnaya\\git\\Utils\\Utility\\src\\main\\java\\com\\nc\\utility\\Final1.csv";
		String outputFile="C:\\Users\\biswnaya\\git\\Utils\\Utility\\src\\main\\java\\com\\nc\\utility\\Result1.csv";
		
		 try{

	          headerFound  = processForMetaData(parentMap, fileName);
	          newFileContent.add(newFileHeader);
	          //Map with lines which has not Level1 entry
	          Set<String> parentSet =  new TreeSet<String>(parentMap.keySet());
	          for (String parent : parentSet) {
	        	  String parentLine = parentMap.get(parent);
	        	  String [] dataArr = parentLine.split("\\|");
	        	  if(getIndexOf(dataArr, parent) == getIndexOf(headerArr,"PartNumber")){
	        		  parentLine = parentLine+"0";
	        		  int levalIndx = getIndexOf(headerArr, "level1");
	        		  String[] parentDataArr = parentLine.split("\\|");
	        		  if(parentDataArr[levalIndx] ==null || parentDataArr[levalIndx].isEmpty()){
	        			  newContentwithoutLevel1.add(parentLine);
	        		  }else{
	        			  newFileContent.add(parentLine);
	        		  }
	        		  //Remove the parent from map for further processing
	        		  parentMap.remove(parent);
	        		  for (String parentMapKey : parentMap.keySet()) {
	        				  String line = parentMap.get(parentMapKey);
	        				  String[] chidDataArr = line.split("\\|");
	        				  
	        				  String childV = chidDataArr[levalIndx];
	        				  if(childV.contains(parent)){
	        					  line = line+"1";
	        					  newFileContent.add(line);
	        				  }
					  }
	        	  }else{
	        		 System.err.println("Skiping the line as this is not in format. Line:"+parentLine);
	        	  }
	          }
	          
	          System.out.println("Size"+parentMap.size()+parentMap);
	          System.err.println("Size"+newFileContent.size()+newFileContent);
	          FileWriter writer = new FileWriter(outputFile); 
	          for(String str: newFileContent) {
	            writer.write(str);
	            writer.write("\n");
	          }
	          for (String string : newContentwithoutLevel1) {
	        	  writer.write(string);
		          writer.write("\n");
	          }
	          writer.close();
			  
	       }catch(Exception e){
	    	   e.printStackTrace();
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
		
	}

	private static boolean processForMetaData(TreeMap<String,String> parentMap,
			String fileName) throws IOException {
		boolean flag = false;
		String header;
		String[] dataArr;
		//Create object of FileReader
        FileReader inputFile = new FileReader(fileName);
		//Instantiate the BufferedReader Class
		  BufferedReader bufferReader = new BufferedReader(inputFile);

		  //Variable to hold the one line data
		  String line;

		  // Read file line by line and print on the console
		  while ((line = bufferReader.readLine()) != null && !line.isEmpty())   {
			  if(!flag && headerMatcher.reset(line).matches()){
				  flag = true;
				  header = line;
				  headerArr = header.split("\\|");
				  continue;
			  }
			  
			  if(flag){
				  dataArr = line.split("\\|");
				  if(dataArr.length==headerArr.length){
					  
					  //generate map which has level1
					  int parentIndx = getIndexOf(headerArr,"PartNumber");
					  parentMap.put(dataArr[parentIndx],line);
					 
					  
				  }else{
		    		  System.out.println("invalid line found "+line);
		    	  }
				 
			  }
		  }
		  //Close the buffer reader
		  bufferReader.close();
		  return flag;
	}
	
	private static int getIndexOf(String [] arr, String hader){
		return Arrays.asList(arr).indexOf(hader);
		
	}

}
