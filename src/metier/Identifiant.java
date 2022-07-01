package metier;

public class Identifiant {
	private int cin;
	private String nom;
	private String prenom;
	private int telephone;
	private String adresse;
	private String date_naissance;
	private String sexe;
	private String login;
	private String Password;
	private String pays;
	private String gouvernourat;

	public Identifiant() {

	}

	public Identifiant(int cin, String nom, String prenom, int telephone, String adresse, String date_naissance,
			String sexe, String login, String password, String pays, String gouvernourat) {
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.date_naissance = date_naissance;
		this.sexe = sexe;
		this.login = login;
		this.Password = password;
		this.pays = pays;
		this.gouvernourat = gouvernourat;
	}

	public String getGouvernourat() {
		return gouvernourat;
	}

	public void setGouvernourat(String gouvernourat) {
		this.gouvernourat = gouvernourat;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

}
