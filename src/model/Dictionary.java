package model;

public class Dictionary {

    private RBTree dictionary;

    public boolean search(String word){
        return dictionary.search(word);
    }

    public boolean insertWord(String word){
        return dictionary.insert(word);
    }

    /*public boolean deleteWord(String word){
        return dictionary.delete(word);
    }*/

    public int getSize(){
        return dictionary.getSize();
    }

    public int getTreeHeight(){
        return dictionary.getHeight();
    }
}
