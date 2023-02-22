package algorithmus;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Sortieren {

	static int count;

	public static ArrayList<String> SelectionSortTitel2(ArrayList<String> filme) {

		for (int i = 0; i < filme.size() - 1; i++) {
			int werti = i;
			for (int j = i + 1; j < filme.size(); j++) {
				if (filme.get(j).compareToIgnoreCase(filme.get(werti)) < 0) {
					werti = j;// Sucht den niedrigsten Wert
				}
			}
			String temp = filme.get(werti);
			filme.set(werti, filme.get(i));
			filme.set(i, temp);

		}
		return filme;
	}
	/* Nicht komplett fertiger Code
	// Selection Sort f�r den Titel
	public static ArrayList<String> SelectionSortTitel(ArrayList<String> filme) {
		// Schleife von der Arraylist
		for (int i = 0; i < filme.size(); i += 7) {
			char iLetter = (filme.get(i).toUpperCase().charAt(0));// Erster Buchstabe des Durchgangs i
			int iValue = (int) iLetter;// Gibt Buchstaben int wert
			int maxPos = i;
			int iIndex = iValue;

			for (int j = i + 7; j < filme.size(); j += 7) {
				char jLetter = (filme.get(j).toUpperCase().charAt(0));// Erster Buchstabe des Durchgangs j
				int jValue = (int) jLetter;
				if (jValue < iIndex) {
					iIndex = jValue;
					maxPos = j;
				}

			}

			if (iIndex < iValue) {
				filme.set(i, filme.set(maxPos, filme.get(i)));
				filme.set(i + 1, filme.set(maxPos + 1, filme.get(i + 1)));
				filme.set(i + 2, filme.set(maxPos + 2, filme.get(i + 2)));
				filme.set(i + 3, filme.set(maxPos + 3, filme.get(i + 3)));
				filme.set(i + 4, filme.set(maxPos + 4, filme.get(i + 4)));
				filme.set(i + 5, filme.set(maxPos + 5, filme.get(i + 5)));
				filme.set(i + 6, filme.set(maxPos + 6, filme.get(i + 6)));
				// Vertaucht die Filme.txt so spezifisch das die kleinsten Char werte oben sind
				// Beispiel Z,B,A,H,T A,B,Z,H,T A,B,H,Z,T A,B,H,T,Z

			}
		}
		return filme;
	}*/

	public static void zeileZählen() {
		BufferedReader br;

		count = 0;
		try {
			Path path = FileSystems.getDefault().getPath("Data", "Filme.txt");
			br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			for (; br.readLine() != null; ) {
				count++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static ArrayList<String> BubbleSort(ArrayList<String> kontrolle) {
		String temp;
		for (int i = 0; i < kontrolle.size() - 1; i++) {
			for (int k = 0; k < kontrolle.size() - 1; k++) {
				if (kontrolle.get(k).compareToIgnoreCase(kontrolle.get(k + 1)) > 0) {
					temp = kontrolle.get(k);
					kontrolle.set(k, kontrolle.get(k + 1));
					kontrolle.set(k + 1, temp);
				}
			}
		}
		return kontrolle;
	}

	// Bubble Sort f�r den Genre
	public static ArrayList<String> BubbleSortTitel(ArrayList<String> filme) {
		for (int i = 0; i < filme.size() - 7; i++) {
			for (int j = 0; j < filme.size() - 7; j += 7) {
				if (filme.get(j).compareToIgnoreCase(filme.get(j + 7)) > 0) {

					String temp = filme.get(j);
					String temp1 = filme.get(j + 1);
					String temp2 = filme.get(j + 2);
					String temp3 = filme.get(j + 3);
					String temp4 = filme.get(j + 4);
					String temp5 = filme.get(j + 5);
					String temp6 = filme.get(j + 6);

					filme.set(j, filme.get(j + 7));
					filme.set(j + 1, filme.get(j + 8));
					filme.set(j + 2, filme.get(j + 9));
					filme.set(j + 3, filme.get(j + 10));
					filme.set(j + 4, filme.get(j + 11));
					filme.set(j + 5, filme.get(j + 12));
					filme.set(j + 6, filme.get(j + 13));

					filme.set(j + 7, temp);
					filme.set(j + 8, temp1);
					filme.set(j + 9, temp2);
					filme.set(j + 10, temp3);
					filme.set(j + 11, temp4);
					filme.set(j + 12, temp5);
					filme.set(j + 13, temp6);
				}
			}
		}
		return filme;
	}

	// Bubble Sort f�r den Genre
	public static ArrayList<String> BubbleSortGenre(ArrayList<String> filme) {
		for (int i = 0; i < filme.size() - 7; i++) {
			for (int j = 0; j < filme.size() - 7; j += 7) {
				if (filme.get(j + 1).compareToIgnoreCase(filme.get(j + 8)) > 0) {

					String temp = filme.get(j);
					String temp1 = filme.get(j + 1);
					String temp2 = filme.get(j + 2);
					String temp3 = filme.get(j + 3);
					String temp4 = filme.get(j + 4);
					String temp5 = filme.get(j + 5);
					String temp6 = filme.get(j + 6);

					filme.set(j, filme.get(j + 7));
					filme.set(j + 1, filme.get(j + 8));
					filme.set(j + 2, filme.get(j + 9));
					filme.set(j + 3, filme.get(j + 10));
					filme.set(j + 4, filme.get(j + 11));
					filme.set(j + 5, filme.get(j + 12));
					filme.set(j + 6, filme.get(j + 13));

					filme.set(j + 7, temp);
					filme.set(j + 8, temp1);
					filme.set(j + 9, temp2);
					filme.set(j + 10, temp3);
					filme.set(j + 11, temp4);
					filme.set(j + 12, temp5);
					filme.set(j + 13, temp6);
				}
			}
		}
		return filme;
	}

	// Bubble Sort f�r die FSK
	public static ArrayList<String> BubbleSortFSK(ArrayList<String> filme) {
		for (int i = 0; i < filme.size() - 7; i++) {
			for (int j = 0; j < filme.size() - 7; j += 7) {
				if (filme.get(j + 2).compareToIgnoreCase(filme.get(j + 9)) > 0) {

					String temp = filme.get(j);
					String temp1 = filme.get(j + 1);
					String temp2 = filme.get(j + 2);
					String temp3 = filme.get(j + 3);
					String temp4 = filme.get(j + 4);
					String temp5 = filme.get(j + 5);
					String temp6 = filme.get(j + 6);

					filme.set(j, filme.get(j + 7));
					filme.set(j + 1, filme.get(j + 8));
					filme.set(j + 2, filme.get(j + 9));
					filme.set(j + 3, filme.get(j + 10));
					filme.set(j + 4, filme.get(j + 11));
					filme.set(j + 5, filme.get(j + 12));
					filme.set(j + 6, filme.get(j + 13));

					filme.set(j + 7, temp);
					filme.set(j + 8, temp1);
					filme.set(j + 9, temp2);
					filme.set(j + 10, temp3);
					filme.set(j + 11, temp4);
					filme.set(j + 12, temp5);
					filme.set(j + 13, temp6);
				}
			}
		}
		return filme;
	}

	// Bubble Sort f�r den Erscheinungsdatum Jahr/Monat/Tag
	public static ArrayList<String> BubbleSortDatum(ArrayList<String> filme) {
		for (int i = 0; i < filme.size() - 7; i++) {
			for (int j = 0; j < filme.size() - 7; j += 7) {
				if (filme.get(j + 3).compareTo(filme.get(j + 10)) > 0) {

					String temp = filme.get(j);
					String temp1 = filme.get(j + 1);
					String temp2 = filme.get(j + 2);
					String temp3 = filme.get(j + 3);
					String temp4 = filme.get(j + 4);
					String temp5 = filme.get(j + 5);
					String temp6 = filme.get(j + 6);

					filme.set(j, filme.get(j + 7));
					filme.set(j + 1, filme.get(j + 8));
					filme.set(j + 2, filme.get(j + 9));
					filme.set(j + 3, filme.get(j + 10));
					filme.set(j + 4, filme.get(j + 11));
					filme.set(j + 5, filme.get(j + 12));
					filme.set(j + 6, filme.get(j + 13));

					filme.set(j + 7, temp);
					filme.set(j + 8, temp1);
					filme.set(j + 9, temp2);
					filme.set(j + 10, temp3);
					filme.set(j + 11, temp4);
					filme.set(j + 12, temp5);
					filme.set(j + 13, temp6);
				}
			}
		}
		return filme;
	}

	// Bubble Sort f�r die Schauspieler
	public static ArrayList<String> BubbleSortSpieler(ArrayList<String> filme) {
		for (int i = 0; i < filme.size() - 7; i++) {
			for (int j = 0; j < filme.size() - 7; j += 7) {
				if (filme.get(j + 4).compareTo(filme.get(j + 11)) > 0) {

					String temp = filme.get(j);
					String temp1 = filme.get(j + 1);
					String temp2 = filme.get(j + 2);
					String temp3 = filme.get(j + 3);
					String temp4 = filme.get(j + 4);
					String temp5 = filme.get(j + 5);
					String temp6 = filme.get(j + 6);

					filme.set(j, filme.get(j + 7));
					filme.set(j + 1, filme.get(j + 8));
					filme.set(j + 2, filme.get(j + 9));
					filme.set(j + 3, filme.get(j + 10));
					filme.set(j + 4, filme.get(j + 11));
					filme.set(j + 5, filme.get(j + 12));
					filme.set(j + 6, filme.get(j + 13));

					filme.set(j + 7, temp);
					filme.set(j + 8, temp1);
					filme.set(j + 9, temp2);
					filme.set(j + 10, temp3);
					filme.set(j + 11, temp4);
					filme.set(j + 12, temp5);
					filme.set(j + 13, temp6);
				}
			}
		}
		return filme;
	}

	// Bubble Sort f�r den Regisseur
	public static ArrayList<String> BubbleSortRegisseur(ArrayList<String> filme) {
		for (int i = 0; i < filme.size() - 7; i++) {
			for (int j = 0; j < filme.size() - 7; j += 7) {
				if (filme.get(j + 5).compareTo(filme.get(j + 12)) > 0) {

					String temp = filme.get(j);
					String temp1 = filme.get(j + 1);
					String temp2 = filme.get(j + 2);
					String temp3 = filme.get(j + 3);
					String temp4 = filme.get(j + 4);
					String temp5 = filme.get(j + 5);
					String temp6 = filme.get(j + 6);

					filme.set(j, filme.get(j + 7));
					filme.set(j + 1, filme.get(j + 8));
					filme.set(j + 2, filme.get(j + 9));
					filme.set(j + 3, filme.get(j + 10));
					filme.set(j + 4, filme.get(j + 11));
					filme.set(j + 5, filme.get(j + 12));
					filme.set(j + 6, filme.get(j + 13));

					filme.set(j + 7, temp);
					filme.set(j + 8, temp1);
					filme.set(j + 9, temp2);
					filme.set(j + 10, temp3);
					filme.set(j + 11, temp4);
					filme.set(j + 12, temp5);
					filme.set(j + 13, temp6);
				}
			}
		}
		return filme;
	}

	// Bubble Sort f�r den Streaming Anbieter
	public static ArrayList<String> BubbleSortAnbieter(ArrayList<String> filme) {
		for (int i = 0; i < filme.size() - 7; i++) {
			for (int j = 0; j < filme.size() - 7; j += 7) {
				if (filme.get(j + 6).compareTo(filme.get(j + 13)) > 0) {

					String temp = filme.get(j);
					String temp1 = filme.get(j + 1);
					String temp2 = filme.get(j + 2);
					String temp3 = filme.get(j + 3);
					String temp4 = filme.get(j + 4);
					String temp5 = filme.get(j + 5);
					String temp6 = filme.get(j + 6);

					filme.set(j, filme.get(j + 7));
					filme.set(j + 1, filme.get(j + 8));
					filme.set(j + 2, filme.get(j + 9));
					filme.set(j + 3, filme.get(j + 10));
					filme.set(j + 4, filme.get(j + 11));
					filme.set(j + 5, filme.get(j + 12));
					filme.set(j + 6, filme.get(j + 13));

					filme.set(j + 7, temp);
					filme.set(j + 8, temp1);
					filme.set(j + 9, temp2);
					filme.set(j + 10, temp3);
					filme.set(j + 11, temp4);
					filme.set(j + 12, temp5);
					filme.set(j + 13, temp6);
				}
			}
		}
		return filme;
	}

}
