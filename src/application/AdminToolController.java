package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import algorithmus.Sortieren;
import data.DataFilm;
import data.DataLogin;
import data.Watchlist;
import functions.functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AdminToolController implements Initializable {
	@FXML
	private ListView<String> lwListViewFilme;
	@FXML
	private TextField tfFilmtitel;
	@FXML
	private TextField tfGenre;
	@FXML
	private TextField tfErscheinungsdatum;
	@FXML
	private TextField tfHauptdarsteller;
	@FXML
	private TextField tfRegisseur;
	@FXML
	private TextField tfStreaminganbieter;
	@FXML
	private TextField tfFsk;
	@FXML
	private TextField tfLaufzeit;
	@FXML
	private TextField tfImage;
	@FXML
	private TextField tfTrailer;
	@FXML
	private TextArea taHandlung;
	@FXML
	private TextArea taKonsole;

	public static String titel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lwListViewFilme.getItems().clear();
		ArrayList<String> FilmListe = new ArrayList<>();
		DataFilm.FilmListeTitel(FilmListe);
		Sortieren.BubbleSort(FilmListe);
		lwListViewFilme.getItems().addAll(FilmListe);
	}

	public void handleButtonClickInformationAction() {

		if (lwListViewFilme.getSelectionModel().getSelectedItem() != null) {
			titel = lwListViewFilme.getSelectionModel().getSelectedItem();
		}
		ArrayList<String> AdminToolUpdateFilm = new ArrayList<>();
		DataFilm.Filmeauslesen(AdminToolUpdateFilm);

		for (int i = 0; i < AdminToolUpdateFilm.size(); i++) {
			if (titel.equals(AdminToolUpdateFilm.get(i))) {
				tfFilmtitel.setText(AdminToolUpdateFilm.get(i));
				tfGenre.setText(AdminToolUpdateFilm.get(i + 1));
				tfFsk.setText(AdminToolUpdateFilm.get(i + 2));
				tfErscheinungsdatum.setText(AdminToolUpdateFilm.get(i + 3));
				tfHauptdarsteller.setText(AdminToolUpdateFilm.get(i + 4));
				tfRegisseur.setText(AdminToolUpdateFilm.get(i + 5));
				tfStreaminganbieter.setText(AdminToolUpdateFilm.get(i + 6));
			}
		}
		ArrayList<String> AdminToolUpdateFilmExtra = new ArrayList<>();
		DataFilm.FilmeExtraAuslesen(AdminToolUpdateFilmExtra);

		for (int i = 0; i < AdminToolUpdateFilmExtra.size(); i++) {
			if (titel.equals(AdminToolUpdateFilmExtra.get(i))) {
				tfTrailer.setText(AdminToolUpdateFilmExtra.get(i + 1));
				taHandlung.setText(AdminToolUpdateFilmExtra.get(i + 2));
				tfImage.setText(AdminToolUpdateFilmExtra.get(i + 3));
				tfLaufzeit.setText(AdminToolUpdateFilmExtra.get(i + 4));
			}
		}

	}

	public void handleButtonUpdateAction() {

		ArrayList<String> AdminToolUpdateFilm = new ArrayList<>();
		DataFilm.FilmLesenUpdate(AdminToolUpdateFilm);
		ArrayList<String> AdminToolUpdateFilmExtra = new ArrayList<>();
		DataFilm.FilmExtraLesenUpdate(AdminToolUpdateFilmExtra);

		DataFilm.FilmDaten.delete();
		DataFilm.FilmeExtra.delete();

		DataFilm.dateiErstellen();

		// Speicher an der Position des geänderten Films die Werte ein
		for (int i = 0; i < AdminToolUpdateFilm.size(); i++) {
			String zusatzTitel = ("Filmtitel:" + titel);
			if (zusatzTitel.equals(AdminToolUpdateFilm.get(i))) {
				AdminToolUpdateFilm.set(i, "Filmtitel:" + tfFilmtitel.getText());
				AdminToolUpdateFilm.set(i + 1, "Genre:" + tfGenre.getText());
				AdminToolUpdateFilm.set(i + 2, "FSK:" + tfFsk.getText());
				AdminToolUpdateFilm.set(i + 3, "Release:" + tfErscheinungsdatum.getText());
				AdminToolUpdateFilm.set(i + 4, "Schauspieler:" + tfHauptdarsteller.getText());
				AdminToolUpdateFilm.set(i + 5, "Regisseur:" + tfRegisseur.getText());
				AdminToolUpdateFilm.set(i + 6, "Streaming:" + tfStreaminganbieter.getText());
			}
		}


		// Speichern des geänderten Films in die Datei Filme.txt
		for (int i = 0; i < AdminToolUpdateFilm.size(); i += 7) {
			DataFilm.filmSpeichernOhneZeile(AdminToolUpdateFilm.get(i), AdminToolUpdateFilm.get(i + 1),
					AdminToolUpdateFilm.get(i + 2), AdminToolUpdateFilm.get(i + 3), AdminToolUpdateFilm.get(i + 4),
					AdminToolUpdateFilm.get(i + 5), AdminToolUpdateFilm.get(i + 6));
		}

		// Speicher an der Position des geänderten Films die Werte ein, für die
		// FilmExtra Datei
		for (int i = 0; i < AdminToolUpdateFilmExtra.size(); i++) {
			if (titel.equals(AdminToolUpdateFilmExtra.get(i))) {
				AdminToolUpdateFilmExtra.set(i, tfFilmtitel.getText());
				AdminToolUpdateFilmExtra.set(i + 1, tfTrailer.getText());
				AdminToolUpdateFilmExtra.set(i + 2, taHandlung.getText().replaceAll("\n", ""));
				AdminToolUpdateFilmExtra.set(i + 3, tfImage.getText());
				AdminToolUpdateFilmExtra.set(i + 4, tfLaufzeit.getText());
			}

		}

		// Speichern des geänderten Films in die Datei FilmeExtra.txt
		for (int i = 0; i < AdminToolUpdateFilmExtra.size(); i += 5) {
			DataFilm.filmExtraSpeichernOhneZeile(AdminToolUpdateFilmExtra.get(i), AdminToolUpdateFilmExtra.get(i + 1),
					AdminToolUpdateFilmExtra.get(i + 2), AdminToolUpdateFilmExtra.get(i + 3),
					AdminToolUpdateFilmExtra.get(i + 4));
		}

		taKonsole.setText("Film " + titel + " wurde erfolgreich aktualisiert.");

	}

	public void handleButtonLogoutAction(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginStartscreen.fxml"));
			Parent root = fxmlLoader.load();
			functions.createWindow(new Stage (),"Login",event,new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void handleButtonRefreshAction(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage oldStage = (Stage) source.getScene().getWindow();
		oldStage.close();

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminToolWindow.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			functions.createWindow(new Stage (),"Admin",event,new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void handleButtonHinzufügenAction(ActionEvent event) {

		taKonsole.setText("");

		if (tfFilmtitel.getText().isEmpty() || tfGenre.getText().isEmpty() || tfErscheinungsdatum.getText().isEmpty()
				|| tfHauptdarsteller.getText().isEmpty() || tfRegisseur.getText().isEmpty()
				|| tfStreaminganbieter.getText().isEmpty() || tfFsk.getText().isEmpty() || tfTrailer.getText().isEmpty()
				|| taHandlung.getText().isEmpty() || tfImage.getText().isEmpty() || tfLaufzeit.getText().isEmpty()) {
			taKonsole.setText("Hinzufügen NICHT erfolgreich. Mindestens ein Feld ist Leer!");
			return;
		}
		ArrayList<String> FilmeSuche = new ArrayList<>();
		DataFilm.Filmeauslesen(FilmeSuche);
		if (DataFilm.filmKontrolle(FilmeSuche, tfFilmtitel.getText()) == false) {
			// Speichern des neuen Films
			DataFilm.filmSpeichern(tfFilmtitel.getText(), tfGenre.getText(), tfFsk.getText(),
					tfErscheinungsdatum.getText(), tfHauptdarsteller.getText(), tfRegisseur.getText(),
					tfStreaminganbieter.getText());
			DataFilm.filmExtraSpeichern(tfFilmtitel.getText(), tfTrailer.getText(), taHandlung.getText(),
					tfImage.getText(), tfLaufzeit.getText());
			taKonsole.setText("Film " + tfFilmtitel.getText() + " wurde erfolgreich hinzugefügt.");
		} else {
			taKonsole.setText("Film schon in der Filmbibliothek!");
			return;
		}
	}

	public void handleButtonLöschenAction(ActionEvent event) {
		// Abfragen, ob man wirklich löschen möchte
		if (titel != null) {
			int eingabe = JOptionPane.showConfirmDialog(null, "Möchten Sie wirklich \n" + titel + " löschen?",
					"Löschen", JOptionPane.OK_CANCEL_OPTION);
			if (eingabe == 0) { // Wenn auf OK geklickt wird

				ArrayList<String> FilmMerke = new ArrayList<>();
				ArrayList<String> FilmExtraMerke = new ArrayList<>();

				// Löschen aus der ListView
				String ausgewählteReihe = lwListViewFilme.getSelectionModel().getSelectedItem();
				lwListViewFilme.getItems().remove(ausgewählteReihe);

				// Speichern der Inhalte der Datei, außer des gelöschten Titels
				DataFilm.FilmLesenLöschen(FilmMerke, DataFilm.FilmDaten, titel);
				DataFilm.FilmExtraLesenLöschen(FilmExtraMerke, DataFilm.FilmeExtra, titel);

				// Löschen der Datei
				DataFilm.FilmDaten.delete();
				DataFilm.FilmeExtra.delete();

				// Neue und leere Dateien erstellen
				DataFilm.dateiErstellen();
				// Bef�llen der neuen erstellten Datei
				for (int i = 0; i < FilmMerke.size(); i += 7) {
					DataFilm.filmSpeichernOhneZeile(FilmMerke.get(i), FilmMerke.get(i + 1), FilmMerke.get(i + 2),
							FilmMerke.get(i + 3), FilmMerke.get(i + 4), FilmMerke.get(i + 5), FilmMerke.get(i + 6));
				}
				for (int i = 0; i < FilmExtraMerke.size(); i += 5) {
					DataFilm.filmExtraSpeichernOhneZeile(FilmExtraMerke.get(i), FilmExtraMerke.get(i + 1),
							FilmExtraMerke.get(i + 2), FilmExtraMerke.get(i + 3), FilmExtraMerke.get(i + 4));
				}

				ArrayList<String> LoginDaten = new ArrayList<>();
				DataLogin.dateiAuslesen(LoginDaten);
				// L�schen des Films in allen Watchlisten
				for (int k = 0; k < LoginDaten.size(); k += 3) { // +3 Um in der File Data/Login die benutzernamen zu
																	// finden
					String benutzername = LoginDaten.get(k);
					ArrayList<String> WatchlistMerke = new ArrayList<>();
					File watchlistAnwender = new File("WatchlistAnwender/Watchlist von " + benutzername);
					// Speichern ohne des gelöschten Titels
					Watchlist.WatchlisteLesenLöschen(WatchlistMerke, watchlistAnwender, titel);
					// Datei löschen
					watchlistAnwender.delete();
					// Datei erstellen
					Watchlist.watchlistDatei(watchlistAnwender);
					// Datei wieder befüllen
					for (int i = 0; i < WatchlistMerke.size(); i++) {
						Watchlist.watchlistSpeichernBeiLöschen(WatchlistMerke.get(i), watchlistAnwender);
					}
				}
			}
		}

	}
}
