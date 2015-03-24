package com.krypton.dynamicprograming;

public class EggDrop {
	private int nEggs;
	private int nFloors;
	
	public EggDrop(int nEggs,int nFloors)
	{
		this.nEggs = nEggs;
		this.nFloors = nFloors;
	}
	public int getMinTrials()
	{
		/* Function to get minimum number of trails needed in worst
		  case with n eggs and k floors */

		/* A 2D table where entry eggFloor[i][j] will represent minimum
		       number of trials needed for i eggs and j floors. */
		int eggFloor[][] = new int [nEggs+1][nFloors+1];
		int res;
		int i, j, x;

		// We need one trial for one floor and0 trials for 0 floors
		for (i = 1; i <= nEggs; i++)
		{
			eggFloor[i][1] = 1;
			eggFloor[i][0] = 0;
		}

		// We always need j trials for one egg and j floors.
		for (j = 1; j <= nFloors; j++)
			eggFloor[1][j] = j;

		// Fill rest of the entries in table using optimal substructure
		// property
		for (i = 2; i <= nEggs; i++)
		{
			for (j = 2; j <= nFloors; j++)
			{
				eggFloor[i][j] = Integer.MAX_VALUE;
				for (x = 1; x <= j; x++)
				{
					res = 1 + Math.max(eggFloor[i-1][x-1], eggFloor[i][j-x]);
					if (res < eggFloor[i][j])
						eggFloor[i][j] = res;
				}
			}
		}
		//print table
		 for(int ii=0;ii<nEggs+1;ii++)
		   {
			   for(int jj=0;jj<nFloors+1;jj++)
				   System.out.format(" %3d",eggFloor[ii][jj]);
			   System.out.println();
			   
		   }	   
		// eggFloor[n][k] holds the result
		return eggFloor[nEggs][nFloors];
	}
}

