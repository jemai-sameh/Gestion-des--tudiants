package Vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controleur.EcouteurAjoutEtudiant;
import Controleur.EcouteurModifierEtudiant;
import Controleur.EcouteurRechercherEtudiant;
import Model.ModelValidateur;
import metier.Etudiant;
import metier.Filiere;
import metier.MetierFiliere;
import metier.MetiereEtudiant;

public class EspaceEtudiant extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	ModelValidateur modelValidateur = new ModelValidateur();
	MetiereEtudiant metiereEtudiant = new MetiereEtudiant();
	private JButton btnNouveauEtudiant;
	private JButton btnModifierEtudiant;
	private JLabel lblCin;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblSexe;
	private JLabel lblTelephone;
	private JLabel lblDatenais;
	private JLabel lblAdresse;
	private JLabel lblPays;
	private JLabel lbllogin;
	private JLabel lblPassword;
	private JLabel lblNiveau;
	private JLabel lblNomModif;
	private JLabel lblPrenomModif;
	private JLabel lblSexeModif;
	private JLabel lblTelephoneModif;
	private JLabel lblDatenaisModif;
	private JLabel lblJourModif;
	private JLabel lblMoisModif;
	private JLabel lblAnneeModif;
	private JLabel lblAdresseModif;
	private JLabel lblPaysModif;
	private JLabel lblgovModif;
	private JLabel lblloginModif;
	private JLabel lblNumEt;
	private JLabel lblNiveauModif;
	private JLabel lblFiliereModif;
	private JLabel lblPasswordModif;
	private JLabel lblNewLabel1;
	private JLabel lblCinModif;
	private JPasswordField passwordAjout;
	private JTextField cinajout;
	private JTextField nomajout;
	private JTextField prenomajout;
	private JComboBox GovAjout;
	private JLabel lblGouvernourat;
	private JComboBox paysAjout;
	private JLabel lblNewLabel;
	private JButton btnAnnuler;
	private JSpinner Annee;
	private JButton btnValider;
	private JSpinner Mois;
	private JSpinner Jour;
	private JLabel lblAnnee;
	private JLabel lblMois1;
	private JLabel lblJour;
	private JComboBox cmbFiliere;
	private JComboBox cmbNiveau;
	private JTextField loginajout;
	private JTextArea textAdresse;
	private JTextField telephoneajout;
	private JRadioButton homme;
	private JRadioButton femme;
	private JLabel lblFiliere;

	private JTextField cinModif;
	private JTextField nomModif;
	private JTextField prenomModif;
	private JTextField telephoneModif;
	private JTextField loginModif;
	private JPasswordField passwordModif;
	private JComboBox cmbNiveauModif;
	private JComboBox cmbFiliereModif;
	private JComboBox govModif;
	private JComboBox paysModif;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnAnnulerModif;
	private JSpinner JourModif;
	private JSpinner MoisModif;
	private JSpinner AnneeModif;
	private JCheckBox CBoxNum;

	private JCheckBox CBoxCin;
	private JTextArea adresseModif;
	private JRadioButton femmeModif;
	private JRadioButton hommeModif;
	private JButton btnRecherche;
	private JComboBox numEt;
	private JPanel pnlModif;

	MetiereEtudiant m = new MetiereEtudiant();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspaceEtudiant frame = new EspaceEtudiant();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EspaceEtudiant() {
		setVerifyInputWhenFocusTarget(false);
		setBackground(Color.BLUE);
		setOpaque(true);
		setBorder(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 876, 516);
		getContentPane().setLayout(null);

		JPanel panelCardLayout = new JPanel();
		panelCardLayout.setBounds(213, 39, 628, 421);
		getContentPane().add(panelCardLayout);
		panelCardLayout.setLayout(new CardLayout(0, 0));

		// ***********************************************************************************************************************************************************************
		// les button
		// **********************************************************************************************************************************************************************

		JPanel panel = new JPanel();
		panel.setBounds(10, 39, 183, 421);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnNouveauEtudiant = new JButton("Nouveau etudiant");
		btnNouveauEtudiant
				.setIcon(new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-nouveau-contact-16.png")));
		btnNouveauEtudiant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNouveauEtudiant.setFont(new Font("Arial", Font.BOLD, 12));
		btnNouveauEtudiant.setBorder(null);
		btnNouveauEtudiant.setBounds(10, 99, 163, 69);
		panel.add(btnNouveauEtudiant);

		btnModifierEtudiant = new JButton("Modifier /Supprimer");
		btnModifierEtudiant.setIcon(
				new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-modifier-l'utilisateur-homme-16.png")));

		btnModifierEtudiant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModifierEtudiant.setBorder(null);
		btnModifierEtudiant.setFont(new Font("Arial", Font.BOLD, 12));
		btnModifierEtudiant.setBounds(10, 219, 163, 69);
		panel.add(btnModifierEtudiant);
		btnNouveauEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				((CardLayout) panelCardLayout.getLayout()).show(panelCardLayout, "panelNouveau");
				emptyComponent("ajout");
			}
		});
		btnModifierEtudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) panelCardLayout.getLayout()).show(panelCardLayout, "panelModifier");
				emptyComponent("modifier");
			}
		});

		// ***********************************************************************************************************************************************************************
		// Ajouter etudiant
		// **********************************************************************************************************************************************************************

		JPanel panelNouveau = new JPanel();
		panelNouveau.setBounds(213, 39, 628, 421);
		panelCardLayout.add(panelNouveau, "panelNouveau");
		panelNouveau.setLayout(null);

		JScrollPane scrollPaneNouveau = new JScrollPane();
		scrollPaneNouveau.setBounds(0, 0, 628, 421);
		panelNouveau.add(scrollPaneNouveau);

		JPanel pnlAjout = new JPanel();
		scrollPaneNouveau.setViewportView(pnlAjout);
		pnlAjout.setLayout(null);
		pnlAjout.setPreferredSize(new Dimension(500, 700));

		lblCin = new JLabel("Cin");
		lblCin.setFont(new Font("Arial", Font.BOLD, 14));
		lblCin.setBounds(43, 94, 144, 14);
		pnlAjout.add(lblCin);

		lblNom = new JLabel("Nom ");
		lblNom.setFont(new Font("Arial", Font.BOLD, 14));
		lblNom.setBounds(43, 123, 144, 14);
		pnlAjout.add(lblNom);

		lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Arial", Font.BOLD, 14));
		lblPrenom.setBounds(43, 162, 144, 14);
		pnlAjout.add(lblPrenom);

		lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Arial", Font.BOLD, 14));
		lblSexe.setBounds(43, 196, 144, 14);
		pnlAjout.add(lblSexe);

		lblTelephone = new JLabel("telephone");
		lblTelephone.setFont(new Font("Arial", Font.BOLD, 14));
		lblTelephone.setBounds(43, 259, 144, 14);
		pnlAjout.add(lblTelephone);

		lblDatenais = new JLabel("Date Naissance");
		lblDatenais.setFont(new Font("Arial", Font.BOLD, 14));
		lblDatenais.setBounds(43, 316, 144, 14);
		pnlAjout.add(lblDatenais);

		lblAdresse = new JLabel("Adresse complet");
		lblAdresse.setFont(new Font("Arial", Font.BOLD, 14));
		lblAdresse.setBounds(43, 363, 144, 14);
		pnlAjout.add(lblAdresse);

		lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Arial", Font.BOLD, 14));
		lblPays.setBounds(43, 406, 144, 14);
		pnlAjout.add(lblPays);

		lbllogin = new JLabel("Login");
		lbllogin.setFont(new Font("Arial", Font.BOLD, 14));
		lbllogin.setBounds(43, 493, 144, 14);
		pnlAjout.add(lbllogin);

		lblPassword = new JLabel("Mot de passe");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblPassword.setBounds(43, 529, 144, 14);
		pnlAjout.add(lblPassword);

		lblNiveau = new JLabel("Niveau");
		lblNiveau.setFont(new Font("Arial", Font.BOLD, 14));
		lblNiveau.setBounds(43, 569, 144, 14);
		pnlAjout.add(lblNiveau);

		lblFiliere = new JLabel("Filiere");
		lblFiliere.setFont(new Font("Arial", Font.BOLD, 14));
		lblFiliere.setBounds(43, 607, 144, 14);
		pnlAjout.add(lblFiliere);

		cinajout = new JTextField();
		cinajout.setColumns(10);
		cinajout.setBounds(239, 92, 226, 20);
		pnlAjout.add(cinajout);

		nomajout = new JTextField();
		nomajout.setColumns(10);
		nomajout.setBounds(239, 121, 226, 20);
		pnlAjout.add(nomajout);

		prenomajout = new JTextField();
		prenomajout.setColumns(10);
		prenomajout.setBounds(239, 160, 226, 20);
		pnlAjout.add(prenomajout);

		femme = new JRadioButton("Femme");
		femme.setFont(new Font("Arial", Font.BOLD, 14));
		femme.setBounds(276, 193, 109, 23);
		pnlAjout.add(femme);

		homme = new JRadioButton("Homme");
		homme.setFont(new Font("Arial", Font.BOLD, 14));
		homme.setBounds(276, 219, 109, 23);
		pnlAjout.add(homme);

		femme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (femme.isSelected()) {
					homme.setSelected(false);
				}

			}
		});
		homme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (homme.isSelected()) {
					femme.setSelected(false);
				}

			}
		});

		telephoneajout = new JTextField();
		telephoneajout.setMinimumSize(new Dimension(8, 8));
		telephoneajout.setMaximumSize(new Dimension(8, 8));
		telephoneajout.setColumns(8);
		telephoneajout.setBounds(239, 257, 226, 20);
		pnlAjout.add(telephoneajout);

		textAdresse = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textAdresse);
		scrollPane.setBounds(239, 345, 226, 48);// setPreferredSize(new Dimension(200,70));
		pnlAjout.add(scrollPane);

		loginajout = new JTextField();
		loginajout.setColumns(10);
		loginajout.setBounds(239, 491, 226, 20);
		pnlAjout.add(loginajout);

		passwordAjout = new JPasswordField();
		passwordAjout.setBounds(239, 527, 226, 20);
		pnlAjout.add(passwordAjout);

		cmbNiveau = new JComboBox();
		cmbNiveau.setModel(new DefaultComboBoxModel(new String[] { "choisir niveau", "1 ere licence", "2 eme licence",
				"3 eme licence", "1 ere master", "2 eme master", "doctorat" }));
		cmbNiveau.setSelectedIndex(0);
		cmbNiveau.setBounds(239, 567, 226, 20);
		pnlAjout.add(cmbNiveau);

		// liste de filiere

		cmbFiliere = new JComboBox();

		cmbFiliere.setBounds(239, 605, 226, 20);
		cmbFiliere.addItem("choisir filiere");

		pnlAjout.add(cmbFiliere);

		MetierFiliere filiere = new MetierFiliere();
		List<Filiere> listFiliere = filiere.getAllFiliere();

		for (Filiere f : listFiliere) {
			cmbFiliere.addItem(f.getNom_filiere());
		}
		cmbFiliere.setSelectedIndex(0);

		lblJour = new JLabel("Jour");
		lblJour.setBounds(239, 288, 46, 14);
		pnlAjout.add(lblJour);

		lblMois1 = new JLabel("Mois");
		lblMois1.setBounds(339, 288, 46, 14);
		pnlAjout.add(lblMois1);

		lblAnnee = new JLabel("Annee");
		lblAnnee.setBounds(419, 288, 46, 14);
		pnlAjout.add(lblAnnee);

		Jour = new JSpinner();
		Jour.setBounds(239, 314, 46, 20);
		Jour.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		pnlAjout.add(Jour);

		Mois = new JSpinner();

		Mois.setModel(new SpinnerListModel(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
				"Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" }));
		Mois.setBounds(305, 314, 80, 20);
		pnlAjout.add(Mois);

		Mois.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String moiss = (String) Mois.getValue();
				if (moiss == "Février") {
					Jour.setModel(new SpinnerNumberModel(1, 1, 29, 1));

				} else if (moiss == "Janvier" || moiss == "Mars" || moiss == "Mai" || moiss == "Juillet"
						|| moiss == "Août" || moiss == "Octobre" || moiss == "Décembre") {
					Jour.setModel(new SpinnerNumberModel(1, 1, 31, 1));

				} else if (moiss == "Avril" || moiss == "Juin" || moiss == "Septembre" || moiss == "Novembre") {
					Jour.setModel(new SpinnerNumberModel(1, 1, 30, 1));
				}

			}
		});

		Annee = new JSpinner();
		Annee.setModel(new SpinnerNumberModel(2020, 1973, 2030, 1));
		Annee.setBounds(402, 314, 63, 20);
		pnlAjout.add(Annee);

		btnValider = new JButton("Ajouter");
		btnValider.setIcon(
				new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-ajouter-un-utilisateur-homme-30.png")));

		btnValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValider.setBounds(118, 656, 132, 30);
		pnlAjout.add(btnValider);

		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setIcon(new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-annuler-24.png")));

		btnAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnnuler.setBounds(300, 656, 132, 30);
		pnlAjout.add(btnAnnuler);

		lblNewLabel = new JLabel("Ajouter Etudiant");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setBounds(134, 30, 220, 34);
		pnlAjout.add(lblNewLabel);

		paysAjout = new JComboBox();
		paysAjout.setModel(new DefaultComboBoxModel(
				new String[] { "choisir Pays", "Algérie", "Allemagne", "Australie", "Belgique", "France", "Tunisie" }));
		paysAjout.setSelectedIndex(0);
		paysAjout.setBounds(240, 404, 225, 20);
		pnlAjout.add(paysAjout);

		lblGouvernourat = new JLabel("Gouvernourat");
		lblGouvernourat.setFont(new Font("Arial", Font.BOLD, 14));
		lblGouvernourat.setBounds(43, 446, 144, 14);
		pnlAjout.add(lblGouvernourat);

		GovAjout = new JComboBox();
		GovAjout.setBounds(240, 444, 225, 20);
		GovAjout.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat" }));

		pnlAjout.add(GovAjout);

		paysAjout.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (paysAjout.getSelectedItem().toString()) {
				case "Algérie":
					GovAjout.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat", "Adrar",
							"Aïn-Defla", "Aïn-Témouchent", "Alger", "Annaba", "Batna", "Béchar", "Béjaïa", "Biskra",
							"Blida", "Bordj-Bou-Arreridj", "Bouira", "Boumerdès", "Chlef", "Constantine", "Djelfa",
							"El-Bayadh", "El-Oued", "El-Taref", "Ghardaia", "Guelma", "Illizi", "Jijel", "Khenchela",
							"Laghouat", "M'Sila", "Mascara", "Médéa", "Mila", "Mostaganem", "Naâma", "Oran", "Ouargla",
							"Oum-El-Bouaghi", "Relizane", "Saida", "Sétif", "Sidi-Bel-Abbès", "Skikda", "Souk-Ahras",
							"Tamanrasset", "Tébessa", "Tiaret", "Tindouf", "Tipaza", "Tissemsilt", "Tizi-Ouzou",
							"Tlemcen" }));

					break;
				case "Allemagne":
					GovAjout.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat",
							"Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen", "Hamburg", "Hessen",
							"Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
							"Saarland", "Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen" }));
					break;
				case "Australie":
					GovAjout.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat",
							"Australian Capital Territory", "New South Wales", "Northern Territory", "Queensland",
							"South Australia", "Tasmania", "Victoria", "Western Australia" }));
					break;
				case "Belgique":
					GovAjout.setModel(new DefaultComboBoxModel(
							new String[] { "choisir Gouvernourat", "Bruxelles-Capitale", "Flamande", "Wallonne" }));
					break;
				case "France":
					GovAjout.setModel(new DefaultComboBoxModel(
							new String[] { "choisir Gouvernourat", "Alsace", "Aquitaine", "Auvergne", "Basse-Normandie",
									"Bourgogne", "Bretagne", "Centre", "Champagne-Ardenne", "Corse", "DOM-TOM",
									"Franche-Comté", "Haute-Normandie", "Ile-de-France", "Languedoc-Roussillon",
									"Limousin", "Lorraine", "Midi-Pyrénées", "Nord-Pas-de-Calais", "Pays de la Loire",
									"Picardie", "Poitou-Charentes", "Provence-Alpes-Côte d'Azur", "Rhône-Alpes" }));
					break;
				case "Tunisie":
					GovAjout.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat", "Ariana",
							"B\u00E9ja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine",
							"Kebili", "Manouba", "Kef", "Mahdia", "Médenine", "Monastir", "Nabeul", "Sfax",
							"Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan" }));

					break;
				default:
					GovAjout.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat" }));
					break;
				}
			}
		});

		// this.setVisible(true);
		// this.setSize(650,650);

		// ********************************************************************************************************************************************************************************************************

		btnValider.addActionListener(new EcouteurAjoutEtudiant(this));

		// annuler
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyComponent("ajout");

			}
		});

		// ***********************************************************************************************************************************************************************
		// MOdifier etudiant
		// **********************************************************************************************************************************************************************

		JPanel panelModifier = new JPanel();
		panelModifier.setBounds(213, 39, 628, 421);
		panelCardLayout.add(panelModifier, "panelModifier");
		panelModifier.setLayout(null);

		JScrollPane scrollPaneModifier = new JScrollPane();
		scrollPaneModifier.setBounds(0, 0, 628, 421);
		panelModifier.add(scrollPaneModifier);

		pnlModif = new JPanel();
		scrollPaneModifier.setViewportView(pnlModif);
		pnlModif.setLayout(null);
		pnlModif.setPreferredSize(new Dimension(500, 899));

		lblNewLabel1 = new JLabel("Modifier Etudiant");
		lblNewLabel1.setBounds(167, 31, 237, 36);
		lblNewLabel1.setForeground(Color.DARK_GRAY);
		lblNewLabel1.setFont(new Font("Arial", Font.BOLD, 22));
		pnlModif.add(lblNewLabel1);

		btnRecherche = new JButton("Rechercher");
		btnRecherche.setIcon(
				new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));

		btnRecherche.setBounds(195, 269, 132, 30);
		btnRecherche.setEnabled(false);
		pnlModif.add(btnRecherche);

		lblCinModif = new JLabel("Cin");
		lblCinModif.setBounds(45, 206, 144, 14);
		lblCinModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblCinModif.setEnabled(false);
		pnlModif.add(lblCinModif);

		lblNomModif = new JLabel("Nom ");
		lblNomModif.setBounds(45, 352, 144, 14);
		lblNomModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblNomModif.setEnabled(false);
		pnlModif.add(lblNomModif);

		lblPrenomModif = new JLabel("Prenom");
		lblPrenomModif.setBounds(45, 391, 144, 14);
		lblPrenomModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblPrenomModif.setEnabled(false);
		pnlModif.add(lblPrenomModif);

		lblSexeModif = new JLabel("Sexe");
		lblSexeModif.setBounds(45, 425, 144, 14);
		lblSexeModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblSexeModif.setEnabled(false);
		pnlModif.add(lblSexeModif);

		lblTelephoneModif = new JLabel("telephone");
		lblTelephoneModif.setBounds(45, 488, 144, 14);
		lblTelephoneModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblTelephoneModif.setEnabled(false);
		pnlModif.add(lblTelephoneModif);

		lblDatenaisModif = new JLabel("Date Naissance");
		lblDatenaisModif.setBounds(45, 545, 144, 14);
		lblDatenaisModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblDatenaisModif.setEnabled(false);
		pnlModif.add(lblDatenaisModif);

		lblAdresseModif = new JLabel("Adresse complet");
		lblAdresseModif.setBounds(45, 592, 144, 14);
		lblAdresseModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblAdresseModif.setEnabled(false);
		pnlModif.add(lblAdresseModif);

		lblloginModif = new JLabel("Login");
		lblloginModif.setBounds(45, 706, 144, 14);
		lblloginModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblloginModif.setEnabled(false);
		pnlModif.add(lblloginModif);

		lblPasswordModif = new JLabel("Mot de passe");
		lblPasswordModif.setBounds(45, 741, 144, 14);
		lblPasswordModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblPasswordModif.setEnabled(false);
		pnlModif.add(lblPasswordModif);

		lblNiveauModif = new JLabel("Niveau");
		lblNiveauModif.setBounds(45, 774, 144, 14);
		lblNiveauModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblNiveauModif.setEnabled(false);
		pnlModif.add(lblNiveauModif);

		lblFiliereModif = new JLabel("Filiere");
		lblFiliereModif.setBounds(45, 816, 144, 14);
		lblFiliereModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblFiliereModif.setEnabled(false);
		pnlModif.add(lblFiliereModif);

		cinModif = new JTextField();
		cinModif.setBounds(241, 204, 226, 20);
		cinModif.setEnabled(false);
		cinModif.setColumns(10);
		pnlModif.add(cinModif);

		nomModif = new JTextField();
		nomModif.setBounds(241, 350, 226, 20);
		nomModif.setEnabled(false);
		nomModif.setColumns(10);
		pnlModif.add(nomModif);

		prenomModif = new JTextField();
		prenomModif.setBounds(241, 389, 226, 20);
		prenomModif.setEnabled(false);
		prenomModif.setColumns(10);
		pnlModif.add(prenomModif);

		femmeModif = new JRadioButton("Femme");
		femmeModif.setBounds(278, 422, 109, 23);
		femmeModif.setFont(new Font("Arial", Font.BOLD, 14));
		femmeModif.setEnabled(false);
		pnlModif.add(femmeModif);

		hommeModif = new JRadioButton("Homme");
		hommeModif.setBounds(278, 448, 109, 23);
		hommeModif.setFont(new Font("Arial", Font.BOLD, 14));
		hommeModif.setEnabled(false);
		pnlModif.add(hommeModif);

		femmeModif.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				if (femmeModif.isSelected()) {
					hommeModif.setSelected(false);
				}

			}
		});
		hommeModif.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				if (hommeModif.isSelected()) {
					femmeModif.setSelected(false);
				}

			}
		});

		telephoneModif = new JTextField();
		telephoneModif.setBounds(241, 486, 226, 20);
		telephoneModif.setEnabled(false);
		telephoneModif.setColumns(10);
		pnlModif.add(telephoneModif);

		adresseModif = new JTextArea();
		adresseModif.setBounds(241, 588, 226, 22);
		adresseModif.setEnabled(false);
		pnlModif.add(adresseModif);

		loginModif = new JTextField();
		loginModif.setBounds(241, 704, 226, 20);
		loginModif.setEnabled(false);
		loginModif.setColumns(10);
		pnlModif.add(loginModif);

		passwordModif = new JPasswordField();
		passwordModif.setBounds(241, 739, 226, 20);
		passwordModif.setEnabled(false);
		pnlModif.add(passwordModif);

		cmbNiveauModif = new JComboBox();
		cmbNiveauModif.setBounds(241, 774, 226, 20);
		cmbNiveauModif.setModel(new DefaultComboBoxModel(new String[] { "choisir niveau", "1 ere licence",
				"2 eme licence", "3 eme licence", "1 ere master", "2 eme master", "doctorat" }));
		cmbNiveauModif.setSelectedIndex(0);

		cmbNiveauModif.setEnabled(false);
		pnlModif.add(cmbNiveauModif);

		cmbFiliereModif = new JComboBox();
		cmbFiliereModif.setBounds(241, 816, 226, 20);
		cmbFiliereModif.setEnabled(false);
		cmbFiliereModif.addItem("choisir filiere");
		pnlModif.add(cmbFiliereModif);

		for (Filiere f : listFiliere) {
			cmbFiliereModif.addItem(f.getNom_filiere());
		}
		cmbFiliereModif.setSelectedIndex(0);

		lblJourModif = new JLabel("Jour");
		lblJourModif.setBounds(241, 517, 46, 14);
		lblJourModif.setEnabled(false);
		pnlModif.add(lblJourModif);

		lblMoisModif = new JLabel("Mois");
		lblMoisModif.setBounds(341, 517, 46, 14);
		lblMoisModif.setEnabled(false);
		pnlModif.add(lblMoisModif);

		lblAnneeModif = new JLabel("annee");
		lblAnneeModif.setBounds(421, 517, 46, 14);
		lblAnneeModif.setEnabled(false);
		pnlModif.add(lblAnneeModif);

		JourModif = new JSpinner();
		JourModif.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		JourModif.setBounds(241, 543, 46, 20);
		JourModif.setEnabled(false);
		pnlModif.add(JourModif);

		MoisModif = new JSpinner();
		MoisModif.setBounds(318, 543, 69, 20);
		MoisModif.setModel(new SpinnerListModel(new String[] { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
				"Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" }));
		MoisModif.setEnabled(false);
		pnlModif.add(MoisModif);

		MoisModif.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String moiss = (String) MoisModif.getValue();
				if (moiss == "Février") {
					JourModif.setModel(new SpinnerNumberModel(1, 1, 29, 1));

				} else if (moiss == "Janvier" || moiss == "Mars" || moiss == "Mai" || moiss == "Juillet"
						|| moiss == "Août" || moiss == "Octobre" || moiss == "Décembre") {
					JourModif.setModel(new SpinnerNumberModel(1, 1, 31, 1));

				} else if (moiss == "Avril" || moiss == "Juin" || moiss == "Septembre" || moiss == "Novembre") {
					JourModif.setModel(new SpinnerNumberModel(1, 1, 30, 1));
				}

			}
		});

		AnneeModif = new JSpinner();
		AnneeModif.setBounds(404, 543, 63, 20);
		AnneeModif.setModel(new SpinnerNumberModel(2020, 1973, 2030, 1));
		AnneeModif.setEnabled(false);
		pnlModif.add(AnneeModif);

		btnModifier = new JButton("Modifier");
		btnModifier.setIcon(
				new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-modifier-l'utilisateur-homme-16.png")));
		btnModifier.setBounds(100, 850, 132, 30);
		btnModifier.setEnabled(false);
		pnlModif.add(btnModifier);

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setIcon(
				new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-supprimer-l'utilisateur-homme-16.png")));
		btnSupprimer.setBounds(250, 850, 132, 30);
		btnSupprimer.setEnabled(false);
		pnlModif.add(btnSupprimer);

		btnAnnulerModif = new JButton("Annuler");
		btnAnnulerModif.setIcon(new ImageIcon(EspaceEtudiant.class.getResource("/Image/icons8-annuler-24.png")));
		btnAnnulerModif.setBounds(400, 850, 132, 30);
		btnAnnulerModif.setEnabled(false);
		pnlModif.add(btnAnnulerModif);

		CBoxNum = new JCheckBox("Rechercher par numero etudiant");
		CBoxNum.setBounds(20, 86, 267, 23);
		CBoxNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		pnlModif.add(CBoxNum);

		CBoxCin = new JCheckBox("Rechercher par CIN");
		CBoxCin.setBounds(341, 86, 163, 23);
		CBoxCin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		pnlModif.add(CBoxCin);

		lblNumEt = new JLabel("Numero etudiant");
		lblNumEt.setBounds(45, 159, 144, 14);
		lblNumEt.setFont(new Font("Arial", Font.BOLD, 14));
		lblNumEt.setEnabled(false);
		pnlModif.add(lblNumEt);

		paysModif = new JComboBox();
		paysModif.setModel(new DefaultComboBoxModel(new String[] { "choisir Pays", "Algérie", "Allemagne", " Australie",
				" Belgique", "France", "Tunisie" }));
		paysModif.setSelectedIndex(5);
		paysModif.setBounds(241, 621, 226, 20);
		paysModif.setEnabled(false);
		pnlModif.add(paysModif);

		lblPaysModif = new JLabel("Pays");
		lblPaysModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblPaysModif.setEnabled(false);
		lblPaysModif.setBounds(45, 634, 144, 14);
		pnlModif.add(lblPaysModif);

		lblgovModif = new JLabel("Gouvernourat");
		lblgovModif.setFont(new Font("Arial", Font.BOLD, 14));
		lblgovModif.setEnabled(false);
		lblgovModif.setBounds(45, 670, 144, 14);
		pnlModif.add(lblgovModif);

		govModif = new JComboBox();
		govModif.setEnabled(false);
		govModif.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat" }));

		govModif.setBounds(241, 668, 226, 20);
		pnlModif.add(govModif);

		numEt = new JComboBox();
		DefaultComboBoxModel cmbModel = (DefaultComboBoxModel) numEt.getModel();
		numEt.setEnabled(false);
		numEt.setBounds(241, 157, 226, 20);

		pnlModif.add(numEt);

		paysModif.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (paysModif.getSelectedItem().toString()) {
				case "Algérie":
					govModif.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat", "Adrar",
							"Aïn-Defla", "Aïn-Témouchent", "Alger", "Annaba", "Batna", "Béchar", "Béjaïa", "Biskra",
							"Blida", "Bordj-Bou-Arreridj", "Bouira", "Boumerdès", "Chlef", "Constantine", "Djelfa",
							"El-Bayadh", "El-Oued", "El-Taref", "Ghardaia", "Guelma", "Illizi", "Jijel", "Khenchela",
							"Laghouat", "M'Sila", "Mascara", "Médéa", "Mila", "Mostaganem", "Naâma", "Oran", "Ouargla",
							"Oum-El-Bouaghi", "Relizane", "Saida", "Sétif", "Sidi-Bel-Abbès", "Skikda", "Souk-Ahras",
							"Tamanrasset", "Tébessa", "Tiaret", "Tindouf", "Tipaza", "Tissemsilt", "Tizi-Ouzou",
							"Tlemcen" }));

					break;
				case "Allemagne":
					govModif.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat",
							"Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen", "Hamburg", "Hessen",
							"Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
							"Saarland", "Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen" }));
					break;
				case "Australie":
					govModif.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat",
							"Australian Capital Territory", "New South Wales", "Northern Territory", "Queensland",
							"South Australia", "Tasmania", "Victoria", "Western Australia" }));
					break;
				case "Belgique":
					govModif.setModel(new DefaultComboBoxModel(
							new String[] { "choisir Gouvernourat", "Bruxelles-Capitale", "Flamande", "Wallonne" }));
					break;
				case "France":
					govModif.setModel(new DefaultComboBoxModel(
							new String[] { "choisir Gouvernourat", "Alsace", "Aquitaine", "Auvergne", "Basse-Normandie",
									"Bourgogne", "Bretagne", "Centre", "Champagne-Ardenne", "Corse", "DOM-TOM",
									"Franche-Comté", "Haute-Normandie", "Ile-de-France", "Languedoc-Roussillon",
									"Limousin", "Lorraine", "Midi-Pyrénées", "Nord-Pas-de-Calais", "Pays de la Loire",
									"Picardie", "Poitou-Charentes", "Provence-Alpes-Côte d'Azur", "Rhône-Alpes" }));
					break;
				case "Tunisie":
					govModif.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat", "Ariana",
							"B\u00E9ja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine",
							"Kebili", "Manouba", "Kef", "Mahdia", "Médenine", "Monastir", "Nabeul", "Sfax",
							"Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan" }));

					break;
				default:
					govModif.setModel(new DefaultComboBoxModel(new String[] { "choisir Gouvernourat" }));

				}
			}
		});

		// ***************************************************************************************************************************************************
		// ***************************************************************************************************************************************************

		CBoxNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CBoxNum.isSelected()) {
					CBoxCin.setSelected(false);
					numEt.setEnabled(true);
					List<Etudiant> et = metiereEtudiant.getAllEtudiant("select  num_etudiant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,niveau,moyenne,etat,	somme_credit ,id_filiere from etudiant ");
					// Vider le JComboBox
					cmbModel.removeAllElements();
					for (Etudiant etudiant : et) {
						numEt.addItem(etudiant.getNum_etudiant());
					}
					lblNumEt.setEnabled(true);
					btnRecherche.setEnabled(true);
					cinModif.setEnabled(false);
					lblCinModif.setEnabled(false);
					//
					enableComponent(false);
					emptyComponent("modifier");

				}

			}
		});

		CBoxCin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CBoxCin.isSelected()) {
					CBoxNum.setSelected(false);
					cinModif.setEnabled(true);
					lblCinModif.setEnabled(true);
					btnRecherche.setEnabled(true);
					numEt.setEnabled(false);
					lblNumEt.setEnabled(false);
					enableComponent(false);
					emptyComponent("modifier");
				}

			}
		});

		btnRecherche.addActionListener(new EcouteurRechercherEtudiant(this, "rechercheEspaceEtudiant"));

		btnModifier.addActionListener(new EcouteurModifierEtudiant(this));

		// button supprimer

		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (CBoxCin.isSelected()) {
						metiereEtudiant.Delete(Integer.parseInt(cinModif.getText()), "cin");

					} else {
						metiereEtudiant.Delete((Integer) numEt.getSelectedItem(), "num_etudiant");
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
							JOptionPane.CLOSED_OPTION);
				}
				List<Etudiant> et = metiereEtudiant.getAllEtudiant("select  num_etudiant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,niveau,moyenne,etat,	somme_credit ,id_filiere from etudiant ");
				cmbModel.removeAllElements();
				for (Etudiant etudiant : et) {
					numEt.addItem(etudiant.getNum_etudiant());
				}
				emptyComponent("supprimer");
				CBoxCin.setSelected(false);
				CBoxNum.setSelected(false);
				numEt.setEnabled(false);
				lblNumEt.setEnabled(false);
				btnRecherche.setEnabled(false);

				enableComponent(false);
			}
		});
		// button annuler
		btnAnnulerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numEt.setEnabled(false);
				lblNumEt.setEnabled(false);
				btnRecherche.setEnabled(false);
				CBoxCin.setSelected(false);
				CBoxNum.setSelected(false);
				emptyComponent("annuler");
				enableComponent(false);

			}
		});

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);

		// image Background
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(EspaceEtudiant.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblNewLabel_1.setBounds(0, 0, 876, 489);
		getContentPane().add(lblNewLabel_1);

	}

	// ***********************************************************************************************************************************************************************
	// les Methode
	// **********************************************************************************************************************************************************************
	public void emptyComponent(String action) {
		if (action.equals("ajout")) {
			cinajout.setText("");
			nomajout.setText("");
			prenomajout.setText("");
			femme.setSelected(false);
			homme.setSelected(false);
			telephoneajout.setText("");
			Jour.setValue(1);
			Mois.setValue("Janvier");
			Annee.setValue(2020);
			paysAjout.setSelectedIndex(0);
			GovAjout.setSelectedIndex(0);
			textAdresse.setText("");
			loginajout.setText("");
			passwordAjout.setText("");
			cmbNiveau.setSelectedIndex(0);
			cmbFiliere.setSelectedIndex(0);
		} else {
			numEt.setSelectedIndex(-1);
			cinModif.setText("");
			nomModif.setText("");
			prenomModif.setText("");
			femmeModif.setSelected(false);
			hommeModif.setSelected(false);
			telephoneModif.setText("");
			JourModif.setValue(1);
			MoisModif.setValue("Janvier");
			AnneeModif.setValue(2020);
			adresseModif.setText("");
			paysModif.setSelectedIndex(0);
			govModif.setSelectedIndex(0);
			loginModif.setText("");
			passwordModif.setText("");
			cmbNiveauModif.setSelectedIndex(0);
			cmbFiliereModif.setSelectedIndex(0);
		}
	}

	public void enableComponent(boolean enabled) {

		lblNomModif.setEnabled(enabled);
		nomModif.setEnabled(enabled);
		lblPrenomModif.setEnabled(enabled);
		prenomModif.setEnabled(enabled);
		lblSexeModif.setEnabled(enabled);
		femmeModif.setEnabled(enabled);
		hommeModif.setEnabled(enabled);
		lblTelephoneModif.setEnabled(enabled);
		telephoneModif.setEnabled(enabled);
		lblDatenaisModif.setEnabled(enabled);
		lblJourModif.setEnabled(enabled);
		lblMoisModif.setEnabled(enabled);
		lblAnneeModif.setEnabled(enabled);
		JourModif.setEnabled(enabled);
		MoisModif.setEnabled(enabled);
		AnneeModif.setEnabled(enabled);
		lblAdresseModif.setEnabled(enabled);
		adresseModif.setEnabled(enabled);
		lblPaysModif.setEnabled(enabled);
		paysModif.setEnabled(enabled);
		lblgovModif.setEnabled(enabled);
		govModif.setEnabled(enabled);
		lblloginModif.setEnabled(enabled);
		loginModif.setEnabled(enabled);
		lblPasswordModif.setEnabled(enabled);
		passwordModif.setEnabled(enabled);
		lblNiveauModif.setEnabled(enabled);
		cmbNiveauModif.setEnabled(enabled);
		lblFiliereModif.setEnabled(enabled);
		cmbFiliereModif.setEnabled(enabled);
		btnModifier.setEnabled(enabled);
		btnSupprimer.setEnabled(enabled);
		btnAnnulerModif.setEnabled(enabled);

	}

	public JTextField getCinModif() {
		return cinModif;
	}

	public JPasswordField getPasswordAjout() {
		return passwordAjout;
	}

	public JPasswordField getPasswordModif() {
		return passwordModif;
	}

	public JTextField getNomModif() {
		return nomModif;
	}

	public JTextField getPrenomModif() {
		return prenomModif;
	}

	public JTextField getTelephoneModif() {
		return telephoneModif;
	}

	public JTextField getLoginModif() {
		return loginModif;
	}

	public JComboBox getCmbNiveauModif() {
		return cmbNiveauModif;
	}

	public JComboBox getCmbFiliereModif() {
		return cmbFiliereModif;
	}

	public JComboBox getGovModif() {
		return govModif;
	}

	public JComboBox getPaysModif() {
		return paysModif;
	}

	public JSpinner getJourModif() {
		return JourModif;
	}

	public JSpinner getMoisModif() {
		return MoisModif;
	}

	public JSpinner getAnneeModif() {
		return AnneeModif;
	}

	public JTextArea getAdresseModif() {
		return adresseModif;
	}

	public JRadioButton getFemmeModif() {
		return femmeModif;
	}

	public JRadioButton getHommeModif() {
		return hommeModif;
	}

	public JCheckBox getCBoxNum() {
		return CBoxNum;
	}

	public JCheckBox getCBoxCin() {
		return CBoxCin;
	}

	public JComboBox getNumEt() {
		return numEt;
	}


	public JTextField getCinajout() {
		return cinajout;
	}

	
	public JTextField getNomajout() {
		return nomajout;
	}


	public JTextField getPrenomajout() {
		return prenomajout;
	}

	public JComboBox getGovAjout() {
		return GovAjout;
	}

	public JComboBox getPaysAjout() {
		return paysAjout;
	}

	
	public JSpinner getMois() {
		return Mois;
	}

	public JSpinner getJour() {
		return Jour;
	}


	public JComboBox getCmbFiliere() {
		return cmbFiliere;
	}


	public JComboBox getCmbNiveau() {
		return cmbNiveau;
	}


	public JTextField getLoginajout() {
		return loginajout;
	}

	public JTextArea getTextAdresse() {
		return textAdresse;
	}


	public JTextField getTelephoneajout() {
		return telephoneajout;
	}


	public JRadioButton getHomme() {
		return homme;
	}


	public JRadioButton getFemme() {
		return femme;
	}

	
}
