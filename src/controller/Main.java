package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Dictionary;


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
