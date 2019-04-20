package model;

public class RBNode {
    private RBNode left, right, parent;
    private String element;
    private int color;
    private boolean doubleBlack = false;

    /* Constructor */
    public RBNode(String theElement)
    {
        this( theElement, null, null );
    }

    /* Constructor */
    public RBNode(String theElement, RBNode lt, RBNode rt)
    {
        left = lt;
        right = rt;
        element = theElement;
        color = 1;
    }

    public RBNode getUncle() {
        if (parent.getParent().getLeft() == parent)
            return parent.getParent().getRight();
        return parent.getRight().getLeft();
    }

    public RBNode getSibling() {
        if (this == parent.getRight())
            return parent.getLeft();
        return parent.getRight();
    }

    public boolean isLeftChild(){
        if (this == parent.getLeft())
            return true;
        return false;
    }

    public boolean isDoubleBlack() {
        return doubleBlack;
    }

    public void setDoubleBlack(boolean doubleBlack) {
        this.doubleBlack = doubleBlack;
    }

    public RBNode getLeft() { return left; }

    public void setLeft(RBNode left) { this.left = left; }

    public RBNode getRight() { return right; }

    public void setRight(RBNode right) { this.right = right; }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }

    public String getElement() { return element; }

    public void setElement(String newElement) { this.element = newElement; }

    public int getColor() { return color; }

    public void setColor(int newColor) { this.color = newColor; }
}
