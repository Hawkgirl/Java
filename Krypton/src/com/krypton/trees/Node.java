package com.krypton.trees;

public class Node {
	
	Node left;
	Node right;
	int data;
	
	Node()
	{
		
	}
	
	Node(int data)
	{
		this.data = data;
	}	
	
	public int getData()
	{
		return data;
	}
	public Boolean hasTwoChildren()	
	{
		if((left != null) && (right != null))
			return true;
		return false;
	}
	
	public Boolean isLeafNode()
	{
		if(left == null && right == null)
			return true;
		return false;
	}
	
	public Boolean hasLeftChild()
	{
		if(left != null)
			return true;
		return false;
	}
	
	public Boolean hasRightChild()
	{
		if(right != null)
			return true;
		return false;
	}
	
	public Boolean hasOnlyLeftChild()
	{
		if(hasLeftChild() == true && hasRightChild() == false)
			return true;
		return false;
	}
	
	public Boolean hasOnlyRightChild()
	{
		if(hasRightChild() == true && hasLeftChild() == false)
			return true;
		return false;
	}
	
	public Boolean hasOnlyOneChild()
	{
		if(hasOnlyLeftChild() == true)
			return true;
		if(hasOnlyRightChild() == true)
			return true;
		return false;
	}
	
	
}
