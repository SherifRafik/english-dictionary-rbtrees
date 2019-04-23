package controller;

import model.File;
import model.rbtrees.RedBlackTree;

public class Controller {

	RedBlackTree tree;
	File fr;
	
	public boolean search(String value) {

		return tree.search(value);
	}

	public boolean insert(String value) {

		return tree.insert(value);
	}

	public boolean delete(String value) {

		return tree.delete(value);
	}

	public int getSize() {
		
		if(tree == null)
			return 0;
		return tree.getSize();
	}
	
	public int getHeight() {
		
		if(tree == null)
			return 0;
		return tree.getHeight();
	}
	public void load(String path) {

		tree = new RedBlackTree();
        fr = new File(path);
        fr.readFile(tree);
	}

	public void save() {

		fr.writeToFile(tree);
	}

	public void saveAs(String path) {

        fr = new File(path);
        fr.writeToFile(tree);
	}

}
