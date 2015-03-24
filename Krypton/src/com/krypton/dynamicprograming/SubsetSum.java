package com.krypton.dynamicprograming;

//Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
// similar to Integer knapset problem.
public class SubsetSum {
	
	private int[] inputarray;
	private int sum;
	
	public SubsetSum(int[] inputarray, int sum)
	{
		this.inputarray = inputarray;
		this.sum = sum;
	}
	
	public Boolean hasSubsetSum()
	{
		Boolean table[][] = new Boolean[inputarray.length+1][sum+1];
		
		// init table to false
		for(int i=0;i<inputarray.length+1;i++)
		{
			for(int j=0;j<sum+1;j++)
				table[i][j] = false;
		}
		
		for(int i = 0;i<inputarray.length+1;i++)
		{
			for(int j=0;j<sum+1;j++)
			{
				if((i==0) || (j==0))
					continue;
				
				// result excluding ith element
				Boolean BResultExcluding = table[i-1][j];
				
				//result including ith element
				Boolean BResultIncluding1 =(inputarray[i-1] == j) ? true : false;
				Boolean BResultIncluding2 = false;
				if(inputarray[i-1] < j)
				{
					BResultIncluding2=table[i-1][j-inputarray[i-1]];
				}
				
				table[i][j] = BResultExcluding || BResultIncluding1 || BResultIncluding2;
			}
		}
		//print array
		for(int i = 0;i<inputarray.length+1;i++)
		{
			for(int j=0;j<sum+1;j++)
			{
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
		return table[inputarray.length][sum];
	}
	

}
