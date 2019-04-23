package controller;

import view.Dictionary;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Dictionary dictionary = new Dictionary();;


    public static void main(String[] args) {
    	
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		dictionary.initialize(primaryStage);
	}
}
