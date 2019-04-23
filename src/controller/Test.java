package controller;

import model.File;
import model.rbtrees.RedBlackTree;

public class Test {

	public static void main(String[] args) {
        
        RedBlackTree tree = new RedBlackTree();
        File fr = new File("dictionary.txt");
        fr.readFile(tree);
        System.out.println(tree.getSize());
        System.out.println(tree.getHeight());


        System.out.println(tree.getSize());
	}

}