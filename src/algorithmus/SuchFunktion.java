package algorithmus;

import java.util.ArrayList;

public class SuchFunktion {
	public static int count = 0;

	public static int binäreSuche(ArrayList<String> array, String gesucht) {
		int links = 0;
		int rechts = array.size() - 1;
		while (links <= rechts) {
			int mitte = links + (rechts - links) / 2;
			int mitteWert = gesucht.compareToIgnoreCase(array.get(mitte));
			
				if (mitteWert == 0) {
					return mitte;
				} else if (mitteWert > 0) {
					links = mitte + 1;
				} else {
					rechts = mitte - 1;
				}
			
		}
		return -1;
	}

	public static int lineareSucheErweitert(ArrayList<String> array, String gesucht) {

		int merke = -1;
		for (int s = 0; s < array.size(); s++) {
			if (gesucht.compareToIgnoreCase(array.get(s)) == 0) {
				count++;
				if (count == 1) {
					merke = s;
				}
			}
		}

		return merke;
	}

	// Wird ben�tigt in der Class FilmansichtController
	public static int lineareSuche(ArrayList<String> array, String gesucht) {

		int merke = -1;
		for (int s = 0; s < array.size(); s++) {
			if (gesucht.compareToIgnoreCase(array.get(s)) == 0) {
				merke = s;
			}
		}
		return merke;
	}
}
