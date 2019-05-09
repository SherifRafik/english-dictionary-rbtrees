package model.rbtrees;

public class RedBlackTree {

	private Node root;
	private Node nil = new Node("nil");
	private int size;

	public RedBlackTree() {
		nil.setColor(Node.Color.BLACK);
		nil.setParent(nil);
		nil.setLeft(nil);
		nil.setRight(nil);
		this.root = nil;
		size = 0;
	}


	/*
	 * Properties violated are 
	 * 1 - Property 2 which requires the root to be black 
	 * 2 - Property 4 which requires a red node to have 2 black children
	 */

	public boolean search(String key) {
		return search(key, root) != nil;
	}

	private Node search(String data, Node node) {
		if (node == nil)
			return nil;
		if (node.getKey().equalsIgnoreCase(data))
			return node;
		else if (node.getKey().compareToIgnoreCase(data) > 0) {
			if (node.getLeft() != nil)
				return search(data, node.getLeft());
		} else if (node.getKey().compareToIgnoreCase(data) < 0) {
			if (node.getRight() != nil)
				return search(data, node.getRight());
		}
		return nil;
	}

	public boolean insert(String data) {
		Node newNode = new Node(data);
		newNode.setLeft(nil);
		newNode.setRight(nil);
		newNode.setParent(nil);

		Node node = this.root;
		Node temp = nil;

		while (node != nil) {
			temp = node; // Get leaf node
			int compareTwoStrings = newNode.getKey().compareToIgnoreCase(node.getKey());
			if (compareTwoStrings < 0)
				node = node.getLeft();
			else if (compareTwoStrings > 0)
				node = node.getRight();
			else
				return false;
		}

		newNode.setParent(temp);
		if (temp == nil)
			this.root = newNode;
		else if (newNode.getKey().compareToIgnoreCase(temp.getKey()) < 0)
			temp.setLeft(newNode);
		else
			temp.setRight(newNode);

		insertFixup(newNode);
		this.size++;
		return true;
	}

	private void insertFixup(Node node) {
		Node uncle;
		while (node.getParent() != nil && node.getParent().getColor() == Node.Color.RED) {
			if (node.getParent() == node.getParent().getParent().getLeft()) {
				uncle = node.getParent().getParent().getRight();
				if (uncle != nil && uncle.getColor() == Node.Color.RED) { // Case 1
					node.getParent().setColor(Node.Color.BLACK);
					uncle.setColor(Node.Color.BLACK);
					node.getParent().getParent().setColor(Node.Color.RED);
					node = node.getParent().getParent();
				} else {
					if (!node.isLeftChild()) { // Double rotation needed
						node = node.getParent();
						rotateLeft(node);
					}
					node.getParent().setColor(Node.Color.BLACK);
					node.getParent().getParent().setColor(Node.Color.RED);
					rotateRight(node.getParent().getParent());
				}
			} else {
				uncle = node.getParent().getParent().getLeft();
				if (uncle != nil && uncle.getColor() == Node.Color.RED) { // Case 1
					node.getParent().setColor(Node.Color.BLACK);
					uncle.setColor(Node.Color.BLACK);
					node.getParent().getParent().setColor(Node.Color.RED);
					node = node.getParent().getParent();
				} else {
					if (node.isLeftChild()) {
						node = node.getParent();
						rotateRight(node);
					}
					node.getParent().setColor(Node.Color.BLACK);
					node.getParent().getParent().setColor(Node.Color.RED);
					rotateLeft(node.getParent().getParent());
				}
			}
		}
		this.root.setColor(Node.Color.BLACK);
	}

	private void rotateLeft(Node parentNode) {
		Node childNode = nil;
		if (parentNode.getRight() != nil)
			childNode = parentNode.getRight();

		parentNode.setRight(childNode.getLeft());
		if (childNode.getLeft() != nil)
			childNode.getLeft().setParent(parentNode);

		childNode.setParent(parentNode.getParent());
		if (parentNode.getParent() == nil)
			root = childNode;
		else if (parentNode.isLeftChild())
			parentNode.getParent().setLeft(childNode);
		else
			parentNode.getParent().setRight(childNode);

		childNode.setLeft(parentNode);
		parentNode.setParent(childNode);
	}

	private void rotateRight(Node parentNode) {
		Node childNode = nil;
		if (parentNode.getLeft() != nil)
			childNode = parentNode.getLeft();

		parentNode.setLeft(childNode.getRight());
		if (childNode.getRight() != nil)
			childNode.getRight().setParent(parentNode);

		childNode.setParent(parentNode.getParent());
		if (parentNode.getParent() == nil)
			root = childNode;
		else if (parentNode.isLeftChild())
			parentNode.getParent().setLeft(childNode);
		else
			parentNode.getParent().setRight(childNode);

		childNode.setRight(parentNode);
		parentNode.setParent(childNode);
	}

	public boolean delete(String value) {
		Node node = getNode(value);
		if (node == nil)
			return false;
		performDelete(node);
		// Decrement the size of the tree
		size--;
		return true;
	}

	private void performDelete(Node node) {

		// If the node has 2 children
		// Copy its inorder successor in it
		// Then recursively delete the successor
		if (node.getLeft() != nil && node.getRight() != nil) {
			Node successor = inOrderSuccessor(node);
			node.setKey(successor.getKey());
			performDelete(successor);
			return;
		}

		// Reaching this line means that the node
		// EITHER has one child
		// OR is a leaf node
		Node child = nil;
		if (node.getLeft() != nil)
			child = node.getLeft();
		else if (node.getRight() != nil)
			child = node.getRight();

		// If node is red
		// Simple Case: either the node or its child is red
		if (node.getColor() == Node.Color.RED) { // The node is red and its child is black
			replaceNode(node, child);
			return;
		}

		// If node is black
		if (node.getColor() == Node.Color.BLACK) {

			// AGAIN The Simple Case
			if (child.getColor() == Node.Color.RED) { // The node is black and its child is red
				child.setColor(Node.Color.BLACK);
				replaceNode(node, child);
				return;
			}

			// Reaching this line means both the node and its child are black
			// DOUBLE BLACK notation is to be used
			child.setDoubleBlack(true);
			replaceNode(node, child);
			fixDoubleBlack(child);
		}
	}

	private void fixDoubleBlack(Node node) {

		if (node == root) {
			node.setDoubleBlack(false);
			return;
		}

		Node sibling;
		try {
			sibling = node.getSibling();
		} catch (Exception e) {
			sibling = nil;
		}
		while (node.isDoubleBlack() && node != root) {

			// Sibling is RED
			if (sibling.getColor() == Node.Color.RED) {
				// perform rotations
				sibling.setColor(Node.Color.BLACK);
				node.getParent().setColor(Node.Color.RED);
				if (sibling.isLeftChild()) {
					// left case
					// right rotate the parent
					rotateRight(node.getParent());
				} else {
					// right case
					// left rotate the parent
					rotateLeft(node.getParent());
				}
				node.setDoubleBlack(false);
			}

			// Sibling is black and both its children are black
			else if (sibling.getLeft().getColor() == Node.Color.BLACK
					&& sibling.getRight().getColor() == Node.Color.BLACK) {
				sibling.setColor(Node.Color.RED);
				if (node.getParent().getColor() == Node.Color.RED) {
					node.getParent().setColor(Node.Color.BLACK);
					node.setDoubleBlack(false);
					return;
				} else {
					sibling.getParent().setDoubleBlack(true);
					node.setDoubleBlack(false);
					fixDoubleBlack(sibling.getParent());
				}
			}

			// Sibling is black and at least one of its children is red
			else {
				if (sibling.isLeftChild()) {
					if (sibling.getLeft().getColor() == Node.Color.RED) { // left left
						sibling.getLeft().setColor(Node.Color.BLACK);
						rotateRight(node.getParent());
					} else { // left right
						sibling.getRight().setColor(Node.Color.BLACK);
						rotateLeft(sibling);
						rotateRight(node.getParent());
					}
				} else {
					if (sibling.getRight().getColor() == Node.Color.RED) { // right right
						sibling.getRight().setColor(Node.Color.BLACK);
						rotateLeft(node.getParent());
					} else { // right left
						sibling.getLeft().setColor(Node.Color.BLACK);
						rotateRight(sibling);
						rotateLeft(node.getParent());
					}
				}
				node.setDoubleBlack(false);
			}
		}
	}

	private void replaceNode(Node oldNode, Node newNode) {
		if (oldNode.isLeftChild()) {
			oldNode.getParent().setLeft(newNode);
			if (newNode != nil)
				newNode.setParent(oldNode.getParent());
		} else {
			oldNode.getParent().setRight(newNode);
			if (newNode != nil)
				newNode.setParent(oldNode.getParent());
		}
	}

	private Node getNode(String value) {
		Node r = root;
		while (r != nil) {
			String rval = r.getKey();
			if (value.compareToIgnoreCase(rval) < 0)
				r = r.getLeft();
			else if (value.compareToIgnoreCase(rval) > 0)
				r = r.getRight();
			else
				break;
		}
		return r;
	}

	private Node inOrderSuccessor(Node n) {
		return minValue(n.getRight());
	}

	private Node minValue(Node node) {
		Node current = node;
		// Loop down to find the leftmost leaf
		while (current.getLeft() != nil) {
			current = current.getLeft();
		}
		return current;
	}

	public void traverseInorder(Node rootNode) {
		if (rootNode != nil) {
			traverseInorder(rootNode.getLeft());
			System.out.println(rootNode.getKey() + " color " + rootNode.getColor());
			traverseInorder(rootNode.getRight());
		}
	}

	public int getHeight() {
		return calculateHeight(this.root);
	}

	private int calculateHeight(Node node) {
		if (node == nil)
			return 0;
		// Calculate the height of each subtree
		int leftSubtreeHeight = calculateHeight(node.getLeft());
		int rightSubtreeHeight = calculateHeight(node.getRight());
		// take the larger one , +1 --> root
		return leftSubtreeHeight > rightSubtreeHeight ? leftSubtreeHeight + 1 : rightSubtreeHeight + 1;
	}

	public int getBlackHeight(Node node) {
		if (node.getKey().compareToIgnoreCase("nil") == 0)
			return 0;
		else if (node.getColor() == Node.Color.BLACK)
			return 1 + getBlackHeight(node.getLeft());
		else
			return getBlackHeight(node.getLeft());
	}

	public Node getRoot() {
		return root;
	}

	public Node getNil() {
		return nil;
	}

	public int getSize() {
		return size;
	}

}
