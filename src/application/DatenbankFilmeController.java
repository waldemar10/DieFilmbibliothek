package application;

//import com.jfoenix.controls.JFXTreeView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;


import data.DataFilm;
import data.DatenbankFilme;
import functions.functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class DatenbankFilmeController implements Initializable {
	@FXML
	private TableView<DatenbankFilme> tabViewDatenbankFilme;

	@FXML
	private TableColumn<DatenbankFilme, String> tcTitel;

	@FXML
	private TableColumn<DatenbankFilme, String> tcGenre;

	@FXML
	private TableColumn<DatenbankFilme, String> tcFsk;

	@FXML
	private TableColumn<DatenbankFilme, String> tcRelease;

	@FXML
	private TableColumn<DatenbankFilme, String> tcSchauspieler;

	@FXML
	private TableColumn<DatenbankFilme, String> tcRegisseur;

	@FXML
	private TableColumn<DatenbankFilme, String> tcStreaming;

	public static int count;


	@FXML
	public void clickZeile(MouseEvent event) throws IOException {
		if (event.getClickCount() == 2 && tabViewDatenbankFilme.getSelectionModel().getSelectedItem() != null) // Für
																												// Doppelklick
		{

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilmansichtWindow.fxml"));
			Parent root = fxmlLoader.load();
			functions.createWindowMouse(new Stage (),
					("Filmansicht: " + tabViewDatenbankFilme.getSelectionModel().getSelectedItem().getTitel()),event,new Scene(root));
		}
	}

	public static void zeileZählen() {
		try {
			count = 0;
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(DataFilm.FilmDaten, "r")) {
				for (; randomAccessFile.readLine() != null;) {
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ObservableList<DatenbankFilme> getDatenbank() {
		ObservableList<DatenbankFilme> list = FXCollections.observableArrayList();
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(DataFilm.FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					String filmtitel = randomAccessFile3.readLine().substring(10);
					String genre = randomAccessFile3.readLine().substring(6);
					String fsk = randomAccessFile3.readLine().substring(4);
					String release = randomAccessFile3.readLine().substring(8);
					String schauspieler = randomAccessFile3.readLine().substring(13);
					String regisseur = randomAccessFile3.readLine().substring(10);
					String streaming = randomAccessFile3.readLine().substring(10);
					list.add(new DatenbankFilme(filmtitel, genre, fsk, release, schauspieler, regisseur,
							streaming));
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

		tcTitel.setCellValueFactory(new PropertyValueFactory<DatenbankFilme, String>("Titel"));
		tcGenre.setCellValueFactory(new PropertyValueFactory<DatenbankFilme, String>("Genre"));
		tcFsk.setCellValueFactory(new PropertyValueFactory<DatenbankFilme, String>("Fsk"));
		tcRelease.setCellValueFactory(new PropertyValueFactory<DatenbankFilme, String>("Release"));
		tcSchauspieler.setCellValueFactory(new PropertyValueFactory<DatenbankFilme, String>("Schauspieler"));
		tcRegisseur.setCellValueFactory(new PropertyValueFactory<DatenbankFilme, String>("Regisseur"));
		tcStreaming.setCellValueFactory(new PropertyValueFactory<DatenbankFilme, String>("Streaming"));
		tabViewDatenbankFilme.setItems(getDatenbank());

	}
}
