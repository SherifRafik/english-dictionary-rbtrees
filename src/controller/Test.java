package controller;

import model.File;
import model.rbtrees.RedBlackTree;

public class Test {

	public static void main(String[] args) {
        
        RedBlackTree tree = new RedBlackTree();
        File fr = new File("test.txt");
        fr.readFile(tree);
        System.out.println(tree.getSize());
        System.out.println(tree.getHeight());
//        tree.traverseInorder(tree.getRoot());
        System.out.println("deletion of Dragon is "+tree.delete("Dragon"));

        System.out.println("deletion of book is "+tree.delete("book"));
        System.out.println("deletion of book is "+tree.delete("book"));
//        tree.traverseInorder(tree.getRoot());

        System.out.println("insertion of book is "+tree.insert("book"));
        System.out.println("insertion of book is "+tree.insert("book"));
        System.out.println("insertion of book is "+tree.insert("hh"));
//        tree.traverseInorder(tree.getRoot());

        System.out.println("banana is " + tree.search("banana"));
        System.out.println("ant is " + tree.search("aNt"));
        System.out.println("jilly is " + tree.search("jilly"));
        System.out.println("gilly is " + tree.search("gilly"));
        fr.writeToFile(tree);

        System.out.println(tree.getSize());
	}

}