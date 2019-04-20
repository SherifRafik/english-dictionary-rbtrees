package rbtrees;

public class RedBlackTree {

	private Node root;
	
	public RedBlackTree(Node root) {
		this.root= root;
	}
	
	
	public RedBlackTree(int data) {
		root = new Node(data);
		root.setColor(1);  //Root node is always black
		root.setLeft(null);
		root.setRight(null);
		root.setParent(null);
	}
	
	
	/*
	 * Properties violated are 
	 * 1 - Property 2 which requires the root to be black
	 * 2 - Property 4 which required a red node to have 2 black children
	 */
	
	public void insert(int data) {	
		Node newNode = new Node(data);
		newNode.setLeft(null); 
		newNode.setRight(null);
		newNode.setParent(null);
		
		Node node = this.root;
		Node temp = null;
		
		while(node != null) {
			temp = node; // Get leaf node
			if(newNode.getKey() < node.getKey()) 
				node = node.getLeft();
			else
				node = node.getRight();
		}
		
		newNode.setParent(temp);
		if(temp == null)
			this.root = newNode;
		else if(newNode.getKey() < temp.getKey())
			temp.setLeft(newNode);
		else
			temp.setRight(newNode);
		
		insertFixup(newNode);
	}
	
	
	
	private void insertFixup(Node node) {
        while (node.getParent() != null && node.getParent().getColor() == 0){
			Node uncle = null;
			if(node.getParent() == node.getParent().getParent().getLeft()) {
				uncle = node.getParent().getParent().getRight();
				if(uncle != null && uncle.getColor() == 0) { // Case 1
					node.getParent().setColor(1);
					uncle.setColor(1);
					node.getParent().getParent().setColor(0);
					node = node.getParent().getParent();
				} else {
					if(node == node.getParent().getRight()) {//Double rotation needed
						node = node.getParent();
						rotateLeft(node);
					}
					node.getParent().setColor(1);
					node.getParent().getParent().setColor(0);
					rotateRight(node.getParent().getParent());
				}
			}
			else {
				uncle = node.getParent().getParent().getRight();
				if(uncle != null && uncle.getColor() == 0) { // Case 1
					node.getParent().setColor(1);
					uncle.setColor(1);
					node.getParent().getParent().setColor(0);
					node = node.getParent().getParent();
				} else {
					if(node == node.getParent().getLeft()) {
						node = node.getParent();
						rotateRight(node);
					}
					node.getParent().setColor(1);
					node.getParent().getParent().setColor(0);
					rotateLeft(node.getParent().getParent());
				}
			}	
		}
		this.root.setColor(1);
	}

	
	private void rotateLeft(Node x){
        Node y = null;
        if(x.getRight() != null)
            y = x.getRight();

        x.setRight(y.getLeft());
        if(y.getLeft() != null)
            y.getLeft().setParent(x);

        y.setParent(x.getParent());
        if(x.getParent() == null)
            root = y;
        else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else 
        	x.getParent().setRight(y);

        y.setLeft(x);
        x.setParent(y);
    }
	
	 
	private void rotateRight(Node x){
        Node y = null;
        if (x.getLeft() != null)
            y = x.getLeft();

        x.setLeft(y.getRight());
        if(y.getRight() != null)
            y.getRight().setParent(x);

        y.setParent(x.getParent());
        if(x.getParent() == null)
            root = y;
        else if(x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else x.getParent().setRight(y);

        y.setRight(x);
        x.setParent(y);
    }
	
	
    public void traverseInorder (Node rootNode){
        if(rootNode != null){
            traverseInorder(rootNode.getLeft());
            System.out.println(rootNode.getKey() + " color " + rootNode.getColor());
            traverseInorder(rootNode.getRight());
        }
    }
    
    public Node search(int data , Node node){
    	if(node == null)
    		return null;
    	if(node.getKey() == data)
    		return node;
    	else if(data < node.getKey()) {
    		if(node.getLeft() != null)
    			return search(data, node.getLeft());
    	}else if(data > node.getKey()) {
    		if(node.getRight() != null)
    			return search(data, node.getRight());
    	}
    	return null;
    }


	public Node getRoot() {
		return root;
	}
   
	
}
