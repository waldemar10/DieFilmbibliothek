package application;

import java.io.File;
import java.io.IOException;

import data.DataLogin;
import data.Watchlist;
import functions.functions;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegistrierungController {
	public static int passwortlängeMin = 8;
	public static int passwortlängeMax = 16;
	public static int benutzernamelängeMin = 3;
	public static int benutzernamelängeMax = 20;
	@FXML
	private TextField tfUsername;
	@FXML
	private TextField tfEmail;
	@FXML
	private Label LabelWrongRegistrierung;
	@FXML
	private PasswordField pfPassword;
	@FXML
	private Button btRegistrierung;
	@FXML
	private Button btBack;
	@FXML
	private Text txPasswortFehler;
	@FXML
	private Text txEmailWiederholung;
	@FXML
	private Text txEmailFehler;
	@FXML
	private Text txBenutzerFehler;
	@FXML
	private Button exitButton;
	 @FXML
		private AnchorPane rootPane;
		
		@FXML
		private AnchorPane parent;
	
	@FXML
	private void handleButtonRegistrierungAction(ActionEvent event) throws IOException {
		DataLogin.dateiErstellen();
		if(event.getSource()==btRegistrierung)
		{
			System.out.println(tfUsername.getText()+ " " + pfPassword.getText()+" "+tfEmail.getText());
			if(tfUsername.getText().isEmpty()||pfPassword.getText().isEmpty()||tfEmail.getText().isEmpty()) {
				LabelWrongRegistrierung.setText("Fehlende Angaben");
				return;
			}
			
			if(dateiKontrolleBenutzername(tfUsername.getText())==true) {
				txPasswortFehler.setText("Passwort ist falsch.");
				return;
			}
			//Erfolgreiche Registrierung
			if(DataLogin.dateiKontrolleRegistrierung(tfUsername.getText(),tfEmail.getText())==false&&dateiKontrollePasswort(pfPassword.getText())==false)
			{
				
				File WatchlistAnwender = new File("WatchlistAnwender/Watchlist von "+tfUsername.getText().toUpperCase());
				Watchlist.watchlistDatei(WatchlistAnwender);
				LoginController.benutzername = tfUsername.getText();
				DataLogin.dateiSpeichern(tfUsername.getText(),pfPassword.getText(),tfEmail.getText());
				AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginStartscreen.fxml"));
				rootPane.getChildren().setAll(pane);
			
			}else if(DataLogin.dateiKontrolleRegistrierung(tfUsername.getText(),tfEmail.getText())==true) {
				LabelWrongRegistrierung.setText("Benutzername oder Email schon vergeben.");
				return;
			}else if(dateiKontrollePasswort(pfPassword.getText())==true) {
				txPasswortFehler.setText("Passwort ist falsch.");
				
				return;
			}
		
		}
	}
	

	
	@FXML
	 private void loadStartseiteGuest(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader (getClass().getResource("StartseiteGuest.fxml"));
		Parent root = fxmlLoader.load();
		functions.createWindow(new Stage (),"Startseite Gast",event,new Scene(root));
	 }
	
	 @FXML
	 private void loadLogin(ActionEvent event) throws IOException {
		 AnchorPane pane = FXMLLoader.load(getClass().getResource("LoginStartscreen.fxml"));
		 rootPane.getChildren().setAll(pane);
	 }
	
public static boolean dateiKontrollePasswort(String passwort){
		
		if(passwort.length()<passwortlängeMin||passwort.length()>passwortlängeMax){
			return true;
		}
		
		char kontrolle;
		int countKleinbuchstaben = 0;
		int countGroßbuchstaben = 0;
		int countZahl = 0;
		int countSonderzeichen = 0;
		for(int i = 0; i<passwort.length();i++) {
			kontrolle = passwort.charAt(i);
			if (Character.isLowerCase(kontrolle)){
				countKleinbuchstaben++;
				}
			if(Character.isUpperCase(kontrolle)) {
				countGroßbuchstaben++;
			}
			if (Character.isDigit(kontrolle)){
				countZahl++;
			}
			if(kontrolle>=33&&kontrolle<=38) {//ASCII Tabelle: 33 = !, 34 = ", 35 = #, 36 = $, 37 = %, 38 = &
				countSonderzeichen++;
			}
		}
		if(countKleinbuchstaben==0) {
			return true;
		}else if(countGroßbuchstaben==0) {
			return true;
		}else if(countZahl==0){
			return true;
		}else if(countSonderzeichen==0) {
			return true;
		}
		
		return false;
	}

public static boolean dateiKontrolleBenutzername(String benutzername){
	char kontrolle;
	if(benutzername.length()<benutzernamelängeMin||benutzername.length()>benutzernamelängeMax){
		return true;
	}
	int countRichtig = 0;
	for(int i = 0; i<benutzername.length();i++) {
		kontrolle = benutzername.charAt(i);
		//ASCII Tabelle: 65-90 = A-Z ** 48-57 = 0-9 ** 97-122 = a-z
		if((kontrolle>=65&&kontrolle<=90)||(kontrolle>=48&&kontrolle<=57)||(kontrolle>=97&&kontrolle<=122)) {
			countRichtig++;
		}
	}
	if(countRichtig != benutzername.length()) {
		return true;
	}
	return false;
	}
public void onClick_exitButton() {
	
	  Stage stage = (Stage) exitButton.getScene().getWindow();
	  stage.close();
	 
}
}
