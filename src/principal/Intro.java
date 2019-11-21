package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Intro extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intro frame = new Intro();
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
	public Intro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Intro.class.getResource("/img/icono.png")));
		setTitle("Inmobiliaria GALAXY");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreadoPorAlumnos = new JLabel("Creado por alumnos del");
		lblCreadoPorAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCreadoPorAlumnos.setBounds(10, 11, 280, 52);
		contentPane.add(lblCreadoPorAlumnos);
		
		JLabel lblIf = new JLabel("2\u00B0 IF Vespertino");
		lblIf.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblIf.setBounds(20, 74, 252, 52);
		contentPane.add(lblIf);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(Intro.class.getResource("/img/logoITS.png")));
		logo.setBounds(43, 137, 215, 152);
		contentPane.add(logo);
		setUndecorated(true);
		setLocationRelativeTo(null);
	}

}
