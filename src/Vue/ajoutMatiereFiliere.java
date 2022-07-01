package Vue;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import Controleur.ecouteurAjoutMatForFiliere;
import Model.ModelFiliere;
import java.awt.Color;
import java.awt.Dimension;

public class ajoutMatiereFiliere extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EspaceFiliere espaceFiliere;
	public JList list;

	/**
	 * Create the frame.
	 */
	public ajoutMatiereFiliere(EspaceFiliere espaceFiliere) {
		this.espaceFiliere = espaceFiliere;
		setResizable(false);
		setBounds(100, 100, 306, 178);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		getContentPane().setLayout(null);
		setVisible(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(177, 27, 113, 77);
		getContentPane().add(scrollPane);

		list = new JList<>(new ModelFiliere().matiere());
		scrollPane.setViewportView(list);

		JLabel lblNewLabel = new JLabel("Choisir liste matiere");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 43, 157, 40);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(135, 206, 250));
		btnNewButton.addActionListener(new ecouteurAjoutMatForFiliere(this, espaceFiliere));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(83, 115, 113, 23);
		getContentPane().add(btnNewButton);

	}

}
