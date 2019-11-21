package jpanel;

import javax.swing.JPanel;

import principal.VentanaPrincipal;
import javax.swing.JButton;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class menuPrincipal extends JPanel {

	private static JPanel gerente = new JPanel();
	private static JPanel interesado = new JPanel();
	public static JLabel nombreYApellidoInteresado = new JLabel("");
	public static JLabel nombreYApellidoGerente = new JLabel("");
	
	/**
	 * Create the panel.
	 */
	public menuPrincipal() {
		setLayout(null);
		
		gerente.setBounds(0, 0, 900, 600);
		add(gerente);
		gerente.setLayout(null);
		
		JButton cerrarSesionGerente = new JButton("Cerrar sesi\u00F3n");
		cerrarSesionGerente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cerrarSesionGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cerrarSesion();
				
			}
		});
		cerrarSesionGerente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cerrarSesionGerente.setBounds(733, 553, 157, 36);
		gerente.add(cerrarSesionGerente);
		
		JLabel administrarAgenda = new JLabel("");
		administrarAgenda.setCursor(new Cursor(Cursor.HAND_CURSOR));
		administrarAgenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				VentanaPrincipal.agenda();
				
			}
		});
		administrarAgenda.setBounds(70, 50, 350, 425);
		gerente.add(administrarAgenda);
		
		JLabel administrarPropiedades = new JLabel("");
		administrarPropiedades.setCursor(new Cursor(Cursor.HAND_CURSOR));
		administrarPropiedades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VentanaPrincipal.administrarPropiedades();
				
			}
		});
		administrarPropiedades.setBounds(478, 50, 350, 425);
		gerente.add(administrarPropiedades);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(menuPrincipal.class.getResource("/img/iconoUsuario.png")));
		label.setBounds(503, 553, 36, 36);
		gerente.add(label);
		
		nombreYApellidoGerente.setForeground(Color.WHITE);
		nombreYApellidoGerente.setBounds(549, 552, 171, 37);
		gerente.add(nombreYApellidoGerente);
		
		JLabel label_1 = new JLabel("2019 CopyRight - Inmobiliaria GALAXY");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_1.setBounds(10, 553, 342, 36);
		gerente.add(label_1);
		
		JLabel fondoGerente = new JLabel("New label");
		fondoGerente.setIcon(new ImageIcon(menuPrincipal.class.getResource("/img/interfazGerente.jpg")));
		fondoGerente.setBounds(0, 0, 900, 600);
		gerente.add(fondoGerente);
		
		interesado.setBounds(0, 0, 900, 600);
		add(interesado);
		interesado.setLayout(null);
		
		JButton cerrarSesionInteresado = new JButton("Cerrar sesi\u00F3n");
		cerrarSesionInteresado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cerrarSesionInteresado.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cerrarSesionInteresado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cerrarSesion();
				
			}
		});
		cerrarSesionInteresado.setBounds(727, 551, 163, 36);
		interesado.add(cerrarSesionInteresado);
		
		JLabel compra = new JLabel("");
		compra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VentanaPrincipal.visualizadorInteresado(0);
				visualizadorInteresado.detectarPropiedades(false, 0);
				
			}
		});
		compra.setCursor(new Cursor(Cursor.HAND_CURSOR));
		compra.setBounds(41, 45, 384, 461);
		interesado.add(compra);
		
		JLabel alquiler = new JLabel("");
		alquiler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VentanaPrincipal.visualizadorInteresado(1);
				visualizadorInteresado.detectarPropiedades(false, 0);
				
			}
		});
		alquiler.setBounds(480, 45, 390, 461);
		alquiler.setCursor(new Cursor(Cursor.HAND_CURSOR));
		interesado.add(alquiler);
		
		JLabel lblCopyright = new JLabel("2019 CopyRight - Inmobiliaria GALAXY");
		lblCopyright.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCopyright.setBounds(10, 553, 342, 36);
		interesado.add(lblCopyright);
		
		JLabel iconoUsuario = new JLabel("");
		iconoUsuario.setIcon(new ImageIcon(menuPrincipal.class.getResource("/img/iconoUsuario.png")));
		iconoUsuario.setBounds(502, 551, 36, 36);
		interesado.add(iconoUsuario);
		nombreYApellidoInteresado.setForeground(Color.WHITE);
		nombreYApellidoInteresado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		nombreYApellidoInteresado.setBounds(548, 556, 169, 24);
		interesado.add(nombreYApellidoInteresado);
		
		JLabel fondoInteresado = new JLabel("");
		fondoInteresado.setIcon(new ImageIcon(menuPrincipal.class.getResource("/img/interfazInteresado.jpg")));
		fondoInteresado.setBounds(0, 0, 900, 600);
		interesado.add(fondoInteresado);

	}
	
	private void cerrarSesion() {
		
		int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cerrar sesión?");
		
		if(opcion == 0) {
			
			VentanaPrincipal.nombre = "";
			VentanaPrincipal.apellido = "";
			VentanaPrincipal.inicioDeSesion = false;
			VentanaPrincipal.inicioDeSesion();
			VentanaPrincipal.establecerTitulo("Inmobiliaria GALAXY");
			
		}
		
	}
	
	public void gerente() {
		
		gerente.setVisible(true);
		interesado.setVisible(false);
		
	}
	
	public void interesado() {
		
		gerente.setVisible(false);
		interesado.setVisible(true);
		
	}
}
