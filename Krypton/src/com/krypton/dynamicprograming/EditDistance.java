package com.krypton.dynamicprograming;

public class EditDistance {
	
	private String string1;
	private String string2;
	
	public EditDistance(String string1,String string2)
	{
		this.string1 = string1;
		this.string2 = string2;
	}
	
	public int getEditDistance()
	{
		int i, j, nString1Length, nString2Length, min;
		nString1Length = string1.length();
		nString2Length = string2.length();
		int[][] table = new int[nString1Length+1][nString2Length+1];
		
		for (i = 0; i <= nString1Length; i++)
			table[i][0] = i;
		for (i = 1; i <= nString2Length; i++)
			table[0][i] = i;

		for (i = 1; i <= nString1Length; i++)
		{
			for (j = 1; j <= nString2Length; j++)
			{
				min = Math.min(table[i-1][j], table[i][j-1]) + 1;
				char c1 = string1.charAt(i-1);
				char c2 = string2.charAt(j-1);
				int isSame = (c1 == c2) ? 0: 1;
				table[i][j] = Math.min(min, table[i-1][j-1] + isSame);
			}
		}
		
		// print header
	   System.out.format("     ");				

		for(int ii=0;ii<nString2Length;ii++)
		{
			   System.out.format(" %3c",string2.charAt(ii));				
		}
		System.out.println();
		// print array
		for(int ii=0;ii<nString1Length+1;ii++)
		   {
				if(ii < 1)
				   System.out.print(" ");
			   else
				   System.out.print(string1.charAt(ii-1));
			   
			   for(int jj=0;jj<nString2Length+1;jj++)
				   System.out.format(" %3d",table[ii][jj]);
			   System.out.println();
			   
				  
			   
		   }	
		return table[nString1Length][nString2Length];
	}

}
