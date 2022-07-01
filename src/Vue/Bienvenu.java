package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Bienvenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField login;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenu frame = new Bienvenu();
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
	public Bienvenu() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Bienvenu.class.getResource("/Image/icons8-mot-de-passe-24.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 246);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getWidth() / 2, dim.height / 2 - this.getHeight() / 2);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		login = new JTextField();
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setColumns(10);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verif();
			}
		});
		login.setBounds(162, 76, 165, 22);
		contentPane.add(login);

		password = new JPasswordField();
		password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verif();
			}
		});

		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(164, 129, 165, 22);
		contentPane.add(password);

		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setForeground(new Color(173, 255, 47));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(52, 68, 82, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mot de passe");
		lblNewLabel_1_1.setForeground(new Color(173, 255, 47));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(46, 129, 101, 22);
		contentPane.add(lblNewLabel_1_1);

		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verif();
			}
		});
		btnNewButton.setIcon(new ImageIcon(Bienvenu.class.getResource("/Image/icons8-verrouiller-2-24.png")));
		btnNewButton.setBackground(new Color(255, 228, 196));
		btnNewButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(269, 162, 155, 34);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Bienvenu.class.getResource("/Image/Annotation 2020-06-27 155253.png")));
		lblNewLabel.setBounds(0, 0, 444, 217);
		contentPane.add(lblNewLabel);
	}

	void verif() {
		char[] pass = password.getPassword();
		String passwor = "";
		for (char c : pass) {
			passwor += c;
		}

		if (passwor.equals("root") && login.getText().equals("root")) {
			MenuPrincipal menu = new MenuPrincipal();
			dispose();
			menu.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, " login ou mot de passe incorrect ", "ERREUR",
					JOptionPane.CLOSED_OPTION);

		}
	}
}
