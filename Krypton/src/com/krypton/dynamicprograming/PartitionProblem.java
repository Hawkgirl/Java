package com.krypton.dynamicprograming;


//Partition problem is to determine whether a given set can be partitioned into two subsets such that the sum of elements in both subsets is same. 
// variation os subset problem
/*
Following are the two main steps to solve this problem:
	1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum, so return false.
	2) If sum of array elements is even, calculate sum/2 and find a subset of array with sum equal to sum/2.
	step 2 is solved by subset problem
	*/
public class PartitionProblem {
	
	private int[] inputarray;
	public PartitionProblem(int[] inputarray)
	{
		this.inputarray = inputarray;
	}

	public Boolean hasEqualSubsets()
	{
		int sum = 0;
		for(int i = 0;i<inputarray.length;i++)
			sum += inputarray[i];
		if(sum%2 == 1)
			return false;
		SubsetSum subsetsum = new SubsetSum(inputarray, sum/2);
		return subsetsum.hasSubsetSum();
	}
}
