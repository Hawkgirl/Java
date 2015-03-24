package com.krypton.string;

import java.util.Enumeration;

// substring Tokenizer return substring as enumerator
// for input ABC, this will return A,BC AB,C
public class SubStringTokenizer implements Enumeration<String []>{

	private String string;
	private int nCurrentIndex = 1;
	
	public SubStringTokenizer(String strInput)
	{
		string = strInput;		
	}
	public boolean hasMoreElements()
	{
		if(nCurrentIndex > (string.length() -1))
			return false;
	
		return true;
	}
	
	public String[] nextElement()
	{
		if(nCurrentIndex > string.length())
			return null;
		
		String[] output  = new String[2];
		output[0] = string.substring(0, nCurrentIndex);
		output[1] = string.substring(nCurrentIndex, string.length());
		
		nCurrentIndex ++;
		return output;
		
	}
}

