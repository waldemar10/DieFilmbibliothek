package application;

import java.io.IOException;
import java.util.ArrayList;

import algorithmus.Sortieren;
import algorithmus.SuchFunktion;
import data.DataFilm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartseiteController {

	public String geradeAusgewählt;

	@FXML
	private TabPane tpStartseite;
	@FXML
	private Tab tabDatenbank;
	@FXML
	private TextField tfSuche;
	@FXML
	private Tab tbFilmbibliothek;
	@FXML
	private Tab tbSuchleiste;
	@FXML
	private Label LabelFilme;
	@FXML
	private ListView<String> lwListView;
	@FXML
	private Button btMeinProfil;
	@FXML
	private Button btWatchlist;
	@FXML
	private Button btSuchen;
	@FXML
	private BorderPane mainPane;
	@FXML
	private Button exitButton;

	public void handleButtonMeinProfilAction(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginStartscreen.fxml"));
		Parent root = null;
		try {
			root = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			System.out.println("Fehler beim laden der Profil.fxml");
			e.printStackTrace();
		}
		Stage stage = new Stage();
		String pfad = "/Image/LogoFilmbibliothek.png";
		Image image = new Image(pfad);
		stage.getIcons().add(image);
		stage.setTitle("Mein Profil");
		stage.setScene(new Scene(root));
		stage.show();
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();
	}
	public void handleButtonWatchlistAction(ActionEvent event) {
		if (event.getSource() == btWatchlist) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Watchlist.fxml"));
			Parent root = null;
			try {
				root = (Parent) fxmlLoader.load();
			} catch (IOException e) {
				System.out.println("Fehler beim laden der Watchlist.fxml");
				e.printStackTrace();
			}
			Stage stage = new Stage();
			String pfad = "/Image/LogoFilmbibliothek.png";
			Image image = new Image(pfad);
			stage.getIcons().add(image);
			stage.setTitle("Watchlist");
			stage.setScene(new Scene(root));
			stage.show();
			Node source = (Node) event.getSource();
			Stage oldStage = (Stage) source.getScene().getWindow();
			oldStage.close();
		}
	}

	public void handleMouseClick(MouseEvent event) {

		lwListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					// F�r das Tab Suchleiste werden Mausklicks erkannt und zum jeweiligen Film
					// gef�hrt
					ArrayList<String> FilmeTitel = new ArrayList<>();
					DataFilm.FilmListeTitel(FilmeTitel);
					Sortieren.BubbleSort(FilmeTitel);

					geradeAusgewählt = lwListView.getSelectionModel().getSelectedItem();

					System.out.println(FilmeTitel.size());
					for (int i = 0; i < FilmeTitel.size(); i++) {
						if (geradeAusgewählt.equals(FilmeTitel.get(i))) {
							Node source = (Node) click.getSource();
							Stage oldStage = (Stage) source.getScene().getWindow();
							oldStage.close();
							FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilmansichtWindow.fxml"));
							Parent root = null;
							try {
								root = (Parent) fxmlLoader.load();
							} catch (IOException e) {
								System.out.println("Fehler beim laden der FilmansichtWindow.fxml");
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
		});

	}

	public Tab suchleisteTab(TabPane tabPane) {

		SingleSelectionModel<Tab> auswahl = tabPane.getSelectionModel();
		int tabIndex = (tpStartseite.getTabs().size() - 1);
		auswahl.select(tabIndex);
		return auswahl.getSelectedItem();
	}

	public void handleButtonSucheAction(ActionEvent event) {

		// Automatisch zum Tab Suchleiste wechseln
		suchleisteTab(tpStartseite);

		// listViewClick();
		ArrayList<String> FilmeTitel = new ArrayList<>();
		ArrayList<String> FilmeGenre = new ArrayList<>();
		ArrayList<String> FilmeFSK = new ArrayList<>();
		ArrayList<String> FilmeRelease = new ArrayList<>();
		ArrayList<String> FilmeSchauspieler = new ArrayList<>();
		ArrayList<String> FilmeRegisseur = new ArrayList<>();
		ArrayList<String> FilmeStreaming = new ArrayList<>();
		ArrayList<String> FilmeSuche = new ArrayList<>();

		// Die Suchen-Logik
		if (event.getSource() == btSuchen) {
			// Suchen der Filmtitel
			DataFilm.FilmListeTitel(FilmeTitel);
			Sortieren.BubbleSort(FilmeTitel);
			int gefundenTitel = SuchFunktion.binäreSuche(FilmeTitel, tfSuche.getText());
			if (gefundenTitel >= 0) {
				lwListView.getItems().clear();
				lwListView.getItems().addAll(FilmeTitel.get(gefundenTitel));
				return;
			}
			// Suchen der Genre
			DataFilm.FilmListeGenre(FilmeGenre);
			Sortieren.BubbleSort(FilmeGenre);
			int gefundenGenre = SuchFunktion.lineareSucheErweitert(FilmeGenre, tfSuche.getText());
			int stelleGenre = gefundenGenre * 7;
			if (gefundenGenre >= 0) {
				lwListView.getItems().clear();
				Sortieren.Filmeauslesen(FilmeSuche);
				Sortieren.BubbleSortGenre(FilmeSuche);
				for (int i = 0; i < SuchFunktion.count; i++) {
					int stelle = stelleGenre + (7 * i);
					lwListView.getItems().addAll(FilmeSuche.get(stelle));
				}
				SuchFunktion.count = 0;
				return;
			}
			// Suchen nach der FSK
			DataFilm.FilmListeFsk(FilmeFSK);
			Sortieren.BubbleSort(FilmeFSK);
			int gefundenFsk = SuchFunktion.lineareSucheErweitert(FilmeFSK, tfSuche.getText());
			int stelleFsk = gefundenFsk * 7;
			if (gefundenFsk >= 0) {
				lwListView.getItems().clear();
				Sortieren.Filmeauslesen(FilmeSuche);
				Sortieren.BubbleSortFSK(FilmeSuche);
				for (int i = 0; i < SuchFunktion.count; i++) {
					int stelle = stelleFsk + (7 * i);
					lwListView.getItems().addAll(FilmeSuche.get(stelle));
				}
				SuchFunktion.count = 0;
				return;
			}
			// Suchen nach Release
			DataFilm.FilmListeRelease(FilmeRelease);
			Sortieren.BubbleSort(FilmeRelease);
			int gefundenRelease = SuchFunktion.lineareSucheErweitert(FilmeRelease, tfSuche.getText());
			int stelleRelease = gefundenRelease * 7;
			if (gefundenRelease >= 0) {
				lwListView.getItems().clear();
				Sortieren.Filmeauslesen(FilmeSuche);
				Sortieren.BubbleSortDatum(FilmeSuche);
				for (int i = 0; i < SuchFunktion.count; i++) {
					int stelle = stelleRelease + (7 * i);
					lwListView.getItems().addAll(FilmeSuche.get(stelle));
				}
				SuchFunktion.count = 0;
				return;
			}
			// Suchen nach Schauspieler
			DataFilm.FilmListeSchauspieler(FilmeSchauspieler);
			Sortieren.BubbleSort(FilmeSchauspieler);
			int gefundenSchauspieler = SuchFunktion.lineareSucheErweitert(FilmeSchauspieler, tfSuche.getText());
			int stelleSchauspieler = gefundenSchauspieler * 7;
			if (gefundenSchauspieler >= 0) {
				lwListView.getItems().clear();
				Sortieren.Filmeauslesen(FilmeSuche);
				Sortieren.BubbleSortSpieler(FilmeSuche);
				for (int i = 0; i < SuchFunktion.count; i++) {
					int stelle = stelleSchauspieler + (7 * i);
					lwListView.getItems().addAll(FilmeSuche.get(stelle));
				}
				SuchFunktion.count = 0;
				return;
			}
			// Suchen nach Regisseur
			DataFilm.FilmListeRegisseur(FilmeRegisseur);
			Sortieren.BubbleSort(FilmeRegisseur);
			int gefundenRegisseur = SuchFunktion.lineareSucheErweitert(FilmeRegisseur, tfSuche.getText());
			int stelleRegisseur = gefundenRegisseur * 7;
			if (gefundenRegisseur >= 0) {
				lwListView.getItems().clear();
				Sortieren.Filmeauslesen(FilmeSuche);
				Sortieren.BubbleSortRegisseur(FilmeSuche);
				for (int i = 0; i < SuchFunktion.count; i++) {
					int stelle = stelleRegisseur + (7 * i);
					lwListView.getItems().addAll(FilmeSuche.get(stelle));
				}
				SuchFunktion.count = 0;
				return;
			}
			// Suchen nach Streaming
			DataFilm.FilmListeStreaming(FilmeStreaming);
			Sortieren.BubbleSort(FilmeStreaming);
			System.out.println(FilmeStreaming);
			int gefundenStreaming = SuchFunktion.lineareSucheErweitert(FilmeStreaming, tfSuche.getText());
			int stelleStreaming = gefundenStreaming * 7;
			if (gefundenStreaming >= 0) {
				lwListView.getItems().clear();
				Sortieren.Filmeauslesen(FilmeSuche);
				Sortieren.BubbleSortAnbieter(FilmeSuche);
				for (int i = 0; i < SuchFunktion.count; i++) {
					int stelle = stelleStreaming + (7 * i);
					lwListView.getItems().addAll(FilmeSuche.get(stelle));
				}
				SuchFunktion.count = 0;
				return;
			}

		}

	}
	public void onClick_exitButton() {
		
		 // get a handle to the stage
		  Stage stage = (Stage) exitButton.getScene().getWindow();
		  System.out.println(stage);
		  // do what you have to do
		  stage.close();
		 
	 }
}
