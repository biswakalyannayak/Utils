package com.nc.utility.io.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestIO {

	public static void main(String[] args) {
		String filename[] = {"CDS_20151201_update_addtional1.sql",
				"CDS_20151215_update_addtional1.sql","CDS_20151231_update_addtional1.sql",
				"CDS_20160105_update_addtional1.sql"};
		final Matcher matcher = Pattern.compile(".*_(\\d{8})_update.*").matcher("");
		File dir = new File("D:\\Biswakalyan\\Bills\\FileTesting");
		
		FilenameFilter filenameFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return matcher.reset(name).matches(); //name.matches(".*_\\d{8}_update.*");
			}
		};
		
		File[] ondisk = dir.listFiles(filenameFilter);
		for (File file : ondisk) {
			System.out.println(file.getName());
		}
			
		Arrays.sort(ondisk, new Comparator<File>() {
	            @Override
	            public int compare(File o1, File o2) {
	                int n1 = extractNumber(o1.getName());
	                int n2 = extractNumber(o2.getName());
	                if(n1>n2){
	                	return 1;
	                }else if(n1<n2){
	                	return -1;
	                }else if(n1==n2){
	                	return 0;
	                }
	                return 0;
	            }

	            private int extractNumber(String name) {
	                int i = 0;
	                try {
	                	matcher.reset(name).matches();
	                    String number = matcher.group(1);
	                    i = Integer.parseInt(number);
	                } catch(Exception e) {
	                    i = 0; // if filename does not match the format
	                           // then default to 0
	                }
	                return i;
	            }
	        });
		
		for (File file : ondisk) {
			System.out.println(file.getName());
		}
				
		
		
	}


	public static void readIOJava7() {
		Path path = Paths.get("D:\\Biswakalyan\\Bills\\FileTesting");
		try {
			System.out.println(path.getNameCount());
			System.out.println(path.isAbsolute());
			System.out.println(path.getFileName());
			System.out.println(path.getFileSystem());
			System.out.println(Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS));
			try (DirectoryStream<Path> stream =
				     Files.newDirectoryStream(path, "*.{java,class,jar}")) {
				    for (Path entry: stream) {
				        System.out.println(entry.getFileName());
				        System.out.println(Files.isDirectory(entry));
				        System.out.println(Files.readAllBytes(entry));
				    }
				} catch (IOException x) {
				    // IOException can never be thrown by the iteration.
				    // In this snippet, it can // only be thrown by newDirectoryStream.
				    System.err.println(x);
				}
		} catch (Exception e) {
			System.err.println("Something goes wrong");
		}
	}
	
	
	public InputStream getFileJava6(){
		// Try loading from the current directory
		InputStream is = null;
		try {
		    File f = new File("config.xml");
		    is = new FileInputStream( f );
		}
		catch ( Exception e ) { /*�*/ }
		 
		// Or, if that fails, you can open the file by searching in the 
		// Java application's class path:
		 
		try {
		    if ( is == null ) {
		        // load from classpath
		        is = getClass().getResourceAsStream("config.xml");
		    }
		}
		catch ( Exception e ) {
		    System.out.println("config.xml file does not exist");
		}
		return is;
	}
	
	public void readJava6(){
		//Option1
		BufferedInputStream bis = new BufferedInputStream(getFileJava6());
		int data = -1;
		try {
			while ( (data = bis.read()) != -1 ) {
			    // ...
			}
		} catch (Exception e) {
			 System.out.println("config.xml file does not exist");
		}
		
		//Option2
		DataInputStream dis = new DataInputStream(getFileJava6());
		try {
			int length = dis.readInt(); // first int is count of doubles
			double[] d = new double[length];
			for ( int i = 0; i < length; i++ ) {
			    d[i] = dis.readDouble();
			}
		} catch (Exception e) {
			 System.out.println("config.xml file does not exist");
		}
		
		
		//option 3
		File f = new File("config.xml");
		try {
			FileReader fr = new FileReader(f);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			while ( line != null ) {
			    // ...
			    line = reader.readLine();
			}
		} catch (Exception e) {
			 System.out.println("config.xml file does not exist");
		}
	}

}
