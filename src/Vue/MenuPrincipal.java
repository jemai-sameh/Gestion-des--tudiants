package Vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;

import Controleur.ecouteurMenu;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JDesktopPane desktopPane;
	private JMenuItem ItmCopier;
	private JMenuItem ItmColler;
	private JMenuItem ItmCouper;
	private JLabel lblimage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setVisible(true);
		setResizable(false);
		this.setTitle("Gestion d'etudiant");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/Image/iconn.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 539);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// menu
		JMenuBar menuBar = new JMenuBar();
		// menuBar.setBounds(0, 0, 20, 20);
		menuBar.setBackground(new Color(0, 191, 255));
		setJMenuBar(menuBar);

		// menu fichier
		JMenu mnFichier = new JMenu("Fichier");
		mnFichier.setHorizontalAlignment(SwingConstants.CENTER);
		mnFichier.setPreferredSize(new Dimension(60, 30));
		mnFichier.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnFichier.setForeground(new Color(0, 0, 0));
		menuBar.add(mnFichier);
		
		
		
		JMenuItem ItmAccueil = new JMenuItem("Accueil");
		ItmAccueil.setIcon(new ImageIcon("C:\\Users\\aa\\eclipse-workspace\\2lAIAG-2semestre\\Gestion etudiant\\src\\Image\\accueil.png"));
		ItmAccueil.addActionListener(new ecouteurMenu(this,"ItmAccueil"));
		ItmAccueil.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnFichier.add(ItmAccueil);
		
		// fichier : menu item quitter
		JMenuItem ItmQuitter = new JMenuItem("Quitter");
		ItmQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		ItmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem ItmDeconnecter = new JMenuItem("D\u00E9connecter");
		ItmDeconnecter.setFont(new Font("Segoe UI", Font.BOLD, 12));
		ItmDeconnecter.addActionListener(new ecouteurMenu(this, "ItmDeconnecter"));
		ItmDeconnecter.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/deconnection.png")));
		mnFichier.add(ItmDeconnecter);
		
		
		ItmQuitter.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/icons8-sortie-10.png")));
		ItmQuitter.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnFichier.add(ItmQuitter);
		
		
		
		
		
		
		// menu Edition
		JMenu mnEdition = new JMenu("Edition");
		mnEdition.setHorizontalAlignment(SwingConstants.CENTER);
		mnEdition.setPreferredSize(new Dimension(60, 30));
		mnEdition.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnEdition.setForeground(new Color(0, 0, 0));
		menuBar.add(mnEdition);
		// edition
		// item coupie
		ItmCopier = new JMenuItem("Copier");
		ItmCopier.setAction(new DefaultEditorKit.CopyAction());
		ItmCopier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		ItmCopier.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/icons8-copie-2-16.png")));
		ItmCopier.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnEdition.add(ItmCopier);
		// item coupie
	//	ItmCouper.setAction(new DefaultEditorKit.PasteAction());
		ItmCouper = new JMenuItem("Couper");
		ItmCouper.setAction(new DefaultEditorKit.PasteAction());
		ItmCouper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		ItmCouper.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/icons8-couper-le-coupon-16.png")));
		ItmCouper.setFont(new Font("Segoe UI", Font.BOLD, 12));

		mnEdition.add(ItmCouper);
		// item coller
	//	ItmColler.setAction(new DefaultEditorKit.CutAction());
		ItmColler = new JMenuItem("Coller");
		ItmColler.setAction(new DefaultEditorKit.CutAction());
		ItmColler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		ItmColler.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/icons8-coller-16.png")));
		ItmColler.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnEdition.add(ItmColler);

		// menu recherche
		JMenu mnRecherche = new JMenu("Recherche");
		mnRecherche.setHorizontalAlignment(SwingConstants.CENTER);
		mnRecherche.setPreferredSize(new Dimension(75, 30));
		mnRecherche.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnRecherche.setForeground(new Color(0, 0, 0));
		menuBar.add(mnRecherche);
		// recherche
		// item recherche etudiant
		JMenuItem ItmRechercheEt = new JMenuItem("Recherche Etudiant");
		ItmRechercheEt.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));
		ItmRechercheEt.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnRecherche.add(ItmRechercheEt);
		// item recherche enseignant
		JMenuItem ItmRechercheEn = new JMenuItem("Recherche Enseignant");

		ItmRechercheEn.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));
		ItmRechercheEn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnRecherche.add(ItmRechercheEn);
		
		JMenuItem ItmRechercheNote = new JMenuItem("Recherche Note");
		ItmRechercheNote.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/chercher.png")));
		ItmRechercheNote.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		mnRecherche.add(ItmRechercheNote);

		// menu espace etudiant
		JMenu personnel = new JMenu("Personnel");
		personnel.setHorizontalAlignment(SwingConstants.CENTER);
		personnel.setPreferredSize(new Dimension(75, 30));
		personnel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		personnel.setForeground(new Color(0, 0, 0));
		menuBar.add(personnel);

		JMenuItem ItmEtudiant = new JMenuItem("Etudiant");
		ItmEtudiant.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/icon.jpg")));
		ItmEtudiant.setFont(new Font("Segoe UI", Font.BOLD, 12));

		personnel.add(ItmEtudiant);

		JMenuItem ItmEnseignant = new JMenuItem("Enseignant");
		ItmEnseignant.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/enseignan.jpg")));

		ItmEnseignant.setFont(new Font("Segoe UI", Font.BOLD, 12));
		personnel.add(ItmEnseignant);

		JMenu mnNewMenu_1 = new JMenu("Gestion des tache");
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_1.setPreferredSize(new Dimension(130, 30));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_1);

		JMenuItem mnItmMatiere = new JMenuItem("Matiere");
		mnItmMatiere.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/matiere.png")));
		mnItmMatiere.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.add(mnItmMatiere);

		JMenuItem mnItmFiliere = new JMenuItem("Filiere");
		mnItmFiliere.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/filiere.jpg")));

		mnItmFiliere.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.add(mnItmFiliere);
		
		JMenuItem mnItmNote = new JMenuItem("Note");
		mnItmNote.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/note.png")));
		mnItmNote.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.add(mnItmNote);

		// menu aide
		JMenu mnAide = new JMenu("Aide");
		mnAide.setHorizontalAlignment(SwingConstants.CENTER);
		mnAide.setPreferredSize(new Dimension(70, 30));
		mnAide.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnAide.setForeground(new Color(0, 0, 0));
		menuBar.add(mnAide);

		JMenuItem ItmPropos = new JMenuItem("\u00E0 propos");
		ItmPropos.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/aide.png")));
		ItmPropos.addActionListener(new ecouteurMenu(this, "ItmPropos"));
		ItmPropos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnAide.add(ItmPropos);

		JMenuItem ItmContact = new JMenuItem("Contact");
		ItmContact.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/contact.png")));
		ItmContact.addActionListener(new ecouteurMenu(this,"ItmContact"));
		ItmContact.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnAide.add(ItmContact);
		getContentPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, -23, 870, 512);
		getContentPane().add(desktopPane);
		
		JLabel lblBienv = new JLabel("Bienvenue");
		lblBienv.setForeground(new Color(0, 0, 128));
		lblBienv.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienv.setFont(new Font("Script MT Bold", Font.BOLD, 99));
		lblBienv.setBounds(99, 177, 586, 183);
		desktopPane.add(lblBienv);
		
		lblimage = new JLabel("");
		lblimage.setHorizontalAlignment(SwingConstants.CENTER);
		lblimage.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblimage.setBounds(0, 0, 880, 512);
		desktopPane.add(lblimage);
		ItmEtudiant.addActionListener(new ecouteurMenu(this,"ItmEtudiant"));
		

		ItmEnseignant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspaceEnseignant enseignant = new EspaceEnseignant();
				enseignant.setLocation(lblimage.getLocation());
				desktopPane.add(enseignant);
				enseignant.show();

			}
		});
		ItmRechercheEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechercheEtudiant rechercheEtudiant = new RechercheEtudiant();
				rechercheEtudiant.setLocation(lblimage.getLocation());
				desktopPane.add(rechercheEtudiant);
				rechercheEtudiant.show();

			}
		});
		ItmRechercheEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RechercheEnseignant rechercheEnseignant = new RechercheEnseignant();
				rechercheEnseignant.setLocation(lblimage.getLocation());
				desktopPane.add(rechercheEnseignant);
				rechercheEnseignant.show();

			}
		});
		mnItmMatiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EspaceMatiere espaceMatiere = new EspaceMatiere();
				espaceMatiere.setLocation(lblimage.getLocation());
				desktopPane.add(espaceMatiere);
				espaceMatiere.show();

			}
		});

		mnItmFiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspaceFiliere espaceFiliere = new EspaceFiliere();
				espaceFiliere.setLocation(lblimage.getLocation());
				desktopPane.add(espaceFiliere);
				espaceFiliere.show();
			}
		});
		mnItmNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EspaceNote espaceNote =new EspaceNote();
				espaceNote.setLocation(lblimage.getLocation());
				desktopPane.add(espaceNote);
				espaceNote.show();
			}
		});
		ItmRechercheNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RechercheNote rechercheMatiere =new RechercheNote();
				rechercheMatiere.setLocation(lblimage.getLocation());
				desktopPane.add(rechercheMatiere);
				rechercheMatiere.show();
				
			}
		});
	}

	public static JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JLabel getLblImage() {
		return lblimage;
	}
}
