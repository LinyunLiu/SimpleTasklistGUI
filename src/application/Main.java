// AUTHOR: LINYUN LIU
// DATE: MARCH 15th, 2021

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private String background3 = "/Users/yunliu/MyApplications/Task/src/background3.jpg";
	public void start(Stage primaryStage) throws FileNotFoundException {
		Image icon = new Image(new FileInputStream("/Users/yunliu/MyApplications/Task/icon.png"));
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("RemindersApp.fxml"));
			Scene scene = new Scene(root,1080,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.setBackground(BackgroundImages.getBackground(background3));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Task");
			primaryStage.getIcons().add(icon);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();}

	}
	

	public static void main(String[] args) {
		launch(args);
	
	}
	
}
