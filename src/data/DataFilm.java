package data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DataFilm {

	public static File FilmDaten = new File("Data/Filme.txt");
	public static File FilmeExtra = new File("Data/FilmeExtra.txt");
	public static int count;

	public static void dateiErstellen() {
		File OrdnerData = new File("Data");

		if (OrdnerData.exists()) {
		} else {
			OrdnerData.mkdirs();
			System.out.println("Ordner 'OrdnerData' wurde neu erstellt.");
		}

		if (FilmDaten.exists()) {
		} else {
			try {
				FilmDaten.createNewFile();
				System.out.println("Eine neue Datei namens 'FilmDaten.txt' wurde erstellt.");
			} catch (IOException e) {
				System.err.println("Fehler beim erstellen der Datei 'FilmDaten.txt'.");
				e.printStackTrace();
			}
		}
		if (FilmeExtra.exists()) {
		} else {
			try {
				FilmeExtra.createNewFile();
				System.out.println("Eine neue Datei namens 'FilmeExtra.txt' wurde erstellt.");
			} catch (IOException e) {
				System.err.println("Fehler beim erstellen der Datei 'FilmeExtra.txt'.");
				e.printStackTrace();
			}
		}
	}

	public static void zeileZählen() {
		try {
			count = 0;
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(FilmDaten, "r")) {
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

	public static void zeileZählenFilmeExtra() {
		try {
			count = 0;
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(FilmeExtra, "r")) {
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

	public static void filmSpeichern(String titel, String genre, String fsk, String release, String schauspieler,
									 String regisseur, String streaming) {
		// Notwendig bei der Class AdminToolController bei der Methode
		// handleButtonHinzufügenAction
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(FilmDaten, "rw")) {
				for (int i = 0; i < count; i++) {
					randomAccessFile.readLine();
				}
				randomAccessFile.writeBytes("\n");
				randomAccessFile.writeBytes("Filmtitel:" + titel + "\n");
				randomAccessFile.writeBytes("Genre:" + genre + "\n");
				randomAccessFile.writeBytes("FSK:" + fsk + "\n");
				randomAccessFile.writeBytes("Release:" + release + "\n");
				randomAccessFile.writeBytes("Schauspieler:" + schauspieler + "\n");
				randomAccessFile.writeBytes("Regisseur:" + regisseur + "\n");
				randomAccessFile.writeBytes("Streaming:" + streaming);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void filmExtraSpeichern(String titel, String trailer, String handlung, String image,
										  String laufzeit) {
		zeileZählenFilmeExtra();
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(FilmeExtra, "rw")) {
				for (int i = 0; i < count; i++) {
					randomAccessFile.readLine();
				}
				randomAccessFile.writeBytes("\n");
				randomAccessFile.writeBytes(titel + "\n");
				randomAccessFile.writeBytes(trailer + "\n");
				randomAccessFile.writeBytes(handlung + "\n");
				randomAccessFile.writeBytes(image + "\n");
				randomAccessFile.writeBytes(laufzeit);
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void filmSpeichernOhneZeile(String titel, String genre, String fsk, String release,
											  String schauspieler, String regisseur, String streaming) {
		zeileZählen();
		int zähler = 0;
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(FilmDaten, "rw")) {
				for (int i = 0; i < count; i++) {
					randomAccessFile.readLine();
					zähler++;
				}
				// Erstes \n wird �bersprungen, um keine L�cke in der Txt datei zu erstellen
				if (zähler > 1) {
					randomAccessFile.writeBytes("\n");
				}
				randomAccessFile.writeBytes(titel + "\n");
				randomAccessFile.writeBytes(genre + "\n");
				randomAccessFile.writeBytes(fsk + "\n");
				randomAccessFile.writeBytes(release + "\n");
				randomAccessFile.writeBytes(schauspieler + "\n");
				randomAccessFile.writeBytes(regisseur + "\n");
				randomAccessFile.writeBytes(streaming);
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void filmExtraSpeichernOhneZeile(String titel, String trailer, String handlung, String image,
												   String laufzeit) {
		zeileZählenFilmeExtra();
		int zähler = 0;
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(FilmeExtra, "rw")) {
				for (int i = 0; i < count; i++) {
					randomAccessFile.readLine();
					zähler++;
				}
				if (zähler > 1) {
					randomAccessFile.writeBytes("\n");
				}
				randomAccessFile.writeBytes(titel + "\n");
				randomAccessFile.writeBytes(trailer + "\n");
				randomAccessFile.writeBytes(handlung + "\n");
				randomAccessFile.writeBytes(image + "\n");
				randomAccessFile.writeBytes(laufzeit);
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static boolean filmKontrolle(ArrayList<String> filme, String filmtitel) {
		// Notwendig bei der Class AdminToolController bei der Methode
		// handleButtonHinzufügenAction
		for (int i = 0; i < filme.size(); i++) {
			if (filme.get(i).equals(filmtitel)) {
				return true;
			}
		}
		return false;
	}
	public static void Filmeauslesen(ArrayList<String> filme) {

		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {

				for (int i = 0; i < count; i += 7) {

					String sucheTitel = randomAccessFile3.readLine().substring(10);
					filme.add(sucheTitel);

					String sucheGenre = randomAccessFile3.readLine().substring(6);
					filme.add(sucheGenre);

					String sucheFreigabe = randomAccessFile3.readLine().substring(4);
					filme.add(sucheFreigabe);

					String sucheRelease  = randomAccessFile3.readLine().substring(8);
					filme.add(sucheRelease);

					String sucheSchauspieler = randomAccessFile3.readLine().substring(13);
					filme.add(sucheSchauspieler);

					String sucheRegisseur = randomAccessFile3.readLine().substring(10);
					filme.add(sucheRegisseur);

					String sucheStreaming = randomAccessFile3.readLine().substring(10);
					filme.add(sucheStreaming);

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Fehler FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Fehler IOException");
			e.printStackTrace();
		}



	}

	public static boolean FilmeExtraAuslesen(ArrayList<String> filme) {
		zeileZählenFilmeExtra();
		// In einer ArrayList die ganze File von 'FilmeExtra.txt' speichern
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmeExtra, "r")) {
				for (int i = 0; i < count; i += 5) {

					String sucheTitel = randomAccessFile3.readLine();
					filme.add(sucheTitel);
					String sucheLink = randomAccessFile3.readLine();
					filme.add(sucheLink);
					String sucheHandlung = randomAccessFile3.readLine();
					filme.add(sucheHandlung);
					String sucheImage = randomAccessFile3.readLine();
					filme.add(sucheImage);
					String sucheLaufzeit = randomAccessFile3.readLine();
					filme.add(sucheLaufzeit);

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static boolean FilmListeTitel(ArrayList<String> filme) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					String sucheTitel = randomAccessFile3.readLine().substring(10);
					filme.add(sucheTitel);
					for (int j = 0; j < 6; j++) {
						randomAccessFile3.readLine();
					}

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static boolean FilmListeGenre(ArrayList<String> filme) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					randomAccessFile3.readLine().substring(10);

					String sucheGenre = randomAccessFile3.readLine().substring(6);
					filme.add(sucheGenre);
					for (int j = 0; j < 5; j++) {
						randomAccessFile3.readLine(); // Zeiger versetzen, um ihn immer an der richtigen Stelle zu haben
					}
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static boolean FilmListeFsk(ArrayList<String> filme) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					randomAccessFile3.readLine();

					randomAccessFile3.readLine();

					String sucheFSK = randomAccessFile3.readLine().substring(4);
					filme.add(sucheFSK);
					for (int j = 0; j < 4; j++) {
						randomAccessFile3.readLine();
					}
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static boolean FilmListeRelease(ArrayList<String> filme) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					for (int j = 0; j < 3; j++) {
						randomAccessFile3.readLine();
					}

					String sucheRelease = randomAccessFile3.readLine().substring(8);
					filme.add(sucheRelease);

					for (int j = 0; j < 3; j++) {
						randomAccessFile3.readLine();
					}

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static boolean FilmListeSchauspieler(ArrayList<String> filme) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					for (int j = 0; j < 4; j++) {
						randomAccessFile3.readLine();
					}

					String sucheSchauspieler = randomAccessFile3.readLine().substring(13);
					filme.add(sucheSchauspieler);
					for (int j = 0; j < 2; j++) {
						randomAccessFile3.readLine();
					}

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static boolean FilmListeRegisseur(ArrayList<String> filme) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					for (int j = 0; j < 5; j++) {
						randomAccessFile3.readLine();
					}

					String sucheRegisseur = randomAccessFile3.readLine().substring(10);
					filme.add(sucheRegisseur);

					randomAccessFile3.readLine(); // Streaming überspringen

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public static boolean FilmListeStreaming(ArrayList<String> filme) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					for (int j = 0; j < 6; j++) {
						randomAccessFile3.readLine();
					}

					String sucheStreaming = randomAccessFile3.readLine().substring(10);
					filme.add(sucheStreaming);

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return false;
	}

	// Für die Löschen-Funktion in der Class AdminToolController

	public static void FilmLesenLöschen(ArrayList<String> filme, File filmliste, String löschen) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(filmliste, "r")) {
				for (int i = 0; i < count - 7; i += 7) {

					String sucheTitel = randomAccessFile.readLine();
					String sucheGenre = randomAccessFile.readLine();
					String sucheFSK = randomAccessFile.readLine();
					String sucheRelease = randomAccessFile.readLine();
					String sucheSchauspieler = randomAccessFile.readLine();
					String sucheRegisseur = randomAccessFile.readLine();
					String sucheStreaming = randomAccessFile.readLine();

					if (sucheTitel.equals("Filmtitel:" + löschen)) { // Findet den Titel der gelöscht werden muss

					} else {

						filme.add(sucheTitel); // Speichert die restlichen Sachen, die nicht gelö�scht werden sollen
						filme.add(sucheGenre);
						filme.add(sucheFSK);
						filme.add(sucheRelease);
						filme.add(sucheSchauspieler);
						filme.add(sucheRegisseur);
						filme.add(sucheStreaming);

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

	public static void FilmExtraLesenLöschen(ArrayList<String> filme, File filmliste, String löschen) {
		zeileZählenFilmeExtra();
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(filmliste, "r")) {
				for (int i = 0; i < count - 5; i += 5) {
					String sucheTitel = randomAccessFile.readLine();
					String sucheTrailer = randomAccessFile.readLine();
					String sucheHandlung = randomAccessFile.readLine();
					String sucheImage = randomAccessFile.readLine();
					String sucheLaufzeit = randomAccessFile.readLine();

					if (sucheTitel.equals(löschen)) {

					} else {

						filme.add(sucheTitel);
						filme.add(sucheTrailer);
						filme.add(sucheHandlung);
						filme.add(sucheImage);
						filme.add(sucheLaufzeit);

					}

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < filme.size(); i++)
			System.out.println(filme.get(i));
	}

	// F�r die Update-Funktion in Class AdminToolController

	public static void FilmLesenUpdate(ArrayList<String> filme) {
		count = 0;
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(DataFilm.FilmDaten, "r")) {
				for (int i = 0; i < count; i += 7) {

					String sucheTitel = randomAccessFile.readLine();
					String sucheGenre = randomAccessFile.readLine();
					String sucheFSK = randomAccessFile.readLine();
					String sucheRelease = randomAccessFile.readLine();
					String sucheSchauspieler = randomAccessFile.readLine();
					String sucheRegisseur = randomAccessFile.readLine();
					String sucheStreaming = randomAccessFile.readLine();

					filme.add(sucheTitel);
					filme.add(sucheGenre);
					filme.add(sucheFSK);
					filme.add(sucheRelease);
					filme.add(sucheSchauspieler);
					filme.add(sucheRegisseur);
					filme.add(sucheStreaming);
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void FilmExtraLesenUpdate(ArrayList<String> filme) {
		count = 0;
		zeileZählenFilmeExtra();
		try {
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(DataFilm.FilmeExtra, "r")) {
				for (int i = 0; i < count; i += 5) {

					String sucheTitel = randomAccessFile.readLine();
					String sucheTrailer = randomAccessFile.readLine();
					String sucheHandlung = randomAccessFile.readLine();
					String sucheImage = randomAccessFile.readLine();
					String sucheLaufzeit = randomAccessFile.readLine();

					filme.add(sucheTitel);
					filme.add(sucheTrailer);
					filme.add(sucheHandlung);
					filme.add(sucheImage);
					filme.add(sucheLaufzeit);
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
