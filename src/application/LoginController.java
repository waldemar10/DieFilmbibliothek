package application;


import java.io.IOException;
import data.DataLogin;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginController {

	 @FXML
	    private Button loginButtonHeader;
	 @FXML
	    private Button registrierenButtonHeader;
	 @FXML
	    private Button sofortzugang;
	 @FXML
	 	private AnchorPane rootPane;
	 @FXML
	    private Button exitButton;
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
	private Label LabelWrongLogin;
	@FXML
	private PasswordField pfPassword;
	@FXML
	private Button btLogin;
	@FXML
	private Button btRegistrierung;
	
	@FXML
	 private void loadRegistration(ActionEvent event) throws IOException {
		 AnchorPane pane = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		 rootPane.getChildren().setAll(pane);
	 }
	
	@FXML
	private void handleButtonLoginAction(ActionEvent event) throws IOException {
		fmPasswort.setFill(Color.RED);
		fmBenutzername.setFill(Color.RED);
		fmFehlermeldung.setFill(Color.RED);
		if(event.getSource()==btLogin) 
		{
			if(AdminCheckLogin()==true) {
				Node source =(Node) event.getSource();
				Stage oldStage = (Stage) source.getScene().getWindow();
				oldStage.close();

				try {
					FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("AdminToolWindow.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage ();
					String pfad = "/Image/LogoFilmbibliothek.png";
					Image image = new Image(pfad);
					stage.getIcons().add(image);
					stage.setTitle("Admin Tool");
					stage.setScene(new Scene(root));
					stage.show();
				}catch(IOException e) {
					e.printStackTrace();

				}
			}
			if(DataLogin.dateiKontrolle(tfName.getText(),pfPassword.getText())==true||LoginTest()==true) {
				  ProfilController.username = tfName.getText();
	                ProfilController.password = pfPassword.getText();
				benutzername = tfName.getText();
				loginTest= true;
				//altes Fenster schlie�en
				Node source =(Node) event.getSource();
				Stage oldStage = (Stage) source.getScene().getWindow();
				oldStage.close();
				
				try {
					System.out.println("Login");
					FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("Startseite.fxml"));
					Parent root = (Parent) fxmlLoader.load();
					Stage stage = new Stage ();
					String pfad = "/Image/LogoFilmbibliothek.png";
					Image image = new Image(pfad);
					stage.getIcons().add(image);
					stage.setTitle("Startseite");
					stage.setScene(new Scene(root));
					stage.show();
				}catch(IOException iOException) {


				}
			}else if(pfPassword.getText().isEmpty()) {
				
	                fmPasswort.setText("Passwort vergessen?");
	                fmBenutzername.setText("");
				
			}else if(tfName.getText().isEmpty()) {
                
                fmBenutzername.setText("Benutzername vergessen?");
                fmPasswort.setText("");


            }else {
				 fmFehlermeldung.setText("Login ist fehlerhaft!");
	                fmPasswort.setText("");
	                fmBenutzername.setText("");
				
			}
		}
	}


	public void onClick_exitButton() {
		
		  Stage stage = (Stage) exitButton.getScene().getWindow();
		  stage.close();
		 
	 }
	
	@FXML
	 private void loadStartseiteGuest(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("StartseiteGuest.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		Stage stage = new Stage ();
		String pfad = "/Image/LogoFilmbibliothek.png";
		Image image = new Image(pfad);
		stage.getIcons().add(image);
		stage.setTitle("Startseite Gast");
		stage.setScene(new Scene(root));
		stage.show();
		Node source =(Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
	 }


	public boolean AdminCheckLogin() throws IOException{
		if(tfName.getText().toString().equals("Admin")&&pfPassword.getText().toString().equals("Cinox")) {
			return true;
			
		}
		return false;
		
	}
	public boolean LoginTest() throws IOException{
		if(tfName.getText().toString().equals("1")&&pfPassword.getText().toString().equals("1")) {		
			loginTest= true;
			return true;
			
		}
		return false;
		
	}
}
