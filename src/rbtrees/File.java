package rbtrees;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class File {
	
    private List<String> dictionaryArray;
    private String filePath;
    
    public File() {
    	dictionaryArray = new ArrayList<String>();
        filePath = "src/resources/dictionary.txt";
    }
	
	public void readFile() throws IOException {
		dictionaryArray = Files.readAllLines(Paths.get(filePath));
        System.out.println("Words are inserted into the ArrayList , ArrayList Size = " + dictionaryArray.size());
	}

	public List<String> getDictionaryArray() {
		return dictionaryArray;
	}

	
	
}
