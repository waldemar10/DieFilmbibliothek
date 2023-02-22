package application;


import java.io.IOException;
import data.DataLogin;
import functions.functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginController {

	 @FXML
	 	private AnchorPane rootPane;
	 @FXML
	    private Text fmFehlermeldung;
	 @FXML
	    private Text fmBenutzername;
	 @FXML
	    private Text fmPasswort;
	
	public static boolean loginTest= false;
	public static String benutzername = "";

	
	@FXML
	private  TextField tfName;
	@FXML
	private PasswordField pfPassword;
	@FXML
	private Button btLogin;

	
	@FXML
	 private void loadRegistration() throws IOException {
		 AnchorPane pane = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		 if(pane != null)
		 rootPane.getChildren().setAll(pane);
	 }
	
	@FXML
	private void handleButtonLoginAction(ActionEvent event){
		fmPasswort.setFill(Color.RED);
		fmBenutzername.setFill(Color.RED);
		fmFehlermeldung.setFill(Color.RED);
		fmPasswort.setText("");
		fmBenutzername.setText("");
		fmFehlermeldung.setText("");

		if(event.getSource()==btLogin) 
		{
			if(AdminCheckLogin()) {
				Node source =(Node) event.getSource();
				Stage oldStage = (Stage) source.getScene().getWindow();
				oldStage.close();

				try {
					FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("AdminToolWindow.fxml"));
					Parent root = fxmlLoader.load();
					functions.createWindow(new Stage (),"Admin",event,new Scene(root));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}

			if( DataLogin.dateiKontrolle( tfName.getText(),pfPassword.getText() ) || LoginTest() ) {
				ProfilController.username = tfName.getText();
				ProfilController.password = pfPassword.getText();
				benutzername = tfName.getText();
				loginTest= true;

				try {
					FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("Startseite.fxml"));
					Parent root = fxmlLoader.load();
					functions.createWindow(new Stage (),"Startseite",event,new Scene(root));
				}catch(IOException iOException) {
					System.err.println("Fehler beim Laden von Startseite");
				}

			}

			if(pfPassword.getText().isEmpty()) {
	                fmPasswort.setText("Passwort vergessen?");
			}
			if(tfName.getText().isEmpty()) {
                fmBenutzername.setText("Benutzername vergessen?");
            }
			if(!pfPassword.getText().isEmpty() && !tfName.getText().isEmpty()) {
				 fmFehlermeldung.setText("Login ist fehlerhaft!");
			}
		}
	}

	
	@FXML
	 private void loadStartseiteGuest(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("StartseiteGuest.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		functions.createWindow(new Stage (),"Startseite Gast",event,new Scene(root));

	 }


	public boolean AdminCheckLogin(){
		if(tfName.getText().equals("Admin") && pfPassword.getText().equals("Film")) {
			return true;

		}
		return false;

	}
	public boolean LoginTest(){
		if(tfName.getText().equals("1")&&pfPassword.getText().equals("1")) {
			loginTest= true;
			return true;
			
		}
		return false;
		
	}
}
