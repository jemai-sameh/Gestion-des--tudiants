package metier;

public class Note {
	private int id_note;
	private Matiere matiere;
	private Etudiant etudiant;
	private Double note_examen;
	private Double note_tp;
	private Double note_Orale;
	private Double note_Ds1;
	private Double note_Ds2;
	private boolean ds;

	public Note() {
	}
	
	public Note(Matiere matiere, Etudiant etudiant, Double note_examen, Double note_tp, Double note_Orale) {
		super();
		this.matiere = matiere;
		this.etudiant = etudiant;
		this.note_examen = note_examen;
		this.note_tp = note_tp;
		this.note_Orale = note_Orale;
	}

	public Note( Matiere matiere, Etudiant etudiant, Double note_Orale,Double note_Ds1, Double note_Ds2,Boolean ds)
 {
		super();
	
		this.matiere = matiere;
		this.etudiant = etudiant;
		this.note_Ds1=note_Ds1;
		this.note_Ds2=note_Ds2;
		this.note_Orale=note_Orale;
		this.ds=ds;
		
	}

	

	
	public Double getNote_Orale() {
		return note_Orale;
	}

	public void setNote_Orale(Double note_Orale) {
		this.note_Orale = note_Orale;
	}

	public Double getNote_Ds1() {
		return note_Ds1;
	}

	public void setNote_Ds1(Double note_Ds1) {
		this.note_Ds1 = note_Ds1;
	}

	public Double getNote_Ds2() {
		return note_Ds2;
	}

	public void setNote_Ds2(Double note_Ds2) {
		this.note_Ds2 = note_Ds2;
	}

	public boolean isDs() {
		return ds;
	}

	public void setDs(boolean ds) {
		this.ds = ds;
	}

	public int getId_note() {
		return id_note;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setId_note(int id_note) {
		this.id_note = id_note;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Double getNote_examen() {
		return note_examen;
	}

	public void setNote_examen(Double note_examen) {
		this.note_examen = note_examen;
	}

	public Double getNote_tp() {
		return note_tp;
	}

	public void setNote_tp(Double note_tp) {
		this.note_tp = note_tp;
	}

}
