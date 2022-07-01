package metier;

import java.util.List;

public class Matiere {
	private int id_matiere;
	private String nom_matiere;
	private Float coefficient;
	private Integer credit;
	private Enseignant enseignant;
	private Boolean DS;
	private List<Filiere> listeFiliere;
	public Matiere(String nom_matiere,Float coefficient, Integer credit, Boolean DS,Enseignant enseignant) {
		this.nom_matiere = nom_matiere;
		this.coefficient = coefficient;
		this.credit = credit;
		this.enseignant=enseignant;
		this.DS=DS;
				
	}
	public Matiere() {
		// TODO Auto-generated constructor stub
	}
	public Boolean getDS() {
		return DS;
	}
	public void setDS(Boolean DS) {
		this.DS = DS;
	}
	public int getId_matiere() {
		return id_matiere;
	}
	
	public void setId_matiere(int id_matiere) {
		this.id_matiere = id_matiere;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public List<Filiere> getListeFiliere() {
		return listeFiliere;
	}
	public void setListeFiliere(List<Filiere> listeFiliere) {
		this.listeFiliere = listeFiliere;
	}
	public String getType_matiere() {
		return nom_matiere;
	}
	public void setType_matiere(String nom_matiere) {
		this.nom_matiere = nom_matiere;
	}
	
	public Float getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Float coefficient) {
		this.coefficient = coefficient;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	
}
