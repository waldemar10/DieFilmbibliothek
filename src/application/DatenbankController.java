package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

import data.DataLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DatenbankController implements Initializable {
	@FXML
	private TableView<data.DatenbankLogin> tabViewDatenbank;
	@FXML
	private TableColumn<data.DatenbankLogin, String> tcBenutzername;
	@FXML
	private TableColumn<data.DatenbankLogin, String> tcPasswort;
	@FXML
	private TableColumn<data.DatenbankLogin, String> tcEmail;
	
	
	static int count;
	public static void zeileZählen() {
		try {
			count=0;
			try (RandomAccessFile randomAccessFile= new RandomAccessFile(DataLogin.LoginDaten, "r")) {
				for(;randomAccessFile.readLine()!=null;){
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ObservableList<data.DatenbankLogin> getDatenbank()
	{
		ObservableList<data.DatenbankLogin> list = FXCollections.observableArrayList();
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(DataLogin.LoginDaten, "r")) {
				for(int i=0;i<count;i+=4){
				

				String benutzer = randomAccessFile3.readLine().substring(13);
				
				String passwort = randomAccessFile3.readLine().substring(9);
				
				String email = randomAccessFile3.readLine().substring(6);
				list.add(new data.DatenbankLogin(benutzer,passwort,email));
				randomAccessFile3.readLine();
				
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		tcBenutzername.setCellValueFactory(new PropertyValueFactory<data.DatenbankLogin, String>("Benutzername"));
        tcPasswort.setCellValueFactory(new PropertyValueFactory<data.DatenbankLogin, String>("Passwort"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<data.DatenbankLogin, String>("Email"));
        tabViewDatenbank.setItems(getDatenbank());
        
		
	}
	

}
