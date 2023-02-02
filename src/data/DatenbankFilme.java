package data;
/**
 *
 * @author Waldemar Justus
 *
 */
public class DatenbankFilme {
	private String titel;
	private String genre;
	private String fsk;
	private String release;
	private String schauspieler;
	private String regisseur;
	private String streaming;
	public DatenbankFilme(String titel, String genre, String fsk, String release, String schauspieler, String regisseur, String streaming) {

		this.titel = titel;
		this.genre = genre;
		this.fsk = fsk;
		this.release = release;
		this.schauspieler = schauspieler;
		this.regisseur = regisseur;
		this.streaming = streaming;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getFsk() {
		return fsk;
	}
	public void setFsk(String fsk) {
		this.fsk = fsk;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getSchauspieler() {
		return schauspieler;
	}
	public void setSchauspieler(String schauspieler) {
		this.schauspieler = schauspieler;
	}
	public String getRegisseur() {
		return regisseur;
	}
	public void setRegisseur(String regisseur) {
		this.regisseur = regisseur;
	}
	public String getStreaming() {
		return streaming;
	}
	public void setStreaming(String streaming) {
		this.streaming = streaming;
	}

}
