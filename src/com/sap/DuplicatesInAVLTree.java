package com.sap;

public class DuplicatesInAVLTree {

	// An AVL tree node
	static class node {
		int key;
		node left;
		node right;
		int height;
		int count;
	}
	
	//Function to get height of the tree
	static int height(node N) {
		if (N == null) {
			return 0;
		}else {
			return N.height;
		}
	}
	
	//Function to get max of two integers
	static int max(int a, int b) {
		return (a>b) ? a:b;
	}
	
	static node newNode(int key) {
		node node = new node();
		node.key = key;
		node.left = null;
		node.right = null;
		node.height = 1; 
		node.count = 1;
		return node;
	}
	
	static node insert (node node, int key) {
		if (node == null) {
			return newNode(key);
		}
		if(key == node.key) {
			(node.count)++;
			return node;
		}
		
		if(key < node.key) {
			node.left = insert(node.left, key);
		}
		else {
			node.right = insert(node.right, key);
		}
		
		//Update height of this ancestor node
		node.height = max(height(node.left), height(node.right)) + 1;
		
		//Get the balance factor of this ancestor node to check if it gets unbalanced
		int balance = getBalance(node);
		
		//If this node becomes unbalanced, then there are 4 cases
		
		//Left Left case
		if (balance > 1 && key < node.left.key) {
			return rightRotate(node);
		}
		
		// Right Right case
		if (balance < -1 && key < node.right.key) {
			return leftRotate(node);
		}
		
		//Left Right case
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		
		//Right Left case
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		
		return node;
	}
	
	private static node rightRotate(node y) {
		// TODO Auto-generated method stub
		node x = y.left;
		node T2 = x.right;
		
		//Perform rotation
		x.right = y;
		y.left = T2;
		
		//Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;
		
		return x;
	}

	private static node leftRotate(node x) {
		// TODO Auto-generated method stub
		node y = x.right;
		node T2 = y.left;
		
		//Perform rotation
		y.left = x;
		x.right = T2;
		
		//Update heights
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;
		
		return y;
	}

	private static int getBalance(node node) {
		// TODO Auto-generated method stub
		if(node == null)
			return 0;
		return height(node.left) - height(node.right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		node root = null;
		
		root = insert(root, 9);
		root = insert(root, 5);
		root = insert(root,10);
		root = insert(root, 5);
		root = insert(root, 9);
		root = insert(root, 7);
		root = insert(root, 17);
		root = insert(root, 20);
	}

}
