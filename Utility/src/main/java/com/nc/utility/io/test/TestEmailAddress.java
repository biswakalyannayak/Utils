package com.nc.utility.io.test;

import javax.mail.internet.InternetAddress;

public class TestEmailAddress {

	public static void main(String[] args) {
		
		
		
		 try {
			 String email = "email@javacodegeeks.com";
			InternetAddress internetAddress = new InternetAddress(email);
			System.out.println(internetAddress.getAddress());
			internetAddress.validate();
			System.out.println("S");
		} catch (Exception e) {
			System.out.println("F");
		}
		 TestEmailAddress test = new TestEmailAddress();
		 String a = "a";
		 String b = "b";
		 Integer ia = Integer.valueOf(127);
		 Integer ib =  Integer.valueOf(127);
		 Integer ia1 =  Integer.valueOf(128);
		 Integer ib1 =  Integer.valueOf(128);
		 int iia = 1;
		 int iib = 2;
		 System.out.println(ia==ib);
		 System.out.println(ia1==ib1);
		 test.add(a, b);
		 test.add(iia, ib);
	}
	
	public void add(String a,String b){
		System.out.println("Both sting"+a+b);
	}
	
	public void add(Integer a,Integer b){
		System.out.println("both integer"+a+b);
	}
	
	public void add(Integer a,String b){
		System.out.println("interger string"+a+b);
	}
	
	public void add(Integer a,int b){
		System.out.println("Integer int"+a+b);
	}
	
	public void add(int a,Integer b){
		System.out.println("int integer"+a+b);
	}
	
	public void add(int a,int b){
		System.out.println("int int"+a+b);
	}

}
