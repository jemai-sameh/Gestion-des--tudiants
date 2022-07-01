package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Contact extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact frame = new Contact();
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
	public Contact() {
		setState(Frame.ICONIFIED);
		setType(Type.UTILITY);
		setTitle("Contact");
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 313, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOk.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOk.setBounds(231, 157, 55, 35);
		contentPane.add(btnOk);

		JLabel lblNewLabel = new JLabel("Contact");
		lblNewLabel.setFont(new Font("Sitka Heading", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 22, 171, 60);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("E-mail : samehjemai98@gmail.com");
		lblNewLabel_1.setFont(new Font("Script MT Bold", Font.BOLD, 17));
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 83, 287, 48);
		contentPane.add(lblNewLabel_1);
	}
}
