package rbtrees;

import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException {
        
        RedBlackTree tree = new RedBlackTree();
        File fr = new File();
        fr.readFile(tree);
        System.out.println(tree.getSize());
        System.out.println(tree.getHeight());
        fr.writeToFile(tree);
	}

}

/*
 *  DONE: Search in RB tree , Insert in RB tree, get RB tree height, get RB tree size ,
 *  read from a file into the tree , write from tree to file 
 */
							
// TODO: Deletion in red black trees 