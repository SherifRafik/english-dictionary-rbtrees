package view;

import controller.Controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;


public class Dictionary {

	Controller controller = new Controller();
	FileChooser fileChooser;

	Stage window;
	BorderPane layout;
	MenuItem loadFile;
	MenuItem saveFile;
	MenuItem saveAsFile;
	Label msgLabel;
	Label sizeLabel;
	Label heightLabel;
	Button searchButton;
	Button insertButton;
	Button deleteButton;
	TextField input;

	private int size;
	private int height;
	String path;

	//  @Override
	//  public void start(Stage primaryStage) throws Exception {
	//  
	//  	try {
	//	        Path currentRelativePath = Paths.get("");
	//	        String path = currentRelativePath.toAbsolutePath().toString() + "/src/view/Dictionary.fxml";
	//	        
	//	        Parent root = FXMLLoader.load(new File(path).toURI().toURL());
	//			Scene scene = new Scene(root,400,400);
	//			primaryStage.setScene(scene);
	//	        primaryStage.setTitle("Hello World");
	//	        primaryStage.show();
	//  	} catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//  }

	public void initialize(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("English Dictionary");
		window.setResizable(false);

		layout = new BorderPane();
		addFileMenu();
		addBody();

		Scene scene = new Scene(layout, 400, 300);
		window.setScene(scene);
		window.show();
	}

	private void buttonsVisibility(boolean value) {

		searchButton.setDisable(!value);
		insertButton.setDisable(!value);
		deleteButton.setDisable(!value);
		saveFile.setDisable(!value);
		saveAsFile.setDisable(!value);
	}

	public void refresh() {

		size = controller.getSize();
		height = controller.getHeight();
		sizeLabel.setText("Tree Size: " + Integer.toString(size));
		heightLabel.setText("Tree Height: " + Integer.toString(height));
	}

	private void load() {

		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(window);
		if(file != null) {
			path = file.getAbsolutePath();
			controller.load(path);
			buttonsVisibility(true);
			refresh();
			msgLabel.setText("File loaded successfuly.");
		}
	}

	private void save() {

		controller.save();
		refresh();
		msgLabel.setText("File saved.");
	}

	private void saveAs() {

		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(window);
		if(file != null) {
			path = file.getAbsolutePath();
			controller.saveAs(path);
			refresh();
			msgLabel.setText("File saved.");
		}
	}

	private void addFileMenu() {

		//File menu
		Menu fileMenu = new Menu("File");
		loadFile = new MenuItem("Load...");
		loadFile.setOnAction(e -> load());
		saveFile = new MenuItem("Save");
		saveFile.setOnAction(e -> save());
		saveAsFile = new MenuItem("Save as...");
		saveAsFile.setOnAction(e -> saveAs());
		fileMenu.getItems().addAll(loadFile, new SeparatorMenuItem(), saveFile, saveAsFile);

		//Main menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu);

		layout.setTop(menuBar);
	}

	private void search(String value) {

		if(value.replaceAll("\\s","").length() != 0) {
			boolean res = controller.search(value);
			if(res)
				msgLabel.setText("Word found.");
			else
				msgLabel.setText("Word not found.");
			refresh();
		}
	}

	private void insert(String value) {

		if(value.replaceAll("\\s","").length() != 0) {
			boolean res = controller.insert(value);
			if(res)
				msgLabel.setText(value + " has been inserted.");
			else
				msgLabel.setText(value + " already exists.");
			refresh();
		}
	}

	private void delete(String value) {

		if(value.replaceAll("\\s","").length() != 0) {
			boolean res = controller.delete(value);
			if(res)
				msgLabel.setText(value + " has been deleted.");
			else
				msgLabel.setText(value + " does not exist.");
			refresh();
		}
	}

	private void addBody() {

		VBox infoBox = new VBox(10);
		infoBox.setPadding(new Insets(10, 10, 10, 10));
		//		infoGrid.setVgap(8);
		//		infoGrid.setHgap(10);

		// Tree Size Label
		sizeLabel = new Label("Tree Size: " + size);
		infoBox.getChildren().add(sizeLabel);

		// Tree Height Label
		heightLabel = new Label("Tree Height: " + height);
		infoBox.getChildren().add(heightLabel);

		// Input Text Field
		input = new TextField();
		infoBox.getChildren().add(input);

		// Search Button
		searchButton = new Button("Search");
		searchButton.setOnAction(e -> search(input.getText()));
		infoBox.getChildren().add(searchButton);

		// Insert Button
		insertButton = new Button("Insert");
		insertButton.setOnAction(e -> insert(input.getText()));
		infoBox.getChildren().add(insertButton);

		// Delete Button
		deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> delete(input.getText()));
		infoBox.getChildren().add(deleteButton);

		// Message Box
		msgLabel = new Label("Message Box");
		msgLabel.setPrefSize(400, 50);
		msgLabel.setStyle("-fx-border-color: grey;");
		infoBox.getChildren().add(msgLabel);

		buttonsVisibility(false);

		layout.setCenter(infoBox);
	}
}