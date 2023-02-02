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

public class DataLogin {
	public static File LoginDaten = new File("Data/Login");
	static int count;

	public static void dateiErstellen() {
		File OrdnerData = new File("Data");

		if (OrdnerData.exists()) {
		} else {
			OrdnerData.mkdirs();
			System.out.println("Ordner 'OrdnerData' wurde neu erstellt.");
		}

		if (LoginDaten.exists()) {
		} else {
			try {
				LoginDaten.createNewFile();
				System.out.println("Eine neue Datei namens 'LoginDaten' wurde erstellt.");
			} catch (IOException e) {
				System.err.println("Fehler beim erstellen der Datei 'LoginDaten'.");
				e.printStackTrace();
			}
		}
	}

	public static void zeileZählen() {
		try {
			count = 0;
			try (RandomAccessFile randomAccessFile = new RandomAccessFile(LoginDaten, "rw")) {
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

	public static void dateiSpeichern(String benutzer, String passwort, String mail) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile2 = new RandomAccessFile(LoginDaten, "rw")) {
				for (int i = 0; i < count; i++) {
					randomAccessFile2.readLine();
				}

				if (count > 0) {
					//Um Abstand einen nach einem Benutzer in der Login.txt zu machen
					randomAccessFile2.writeBytes("\n");
					randomAccessFile2.writeBytes("\n");
				}
				randomAccessFile2.writeBytes("Benutzername:" + benutzer.toUpperCase() + "\n");
				randomAccessFile2.writeBytes("Passwort:" + passwort + "\n");
				randomAccessFile2.writeBytes("Email:" + mail.toUpperCase());
			}

		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static boolean dateiKontrolle(String benutzer, String passwort) {
		zeileZählen();
		//Schauen ob Benutzername und Passwort mit der txt Datei �bereinstimmen
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(LoginDaten, "rw")) {
				for (int i = 0; i < count; i += 4) {

					String sucheBenutzer = randomAccessFile3.readLine().substring(13);
					String suchePasswort = randomAccessFile3.readLine().substring(9);
					if (benutzer.toUpperCase().equals(sucheBenutzer) & passwort.equals(suchePasswort)) {
						return true;
					}
					for (int j = 1; j <= 2; j++) {
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
	public static ArrayList<String> dateiAuslesen(ArrayList<String> login) {
		zeileZählen();
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(LoginDaten, "rw")) {
				for (int i = 0; i < count; i += 4) {

					String sucheBenutzer = randomAccessFile3.readLine().substring(13);
					login.add(sucheBenutzer);
					String suchePasswort = randomAccessFile3.readLine().substring(9);
					login.add(suchePasswort);
					String sucheEmail = randomAccessFile3.readLine().substring(6);
					login.add(sucheEmail);
					randomAccessFile3.readLine();
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return login;
	}
	public static String readEmail( String username) {
		String email = "";
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(LoginDaten, "rw")) {
				for (int i = 0; i < count; i += 4) {

					String sucheBenutzer = randomAccessFile3.readLine().substring(13);
					randomAccessFile3.readLine();
					String sucheEmail = randomAccessFile3.readLine().substring(6);

					if (username.toUpperCase().equals(sucheBenutzer)) {
						email = sucheEmail;
					}
					randomAccessFile3.readLine();

				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Datei wurde nicht gefunden.");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return email;
	}

	public static boolean dateiKontrolleRegistrierung(String benutzer, String email) {
		zeileZählen();
		//Bei der Registrierung darf der Benutzername und/oder die Email nicht vergeben sein
		try {
			try (RandomAccessFile randomAccessFile3 = new RandomAccessFile(LoginDaten, "rw")) {
				for (int i = 0; i < count; i += 4) {

					String sucheBenutzer = randomAccessFile3.readLine().substring(13);
					randomAccessFile3.readLine();
					String sucheEmail = randomAccessFile3.readLine().substring(6);
					if (benutzer.toUpperCase().equals(sucheBenutzer) || email.toUpperCase().equals(sucheEmail)) {
						return true;
					}
					randomAccessFile3.readLine();

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

}
