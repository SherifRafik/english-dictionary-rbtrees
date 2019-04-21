package model.rbtrees;

public class Node {

	private String key;
	private Color color;
	private Node left, right, parent;
	private boolean doubleBlack;
	
    public enum Color {
        RED,
        BLACK
    }
    
	Node(String key) {
		this.key = key;
		this.left = null;
		this.right = null;
		color = Color.RED;
		doubleBlack = false;
	}

	/*public Node getUncle() {
		if (parent.isLeftChild())
			return parent.getParent().getRight();
		return parent.getRight().getLeft();
	}*/

	Node getSibling() {
    	if (this.isLeftChild())
    		return parent.getRight();
		return parent.getLeft();
	}

	boolean isLeftChild(){
		return this == parent.getLeft();
	}

	public String getKey() {
			return key;
		}

	void setKey(String data) {
			this.key = data;
		}
		
	Color getColor() {
		return color;
	}

	void setColor(Color color) {
		this.color = color;
	}

	public Node getLeft() {
		return left;
    }
		
	void setLeft(Node left) {
			this.left = left;
		}
		
	public Node getRight() {
			return right;
		}
		
	void setRight(Node right) {
			this.right = right;
		}
		
	Node getParent() {
			return parent;
		}
		
	void setParent(Node parent) {
			this.parent = parent;
		}

	boolean isDoubleBlack() {
		return doubleBlack;
	}

	void setDoubleBlack(boolean doubleBlack) {
		this.doubleBlack = doubleBlack;
	}
}