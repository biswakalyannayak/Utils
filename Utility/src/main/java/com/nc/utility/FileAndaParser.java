package com.nc.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAndaParser {
	
	static Matcher headerMatcher = Pattern.compile("Serialnmber\\|Sortorder\\|PartNumber\\|Partname\\|level1\\|serialized\\|Source\\|Lastupdateddate").matcher("");
	
	static String newFileHeader = "Serialnmber|Sortorder|PartNumber|Partname|level1|serialized|Source|Lastupdateddate|Indent";
	static List<String> newFileContent = new ArrayList<String>();
	static Set<String> newContentwithoutLevel1 = new TreeSet<String>();
	static String [] headerArr = null;
	static TreeSet<String> parentSet = new TreeSet<String>();
	static TreeSet<String> dataSet = new TreeSet<String>();
	
	
	
	public static void main(String[] args) {
		boolean headerFound = false;
		
		TreeMap<String,String> parentMap = new TreeMap<String, String>();
		//String fileName="C:\\Users\\biswnaya\\git\\Utils\\Utility\\src\\main\\java\\com\\nc\\utility\\Final1.csv";
		//String outputFile="C:\\Users\\biswnaya\\git\\Utils\\Utility\\src\\main\\java\\com\\nc\\utility\\Result1.csv";
		String fileName="E:\\WorkSpace\\CommonUtility\\GitHub\\Utility\\src\\main\\java\\com\\nc\\utility\\Final1.csv";
		String outputFile="E:\\WorkSpace\\CommonUtility\\GitHub\\Utility\\src\\main\\java\\com\\nc\\utility\\Result1.csv";
				
		 try{
			 //Get the header and parent and data set
			 headerFound  = processForMetaData(parentMap, fileName);
			 //Make a local copy for iteration
			 TreeSet<String> parentSetL1 = new TreeSet<String>(parentSet);
			 TreeSet<String> dataSetL1 = new TreeSet<String>(dataSet);
	         //Provide the header
	         newFileContent.add(newFileHeader);
	         //Get the index
	         int levalIndx = getIndexOf(headerArr, "level1");
	         int serilizeIndx = getIndexOf(headerArr, "serialized");
	         int partNumberIndx = getIndexOf(headerArr,"PartNumber");
    		  //iterate over local parent copy
	          for (String parentLine : parentSetL1) {
	        	  String [] parentDataArr = parentLine.split("\\|");
	        	  newFileContent.add(parentLine+"0");
	        	  String parentcode = parentDataArr[partNumberIndx];
	        	  //Iterate dataset local and find all exact matching level1
	        	  Iterator<String> dataLineIt1 = dataSetL1.iterator();
	        	  while (dataLineIt1.hasNext()) {
        				String dataLine = dataLineIt1.next();
        				String[] dataArr = dataLine.split("\\|");
        				String level1 = dataArr[levalIndx];
        				String childParentCode = dataArr[partNumberIndx];
        				if(level1.equalsIgnoreCase(parentcode)){
        					newFileContent.add(dataLine+"1");
        					dataSet.remove(dataLine);
        					dataLineIt1.remove();
        					//Find exact match for of sub parent in level1
        					extractSubParent(new TreeSet<String>(dataSet),childParentCode,levalIndx,partNumberIndx);
        				}
        				else if(level1.contains(parentcode)){
        					String subChildParentCode;
        					if(level1.length()>parentcode.length()+6){
        						subChildParentCode = level1.substring(parentcode.split("|").length,parentcode.split("|").length+6);
        					}
        					else{
        						subChildParentCode = level1.substring((parentcode).split("|").length);
        					}
        					TreeSet<String > temp = (TreeSet<String>) dataSet.clone();
        					Iterator<String> tempItr = temp.iterator();
              			  	while (tempItr.hasNext()) {
	              			  	String tempDataLine = tempItr.next();
	            				String[] tempDataArr = dataLine.split("\\|");
	            				String tempLevel1 = dataArr[levalIndx];
	            				String tempChildParentCode = dataArr[partNumberIndx];
	            				if(tempLevel1.equalsIgnoreCase(subChildParentCode)){
	            					tempDataLine = tempDataLine+"1";
		            				tempItr.remove();
		            				temp.remove(tempDataLine);
		        					newFileContent.add(tempDataLine);
	            				}else if(tempLevel1.contains(subChildParentCode)){
	            					String subChildParentCode1 = level1.substring((parentcode+subChildParentCode).split("|").length);
	            					newFileContent.add(tempDataLine);
	            				}
	            				
              			  	}
        					
        				}
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

	private static void extractSubParent(TreeSet<String> dataSet1, String childParentCode, int levalIndx, int partNumberIndx) {
		  Iterator<String> dataLineIt1 = dataSet1.iterator();
		  while (dataLineIt1.hasNext()) {
			String dataLine = dataLineIt1.next();
			String[] dataArr = dataLine.split("\\|");
			String level1 = dataArr[levalIndx];
			String childSubparentcode = dataArr[partNumberIndx];
			if(level1.equalsIgnoreCase(childParentCode)){
				dataSet.remove(dataLine);
				dataLine = dataLine+"1";
				newFileContent.add(dataLine);
				dataLineIt1.remove();
				
				//exrtractRelativeParent((TreeSet<String>) dataSet1.clone(),childParentCode,levalIndx,partNumberIndx);
			}
		}
		
	}

	private static void exrtractRelativeParent(TreeSet<String> dataSet2, String childParentCode, int levalIndx, int partNumberIndx) {
		 Iterator<String> dataLineIt2 = dataSet2.iterator();
		  while (dataLineIt2.hasNext()) {
			String dataLine = dataLineIt2.next();
			String[] dataArr = dataLine.split("\\|");
			String level1 = dataArr[levalIndx];
			if(level1.contains(childParentCode)){
				dataLine = dataLine+"1";
				newFileContent.add(dataLine);
				dataLineIt2.remove();
				dataSet2.remove(dataLine);
			}
		  
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
					  int levalIndx = getIndexOf(headerArr, "level1");
					  if((dataArr[levalIndx] ==null || dataArr[levalIndx].isEmpty())){
	        			  parentSet.add(line);
	        		  }else{
	        			  dataSet.add(line);
	        		  }
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
