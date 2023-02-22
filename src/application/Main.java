package application;

import functions.functions;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

@Override
		public void start(Stage primaryStage) throws Exception{
			Parent root = FXMLLoader.load(getClass().getResource("LoginStartscreen.fxml"));
			Scene scene=new Scene(root, 800,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			functions.createWindow(new Stage (),"Login",null,scene);
}

public static void main(String[] args) {
  
	launch(args);
}
}