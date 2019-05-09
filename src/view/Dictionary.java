package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Dictionary {

	Controller controller = new Controller();
	FileChooser fileChooser;

	public Stage window;
//	public BorderPane layout;
	public MenuItem loadFile;
	public MenuItem saveFile;
	public MenuItem saveAsFile;
	public Label msgLabel;
	public Label sizeLabel;
	public Label heightLabel;
	public Button searchButton;
	public Button insertButton;
	public Button deleteButton;
	public TextField input;

	private int size;
	private int height;
	String path;
	

	public void initialize(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("English Dictionary");
		window.setResizable(false);

		try {
			Path currentRelativePath = Paths.get("");
			String path = currentRelativePath.toAbsolutePath().toString() + "/src/view/Dictionary.fxml";

			Parent root = FXMLLoader.load(new File(path).toURI().toURL());
			Scene scene = new Scene(root);
			scene.getStylesheets().add("view/darkTheme.css");
			primaryStage.setScene(scene);
			primaryStage.setTitle("English Dictionary");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void buttonsVisibility(boolean value) {

		input.setDisable(!value);
		searchButton.setDisable(!value);
		insertButton.setDisable(!value);
		deleteButton.setDisable(!value);
		saveFile.setDisable(!value);
		saveAsFile.setDisable(!value);
	}

	public void refresh() {

		size = controller.getSize();
		height = controller.getHeight();
		sizeLabel.setText(Integer.toString(size));
		heightLabel.setText(Integer.toString(height));
	}

	public void load() {

		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
		String currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/resources";
		fileChooser.setInitialDirectory(new File(currentPath));
		File file = fileChooser.showOpenDialog(window);
		if(file != null) {
			path = file.getAbsolutePath();
			controller.load(path);
			buttonsVisibility(true);
			refresh();
			msgLabel.setTextFill(Color.WHITE);
			msgLabel.setText("File loaded successfuly.");
		}
	}

	public void save() {

		controller.save();
		refresh();
		msgLabel.setTextFill(Color.WHITE);
		msgLabel.setText("File saved.");
	}

	public void saveAs() {

		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(window);
		if(file != null) {
			path = file.getAbsolutePath();
			controller.saveAs(path);
			refresh();
			msgLabel.setTextFill(Color.WHITE);
			msgLabel.setText("File saved to destination.");
		}
	}

//	private void addFileMenu() {
//
//		//File menu
//		Menu fileMenu = new Menu("File");
//		loadFile = new MenuItem("Load...");
//		loadFile.setOnAction(e -> load());
//		saveFile = new MenuItem("Save");
//		saveFile.setOnAction(e -> save());
//		saveAsFile = new MenuItem("Save as...");
//		saveAsFile.setOnAction(e -> saveAs());
//		fileMenu.getItems().addAll(loadFile, new SeparatorMenuItem(), saveFile, saveAsFile);
//
//		//Main menu bar
//		MenuBar menuBar = new MenuBar();
//		menuBar.getMenus().add(fileMenu);
//
//		layout.setTop(menuBar);
//	}

	public void search() {

		String value = input.getText();
		if(value.replaceAll("\\s","").length() != 0) {
			boolean res = controller.search(value);
			if(res) {
				msgLabel.setTextFill(Color.LIGHTGREEN);
				msgLabel.setText("Word found.");
			} else {
				msgLabel.setTextFill(Color.RED);
				msgLabel.setText("Word not found.");
			}
			refresh();
		}
	}

	public void insert() {

		String value = input.getText();
		if(value.replaceAll("\\s","").length() != 0) {
			boolean res = controller.insert(value);
			if(res) {
				msgLabel.setTextFill(Color.LIGHTGREEN);
				msgLabel.setText(value + " has been inserted.");
			} else {
				msgLabel.setTextFill(Color.RED);
				msgLabel.setText(value + " already exists.");
			}
			refresh();
		}
	}

	public void delete() {

		String value = input.getText();
		if(value.replaceAll("\\s","").length() != 0) {
			boolean res = controller.delete(value);
			if(res) {
				msgLabel.setTextFill(Color.LIGHTGREEN);
				msgLabel.setText(value + " has been deleted.");
			} else {
				msgLabel.setTextFill(Color.RED);
				msgLabel.setText(value + " does not exist.");
			}
			refresh();
		}
	}

//	private void addBody() {
//
//		VBox infoBox = new VBox(10);
//		infoBox.setPadding(new Insets(10, 10, 10, 10));
//		//		infoGrid.setVgap(8);
//		//		infoGrid.setHgap(10);
//
//		// Tree Size Label
//		sizeLabel = new Label("Tree Size: " + size);
//		infoBox.getChildren().add(sizeLabel);
//
//		// Tree Height Label
//		heightLabel = new Label("Tree Height: " + height);
//		infoBox.getChildren().add(heightLabel);
//
//		// Input Text Field
//		input = new TextField();
//		infoBox.getChildren().add(input);
//
//		// Search Button
//		searchButton = new Button("Search");
//		searchButton.setOnAction(e -> search());
//		infoBox.getChildren().add(searchButton);
//
//		// Insert Button
//		insertButton = new Button("Insert");
//		insertButton.setOnAction(e -> insert());
//		infoBox.getChildren().add(insertButton);
//
//		// Delete Button
//		deleteButton = new Button("Delete");
//		deleteButton.setOnAction(e -> delete());
//		infoBox.getChildren().add(deleteButton);
//
//		// Message Box
//		msgLabel = new Label("Message Box");
//		msgLabel.setPrefSize(400, 50);
//		msgLabel.setStyle("-fx-border-color: grey;");
//		infoBox.getChildren().add(msgLabel);
//
//		buttonsVisibility(false);
//
//		layout.setCenter(infoBox);
//	}
}