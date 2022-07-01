package metier;

public class FiliereMetiere{
	private Integer id_filierematiere;
	private Filiere filiere;
	private Matiere matiere;
	
	
	public FiliereMetiere() {}


	public FiliereMetiere(Filiere filiere, Matiere matiere) {
		super();
		this.filiere = filiere;
		this.matiere = matiere;
	}


	public Integer getId_filierematiere() {
		return id_filierematiere;
	}


	public void setId_filierematiere(Integer id_filierematiere) {
		this.id_filierematiere = id_filierematiere;
	}


	public Filiere getFiliere() {
		return filiere;
	}


	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}


	public Matiere getMatiere() {
		return matiere;
	}


	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	

}
