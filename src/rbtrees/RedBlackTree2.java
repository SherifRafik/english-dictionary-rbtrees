package rbtrees;

public class RedBlackTree2 {

	private Node root;
	private Node nil = new Node("nil");
	
	public RedBlackTree2(Node root) {
		this.root= root;
	}
	
	
	public RedBlackTree2(String data) {
		nil.setColor(1);
		root = new Node(data);
		root.setColor(1);  //Root node is always black
		root.setLeft(nil);
		root.setRight(nil);
		root.setParent(nil);
	}
	
	
	/*
	 * Properties violated are 
	 * 1 - Property 2 which requires the root to be black
	 * 2 - Property 4 which required a red node to have 2 black children
	 */
	
	public void insert(String data) {	
		Node newNode = new Node(data);
		newNode.setLeft(nil); 
		newNode.setRight(nil);
		newNode.setParent(nil);
		
		Node node = this.root;
		Node temp = nil;
		
		while(node != nil) {
			temp = node; // Get leaf node
			int compareTwoStrings = node.getKey().compareToIgnoreCase(newNode.getKey());
			if(compareTwoStrings > 0) 
				node = node.getLeft();
			else
				node = node.getRight();
		}
		
		newNode.setParent(temp);
		if(temp == nil)
			this.root = newNode;
		else if(temp.getKey().compareToIgnoreCase(newNode.getKey()) > 0)
			temp.setLeft(newNode);
		else
			temp.setRight(newNode);
		
		insertFixup(newNode);
	}
	
	
	
	private void insertFixup(Node node) {
        while (node.getParent() != nil && node.getParent().getColor() == 0){
			if(node.getParent() == node.getParent().getParent().getLeft()) {
				Node uncle = node.getParent().getParent().getRight();
				if(uncle != nil && uncle.getColor() == 0) { // Case 1
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
				Node uncle = node.getParent().getParent().getLeft();
				if(uncle != nil && uncle.getColor() == 0) { // Case 1
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
        Node y = nil;
        if(x.getRight() != nil)
            y = x.getRight();

        x.setRight(y.getLeft());
        if(y.getLeft() != nil)
            y.getLeft().setParent(x);

        y.setParent(x.getParent());
        if(x == root)
            root = y;
        else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else 
        	x.getParent().setRight(y);

        y.setLeft(x);
        x.setParent(y);
    }
	
	 
	private void rotateRight(Node x){
        Node y = nil;
        if (x.getLeft() != nil)
            y = x.getLeft();

        x.setLeft(y.getRight());
        if(y.getRight() != nil)
            y.getRight().setParent(x);

        y.setParent(x.getParent());
        if(x == root)
            root = y;
        else if(x == x.getParent().getLeft())
            x.getParent().setLeft(y);
        else x.getParent().setRight(y);

        y.setRight(x);
        x.setParent(y);
    }
	
	
    public void traverseInorder (Node rootNode){
        if(rootNode != nil){
            traverseInorder(rootNode.getLeft());
            System.out.println(rootNode.getKey() + " color " + rootNode.getColor());
            traverseInorder(rootNode.getRight());
        }
    }
    
    public Node search(String data , Node node){
    	if(node == nil)
    		return nil;
    	if(node.getKey().equalsIgnoreCase(data))
    		return node;
    	else if(node.getKey().compareToIgnoreCase(data) > 0) {
    		if(node.getLeft() != nil)
    			return search(data, node.getLeft());
    	}else if(node.getKey().compareToIgnoreCase(data) < 0) {
    		if(node.getRight() != nil)
    			return search(data, node.getRight());
    	}
    	return nil;
    }


	public Node getRoot() {
		return root;
	}
   
	
}
