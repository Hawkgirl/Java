package com.krypton.dynamicprograming;


import java.util.HashMap;
import java.util.Map;

import com.krypton.string.SubStringTokenizer;
import com.krypton.string.SubStringTokenizerOfLength;

/*
 * Given a sequence of matrices, find the most efficient way to multiply these matrices together. The problem is not actually to perform the multiplications, but merely to decide in which order to perform the multiplications.

We have many options to multiply a chain of matrices because matrix multiplication is associative. In other words, no matter how we parenthesize the product, the result will be the same. For example, if we had four matrices A, B, C, and D, we would have:

    (ABC)D = (AB)(CD) = A(BCD) = ....

However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed to compute the product, or the efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,

    (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
    A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.

 */

public class MatrixChainMultiplication {

	private int nMatrixCount;
	private String strAllMatrixNames = new String();
	private HashMap<String, Matrix> hashMap = new HashMap<String, Matrix>();
	class Matrix
	{
		private int nRows;
		private int nColumns;
		private String strName;
		int nCost;
		
		
		public Matrix(String strName,int nRows, int nColumns,int nCost)
		{
			this.strName = strName;
			this.nRows = nRows;
			this.nColumns = nColumns;
			this.nCost = nCost;
		}
		
		public int getCost()
		{
			return nCost;
		}
		public int computeCost(Matrix matrix)
		{
			return nRows * nColumns * matrix.nColumns;
		}
		
		public Matrix Product(Matrix matrix)
		{
			String strMatrixName = strName + matrix.strName;
			int nRows = this.nRows;
			int nColumns = matrix.nColumns;
			int nCost = 0;
			return new Matrix(strMatrixName, nRows, nColumns, nCost);
		}
		public void printMatrix()
		{
			System.out.println("Matrix name:" + strName + " no of rows:" + nRows + " no of columns:" + nColumns + " cost:" + nCost);
		}
		
		
				
	}
	public MatrixChainMultiplication(int[] array)
	{
		nMatrixCount = (array.length-1);
		for(int i = 0; i < nMatrixCount;i++)
		{
			char c ='A';
			int nAsciiValue = (int)c;
			nAsciiValue += i;
			c = (char)nAsciiValue;
			String strMatrixName = String.valueOf(c);
			Matrix matrix = new Matrix(strMatrixName, array[i], array[i+1], 0);
			hashMap.put(strMatrixName, matrix);
			strAllMatrixNames += strMatrixName;
		}		
	}
	
	
	public void printMatrixHashMap()
	{
		for(Map.Entry<String, Matrix> entry:hashMap.entrySet())
		{
			Matrix matrix = entry.getValue();
			matrix.printMatrix();
		}
	}
	
	public Matrix processMatrix(String substring)
	{
		int nCost = 0;
		int nMinCost = Integer.MAX_VALUE;
		int nRows = 0,nColumns = 0;
		SubStringTokenizer substrings = new SubStringTokenizer(substring);
		while(substrings.hasMoreElements())
		{
			String strMatrix[] = substrings.nextElement();
			Matrix matrix1 = hashMap.get(strMatrix[0]);
			Matrix matrix2 = hashMap.get(strMatrix[1]);
			nRows = matrix1.nRows;
			nColumns = matrix2.nColumns;
			nCost = matrix1.getCost() + matrix2.getCost() + matrix1.computeCost(matrix2);
			if(nCost < nMinCost)
				nMinCost = nCost;					
		}
		String strMatrixName = substring;
		Matrix newMatrix = new Matrix(strMatrixName, nRows, nColumns, nMinCost);
		return newMatrix;
		
	}
	
	public int getMinMultiplicationCost()
	{
		
		for(int nLength=2;nLength<nMatrixCount+1;nLength++)
		{
			SubStringTokenizerOfLength subStrings = new SubStringTokenizerOfLength(strAllMatrixNames, nLength);
			while(subStrings.hasMoreElements())
			{
				String subString = subStrings.nextElement();
				// create new matrix with name & add it to hash
				Matrix newMatrix = processMatrix(subString);
				hashMap.put(newMatrix.strName, newMatrix);
				
			}	
		}
		Matrix matrix = hashMap.get(strAllMatrixNames);
		this.printMatrixHashMap();
		return matrix.nCost;
	}

}
