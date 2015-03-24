package com.krypton.dynamicprograming;

public class LongestIncreasingSubsequence {
	private int[] inputarray;
	
	public LongestIncreasingSubsequence(int[] inputarray)
	{
		this.inputarray = inputarray;
	}
	
	public int getLongestIncreasingSubsequence()
	{
		int[] auxArray = new int[inputarray.length];
		
		for(int i=0;i<inputarray.length;i++)
		{
			auxArray[i] = 1;
		}
		for(int i=1;i<inputarray.length;i++)
		{
			for(int j=0;j<i;j++)
			{
				if(inputarray[i] >= inputarray[j])
				{
					int currentValue = auxArray[i];
					int newValue = auxArray[j]+1;
					if(newValue>currentValue)
						auxArray[i] = newValue;
				}
			}
		}
		
		//print aux array
		for(int i = 0;i<auxArray.length;i++)
		{
			System.out.print(auxArray[i] + " ");
		}
		//find max
		int max=0;		
		for(int i = 0;i<auxArray.length;i++)
		{
			if(auxArray[i]>max)
				max = auxArray[i];
		}
		return max;	
		
	}

}
