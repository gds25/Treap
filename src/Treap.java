// Gabriel Souza

import java.util.*;

public class Treap<E extends Comparable<E>> {

	private Random priorityGenerator; 
	private Node<E> root; 
	
	/**
	* 
	* Creates a treap (randomized binary search tree) with max-heap order; 
	* initializes  a root node to store the treap's root and 
	* a random number generator to randomly assign priority numbers to nodes
	*
	*/
	public Treap() {
		// TODO Auto-generated constructor stub
		this.root = null;
		priorityGenerator = new Random();
	}
	
	/**
	* 
	* Creates a treap; 
	* the random number generator is assigned a seed number
	*
	*/
	public Treap(long seed) {
		this.root = null;
		priorityGenerator = new Random(seed); 
	}
	
	/**
	* add() Wrapper method ; see add(E key, int priority) method
	* 
	* @param  key  the node's data to be added to the tree
	* @return      true if node was properly added to tree; 
	* 			   false if node data or priority already is in tree
	*/
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt(100));
	}
	
	/**
	* 
	* Adds a node at as a leaf, ie. at the bottom of the treap
	* 
	* @param  key  the node's data to be added to the tree
	* @param  priority  the node's priority number (either user input or randomly generated)
	* @return      true if node was properly added to tree; 
	* 			   false if node data already is in tree, or if duplicate priority exists within that node's ancestors/children
	*/
	boolean add(E key, int priority) {
		if (key == null || priority < 0) {
			throw new IllegalArgumentException();
		}
		
		Node<E> newNode = new Node<E>(key, priority);
		if (this.root == null) {
			this.root = newNode;
			return true;
		}

		Stack <Node<E>> path = new Stack <Node<E>>();
		Node<E> currNode = this.root;
		path.push(currNode);
		
		Node<E> prevNode = null;
		while (currNode != null) {
			prevNode = currNode;
			if (currNode.Data == key || currNode.priority == priority) {
				return false;
			}
			else if ((currNode.Data).compareTo(key) < 0) {
				currNode = currNode.right;
			}
			else {
				currNode = currNode.left;
			}
			path.push(prevNode);
		}
		
		path.pop();
		if (prevNode.Data.compareTo(key) < 0) prevNode.right = newNode;
		else if (prevNode.Data.compareTo(key) > 0) prevNode.left = newNode;
		
		path.push(prevNode);
		
		this.root = reheap(newNode, path);
		
		return true;
	}
	
	/**
	* 
	* Converts the binary tree to a max-heap treap, 
	* with higher priority nodes higher up the tree
	* <p>
	* Moves a node up the tree according to its priority
	* 
	* @param  leaf  the node being moved
	* @param  path  the node's path up the tree, ending at the root
	* @return      the updated treap's root node
	*/
	private Node<E> reheap(Node <E> leaf, Stack <Node<E>> path) {
		while (!path.isEmpty()) {
			Node<E> root = path.pop();
			
			if (leaf.priority <= root.priority) {
				break;
			}
			else {
				if (root.right == leaf) {

					leaf = root.rotateLeft();
				}
				else if (root.left == leaf) {

					leaf = root.rotateRight();
				}
			}
		}
		if (leaf.priority > root.priority) return leaf;
		return root;
	}
	
	/**
	* 
	* Deletes a node from the tree by shifting it downwards, 
	* converting it to a leaf where it can "fall" from the tree
	* 
	* @param  key  the node's data to be added to the tree
	* @param  priority  the node's priority number (either user input or randomly generated)
	* @return      true if node was properly removed from tree; 
	* 			   false if node is not in tree
	*/
	boolean delete(E key)  {
		if (this.root == null) {
			throw new IllegalArgumentException();
		}
		
		Node<E> currNode = this.root;
		
		while (currNode != null && currNode.Data != key) {
			if ((currNode.Data).compareTo(key) < 0) {
				currNode = currNode.right;
			}
			else if ((currNode.Data).compareTo(key) > 0) {
				currNode = currNode.left;
			}
		}
		
		if (currNode == null) {
			return false;
		}
		
		Node<E> prevNode = null;
		
		while (currNode.left != null || currNode.right != null) {
			prevNode = currNode;
			if (currNode.left == null) {
				currNode = prevNode.rotateLeft();
				currNode = currNode.left;
			}
			else if (currNode.right == null) {
				currNode = prevNode.rotateRight();
				currNode = currNode.right;
			}
			else if  (currNode.left.priority >= currNode.right.priority) {
				currNode = prevNode.rotateRight();
				currNode = currNode.right;
			}
			else {
				currNode = prevNode.rotateLeft();
				currNode = currNode.left;
			}
		}
		
		if (prevNode.left == currNode) prevNode.left = null;
		else prevNode.right = null;
		
		return true;
	}
	
	/**
	* 
	* Searches for a particular node in the tree (rooted at a given root)
	* according to its key
	* 
	* @param  root  the node to start to search at
	* @param  key  the node data to be added searched for
	* @return      true if node was found; 
	* 			   false otherwise
	*/
	private boolean find(Node<E> root,  E key) {
		if (root == null || key == null) {
			throw new IllegalArgumentException();
		}
		if (root.Data == key) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return false;
		}
		
		if (key.compareTo(root.Data) < 0 && root.left != null) {
			return find(root.left, key);
		}
		
		return find(root.right, key);

	}

	/**
	* find() Wrapper method ; see find(Node<E> root,  E key) method
    *
	* @param  key  the node data to be added searched for
	* @return      true if node was found; 
	* 			   false otherwise
	*/
	public boolean find(E key) {
		return find(this.root, key);
	}
       
	/**
	* toString() Wrapper method ; see toString(Node<E> curr,  String level) method
    *
	* @return      the treap in String form
	*/
	public String toString() {
		return toString(root, "");
	}
	
	/**
	* Returns a String representation of the treap; 
	* the String representation of each node is provided on its own line; 
	* and is indented according to its height (level) within the tree
	* <p>
	* Each  node  with  key  k  and  priority  p,  left  child  l,  and  right  child  r  is 
	* represented as the string "[k, p] --> newline (l) --> newline (r)"
	* <p>
	* Node children (l) and (r) may be represented with "null" if the child does not exist
	*
	* @param  curr  the node currently being converted to string
	* @param  level  the current height, or level, of node 'curr'
	* @return      the treap in String form
	*/
	public String toString(Node<E> curr, String level) {
		if (this.root == null) {
			throw new IllegalArgumentException();
		}
		if (curr == null) {
			System.out.println(level + (String) null);
			return null;
		}
		
		System.out.println(level + curr.toString());
		
		toString(curr.left, "    " + level);
		toString(curr.right, "    " + level);
		
		return curr.toString();
	}

	private static class Node<E> {
		E Data; // key for the search
		int priority; // random heap priority
		Node<E> left;
		Node<E> right;
		
		/**
		* 
		* Creates a new node to store the given data.
		*
		* @param  data the data of the node
		* @param  priority the node's priority number
		*/
		Node (E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			}
			
			this.Data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		/**
		* 
		* Rotates the current node object and its children to the right;
		* ie. The current node's left child becomes the parent node, and the current node
		* becomes the new parent node's right child
		*
		* @return  the new parent (root) node
		*
		*/
		Node<E> rotateRight() {
			
			Node<E> tempRight = right;
			right = new Node<E>(Data, priority);
			if (tempRight != null) right.right = tempRight;
			
			this.Data = left.Data;
			this.priority = left.priority;
			
			if (left.right != null) right.left = left.right;
			
			if (left.left != null) this.left = left.left;
			else left = null;
			
			return this;
		}
		
		/**
		* 
		* Rotates the current node object and its children to the left;
		* ie. The current node's right child becomes the parent node, and the current node
		* becomes the new parent node's left child
		*
		* @return  the new parent (root) node
		*
		*/
		Node<E> rotateLeft() {
			Node<E> tempLeft = left;
			left = new Node<E>(Data, priority);
			
			if (tempLeft != null) left.left = tempLeft;
			this.Data = right.Data;
			this.priority = right.priority;

			if (right.left != null) left.right = right.left;
			
			if (right.right != null) this.right = right.right;
			else right = null;
			
			return this;
		}
		
		/**
		* Returns a String representation of the node in the form of:
		* <p>
		* "(key = x, priority = y)"
		* @return      the (key, priority) pair in String form
		*/
		public String toString() {
			return "(key = " + Data + ", priority = " + priority + ")";
		}
		
	}
}	
