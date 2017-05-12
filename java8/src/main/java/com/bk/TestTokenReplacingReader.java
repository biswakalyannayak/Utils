package com.bk;

import java.util.HashMap;
import java.util.Map;

public class TestTokenReplacingReader {

	public static void main(String[] args) {
		Map<String, String> tokens = new HashMap<String, String>();
	    tokens.put("token1", "value1");
	    tokens.put("token2", "JJ ROCKS!!!");

	    MapTokenResolver resolver = new MapTokenResolver(tokens);

	    Reader source =
	        new StringReader("1234567890${token1}abcdefg${token2}XYZ$000");

	    Reader reader = new TokenReplacingReader(source, resolver);

	    int data = reader.read();
	    while(data != -1){
	        System.out.print((char) data);
	        data = reader.read();
	    }
	}
//http://tutorials.jenkov.com/java-howto/replace-strings-in-streams-arrays-files.html
}
