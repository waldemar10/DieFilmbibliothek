package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import algorithmus.Sortieren;
import data.DataFilm;
import data.Watchlist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WatchlistController {
	@FXML
	private Button btFilminformation;
	@FXML
	private Button btWatchlistLaden;
	@FXML
	private Button btLöschen;
	@FXML
	private Button btZurück;
	@FXML
	private Button exitButton;
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

	static int count;
	static String titel;

	public void handleButtonClickInformationAction(MouseEvent event) {
		if (tabViewWatchliste.getSelectionModel().getSelectedItem() != null) {
			titel = tabViewWatchliste.getSelectionModel().getSelectedItem().getTitel();
		}

	}

	public void handleButtonInformationAction(ActionEvent event) {
		ArrayList<String> FilmeTitel = new ArrayList<>();
		DataFilm.FilmListeTitel(FilmeTitel);
		Sortieren.BubbleSort(FilmeTitel);
		if (titel != null) {
			for (int i = 0; i < FilmeTitel.size(); i++) {
				if (titel.equals(FilmeTitel.get(i))) {
					Node source = (Node) event.getSource();
					Stage oldStage = (Stage) source.getScene().getWindow();
					oldStage.close();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilmansichtWindow.fxml"));
					Parent root = null;
					try {
						root = (Parent) fxmlLoader.load();
					} catch (IOException e) {
						System.out.println("Fehler beim �ffnen der FilmansichtWindow.fxml");
						e.printStackTrace();
					}
					Stage stage = new Stage();
					String pfad = "/Image/LogoFilmbibliothek.png";
					Image image = new Image(pfad);
					stage.getIcons().add(image);
					stage.setTitle("Filmansicht: " + FilmeTitel.get(i));
					stage.setScene(new Scene(root));
					stage.show();
				}
			}
		}
	}

	public void handleButtonLöschenAction(ActionEvent event) {
		if (titel != null) {
			// Abfragen, ob man wirklich löschen möchte
			int eingabe = JOptionPane.showConfirmDialog(null, "Möchten Sie wirklich \n" + titel + " löschen?",
					"Löschen", JOptionPane.OK_CANCEL_OPTION);
			if (eingabe == 0) { // Wenn auf OK geklickt wird
				ArrayList<String> WatchlistMerke = new ArrayList<>();
				File watchlistAnwender = new File("WatchlistAnwender/Watchlist von " + LoginController.benutzername);
				// Löschen aus der TableView
				data.DatenbankFilme ausgewählteReihe = tabViewWatchliste.getSelectionModel().getSelectedItem();
				tabViewWatchliste.getItems().remove(ausgewählteReihe);
				// Speichern ohne des gelöschten Titels
				Watchlist.WatchlisteLesenLöschen(WatchlistMerke, watchlistAnwender, titel);
				// Datei löschen
				watchlistAnwender.delete();
				// Datei neu erstellen
				Watchlist.watchlistDatei(watchlistAnwender);
				// Datei befüllen
				for (int i = 0; i < WatchlistMerke.size(); i++) {
					Watchlist.watchlistSpeichernBeiLöschen(WatchlistMerke.get(i), watchlistAnwender);
				}
			}
		}

	}

	public static ObservableList<data.DatenbankFilme> getWatchlist() {
		ObservableList<data.DatenbankFilme> list = FXCollections.observableArrayList();
		DataFilm.zeileZählen();
		ArrayList<String> Watchliste = new ArrayList<>();
		ArrayList<String> Filmliste = new ArrayList<>();
		File watchlistAnwender = new File("WatchlistAnwender/Watchlist von " + LoginController.benutzername);

		Watchlist.WatchlisteLesen(Watchliste, watchlistAnwender);
		Sortieren.BubbleSort(Watchliste);

		Sortieren.Filmeauslesen(Filmliste);
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

	public void handleButtonWishlistLadenAction(ActionEvent event) {
		tcTitel.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Titel"));
		tcGenre.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Genre"));
		tcFsk.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Fsk"));
		tcRelease.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Release"));
		tcSchauspieler.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Schauspieler"));
		tcRegisseur.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Regisseur"));
		tcStreaming.setCellValueFactory(new PropertyValueFactory<data.DatenbankFilme, String>("Streaming"));
		tabViewWatchliste.setItems(getWatchlist());
	}

	public void handleButtonZurück(ActionEvent event) {
		if (event.getSource() == btZurück) {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Startseite.fxml"));
			Parent root = null;
			try {
				root = (Parent) fxmlLoader.load();
			} catch (IOException e) {
				System.out.println("Fehler beim öffnen der Startseite.fxml");
				e.printStackTrace();
			}
			Stage stage = new Stage();
			String pfad = "/Image/LogoFilmbibliothek.png";
			Image image = new Image(pfad);
			stage.getIcons().add(image);
			stage.setTitle("Startseite");
			stage.setScene(new Scene(root));
			stage.show();
			Node source = (Node) event.getSource();
			Stage oldStage = (Stage) source.getScene().getWindow();
			oldStage.close();
		}
	}
	public void onClick_exitButton() {
		
		  Stage stage = (Stage) exitButton.getScene().getWindow();
		  stage.close();
		 
	 }
}
