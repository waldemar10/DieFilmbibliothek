package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


		public class Main extends Application {

@Override
		public void start(Stage primaryStage) throws Exception{
			Parent root = FXMLLoader.load(getClass().getResource("LoginStartscreen.fxml"));
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setResizable(false);
			Scene scene=new Scene(root, 800,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			primaryStage.show();
    
}

public static void main(String[] args) {
  
	launch(args);
}
}