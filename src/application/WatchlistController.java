package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import algorithmus.Sortieren;
import algorithmus.SuchFunktion;
import data.DataFilm;
import data.Watchlist;
import functions.functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WatchlistController implements Initializable {

	@FXML
	private ImageView imageViewWatchlist;
	@FXML
	private Button btZurück;
	@FXML
	private TableView<data.DatenbankFilme> tabViewWatchliste;

	@FXML
	private TableColumn<data.DatenbankFilme, String> tcTitel;

	@FXML
	private TableColumn<data.DatenbankFilme, String> tcGenre;

	@FXML
	private TableColumn<data.DatenbankFilme, String> tcFsk;

	@FXML
	private TableColumn<data.DatenbankFilme, String> tcRelease;

	@FXML
	private TableColumn<data.DatenbankFilme, String> tcSchauspieler;

	@FXML
	private TableColumn<data.DatenbankFilme, String> tcRegisseur;

	@FXML
	private TableColumn<data.DatenbankFilme, String> tcStreaming;

	public String geradeAusgewählt;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		tabViewWatchliste.setStyle("-fx-base : #333333; -fx-background-color : gray");
		String pfad = "";
		if(getWatchlist().size() >0){
			geradeAusgewählt =getWatchlist().get(0).getTitel();
			ArrayList<String> FilmeExtra = new ArrayList<>();
			DataFilm.FilmeExtraAuslesen(FilmeExtra);
			int gefundenFilmeExtra = SuchFunktion.lineareSuche(FilmeExtra, geradeAusgewählt);
			int stelleImage = gefundenFilmeExtra + 3;
			pfad = FilmeExtra.get(stelleImage);

		}else{

			pfad = "/Image/NoImage.png";
		}

		Image image = new Image(pfad);
		imageViewWatchlist.setImage(image);

		handleButtonWatchlistLadenAction();
	}

	public void handleButtonClickInformationAction(MouseEvent event) throws IOException {
		/*if (tabViewWatchliste.getSelectionModel().getSelectedItem() != null) {
			titel = tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel();
		}*/
		if(tabViewWatchliste.getSelectionModel().getSelectedItem() != null) {


			geradeAusgewählt = tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel();
			ArrayList<String> FilmeExtra = new ArrayList<>();
			DataFilm.FilmeExtraAuslesen(FilmeExtra);

			int gefundenFilmeExtra = SuchFunktion.lineareSuche(FilmeExtra, geradeAusgewählt);
			int stelleImage = gefundenFilmeExtra + 3;

			String pfad = FilmeExtra.get(stelleImage);
			Image image = new Image(pfad);
			imageViewWatchlist.setImage(image);

			if (event.getClickCount() == 2 && tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel() != null) { // Doppelklick

				DatenbankFilmeController.headline = tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilmansichtWindow.fxml"));
				Parent root = fxmlLoader.load();
				functions.createWindowMouse(new Stage(), "Filmansicht: " +
						tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel(), event, new Scene(root));

			}
		}
	}

	public void handleButtonInformationAction(ActionEvent event) throws IOException {
		ArrayList<String> FilmeTitel = new ArrayList<>();
		DataFilm.FilmListeTitel(FilmeTitel);
		Sortieren.BubbleSort(FilmeTitel);

		if (geradeAusgewählt != null) {
			for (int i = 0; i < FilmeTitel.size(); i++) {
				if (geradeAusgewählt.equals(FilmeTitel.get(i))) {

					DatenbankFilmeController.headline = FilmeTitel.get(i);

					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilmansichtWindow.fxml"));
					Parent root = fxmlLoader.load();
					functions.createWindow(new Stage (),"Filmansicht: " + FilmeTitel.get(i),event,new Scene(root));
				}
			}
		}
	}

	public void handleButtonLöschenAction() {

		if (tabViewWatchliste.getSelectionModel().getSelectedItem() != null) {

			String deleteThisMovie = tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel();
			data.DatenbankFilme deleteThisRow = tabViewWatchliste.getSelectionModel().getSelectedItem();

			// Abfragen, ob man wirklich löschen möchte
			int eingabe = JOptionPane.showConfirmDialog(null, "Möchten Sie wirklich \n" +
							tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel() + " löschen?",
					"Löschen", JOptionPane.OK_CANCEL_OPTION);

			if (eingabe == 0) { // Wenn auf OK geklickt wird
				ArrayList<String> WatchlistMerke = new ArrayList<>();
				File watchlistAnwender = new File("WatchlistAnwender/Watchlist von " + LoginController.benutzername+".txt");
				// Löschen aus der TableView
				//data.DatenbankFilme ausgewählteReihe = tabViewWatchliste.getSelectionModel().getSelectedItem();
				tabViewWatchliste.getItems().remove(deleteThisRow);

				// Speichern ohne des gelöschten Titels
				Watchlist.WatchlisteLesenLöschen(WatchlistMerke, watchlistAnwender,deleteThisMovie);

				// Datei löschen
				watchlistAnwender.delete();
				// Datei neu erstellen
				Watchlist.watchlistDatei(watchlistAnwender);
				// Datei befüllen
				for (int i = 0; i < WatchlistMerke.size(); i++) {
					Watchlist.watchlistSpeichernBeiLöschen(WatchlistMerke.get(i), watchlistAnwender);
				}
			}

			String pfad = "";
			if(getWatchlist().size() >0){
				geradeAusgewählt =getWatchlist().get(0).getTitel();
				ArrayList<String> FilmeExtra = new ArrayList<>();
				DataFilm.FilmeExtraAuslesen(FilmeExtra);
				int gefundenFilmeExtra = SuchFunktion.lineareSuche(FilmeExtra, geradeAusgewählt);
				int stelleImage = gefundenFilmeExtra + 3;
				pfad = FilmeExtra.get(stelleImage);
			}else{

				pfad = "/Image/NoImage.png";
			}
			Image image = new Image(pfad);
			imageViewWatchlist.setImage(image);

			geradeAusgewählt = null;

			handleButtonWatchlistLadenAction();
		}

	}

	public static ObservableList<data.DatenbankFilme> getWatchlist() {
		ObservableList<data.DatenbankFilme> list = FXCollections.observableArrayList();
		DataFilm.zeileZählen();
		ArrayList<String> Watchliste = new ArrayList<>();
		ArrayList<String> Filmliste = new ArrayList<>();
		File watchlistAnwender = new File("WatchlistAnwender/Watchlist von " + LoginController.benutzername+".txt");

		Watchlist.WatchlisteLesen(Watchliste, watchlistAnwender);
		Sortieren.BubbleSort(Watchliste);

		DataFilm.Filmeauslesen(Filmliste);
		Sortieren.BubbleSortTitel(Filmliste);

		int[] speichern = new int[Watchliste.size()];
		int merke = -1;
		for (int j = 0; j < Watchliste.size(); j++) {
			for (int k = 0; k < Filmliste.size(); k++) {
				if (Watchliste.get(j).compareToIgnoreCase(Filmliste.get(k)) == 0) {
					merke = k;
					speichern[j] = merke;
				}

			}
		}

		for (int k = 0; k < speichern.length; k++) {
			String filmtitel = Filmliste.get(speichern[k]);
			String genre = Filmliste.get(speichern[k] + 1);
			String fsk = Filmliste.get(speichern[k] + 2);
			String release = Filmliste.get(speichern[k] + 3);
			String schauspieler = Filmliste.get(speichern[k] + 4);
			String regisseur = Filmliste.get(speichern[k] + 5);
			String streaming = Filmliste.get(speichern[k] + 6);
			list.add(new data.DatenbankFilme(filmtitel, genre, fsk, release, schauspieler, regisseur, streaming));
		}

		return list;
	}

	public void handleButtonWatchlistLadenAction() {
		tcTitel.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Titel"));
		tcGenre.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Genre"));
		tcFsk.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Fsk"));
		tcRelease.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Release"));
		tcSchauspieler.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Schauspieler"));
		tcRegisseur.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Regisseur"));
		tcStreaming.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Streaming"));
		tabViewWatchliste.setItems(getWatchlist());
	}

	public void handleButtonZurück(ActionEvent event) throws IOException {
		if (event.getSource() == btZurück) {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Startseite.fxml"));
			Parent root = fxmlLoader.load();
			functions.createWindow(new Stage (),"Startseite",event,new Scene(root));
		}
	}
}
