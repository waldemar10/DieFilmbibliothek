package application;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;

import algorithmus.Sortieren;
import algorithmus.SuchFunktion;
import data.DataFilm;
import data.Watchlist;
import functions.functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static application.DatenbankFilmeController.headline;

public class FilmansichtController implements Initializable {
	@FXML
	private ImageView imageView;
	@FXML
	private Text txFilmname;
	@FXML
	private TextArea tfFilmtitel;
	@FXML
	private TextField tfGenre;
	@FXML
	private TextField tfRelease;
	@FXML
	private TextField tfFsk;
	@FXML
	private TextField tfJahr;
	@FXML
	private TextField tfLaufzeit;
	@FXML
	private TextField tfRegisseur;
	@FXML
	private TextField tfSchauspieler;
	@FXML
	private TextArea taHandlung;
	@FXML
	private Button btZurück;
	@FXML
	private Button btWatchlistSpeichern;
	@FXML
	private Button btTrailer;
	@FXML
	private Button btStreaming;
	@FXML
	private Button btLaden;
	@FXML
	private Button exitButton;

	public static boolean check; // Watchlist und Trailer button funktioniert erst, wenn es true ist

	public void handleButtonZurück(ActionEvent event) throws IOException {
		if (event.getSource() == btZurück) {
			// Falls man sich eingeloggt hat, kommt man zurück zur normalen Startseite
			if (LoginController.loginTest == true) {
				check = false;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Startseite.fxml"));
				Parent root = fxmlLoader.load();
				functions.createWindow(new Stage (),"Startseite",event,new Scene(root));
			} 
			else 
			{
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartseiteGuest.fxml"));
				Parent root =  fxmlLoader.load();
				functions.createWindow(new Stage (),"Startseite Gast",event,new Scene(root));
			}
		}
	}

	public void handleLadenButton(String title) {
		if(title!=null) {
			ArrayList<String> FilmeSuche = new ArrayList<>();
			ArrayList<String> FilmeExtra = new ArrayList<>();

			// Um die Fensterüberschrift zu bekommen
		/*Node node = (Node) event.getSource();
		Stage thisStage = (Stage) node.getScene().getWindow();
		headline = thisStage.getTitle().substring(13);*/

			DataFilm.Filmeauslesen(FilmeSuche);
			int gefunden = SuchFunktion.lineareSuche(FilmeSuche, title);
			int stelleFilmtitel = gefunden + 0;
			int stelleGenre = gefunden + 1;
			int stelleFsk = gefunden + 2;
			int stelleRelease = gefunden + 3;
			int stelleSchauspieler = gefunden + 4;
			int stelleRegisseur = gefunden + 5;
			int stelleStreaming = gefunden + 6;

			DataFilm.FilmeExtraAuslesen(FilmeExtra);

			int gefundenFilmeExtra = SuchFunktion.lineareSuche(FilmeExtra, title);
			int stelleHandlung = gefundenFilmeExtra + 2;
			int stelleImage = gefundenFilmeExtra + 3;
			int stelleLaufzeit = gefundenFilmeExtra + 4;

			tfSchauspieler.setText(FilmeSuche.get(stelleSchauspieler));
			tfRegisseur.setText(FilmeSuche.get(stelleRegisseur));
			tfFilmtitel.setText(FilmeSuche.get(stelleFilmtitel));
			tfRelease.setText(FilmeSuche.get(stelleRelease));
			tfFsk.setText(FilmeSuche.get(stelleFsk));
			tfGenre.setText(FilmeSuche.get(stelleGenre));
			btStreaming.setText("Zu " + FilmeSuche.get(stelleStreaming));
			tfJahr.setText("(" + FilmeSuche.get(stelleRelease).substring(6) + ")");
			taHandlung.setText(FilmeExtra.get(stelleHandlung));
			tfLaufzeit.setText(FilmeExtra.get(stelleLaufzeit));
			txFilmname.setText(FilmeSuche.get(stelleFilmtitel));

			String pfad = FilmeExtra.get(stelleImage);
			Image image = new Image(pfad);
			imageView.setImage(image);


			btStreaming.setVisible(true);
			btTrailer.setVisible(true);
			if (LoginController.loginTest == true) {
				btWatchlistSpeichern.setVisible(true);
			}

			// Check, um die Button Funktionsfähig zu machen, wenn die Filmdaten geladen werden

			check = true;
		}
	}

	public void handleMouseClickTrailer(MouseEvent event) {
		if (check == true) {
			ArrayList<String> FilmeTrailer = new ArrayList<>();
			Node node = (Node) event.getSource();
			Stage thisStage = (Stage) node.getScene().getWindow();
			headline = thisStage.getTitle().substring(13); // Die überschrift des Fensters bekommen, um zu
															// wissen, welcher Titel abgerufen wird
			DataFilm.FilmeExtraAuslesen(FilmeTrailer);
			int gefunden = SuchFunktion.lineareSuche(FilmeTrailer, headline);
			int stelleLink = gefunden + 1; // +1 um die Stelle des Trailers im File 'FilmeExtra.txt' zu finden
			try {
				// Den Standardbrowser zum öffnen der URL
				Desktop.getDesktop().browse(new URI(FilmeTrailer.get(stelleLink)));

			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}

	}

	public void handleButtonWatchlistAction(ActionEvent event) {
		if (check == true) {
			if (event.getSource() == btWatchlistSpeichern) {
				File watchlistAnwender = new File("WatchlistAnwender/Watchlist von " + LoginController.benutzername+".txt");
				Watchlist.watchlistDatei(watchlistAnwender);
				Node node = (Node) event.getSource();
				Stage thisStage = (Stage) node.getScene().getWindow();
				///headline = thisStage.getTitle().substring(13);
				Watchlist.watchlistSpeichern(headline, watchlistAnwender, btWatchlistSpeichern);
			}
		}

	}

	public void handleStreamingButton(MouseEvent event) {
		// Es gibt vier fest definierte Streaminganbieter
		if (btStreaming.getText().equals("Zu Netflix")) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.netflix.com/de/"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (btStreaming.getText().equals("Zu Amazon Prime Video")) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.primevideo.com/?_encoding=UTF8&language=de_DE"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (btStreaming.getText().equals("Zu Joyn")) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.joyn.de/"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (btStreaming.getText().equals("Zu Disney Plus")) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.disneyplus.com/de-de"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Buttons werden erst angezeigt, wenn man die Filmdaten geladen hat
		btStreaming.setVisible(false);
		btWatchlistSpeichern.setVisible(false);
		btTrailer.setVisible(false);
		handleLadenButton(headline);
	}

}
