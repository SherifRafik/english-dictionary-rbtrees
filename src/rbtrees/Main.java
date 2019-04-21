package rbtrees;

import java.io.IOException;
public class Main {

	public static void main(String[] args) throws IOException {
        /*RedBlackTree tree = new RedBlackTree(41);
        tree.insert(38);
        tree.insert(31);
        tree.insert(12);
        tree.insert(19);
        tree.traverseInorder(tree.getRoot());
        
        Node test = tree.search(40, tree.getRoot());
        System.out.println(tree.getRoot().getKey());*/
        
        File fr = new File();
        fr.readFile();
        //ArrayList<String> list = (ArrayList<String>) fr.getDictionaryArray();
        RedBlackTree tree = new RedBlackTree();
        for( int i = 0 ; i < fr.getDictionaryArray().size() ; i++) {
        	String element = fr.getDictionaryArray().get(i);
        	tree.insert(element);
        }
        System.out.println(tree.getRoot().getKey());
        System.out.println(tree.getRoot().getLeft().getKey());
        System.out.println(tree.getRoot().getRight().getKey());
	}

}

// DONE: Read from file, Insert to the tree
// TODO: Deletion in red black trees , Find a better way to insert strings