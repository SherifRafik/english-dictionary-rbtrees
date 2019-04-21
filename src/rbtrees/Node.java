package rbtrees;

public class Node {

	private String key;
	private int color; // 0 - Red / 1 - Black
	private Node left, right, parent;
	private boolean doubleBlack;

	public Node(String key) {
		this.key = key;
		this.left = null;
		this.right = null;
		color = 0;
		doubleBlack = false;
	}

	public Node getUncle() {
		if (parent.getParent().getLeft() == parent)
			return parent.getParent().getRight();
		return parent.getRight().getLeft();
	}

	public Node getSibling() {
		if (this == parent.getRight())
			return parent.getLeft();
		return parent.getRight();
	}

	public boolean isLeftChild(){
		return this == parent.getLeft();
	}

	public String getKey() {
			return key;
		}

	public void setKey(String data) {
			this.key = data;
		}

	public int getColor() {
			return color;
		}
		
	public void setColor(int color) {
			this.color = color;
		}
		
	public Node getLeft() {
			return left;
		}
		
	public void setLeft(Node left) {
			this.left = left;
		}
		
	public Node getRight() {
			return right;
		}
		
	public void setRight(Node right) {
			this.right = right;
		}
		
	public Node getParent() {
			return parent;
		}
		
	public void setParent(Node parent) {
			this.parent = parent;
		}

	public boolean isDoubleBlack() {
		return doubleBlack;
	}

	public void setDoubleBlack(boolean doubleBlack) {
		this.doubleBlack = doubleBlack;
	}
}