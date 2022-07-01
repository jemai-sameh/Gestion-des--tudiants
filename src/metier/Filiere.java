package metier;

import java.util.List;

public class Filiere {
	private Integer id_filiere;
	private String abriviation ;
	private String nom_filiere;
	private List<Matiere> matiere;
	
	public Filiere() {
		
	}
	public Filiere(List<Matiere> matiere, String abriviation, String nom_filiere) {
		this.matiere=matiere;
		this.abriviation = abriviation;
		this.nom_filiere = nom_filiere;
	}

	public Integer getId_filiere() {
		return id_filiere;
	}
	public void setId_filiere(Integer id_filiere) {
		this.id_filiere = id_filiere;
	}
	public String getAbriviation() {
		return abriviation;
	}
	public void setAbriviation(String abriviation) {
		this.abriviation = abriviation;
	}
	public String getNom_filiere() {
		return nom_filiere;
	}
	public void setNom_filiere(String nom_filiere) {
		this.nom_filiere = nom_filiere;
	}
	public List<Matiere> getMatiere() {
		return matiere;
	}
	public void setMatiere(List<Matiere> matiere) {
		this.matiere = matiere;
	}
}
