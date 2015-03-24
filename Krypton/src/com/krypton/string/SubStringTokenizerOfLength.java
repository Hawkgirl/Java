package com.krypton.string;

import java.util.Enumeration;

// substring Tokenizer return substring as enumerator
// for input ABCD & length 2, this will return AB,BC,CD
public class SubStringTokenizerOfLength implements Enumeration<String>{

	private String string;
	private int nCurrentIndex = 0;
	private int nLength;
	
	public SubStringTokenizerOfLength(String strInput, int nLength)
	{
		string = strInput;	
		this.nLength = nLength;
	}
	public boolean hasMoreElements()
	{
		if((nCurrentIndex + nLength) > (string.length()))
			return false;
	
		return true;
	}
	
	public String nextElement()
	{
		if((nCurrentIndex + nLength) > string.length())
			return null;
		
		String output  = new String();
		output = string.substring(nCurrentIndex, nCurrentIndex+nLength);
		
		nCurrentIndex ++;
		return output;
		
	}
}

