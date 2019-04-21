package rbtrees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFile {
	
    private String filePath;
    
    public ReadFile() {
        filePath = "src/resources/dictionary.txt";
    }
	
	public void readFile(RedBlackTree tree) {
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String word = bufferedReader.readLine();
			while(word != null) {
				tree.insert(word);
				word = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(RedBlackTree tree) {
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			writeToFile(bufferedWriter , tree , tree.getRoot());
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	// Need this function to avoid creating new FileWriter and BufferedWriter in recusrion
	// In-order Traversal of rb tree
	private void writeToFile(BufferedWriter bufferedWriter , RedBlackTree tree , Node node) {
		if(node != tree.getNil()) {
			writeToFile(bufferedWriter , tree , node.getLeft());
            try {
				bufferedWriter.write(node.getKey() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			writeToFile(bufferedWriter , tree, node.getRight());
		}
	}
	


	
	
}
