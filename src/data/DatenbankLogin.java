package data;
/**
 *
 * @author Waldemar Justus
 *
 */
public class DatenbankLogin {
	private String  benutzername;
	private String  passwort;
	private String  email;

	public DatenbankLogin(String benutzername, String passwort, String email) {

		this.benutzername = benutzername;
		this.passwort = passwort;
		this.email = email;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
