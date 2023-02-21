package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.DataLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class ProfilController implements Initializable {
	public static String username = "";
	public static String password = "";
	@FXML
    private Label lblUsername;
	@FXML
    private Label lblUsermail;
	@FXML
    private Button btShowPassword;
	@FXML
    private TextField tfShowPassword;
	@FXML
    private Button btLogout;
	@FXML
	private Button exitButton;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblUsername.setText(username);
		lblUsermail.setText(DataLogin.readEmail(username));

		
	}
	
	
	@FXML
	private void showPassword(ActionEvent event) throws IOException 
	{
		tfShowPassword.setText(password);

		
		
	}
	
	@FXML
	 private void loadWatchlist(ActionEvent event) throws IOException {
		Node source =(Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();

		try {
			FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("Startseite.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage ();
			String pfad = "/Image/LogoFilmbibliothek.png";
			Image image = new Image(pfad);
			stage.getIcons().add(image);
			stage.setTitle("Main Window");
			stage.setScene(new Scene(root));
			stage.show();
		}catch(IOException iOException) {


		}
	}
	
	
	@FXML
	 private void loadLogout(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("LoginStartscreen.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage ();
		String pfad = "/Image/LogoFilmbibliothek.png";
		Image image = new Image(pfad);
		stage.getIcons().add(image);
		stage.setTitle("Main Window");
		stage.setScene(new Scene(root));
		stage.show();
		Node source =(Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
		
	 }
	public void onClick_exitButton() {
		
		  Stage stage = (Stage) exitButton.getScene().getWindow();
		  stage.close();
		 
	 }
}
