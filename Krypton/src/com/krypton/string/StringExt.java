package com.krypton.string;

public class StringExt {
	private String string;
	int count;
	public StringExt(String string)
	{
		this.string = string;
	}
	
	public String getString()
	{
		return string;
	}
	public String swap(String string,int i, int j)
	{
		
		String newString;
		char charAti = string.charAt(i);
		char charAtj = string.charAt(j);
		newString = string.substring(0,i) + charAtj + string.substring(i+1,j)
						+ charAti + string.substring(j+1,string.length());
		return newString;
	}
	public void swap(int i, int j)
	{
		string = swap(string, i, j);
		
	}
	
		public void printPermutations()
	{
		//rotateAndPrint(string);
		for(int i=0;i<string.length()-1;i++)
			for(int j=i;j<string.length();j++)
			{
				if(i==j) continue;
				String newString = swap(string, i, j);
				//System.out.println(newString);
				System.out.println("j=" + j + "i=" + i + "  "+newString);
				
				
				//rotateAndPrint(newString);				
			}
	}

	public void rotateAndPrint()
	{
		rotateAndPrint(string);
	}
	public void rotateAndPrint(String string)
	{
		for(int i =0; i< string.length(); i++)
		{
			System.out.println(++count + "-"+string.substring(i+1, string.length()) + string.substring(0, i+1) );
		}
			
	}
}
