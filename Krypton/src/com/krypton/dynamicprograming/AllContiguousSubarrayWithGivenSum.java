package com.krypton.dynamicprograming;

public class AllContiguousSubarrayWithGivenSum {
	
	private int[] inputArray;
	private int nSum;
	
	public AllContiguousSubarrayWithGivenSum(int[] array, int nSum)
	{
		this.inputArray = array;
		this.nSum = nSum;
	}
	// create an auxiliary summed up array 
	// and for each element in the auxiliary array search
	// for an element where aux[i]+sum=aux[k]
	public int getSubarrayWithGivenSum()
	{
		int nCount=0;
		int[] auxSummedUpArray = new int[inputArray.length];
		auxSummedUpArray[0] = inputArray[0];
		for(int i = 1; i < inputArray.length;i++)
			auxSummedUpArray[i] = auxSummedUpArray[i-1] + inputArray[i];			
	
		for(int i = 0; i<auxSummedUpArray.length;i++)
		{
			int nFindElement = auxSummedUpArray[i] + nSum;
			for(int j = i+1 ; j< auxSummedUpArray.length;j++)
			{
				if(auxSummedUpArray[j] == nFindElement)
				{
					//element found
					nCount++;
					printArray(i,j);
				}
			}
		}
		return nCount;
	}
	void printArray(int start,int end)
	{
		System.out.print("Subarray with sum" + nSum + ":");
		for(int i=start+1;i<end+1;i++)
		{
			System.out.print(inputArray[i]+" ");
		}
		System.out.println();
	}

}
