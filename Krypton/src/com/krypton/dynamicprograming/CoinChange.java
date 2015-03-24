package com.krypton.dynamicprograming;

public class CoinChange {
	
	private int[] array;
	int nSum;
	
	public CoinChange(int[] array, int nSum)
	{
		this.array = array;
		this.nSum = nSum;
	}
	
	public int getCount()
	{
		int i, j, x, y;
		 
	    // We need n+1 rows as the table is consturcted in bottom up manner using
	    // the base case 0 value case (n = 0)
		
	    int table[][] = new int[nSum+1][array.length];
	    
	    // Fill the enteries for 0 value case (n = 0)
	    for (i=0; i<array.length; i++)
	        table[0][i] = 1;
	 
	    // Fill rest of the table enteries in bottom up manner 
	    for (i = 1; i < nSum+1; i++)
	    {
	        for (j = 0; j < array.length; j++)
	        {
	            // Count of solutions including S[j]
	            x = (i-array[j] >= 0)? table[i - array[j]][j]: 0;
	 
	            // Count of solutions excluding S[j]
	            y = (j >= 1)? table[i][j-1]: 0;
	 
	            // total count
	            table[i][j] = x + y;
	        }
	    }
	    printarray(table, nSum+1, array.length);
	    return table[nSum][array.length-1];
	}
	void printarray(int[][] table, int n, int m)
	{
	 for(int i=0;i<n;i++)
	   {
		 	System.out.print("Sum:" + i + "  ");
		   for(int j=0;j<m;j++)
		   {
			   
			   System.out.format(" %3d",table[i][j]);
		   }
		   System.out.println();
		   
	   }	
	}
	public int getCount2()
	{
	    // table[i] will be storing the number of solutions for
	    // value i. We need n+1 rows as the table is consturcted
	    // in bottom up manner using the base case (n = 0)
	    int table[] = new int[nSum+1];
	 
	    // Initialize all table values as 0
	    
	    // Base case (If given value is 0)
	    table[0] = 1;
	 
	    // Pick all coins one by one and update the table[] values
	    // after the index greater than or equal to the value of the
	    // picked coin
	    for(int i=0; i<array.length; i++)
	        for(int j=array[i]; j<=nSum; j++)
	            table[j] += table[j-array[i]];
	 
	    for(int i=0;i<nSum;i++)
	    {
	    	System.out.print(table[i] + "  ");
	    }
	    return table[nSum];
	}
}
