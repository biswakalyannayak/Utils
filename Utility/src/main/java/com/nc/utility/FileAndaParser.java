package com.nc.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAndaParser {
	
	static Matcher headerMatcher = Pattern.compile("Serialnmber\\|Sortorder\\|PartNumber\\|Partname\\|level1\\|serialized\\|Source\\|Lastupdateddate").matcher("");
	
	static String newFileHeader = "Serialnmber|Sortorder|PartNumber|Partname|level1|serialized|Source|Lastupdateddate|Indent";
	static LinkedHashMap<String,String> newFileContent = new LinkedHashMap<String, String>();
	static Set<String> newContentwithoutLevel1 = new TreeSet<String>();
	static String [] headerArr = null;
	static TreeSet<String> parentSet = new TreeSet<String>();
	static TreeSet<String> dataSet = new TreeSet<String>();
	static TreeSet<String> parsedLevel = new TreeSet<String>();
	
	
	public static void main(String[] args) {
		boolean headerFound = false;
		
		TreeMap<String,String> parentMap = new TreeMap<String, String>();
		String fileName="C:\\Users\\biswnaya\\git\\Utils\\Utility\\src\\main\\java\\com\\nc\\utility\\Final1.csv";
		String outputFile="C:\\Users\\biswnaya\\git\\Utils\\Utility\\src\\main\\java\\com\\nc\\utility\\Result1.csv";
		//String fileName="E:\\WorkSpace\\CommonUtility\\GitHub\\Utility\\src\\main\\java\\com\\nc\\utility\\Final1.csv";
		//String outputFile="E:\\WorkSpace\\CommonUtility\\GitHub\\Utility\\src\\main\\java\\com\\nc\\utility\\Result1.csv";
				
		 try{
			 //Get the header and parent and data set
			 headerFound  = processForMetaData(parentMap, fileName);
			 //Make a local copy for iteration
			 TreeSet<String> parentSetL1 = new TreeSet<String>(parentSet);
			 TreeSet<String> dataSetL1 = new TreeSet<String>(dataSet);
	         //Provide the header
	         newFileContent.put("Header",newFileHeader);
	         //Get the index
	         int levalIndx = getIndexOf(headerArr, "level1");
	         int partNumberIndx = getIndexOf(headerArr,"PartNumber");
	         
    		  //iterate over local parent copy
	          for (String parentLine : parentSetL1) {
	        	  parentSet.remove(parentLine);
	        	  String [] parentDataArr = parentLine.split("\\|");
	        	  String parentPartCode = parentDataArr[partNumberIndx];
	        	  if(newFileContent.get(parentPartCode)==null){
	        		  newFileContent.put(parentPartCode,parentLine+"0");
	        	  }
	        	 
	        	  //Iterate dataset local and find all exact matching level1
	        	  Iterator<String> dataLineIt1 = dataSetL1.iterator();
	        	  while (dataLineIt1.hasNext()) {
        				String dataLine = dataLineIt1.next();
        				String[] dataArr = dataLine.split("\\|");
        				String level1 = dataArr[levalIndx];//10732256C76004E8467  | 10732256
        				String dataPartCode = dataArr[partNumberIndx];
        				if(level1.contains(parentPartCode)){
        					if(level1.equalsIgnoreCase(parentPartCode)){//10732256
        						 if(newFileContent.get(dataPartCode)==null){
        			        		  newFileContent.put(dataPartCode,dataLine+"1");
        			        	  }
            					dataSet.remove(dataLine);
            					dataLineIt1.remove();
            					//Find the child of dataPartCode. This can be possible for 1 step
            					//call 2nd level
            					extractRelSubChild(new TreeSet<String>(dataSet), dataPartCode, levalIndx, partNumberIndx,2);
        					}else{//10732256C76004E8467
        						//if it contain the parent part code check for next. this can be possible for 3 step
        						//Extract the next partcode from laevel1
        						if(!parsedLevel.contains(level1)||isParsed(level1)){
        							exrtractRelativeParent(new TreeSet<String>(dataSet), parentPartCode, level1, levalIndx, partNumberIndx,1);
            						parsedLevel.add(level1);
        						}
        						
        					}
        					
        				}
        				
					}
        			  
        			 
			}
	          
	          System.out.println("Size"+parentMap.size()+parentMap);
	          System.err.println("Size"+newFileContent.size()+newFileContent);
	          FileWriter writer = new FileWriter(outputFile); 
	          for(String str: newFileContent.keySet()) {
	        	  
	            writer.write(newFileContent.get(str));
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
	private static boolean isParsed(String level1) {
		for (String string : parentSet) {
			string.startsWith(level1);
			return true;
		}
		return false;
	}
	//1st level sub child
	private static void extractRelSubChild(TreeSet<String> dataSet1, String dataPartCode, int levalIndx, int partNumberIndx,int indent) {
		  Iterator<String> dataLineIt1 = dataSet1.iterator();
		  while (dataLineIt1.hasNext()) {
			String dataLine = dataLineIt1.next();
			String[] dataArr = dataLine.split("\\|");
			String level1 = dataArr[levalIndx];
			String childPartCode = dataArr[partNumberIndx];
			if(level1.contains(dataPartCode)){
				if(level1.equalsIgnoreCase(dataPartCode)){
					dataSet.remove(dataLine);
					if(newFileContent.get(childPartCode)==null){
		        		  newFileContent.put(childPartCode,dataLine+indent);
		        	  }
					dataLineIt1.remove();
				}else{
					//combination 10732256C76004E8467
					exrtractRelativeParent(new TreeSet<String>(dataSet), childPartCode, level1, levalIndx, partNumberIndx,3);
				}
			}
		}
		
	}
	
	private static void extractSubParent(String dataPartCode, int levalIndx, int partNumberIndx,int indent) {
		boolean isparent = false;
		
		Iterator<String> it = new TreeSet<String>(dataSet).iterator();
		while (it.hasNext()) {
			String string =  it.next();
			String[] dataArr = string.split("\\|");
			String partNumer = dataArr[partNumberIndx];
			if(partNumer.equalsIgnoreCase(dataPartCode)){
				isparent = true;
				dataSet.remove(string);
				//string.replaceAll(dataPartCode, "");
				if(newFileContent.get(dataPartCode)==null){
	        		  newFileContent.put(dataPartCode,string+indent);
	        		  break;
	        	  }
			}
		}
		
		if(!isparent){
			//Construct your own.
			String dataLine = "0|0|"+dataPartCode+"|ENGINE||0|S|30-AUG-12|";
			if(newFileContent.get(dataPartCode)==null){
      		  newFileContent.put(dataPartCode,dataLine+indent);
      	  }
		}
		
	}

	private static void exrtractRelativeParent(TreeSet<String> dataSet2, String parentPartCode, String level1, int levalIndx, int partNumberIndx, int indent) {
		//10732256
		String subChildPartCode = null;
		String relativeChid = null;
		if(level1.length()>=parentPartCode.length()+12){//10732256C76004E8467 | 6C7600 | 4E8467
			subChildPartCode = level1.substring(parentPartCode.split("|").length-1,parentPartCode.split("|").length+5);
			String temp = parentPartCode+subChildPartCode;
			relativeChid = level1.substring(temp.length(),temp.length()+6);
		}
		else if(level1.length()>parentPartCode.length()){//10732256C7600 | 6C7600
			relativeChid = level1.substring(parentPartCode.length());
		}else{//10732256
			subChildPartCode = parentPartCode;
		}
		//1st put the parent line
		if(subChildPartCode!= null && !subChildPartCode.isEmpty()){
			extractSubParent(subChildPartCode, levalIndx, partNumberIndx, 1);
				
		}
		
		
		//Try searching further child
		if(relativeChid!= null && !relativeChid.isEmpty()){
			extractSubParent(relativeChid, levalIndx, partNumberIndx, 2);
			Iterator<String> dataLineIt2 = new TreeSet<String>(dataSet).iterator();
			while (dataLineIt2.hasNext()) {
				String dataline2 = dataLineIt2.next();
				String[] dataArr2 = dataline2.split("\\|");
				String Level2 = dataArr2[levalIndx];
				String childSubparentcode = dataArr2[partNumberIndx];
				if(Level2.contains(relativeChid)){
					dataSet.remove(dataline2);
					if(newFileContent.get(childSubparentcode)==null){
						/*if(subChildPartCode!= null && !subChildPartCode.isEmpty()){
							newFileContent.put(childSubparentcode,dataline2+(indent+1));
						}else{
							newFileContent.put(childSubparentcode,dataline2+(indent+2));
						}*/
						newFileContent.put(childSubparentcode,dataline2+(indent+2));
		        	  }
					dataLineIt2.remove();
				}
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
