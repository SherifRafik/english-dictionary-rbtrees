package model;

public class RBTree {

    public int getHeight(){
        return height;
    }

    public  int getSize() { return size; }

    /* Black - 1  RED - 0 */
    private static final int BLACK = 1;
    private static final int RED   = 0;
    private int height;
    private int size;
    private RBNode current;
    private RBNode grand;
    private RBNode great;
    private RBNode parent;
    private RBNode root;
    private static RBNode nullNode;

    /* static initializer for nullNode */
    static
    {
        nullNode = new RBNode("");
        nullNode.setLeft(nullNode);
        nullNode.setRight(nullNode);
    }

    /* Constructor */
    public RBTree(String negInf)
    {
        root = new RBNode(negInf);
        root.setLeft(nullNode);
        root.setRight(nullNode);
        size = 0;
    }

    /* Functions to search for an element */
    public boolean search(String val)
    {
        return search(root.getRight(), val);
    }

    private boolean search(RBNode r, String val)
    {
        boolean found = false;
        while ((r != nullNode) && !found)
        {
            String rval = r.getElement();
            if (val.compareTo(rval) < 0)
                r = r.getLeft();
            else if (val.compareTo(rval) > 0)
                r = r.getRight();
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    /* Function to insert item */
    public boolean insert(String item ) {
        current = parent = grand = root;
        nullNode.setElement(item);
        while (!current.getElement().equals(item)) {
            great = grand;
            grand = parent;
            parent = current;
            current = item.compareTo(current.getElement()) < 0 ? current.getLeft() : current.getRight();
            // Check if two red children and fix if so
            if (current.getLeft().getColor() == RED && current.getRight().getColor() == RED)
                handleReorient( item );
        }
        // Insertion fails if already present
        if (current != nullNode)
            return false;
        current = new RBNode(item, nullNode, nullNode);
        // Attach to parent
        if (item.compareTo(parent.getElement()) < 0)
            parent.setLeft(current);
        else
            parent.setRight(current);
        handleReorient( item );
        size++;
        calculateHeight();
        return true;
    }

    private void handleReorient(String item)
    {
        // Do the color flip
        current.setColor(RED);
        current.getLeft().setColor(BLACK);
        current.getRight().setColor(BLACK);

        if (parent.getColor() == RED)
        {
            // Have to rotate
            grand.setColor(RED);
            if (item.compareTo(grand.getElement()) < 0 != item.compareTo(parent.getElement()) < 0) // XOR of the 2 conditions
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate(item, great );
            current.setColor(BLACK);
        }
        // Make root black
        root.getRight().setColor(BLACK);
    }

    private RBNode rotate(String item, RBNode parent) {
        if(item.compareTo(parent.getElement()) < 0){
            if (item.compareTo(parent.getLeft().getElement()) <0)
                parent.setLeft(rotateWithLeftChild(parent.getLeft()));
            else
                parent.setLeft(rotateWithRightChild(parent.getLeft()));
            return parent.getLeft();
        }else {
            if (item.compareTo(parent.getRight().getElement()) < 0)
                parent.setLeft(rotateWithLeftChild(parent.getRight()));
            else
                parent.setLeft(rotateWithRightChild(parent.getRight()));
            return parent.getRight();
        }
    }

    /* Rotate binary tree node with left child */
    private RBNode rotateWithLeftChild(RBNode node) {
        RBNode newParent = node.getRight();
        if (node == root)
            root = newParent;
        // mode down
        node.setRight(newParent.getLeft());
        if (newParent.getLeft() != nullNode)
            newParent.getLeft().setParent(node);
        newParent.setLeft(node);

        /*RBNode k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        return k1;*/
        return newParent;
    }

    /* Rotate binary tree node with right child */
    private RBNode rotateWithRightChild(RBNode k1)
    {
        RBNode k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        return k2;
    }

    /* Delete a node */
    public boolean delete(String value) {
        RBNode node = getNode(value);
        if (node == null)
            return false;
        performDelete(node);
        return true;
    }

    private void performDelete(RBNode node) {
        // If node has 2 children
        // Copy its inorder successor in it
        // Then recursively delete the successor
        if (node.getLeft()!=nullNode && node.getRight()!=nullNode) {
            RBNode successor = inOrderSuccessor(node);
            node.setElement(successor.getElement());
            performDelete(successor);
            return;
        }
        // If node is red
        if (node.getColor() == RED){
            if (node.getLeft() != nullNode){
                RBNode child = node.getLeft();
                replaceNode(node, child);
            }else if (node.getRight() != nullNode){
                RBNode child = node.getLeft();
                replaceNode(node, child);
            }else {
                replaceNode(node, nullNode);
            }
            return;
        }
        if (node.getColor() == BLACK){
            // Node has only a left child
            if (node.getLeft() != nullNode){
                RBNode child = node.getLeft();
                if (child.getColor() == RED){
                    child.setColor(BLACK);
                    replaceNode(node, child);
                }
                else {
                    child.setDoubleBlack(true);
                    replaceNode(node, child);
                    fixDoubleBlack(child);
                }
            }
            // Node has only a right child
            else if (node.getRight() != nullNode){
                RBNode child = node.getRight();
                if (child.getColor() == RED){
                    child.setColor(BLACK);
                    replaceNode(node, child);
                }
                else {
                    child.setDoubleBlack(true);
                    replaceNode(node, child);
                    fixDoubleBlack(child);
                }
            }
            // Node has no children
            else {
                replaceNode(node, nullNode);
            }
        }
    }

    private void fixDoubleBlack(RBNode node) {
        RBNode sibling = node.getSibling();
        while (node.isDoubleBlack() && node != root) {
            if (sibling.getColor() == RED){
                //perform rotations
                if (sibling.isLeftChild()) {
                    // left case
                    // right rotate the parent
                } else {
                    // right case
                    // left rotate the parent
                }

            }
            else {
                if (sibling.getLeft().getColor() == RED) {
                     if (sibling.isLeftChild()){
                         // left left rotation
                     }else {
                         // right left rotation
                     }
                }
                else if (sibling.getRight().getColor() == RED) {
                    if (sibling.isLeftChild()){
                        // left right rotation
                    }else {
                        // right right rotation
                    }
                }
                else {
                    sibling.setColor(RED);
                    if (sibling.getParent().getColor() == RED)
                        sibling.getParent().setColor(BLACK);
                    else
                        sibling.getParent().setDoubleBlack(true);
                    fixDoubleBlack(sibling.getParent());
                }
            }
        }
        if (node == root)
            node.setDoubleBlack(false);
    }


    private void replaceNode(RBNode oldNode, RBNode newNode){
        if (!oldNode.isLeftChild()) {
            oldNode.getParent().setRight(newNode);
            newNode.setParent(oldNode.getParent());
        }
        else{
            oldNode.getParent().setLeft(newNode);
            newNode.setParent(oldNode.getParent());
        }
    }

    private RBNode getNode(String value) {
        RBNode r = root;
        while (r != nullNode) {
            String rval = r.getElement();
            if (value.compareTo(rval) < 0)
                r = r.getLeft();
            else if (value.compareTo(rval) > 0)
                r = r.getRight();
            else
                break;
        }
        return r;
    }

    private RBNode inOrderSuccessor(RBNode n) {
        // step 1 of the above algorithm
        if (n.getRight() != null) {
            return minValue(n.getRight());
        }
        // step 2 of the above algorithm
        RBNode p = n.getParent();
        while (p != null && n == p.getParent()) {
            n = p;
            p = p.getParent();
        }
        return p;
    }

    private RBNode minValue(RBNode node) {
        RBNode current = node;
        // Loop down to find the leftmost leaf
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    /* Update the tree height */
    private void calculateHeight(){

    }

    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root.getRight() == nullNode;
    }

    /* Make the tree logically empty */
    public void makeEmpty()
    {
        root.setRight(nullNode);
    }

    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(root.getRight());
    }

    private int countNodes(RBNode r)
    {
        if (r == nullNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());
            return l;
        }
    }

    /* Function for inorder traversal */
    public void inorder()
    {
        inorder(root.getRight());
    }

    private void inorder(RBNode r)
    {
        if (r != nullNode)
        {
            inorder(r.getLeft());
            char c = 'B';
            if (r.getColor() == 0)
                c = 'R';
            System.out.print(r.getElement() +""+c+" ");
            inorder(r.getRight());
        }
    }

    /* Function for preorder traversal */
    public void preorder()
    {
        preorder(root.getRight());
    }

    private void preorder(RBNode r)
    {
        if (r != nullNode)
        {
            char c = 'B';
            if (r.getColor() == 0)
                c = 'R';
            System.out.print(r.getElement() +""+c+" ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    /* Function for postorder traversal */
    public void postorder()
    {
        postorder(root.getRight());
    }

    private void postorder(RBNode r)
    {
        if (r != nullNode)
        {
            postorder(r.getLeft());
            postorder(r.getRight());
            char c = 'B';
            if (r.getColor() == 0)
                c = 'R';
            System.out.print(r.getElement() +""+c+" ");
        }
    }


}