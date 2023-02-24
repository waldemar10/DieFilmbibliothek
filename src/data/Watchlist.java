package data;
/**
 *
 * @author Waldemar Justus
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javafx.scene.control.Button;

public class Watchlist {
	static int count;
	public static void watchlistDatei(File watchlist) {
		File OrdnerWatchlist = new File("WatchlistAnwender");
		if (OrdnerWatchlist.exists()) {
		} else {
			OrdnerWatchlist.mkdirs();
			System.out.println("Ordner 'OrdnerData' wurde neu erstellt.");
		}

		if (watchlist.exists()) {
		} else {
			try {
				watchlist.createNewFile();
			} catch (IOException e) {
				System.err.println("Fehler beim erstellen der Datei im Ordner 'WatchlistAnwender'.");
				e.printStackTrace();
			}
		}
	}

	public static void zeileZählenWatchlist(File watchlist) {
		//Gr��e der jeweiligen Watchlist z�hlen
		try {
			count = 0;
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(watchlist, "r")) {
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
	public static void watchlistSpeichern(String film, File watchlist, Button btWatchlistSpeichern) {
		zeileZählenWatchlist(watchlist);
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(watchlist, "rw")) {
				for (int j = 0; j < count; j++) {
					String sucheFilm = randomAccessFile3.readLine();
					if (film.equals(sucheFilm)) {
						// Setzt den Text im Button in der Filmansicht um 
						btWatchlistSpeichern.setText("Schon gespeichert!");
						return;
					}
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			try (RandomAccessFile randomAccessFile2 = new RandomAccessFile(watchlist, "rw")) {
				for (int i = 0; i < count; i++) {
					randomAccessFile2.readLine();
				}
				//der gew�nschte Film wird gespeichert in der Watchlist
				randomAccessFile2.writeBytes(film + "\n");

			}

		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}



	public static void WatchlisteLesen(ArrayList<String> filme, File watchlist) {
		zeileZählenWatchlist(watchlist);
		//Die File der Watchlist des Nutzers in eine ArrayList auslesen
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(watchlist, "r")) {
				for (int i = 0; i < count; i++) {

					String sucheTitel = randomAccessFile3.readLine();
					filme.add(sucheTitel);
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}


	}

	public static void WatchlisteLesenLöschen(ArrayList<String> filme, File watchlist, String löschen) {
		zeileZählenWatchlist(watchlist);
		//Wenn der gel�schte Titel auf die File Watchlist trifft, wird dies �bersprungen
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(watchlist, "r")) {
				for (int i = 0; i < count; i++) {

					String sucheTitel = randomAccessFile3.readLine();
					if (sucheTitel.equals(löschen)) {

					} else {
						filme.add(sucheTitel);
					}
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void watchlistSpeichernBeiLöschen(String film, File watchlist) {
		zeileZählenWatchlist(watchlist);
		//Die neue Watchlist wird gespeichert
		try {
			try (RandomAccessFile randomAccessFile2 = new RandomAccessFile(watchlist, "rw")) {
				for (int i = 0; i < count; i++) {
					randomAccessFile2.readLine();
				}
				randomAccessFile2.writeBytes(film + "\n");
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


}