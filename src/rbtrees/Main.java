package rbtrees;

import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException {
        
        RedBlackTree tree = new RedBlackTree();
        ReadFile fr = new ReadFile();
        fr.readFile(tree);
        System.out.println(tree.getRoot().getKey());
        System.out.println(tree.getRoot().getLeft().getKey());
        System.out.println(tree.getRoot().getRight().getKey());
        System.out.println(tree.insert("Sherif"));
        fr.writeToFile(tree);
	}

}

// DONE: Search in RB tree , Insert in RB tree, get RB tree height , read from a file into the tree
// TODO: Deletion in red black trees , write from tree to file