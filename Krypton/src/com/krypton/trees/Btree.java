package com.krypton.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Btree {

	private Node rootNode = null;
	
		
	
	public Btree(int... datalist)
	{
		if(datalist.length == 0)
		{
			rootNode = null;
			return;
		}
		
		List<Node> lstNode = new ArrayList<Node>();
		
		for(int index = 0; index < datalist.length; index++)
			lstNode.add(new Node(datalist[index]));
		
		for(int index = 0; index < (datalist.length/2); index++)
		{
			Node node = lstNode.get(index);
			node.left = lstNode.get(index * 2 + 1);
			if(datalist.length > ((index * 2)+ 2))
			{
				node.right = lstNode.get(index * 2 + 2);				
			}
		}
		
		rootNode = lstNode.get(0);	
		
	}
	
	public void printStats()
	{
		System.out.print("Inorder traversal:");
		printInOrderTraversal();
		System.out.println();
		
		System.out.print("Preorder traversal:");
		printPreOrderTraversal();
		System.out.println();
		
		System.out.print("Postorder traversal:");
		printPostOrderTraversal();
		System.out.println();
		
		System.out.print("Levelorder traversal:");
		printLevelOrderTraversal();
		System.out.println();
		
		System.out.print("ReverseLevelorder traversal:");
		printReverseLevelOrderTraversal();
		System.out.println();
		
		System.out.print("Zigzag traversal:");
		printZigZagTraversal();
		System.out.println();
		
		System.out.println("Height of tree is:" + height());
		System.out.println("Count of nodes in the tree is:" + nodeCount());
		System.out.println("Count of Leaf nodes in the tree is:" + leafNodeCount());
		System.out.println("Count of Full nodes in the tree is:" + fullNodeCount());
		System.out.println("Count of half nodes in the tree is:" + halfNodeCount());		
		System.out.println("Is the tree strict binary tree:" + isStrictBinaryTree().toString());
		System.out.println("Is the tree Full binary tree:" + isFullBinaryTree().toString());
		System.out.println("Is the tree Complete binary tree:" + isCompleteBinaryTree().toString());		
		System.out.println("Max element:" + getMaxElement());	
		System.out.println("Min element:" + getMinElement());	
		System.out.println("Tree has element 15: " + hasNode(15));	
		System.out.println("Last node is:" + getLastNode(rootNode).data);	
		System.out.println("parent node of last node is:" + getParentNode(getLastNode(rootNode)).data);
		System.out.println("Root to leaf nodes:\n");
		printRootToLeafNode();	

					
	}
	
	public Boolean isEmpty()
	{
		if(rootNode == null)
			return true;
		return false;
	}
	
	public void printTree()
	{
		printInOrderTraversal(rootNode);
	}
	
	public void printInOrderTraversal()
	{
		printInOrderTraversal(rootNode);
	}
	
	void printInOrderTraversal(Node node)
	{
		if(node == null)
			return;
		
		printInOrderTraversal(node.left);
		System.out.print(node.data + " ");		
		printInOrderTraversal(node.right);
	}
	
	public void printPreOrderTraversal()
	{
		printPreOrderTraversal(rootNode);
	}
	
	void printPreOrderTraversal(Node node)
	{
		if(node == null)
			return;
		System.out.print(node.data + " ");
		printPreOrderTraversal(node.left);
		printPreOrderTraversal(node.right);
	}
	
	public void printPostOrderTraversal()
	{
		printPostOrderTraversal(rootNode);
	}
	
	void printPostOrderTraversal(Node node)
	{
		if(node == null)
			return;
		printPostOrderTraversal(node.left);
		printPostOrderTraversal(node.right);
		System.out.print(node.data + " ");		
	}
	
	public void printLevelOrderTraversal()
	{
		printLevelOrderTraversal(rootNode);
	}
	
	void printLevelOrderTraversal(Node node)
	{
		if(node == null)
			return;
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(node);
		while(!queue.isEmpty())
		{
			Node tmpNode = queue.remove();
			System.out.print(tmpNode.data + " ");
			if(tmpNode.left != null)
				queue.add(tmpNode.left);
			if(tmpNode.right != null)
				queue.add(tmpNode.right);

		}
	}
	
	public void printReverseLevelOrderTraversal()
	{
		printReverseLevelOrderTraversal(rootNode);
	}
	
	void printReverseLevelOrderTraversal(Node node)
	{
		if(node == null)
			return;
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		queue.add(node);
		while(!queue.isEmpty())
		{
			Node tmpNode = queue.remove();
			stack.push(tmpNode);
			if(tmpNode.right != null)
				queue.add(tmpNode.right);
			if(tmpNode.left != null)
				queue.add(tmpNode.left);

		}
		while(!stack.empty())
		{
			Node tmpNode = stack.pop();
			System.out.print(tmpNode.data + " ");			
		}		
	}
	
	// level order traversal except the even level nodes are
	// printed from right to left
	
	// use 2 stacks currentLevel & nextLevel.
	// while process currentlevel move node to nextleve & at the end
	// swap currentlevel & nextlevel
	public void printZigZagTraversal()
	{
		printZigZagTraversal(rootNode);
	}
	
	void printZigZagTraversal(Node node)
	{
		if(node == null)
			return;
		Stack<Node> currentLevel = new Stack<Node>();
		Stack<Node> nextLevel = new Stack<Node>();
		Boolean bLeftToRight = true;
		currentLevel.push(node);
		while(!currentLevel.isEmpty())
		{
			Node tmp = currentLevel.pop();
			if(tmp != null)
			{
				System.out.print(tmp.data + " ");
				if(bLeftToRight)
				{
					if(tmp.left != null)
						nextLevel.push(tmp.left);
					if(tmp.right != null)
						nextLevel.push(tmp.right);
				}
				else
				{
					if(tmp.right != null)
						nextLevel.push(tmp.right);
					if(tmp.left != null)
						nextLevel.push(tmp.left);
				}			
			}
			if(currentLevel.isEmpty())
			{
				//swap current level with nextlevel
				bLeftToRight = (bLeftToRight == true) ? false : true;
				Stack<Node> tmpStack = new Stack<Node>();
				tmpStack = currentLevel;
				currentLevel = nextLevel;
				nextLevel = tmpStack;
			}			
		}
	}
	
	void printRootToLeafNode()
	{
		if(rootNode == null)
			return;
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(rootNode);
		printRootToLeafNode(nodes);
	}
	void printRootToLeafNode(ArrayList<Node> nodes)
	{
		Node node = nodes.get(nodes.size()-1);
		if(node.isLeafNode() == true)
		{
			for(Node tmpNode:nodes)
			{
				System.out.print(tmpNode.data + " ");
			}
			System.out.println();
		}
		if(node.hasLeftChild() == true)
		{
			nodes.add(node.left);
			printRootToLeafNode(nodes);			
		}
		if(node.hasRightChild() == true)
		{
			nodes.add(node.right);
			printRootToLeafNode(nodes);
		}
		nodes.remove(nodes.size()-1);
	}
	
	public Boolean getAncestors(Deque<Node> deque,int data)
	{
		if(rootNode == null)
			return false;
		Node node = getNode(data);
		if(node == null)
			return false;
		deque.add(rootNode);
		if(rootNode == node)
		{
			return true;
		}
		return getAncestors(deque, node);		
	}
	
	Boolean getAncestors(Deque<Node> deque,Node node)
	{
		
		Node tmpNode = deque.getFirst();
		if(tmpNode.left != null)
		{
			if(tmpNode.left == node)
			{
				//node found
				deque.addFirst(tmpNode.left);
				return true;
			}
			else
			{
				//node not found continue with left subtree
				deque.addFirst(tmpNode.left);
				Boolean bNodeFound = getAncestors(deque, node);
				if(bNodeFound == true)
					return true;
			}
		}
		if(tmpNode.right != null)
		{
			if(tmpNode.right == node)
			{
				//node found
				deque.addFirst(tmpNode.right);
				return true;
			}
			else
			{
				//node not found continue with left subtree
				deque.addFirst(tmpNode.right);
				Boolean bNodeFound = getAncestors(deque, node);
				if(bNodeFound == true)
					return true;
			}
		}
		// node not found on left & right subtree
		deque.removeFirst();
		return false;
	}
		
	public int height()
	{
		return height(rootNode);
	}

	int height(Node node)
	{
		if(node == null)
			return -1;
		
		int nLeftTreeHeight, nRightTreeHeight;
		
		nLeftTreeHeight = height(node.left);
		nRightTreeHeight = height(node.right);
		
		int maxHeight = nLeftTreeHeight > nRightTreeHeight ? nLeftTreeHeight :
							nRightTreeHeight;
		return maxHeight + 1;
	}
	
	public int nodeCount()
	{
		return nodeCount(rootNode); 
	}
	
	int nodeCount(Node node)
	{
		if(node == null)
			return 0;
		
		int nLeftTreeNodeCount,nRightTreeNodeCount;
		
		nLeftTreeNodeCount = nodeCount(node.left);
		nRightTreeNodeCount = nodeCount(node.right);
		
		return 1 + nLeftTreeNodeCount + nRightTreeNodeCount;
	}
	
	// nodes with 2 children
	public int fullNodeCount()
	{
		return fullNodeCount(rootNode);
	}
	
	int fullNodeCount(Node node)
	{
		if(node == null)
			return 0;
		int nFullNodeCount = 0;
		if(node.hasTwoChildren() == true)
			nFullNodeCount++;
		return nFullNodeCount + fullNodeCount(node.left) + fullNodeCount(node.right);
	}
	
	// nodes with 1 children
	public int halfNodeCount()
	{
		return halfNodeCount(rootNode);
	}
	
	int halfNodeCount(Node node)
	{
		if(node == null)
			return 0;
		int nHalfNodeCount = 0;
		if(node.hasOnlyOneChild() == true)
			nHalfNodeCount++;
		return nHalfNodeCount + halfNodeCount(node.left) + halfNodeCount(node.right);
	}
	
	public int leafNodeCount()
	{
		return leafNodeCount(rootNode);
	}
	
	int leafNodeCount(Node node)
	{
		if(node == null)
			return 0;
		if(node.isLeafNode() == true)
			return 1;
		 
		int nLeftTreeLeafNodeCount,inRightTreeLeafNodeCount;
		nLeftTreeLeafNodeCount = leafNodeCount(node.left);
		inRightTreeLeafNodeCount = leafNodeCount(node.right);
		
		return nLeftTreeLeafNodeCount + inRightTreeLeafNodeCount;		
	}
	
	// Each node should have zero or 2 children
	public Boolean isStrictBinaryTree()
	{
		return isStrictBinaryTree(rootNode);
	}
	
	Boolean isStrictBinaryTree(Node node)
	{
		if(node == null)
			return true;
		if(node.hasOnlyOneChild() == true)
			return false;
		if(isStrictBinaryTree(node.left) == false)
			return false;
		if(isStrictBinaryTree(node.right) == false)
			return false;
		
		return true;
	}
	
	//Each node has zero or 2 children & all leaf nodes  are at same level
	public Boolean isFullBinaryTree()
	{
		return isFullBinaryTree(rootNode);
	}
	
	Boolean isFullBinaryTree(Node node)
	{
		if(node == null)
			return true;
		if(isStrictBinaryTree(node) == false)
			return false;
		
		int nLeftTreeHeight,nRightTreeHeight;
		nLeftTreeHeight = height(node.left);
		nRightTreeHeight = height(node.right);
		
		if(nLeftTreeHeight == nRightTreeHeight)
			return true;
		
		return false;		
	}
	//Each node has zero or 2 children & all leaf nodes  are at same level or
	// left tree & right tree height should be h or h-1
	public Boolean isCompleteBinaryTree()
	{
		return isCompleteBinaryTree(rootNode);
	}
	
	Boolean isCompleteBinaryTree(Node node)
	{
		if(node == null)
			return true;
		if(isStrictBinaryTree(node) == false)
			return false;
		
		int nLeftTreeHeight,nRightTreeHeight;
		nLeftTreeHeight = height(node.left);
		nRightTreeHeight = height(node.right);
		
		if(nLeftTreeHeight == nRightTreeHeight)
			return true;
		
		if(nLeftTreeHeight  == (nRightTreeHeight + 1))
			return true;
		
		return false;		
	}
	
	public int getMaxElement()
	{
		return getMaxElement(rootNode);
	}
	
	int getMaxElement(Node node)
	{
		if(node == null)
			return Integer.MIN_VALUE;
		
		int nLeftTreeMaxElement,nRightTreeMaxElement;
		
		nLeftTreeMaxElement = getMaxElement(node.left);
		nRightTreeMaxElement = getMaxElement(node.right);
		
		return Math.max(node.data, Math.max(nLeftTreeMaxElement, nRightTreeMaxElement));		
	}
	
	public int getMinElement()
	{
		return getMinElement(rootNode);
	}
	
	int getMinElement(Node node)
	{
		if(node == null)
			return Integer.MAX_VALUE;
		
		int nLeftTreeMinElement,nRightTreeMinElement;
		
		nLeftTreeMinElement = getMinElement(node.left);
		nRightTreeMinElement = getMinElement(node.right);
		
		return Math.min(node.data, Math.min(nLeftTreeMinElement, nRightTreeMinElement));		
	}
	
	public Boolean hasNode(int data)
	{
		return hasNode(rootNode,data);
	}
	
	Boolean hasNode(Node node,int data)
	{
		if(node == null)
			return false;		
	
		if(node.data == data)
			return true;
		if(hasNode(node.left, data) == true)
			return true;
		if(hasNode(node.right, data) == true)
			return true;
		return false;
	}
	
	public Node getNode(int data)
	{
		return getNode(rootNode,data);
	}
	
	Node getNode(Node node,int data)
	{
		if(node == null)
			return null;		
	
		if(node.data == data)
			return node;
		Node requiredNode;
		requiredNode = getNode(node.left, data);
		if(requiredNode != null)
			return requiredNode;
		
		requiredNode = getNode(node.right, data);		
		if(requiredNode != null)
			return requiredNode;
		
		return null;
	}
	
	public void swapNodes(Node node1,Node node2)
	{
		if((node1 == null) || (node2 == null))
			return ;
		if(node1 == node2)
			return;
		int tmp;
		tmp = node1.data;
		node1.data = node2.data;
		node2.data = tmp;
		return;
	}
	// do level order traversal & add node to the end
	public void addNode(int data)
	{
		Queue<Node> queue = new LinkedList<Node>();
		Node newNode = new Node(data);
		if(rootNode == null)
		{
			rootNode = newNode;
			return;
		}
		queue.add(rootNode);
		while(!queue.isEmpty())
		{
			Node tmpNode = queue.remove();
			if(tmpNode.left == null)
			{
				tmpNode.left = newNode;
				break;
			}
			else
			{
				queue.add(tmpNode.left);
			}
			if(tmpNode.right == null)
			{
				tmpNode.right = newNode;
				break;
			}
			else
			{
				queue.add(tmpNode.right);
			}				
		}		
	}
	
	public Node removeNode(int data)
	{
		Node nodeTobeRemoved = getNode(data);
		if(nodeTobeRemoved == null)
			return null;
		return removeNode(nodeTobeRemoved);
	}
	public Node removeNode()
	{
		Node lastNode = getLastNode();
		if(lastNode == null)
			return null;
		return removeNode(lastNode);
	}
	
	Node removeNode(Node node)
	{
		if(node == null)
			return null;
		Node lastNode = getLastNode();
		swapNodes(lastNode,node);
		Node parentNode = getParentNode(lastNode);
		if(parentNode == null)
			return null;
		if(parentNode.left == lastNode)
			parentNode.left = null;
		if(parentNode.right == lastNode)
			parentNode.right = null;
		return lastNode;
		
	}
	
	// Do level order traversal & return last node
	public Node getLastNode()
	{
		return getLastNode(rootNode);
	}
	Node getLastNode(Node node)
	{
		if(node == null)
			return null;
		
		Queue<Node> queue = new LinkedList<Node>();
		Node lastNode = null;
		queue.add(node);
		while(!queue.isEmpty())
		{
			Node tmpNode = queue.remove();
			if(tmpNode.left != null)
				queue.add(tmpNode.left);
			if(tmpNode.right != null)
				queue.add(tmpNode.right);
			lastNode = tmpNode;
		}
		return lastNode;
	}
	
	public Node getParentNode(Node node)
	{
		if(node == null)
			return null;
		if(rootNode == node)
			return null;
		return getParentNode(rootNode, node);
	}
	
	Node getParentNode(Node subTreeRoot, Node node)
	{
		if((subTreeRoot.left == node) || (subTreeRoot.right == node))
			return subTreeRoot;
		
		Node parentNode;
		if(subTreeRoot.left != null)
		{
			parentNode = getParentNode(subTreeRoot.left, node);
			if(parentNode != null)
				return parentNode;
		}
		if(subTreeRoot.right != null)
		{
			parentNode = getParentNode(subTreeRoot.right, node);
			if(parentNode != null)
				return parentNode;
		}	
		return null;
		
	}
	void deepestNode(Node inputNode, Node deepestNode, int depth)
	{
	 //Todo
	}
	// Diameter of the tree is the max distance between 2 nodes
	void diameter()
	{
		
	}
	
	public int leastCommonAncestor(int data1, int data2)
	{
		Node node1 = getNode(data1);
		if(node1 == null)
			return Integer.MIN_VALUE;
		Node node2 = getNode(data2);
		if(node2 == null)
			return Integer.MIN_VALUE;
		Node lca = leastCommonAncestor(node1, node2);
		return lca.data;
		
	}
	 Node leastCommonAncestor(Node node1, Node node2)
	{
		Node lca = null;
		// get the root to node(ancestors) for both the nodes
		// from both the list of nodes, LCA can be computed
		Deque<Node> deque1 = new ArrayDeque<Node>();
		Deque<Node> deque2 = new ArrayDeque<Node>();
		
		getAncestors(deque1, node1.data);
		getAncestors(deque2, node2.data);
		 
		
		Node prev;
		lca = prev= deque1.getLast();
		Node tmp1 = deque2.getLast();
		while((deque1.removeLast() == deque2.removeLast()))
		{
			lca = prev;
			if(deque1.isEmpty() || deque2.isEmpty())
				break;
			
			prev = deque1.getLast();
			continue;
		}
		return lca;
	}
}
