package metier;

public class Etudiant extends Identifiant {
	private Integer num_etudiant;
	private Integer cr�dit;
	private String niveau, etat;
	private Filiere filiere;
	private Float moyenne;

	public Etudiant() {
		// TODO Auto-generated constructor stub
	}

	public Etudiant(String niveau, String etat, Filiere filiere, Float moyenne, Integer cr�dit) {

		super();
		this.niveau = niveau;
		this.etat = etat;
		this.filiere = filiere;
		this.cr�dit = cr�dit;
		this.moyenne = moyenne;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public int getNum_etudiant() {
		return num_etudiant;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setNum_etudiant(Integer num_etudiant) {
		this.num_etudiant = num_etudiant;
	}

	public String getNiveau() {
		return niveau;
	}

	public String getEtat() {
		return etat;
	}

	public Float getMoyenne() {
		return moyenne;
	}

	public Integer getCr�dit() {
		return cr�dit;
	}

	public void setCr�dit(Integer cr�dit) {
		this.cr�dit = cr�dit;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public void setMoyenne(Float moyenne) {
		this.moyenne = moyenne;
	}

}
