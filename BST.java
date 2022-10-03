/**
 * This is a program is a simple data-structure implementation
 * of a binary search tree. 
 * @author Austin Daigle
 * @version 1.0
 * @since 4/13/2021
 */


public class BST {
	
	//Create the attributes that will be used in the core BST class
	private Node root;	//BST root attribute
	
	//This is the nested class used for making BST objects.
	class Node {
		/**
		 * This is the default constructor for the binary 
		 * search tree class. This default constructor makes
		 * the nodes and tree objects that are created.
		 * @param data This is the field that stores the data
		 * within each node.
		 */
		private Node(int data) {
			this.data = data;
		}
		
		/*
		 * Create the private attributes for each node 
		 * and BST object
		 * data is for storing node data.
		 * left is for the left node refference.
		 * right is for the right node reffernece.
		 */
		private int data;
		private Node left;
		private Node right;
	} //End of nested NODE class
	
	//Binary Search Tree object constructor
	public BST() {
		this.root = null;
	}
	
	/**
	 * This is a interface method for the user
	 * to be able to a add new nodes starting from
	 * the root node. 
	 * @param data	This is the parameter that is used
	 * to adding data into the new Nodes into the BST.
	 * @throws Exception ???
	 */
	public void insert(int data) throws Exception
	{
		//This method handle empty lists
		if(this.root == null) {
			this.root = new Node(data);
		}
		//If the tree is populated, then start from the root
		//Recurse if needed. 
		else
		{
			insert(this.root, data);
		}
	}
	
	/**
	 * This is a recursive helper method that 
	 * inserts nodes on the left or right 
	 * side a BST branch. 
	 * @param recursionNode NODE object reffernce
	 * @param data This parameter stores the data that is used to 
	 * create new nodes. 
	 * @throws Exception ???
	 */
	private void insert(Node recursionNode, int data) throws Exception {
		if(recursionNode == null) {
			throw new Exception("Error: ???");
		}
		//insert a node to the right side of the branch
		if(recursionNode.data < data) {
			//If the right side of the node is not null
			if(recursionNode.right != null) {
				/*
				 * call (recurse) the function with the 
				 * recursionNode node right parameter and the 
				 * data field. 
				 */
				insert(recursionNode.right, data);
			}
			else {
				/*
				 * create set the recursionNode node's right field to 
				 * the refference of a new Node object call with data as 
				 * as input.
				 */
				recursionNode.right = new Node(data);
			}
		}
		//insert a node to the left side of the branch
		else if(recursionNode.data > data) {
			/*
			 * call (recurse) the function with the 
			 * recursionNode node left parameter and the 
			 * data field. 
			 */
			if(recursionNode.left != null) {
				insert(recursionNode.left, data);
			}
			else {
				/*
				 * create set the recursionNode node's left field to 
				 * the refference of a new Node object call with data as
				 * as input.
				 */
				recursionNode.left = new Node(data);
			}
		}
	}
	
	/**
	 * This method returns the int value of the node at the 
	 * element value. 
	 * @param k This is the element value to check for the BST object
	 * @return Returns a integer output from the Kth element
	 * @throws Exception throws an exception if the index is out of bounds.
	 */
	public int findKth(int k) throws Exception {
		//if the tree is empty through an exception
		if(this.root == null) {
			throw new Exception("Error: There are no nodes in populated in this binary search tree.");
		}
		//if the inded if output of bounds then throw an exception
		else if (k < 0 || k > size()) {
			throw new Exception("Error: OutOfBoundsExceptions");
		}
		else
		{
			//pull data from the order that the tree generated as.
			String rawTreeData = this.preOrder();
			//create a new string with a regedit for empty spaces
			String[] processRawTreeData = rawTreeData.split(" ");
			//create a new int array that is the length of the regedited string.
			int[] allElements = new int[processRawTreeData.length];
			//for the length of the regedited string
			for(int i = 0; i < processRawTreeData.length; i++) {
				//add the elements from the string array into the int array.
				allElements[i] = Integer.parseInt(processRawTreeData[i]);
			}
			//return the int at the index of the given value of k. 
			return allElements[k];
		}
	}
	
	/**
	 * This method returns the doPreOrder data of this.root.
	 * @return return the doPreOrder data of root data.
	 */
	public String preOrder() {
		return doPreOrder(this.root);
	}
	
	/**
	 * This is a helper method that perform a recursion
	 * iteration through the pre-Ordered iteration order.
	 * @param this is the node object that is iterated 
	 * through or input by another method.
	 * @return returns the string value of a given node.
	 */
	private String doPreOrder(Node recursiveNode) {
		//if the node object is not equal to null
		if(recursiveNode != null) {
			//return a string of data and the left/right attributes.
			return recursiveNode.data + " "
			+ doPreOrder(recursiveNode.left)
			+ doPreOrder(recursiveNode.right);
		}
		//if the node object is null then return a blank string.
		else {
			return "";
		}
	}
	
	/**
	 * This method returns the numerically ordered version of the 
	 * bst tree.
	 * @return returns a string version of the bst tree numerically ordered.
	 */
	public String inOrder() {
		return doInOrder(this.root);
	}
	
	/**
	 * This is a private method that iterates through the node 
	 * object and returns a string value of the data and left/right
	 * for the bst tree. 
	 * @param recursiveNode this is the node that is recursed through and 
	 * its data is recursed through.
	 * @return Returns the string data of the node data and the left/right 
	 * refference. 
	 */
	public String doInOrder(Node recursiveNode) {
		//if the node is not equal to null
		if(recursiveNode != null) {
			//return the left, data, and right data as a string.
			return doInOrder(recursiveNode.left)
			+ recursiveNode.data + " " +
			doInOrder(recursiveNode.right);			
		}
		//if the node is equal to null and return a empty string. 
		else {
			return "";
		}
	}
	
	/**
	 * This method deletes a node at a given key value,
	 * this method functions on a private helper method. 
	 * @param key This is the index value that points to
	 * the node to be deleted.
	 */
	public void remove(int key) {
		root = remove(root, key);
	}
	
	/**
	 * This is a private method that removes the node at a given
	 * key value.
	 * @param root This is the node object that is iterated through
	 * for the sake of removing the indicated key.
	 * @param key This is the int value that indicates the position of 
	 * of the node to be removed.
	 * @return returns a node for the sake of iteration.
	 */
	private Node remove(Node root, int key) {
		//If the tree is empty then return the root node value.
		if(root == null) {
			return root;
		}
		/* 
		 * Iterate/traverse throughout the tree
		 * If key is less than root.data then update the root's left 
		 * refference, then recurse the function.
		 */
		if(key < root.data) {
			root.left = remove(root.left, key);
		}
		/**
		 * If the key value of larger than the root.data then update the 
		 * root's right refference, the recurse the function as 
		 * a part of the method call.
		 */
		else if(key > root.data) {
			root.right = remove(root.right, key);
		}
		else {
			/**
			 * If the root's left refference is null then
			 * return the root's right refference. 
			 */
			if(root.left == null) {
				return root.right;
			}
			/*
			 * If the root's right reffernce is null then 
			 * return the root's left refference.
			 */
			else if(root.right == null) {
				return root.left;
			}
			/*
			 * Update the root's left data refference to reflect the 
			 * smallest value under the parameter of the root's right 
			 * address refference.
			 */
			root.data = minValue(root.right);
			/*
			 * Update the root's right data refference to reflect 
			 * the result of the Node object after the removal 
		     * method has been called with root.right nad root.left 
		     * as parameters.
			 */
			root.right = remove(root.right, root.data);
		}
		//return the root Node object the product of the process.
		return root;
	}
	
	/**
	 * This method returns the boolean status if the 
	 * BST is empty
	 * @return returns a boolean indicating if a BST object is empty.
	 */
	public boolean isEmpty()
	{
		return root == null;
	}
	
	/**
	 * This method returns the lowest int value 
	 * from the BST object.
	 * @param root This is the base Node root object refference used
	 * to sort through the tree.
	 * @return returns the lowest int value in the tree.
	 */
	public int minValue(Node root) {
		//create a value at default to be set to root.
		int minimumValue = root.data;
		//Iterate through tree to find the min value.
		while(root.left != null) {
			//during iteration, cycle and update to lowest value.
			minimumValue = root.left.data;
			root = root.left;
		}
		//return the final lowest value from the tree.
		return minimumValue;
	}
	
	/**
	 * This method returns the highest value found 
	 * in the bst object
	 * @param root This is the Node object that is a paramter 
	 * for iteration.
	 * @return returns the max int value found in the tree.
	 */
	private int maxValue(Node root) {
		//Create a value at default to be set to the root.
		int maximumValue = root.data;
		//Iterate through tree to find the max value.
		while(root.right != null) {
			//during iteration, cycle and update to the highest value.
			maximumValue = root.right.data;
			root = root.right;
		}
		//return the final highest value from the tree. 
		return maximumValue;
	}
	
	/**
	 * This method returns the integer value of the 
	 * the size of the bst object. 
	 * @return return the size of the binary
	 * search tree object in refference. 
	 */
	public int size() {
		//If root equals null then return zero
		if(this.root == null) {
			return 0;
		}
		//otherwise return the whole tree size value.
		return size(this.root);
	}
	
	/**
	 * This method returns both the height of the 
	 * entire binary search tree object or all of the 
	 * length at a specific branch downwards at a given key index.
	 * If you set the paramter to the first key then the entire 
	 * tree's height is returns, at any other key index and that 
	 * returns the height that branch downwards.
	 * @param recursiveNode this is the node object that is going 
	 * to be analyzed for size at a given index.
	 * @return returns the height of the tree as a whole or a branch.
	 */
	private static int size(Node recursiveNode) {
		//If the node is null then return zero.
		if(recursiveNode == null) {
			return 0;
		}
		else {
			/*
			 * return the sum of one plus the recursions of size() with
			 * respect to the left and right nodes. This recursively 
			 * iterates through the tree inspecting every branch, the
			 * base case if met when there are not other nodes 
			 * to start iterations for.
			 */
			return 1 + size(recursiveNode.left) + size(recursiveNode.right);
		}
	}
	
	/**
	 * This method searched for the given integer and if the int is 
	 * found the the memory reffernce is given, else then null
	 * is returned.
	 * @param searchFor This is the int that is returned.
	 * @param treeObject This is the node object that is seached through.
	 * @return returns the found entry memory address.
	 */
	public static Node search(int searchFor, Node treeObject) {
		//If the bst object is null or the object data equal searchFor
		if(treeObject == null || treeObject.data == searchFor) {
			//then return tree object.
			return treeObject;
		}
		//If the treeObject's data is larger than searchFor
		else if(treeObject.data > searchFor) {
			/*
			 * return the cursion with parameters adjusting for the right
			 * reffernece address.
			 */
			return search(searchFor, treeObject.right);	
		}
		else {
			/*
			 * return the cursion with parameters adjusting for the right
			 * reffernece address.
			 */
			return search(searchFor, treeObject.left);
		}
	}
	
	/**
	 * This methods searched through the bst and returns the 
	 * boolean result if the given key is given or not.
	 * @param key this is the entry that is being searachd for.
	 * @return returns the boolean result if the given input 
	 * is present or not. 
	 */
	public boolean find(int key) {
		//update root to the recursion to the parameter root and key.
		root = find(root, key);
		//if the root is not null then return true
		if(root != null) {
			return true;
		}
		else {
			//else return false
			return false;
		}
	}
	
	/**
	 * This is the helper method that allows for
	 * iteration and verifcation of if the given integer 
	 * value is present in the bst object.
	 * @param root This is the node being searched through.
	 * @param key This is the value of the object being searched for.
	 * @return returns a node object memory address value of the given 
	 * found entry.
	 */
	private Node find(Node root, int key) {
		//if root equals null or if root.data equals key
		if(root == null || root.data == key) {
			//return root
			return root;
		}
		//if root.data is greater than key then recurse with the left parameter.
		if(root.data > key) {
			return find(root.left, key);
		}
		//return recusion of the node parameter in the right parameter.
		return find(root.right, key);
	} //end of BST class

	//Driver method
    public static void main(String[] args) throws Exception {
        BST t = new BST(); //create a BST

        //insert some data into the tree
        t.insert(5);
        t.insert(2);
        t.insert(1);
        t.insert(3);
        t.insert(7);

        //print out the tree
        System.out.println("In original order:");
        System.out.println(t.preOrder()); //expected 5 2 1 3 7
        System.out.println("In numerical order:");
        System.out.println(t.inOrder()); //expected 1 2 3 5 7

        System.out.println("Whole tree size: " + t.size());
        System.out.println("Minimum Value: " + t.minValue(t.root));
        System.out.println("Maximum Value: " + t.maxValue(t.root));
        System.out.println("Branch Height at root: " + size(BST.search(5, t.root)));
        System.out.println("Branch Height at entry 7: " + size(BST.search(7, t.root)));
        System.out.println("find 5 in tree: " + t.find(5));
        System.out.println("find 5's memory address in tree: " + BST.search(5, t.root));
        System.out.println("Delete 5 from the tree:");
        t.remove(5);
        System.out.println(t.preOrder()); //expected 5 2 1 3 7
        System.out.println("Tree size: " + t.size());
        System.out.println("find the entry at index 1: "+t.findKth(1));


    } //end of driver class
}


