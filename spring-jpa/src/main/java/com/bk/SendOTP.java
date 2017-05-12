package com.bk;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.utils.URIBuilder;



public class SendOTP {

	public static void main(String[] args) throws Exception {
		System.out.println("Started sending otp");
		
		String msgPart1 = "One Time Password for BeNow Registration is ";
		//String msgPart2 = getOTP();
		String msgPart3 = ". Please use the otp to complete the Registration. Please do not share this with anyone";
		//String msg = msgPart1.concat(msgPart2).concat(msgPart3);
		//String msg = "BeNow verification code is 9920566947||1475245649621|e9424595ecb4c5250bb4173aa01cd163360232";
		//System.out.println("msg " + msg);
		String url = "http://103.16.101.52:8000/bulksms/bulksms";//?feedid=357105&username=9619595088&password=tagmw&To=919619595088&Text=testOTP674900

		URIBuilder builder = new URIBuilder(url);
		//builder.addParameter("feedid", "357105");
		/*builder.addParameter("feedid", "360232");
		builder.addParameter("username", "9619595088");
		builder.addParameter("password", "tjawg");
		builder.addParameter("To", "919619595088");
		builder.addParameter("Text", msg);*/
		builder.addParameter("username", "iho-mobapptest");
		builder.addParameter("password", "12345678");
		builder.addParameter("type", "0");
		builder.addParameter("dlr", "1");
		builder.addParameter("destination", "7498361506");
		builder.addParameter("source", "IHOSRV");
		builder.addParameter("message", "Pravin hi");
		System.out.println("URL " + builder.toString());
		String uri = builder.toString();
		GetMethod get = new GetMethod(uri);
		new HttpClient().executeMethod(get);
		System.out.println("OTP sent");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ;		
		byte[] byteArray = new byte[1024];		
		int count = 0 ;		
		while ((count = get.getResponseBodyAsStream().read(byteArray, 0,
				byteArray.length)) > 0)
		{
			outputStream.write(byteArray, 0, count);
		}
	
		System.out.println("Response :" + new String(outputStream.toByteArray(), "UTF-8"));
	}

	/*private static String getOTP() {
		 String pass = "12345678901234567890";
		 byte[] code = pass.getBytes();		
		 long timeInMilliSecond = new Date().getTime();
		 try {
			return HOTPAlgorithm.generateOTP(code, timeInMilliSecond, 6, false, 1);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
}
