package rbtrees;

import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException {
        
        File fr = new File();
        fr.readFile();
        RedBlackTree tree = new RedBlackTree();
        for( int i = 0 ; i < fr.getDictionaryArray().size() ; i++) {
        	String element = fr.getDictionaryArray().get(i);
        	tree.insert(element);
        }
        System.out.println(tree.getRoot().getKey());
        System.out.println(tree.getRoot().getLeft().getKey());
        System.out.println(tree.getRoot().getRight().getKey());
        System.out.println(tree.getHeight());
	}

}

// DONE: Search in RB tree , Insert in RB tree, get RB tree height , read from a file
// TODO: Deletion in red black trees , write from file to tree