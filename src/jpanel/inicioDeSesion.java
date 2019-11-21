package jpanel;

import javax.swing.JPanel;
import jpanel.fondo;
import principal.MySQL;
import principal.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class inicioDeSesion extends JPanel {

	private fondo fondo = new fondo();
	private JTextField cIdIniciarSesion = new JTextField();
	private JTextField cIdRegistrar = new JTextField();
	private JTextField nombreRegistrar = new JTextField();
	private JTextField apellidoRegistrar = new JTextField();
	private JTextField correoRegistrar = new JTextField();
	private JPasswordField contraseñaRegistrar = new JPasswordField();
	private JPasswordField confirmarContraseñaRegistrar = new JPasswordField();
	private JPanel panelRegistrarUsuario = new JPanel();
	private JPasswordField contraseñaIniciarSesion;
	private JPanel panelPrincipal = new JPanel();
	private JDateChooser fechaDeNacimiento = new JDateChooser();
	private Statement st = null;
	private ResultSet rs = null;
	
	/**
	 * Create the panel.
	 */
	public inicioDeSesion() {
		setLayout(null);
		fondo.setBounds(0, 0, 900, 600);
		add(fondo); 
		fondo.setLayout(null);
		
		panelPrincipal.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelPrincipal.setBounds(284, 95, 331, 407);
		fondo.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(inicioDeSesion.class.getResource("/img/logo.png")));
		logo.setBounds(10, 11, 311, 86);
		panelPrincipal.add(logo);
		
		cIdIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cIdIniciarSesion.setBounds(73, 129, 248, 36);
		panelPrincipal.add(cIdIniciarSesion);
		cIdIniciarSesion.setColumns(10);
		
		JLabel lblnoTienesCuenta = new JLabel("\u00BFNo tienes cuenta? Registrate");
		lblnoTienesCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				panelPrincipal.setVisible(false);
				panelRegistrarUsuario.setVisible(true);
				cIdRegistrar.setText("");
				nombreRegistrar.setText("");
				apellidoRegistrar.setText("");
				correoRegistrar.setText("");
				contraseñaRegistrar.setText("");
				confirmarContraseñaRegistrar.setText("");
				fechaDeNacimiento.setCalendar(null);
				
			}
		});
		lblnoTienesCuenta.setForeground(Color.BLUE);
		lblnoTienesCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblnoTienesCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblnoTienesCuenta.setBounds(10, 382, 311, 14);
		lblnoTienesCuenta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelPrincipal.add(lblnoTienesCuenta);
		
		JLabel iconoCedula = new JLabel("");
		iconoCedula.setIcon(new ImageIcon(inicioDeSesion.class.getResource("/img/cedulaIcono.png")));
		iconoCedula.setBounds(10, 128, 50, 37);
		panelPrincipal.add(iconoCedula);
		
		JLabel contrasenaIcono = new JLabel("");
		contrasenaIcono.setIcon(new ImageIcon(inicioDeSesion.class.getResource("/img/llaveIcono.png")));
		contrasenaIcono.setBounds(10, 182, 50, 37);
		panelPrincipal.add(contrasenaIcono);
		
		JButton iniciarSesionBoton = new JButton("Iniciar sesi\u00F3n");
		iniciarSesionBoton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iniciarSesionBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				iniciarSesion(cIdIniciarSesion.getText(), String.valueOf(contraseñaIniciarSesion.getPassword()));
				
			}
		});
		iniciarSesionBoton.setBounds(10, 244, 311, 36);
		panelPrincipal.add(iniciarSesionBoton);
		
		contraseñaIniciarSesion = new JPasswordField();
		contraseñaIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contraseñaIniciarSesion.setBounds(73, 176, 248, 36);
		panelPrincipal.add(contraseñaIniciarSesion);
		panelRegistrarUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		
		panelRegistrarUsuario.setBounds(284, 95, 331, 407);
		fondo.add(panelRegistrarUsuario);
		panelRegistrarUsuario.setLayout(null);
		panelRegistrarUsuario.setVisible(false);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(inicioDeSesion.class.getResource("/img/logo.png")));
		label.setBounds(10, 11, 311, 86);
		panelRegistrarUsuario.add(label);
		
		cIdRegistrar = new JTextField();
		cIdRegistrar.setBounds(126, 101, 193, 20);
		panelRegistrarUsuario.add(cIdRegistrar);
		cIdRegistrar.setColumns(10);
		
		JLabel lblCdula = new JLabel("C\u00E9dula");
		lblCdula.setBounds(7, 104, 46, 14);
		panelRegistrarUsuario.add(lblCdula);
		
		nombreRegistrar.setBounds(126, 132, 193, 20);
		panelRegistrarUsuario.add(nombreRegistrar);
		nombreRegistrar.setColumns(10);
		
		apellidoRegistrar.setBounds(126, 163, 193, 20);
		panelRegistrarUsuario.add(apellidoRegistrar);
		apellidoRegistrar.setColumns(10);
		
		correoRegistrar.setBounds(126, 225, 193, 20);
		panelRegistrarUsuario.add(correoRegistrar);
		correoRegistrar.setColumns(10);
		
		contraseñaRegistrar.setBounds(126, 256, 193, 20);
		panelRegistrarUsuario.add(contraseñaRegistrar);
		
		confirmarContraseñaRegistrar.setBounds(126, 287, 193, 20);
		panelRegistrarUsuario.add(confirmarContraseñaRegistrar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(8, 135, 57, 14);
		panelRegistrarUsuario.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(8, 166, 57, 14);
		panelRegistrarUsuario.add(lblApellido);
		
		fechaDeNacimiento.setDateFormatString("yyyy-MM-dd");
		fechaDeNacimiento.setBounds(126, 194, 101, 20);
		panelRegistrarUsuario.add(fechaDeNacimiento);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(8, 228, 57, 14);
		panelRegistrarUsuario.add(lblCorreo);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(8, 259, 83, 14);
		panelRegistrarUsuario.add(lblContrasea);
		
		JLabel lblConfirmarCont = new JLabel("Confirmar cont.");
		lblConfirmarCont.setBounds(8, 290, 94, 14);
		panelRegistrarUsuario.add(lblConfirmarCont);
		
		JTextPane txtpnAlCrearseUna = new JTextPane();
		txtpnAlCrearseUna.setBackground(UIManager.getColor("Button.background"));
		txtpnAlCrearseUna.setText("Al crearse una cuenta, usted acepta los t\u00E9rminos y condiciones puestos en Marzo del 2019 en adelante.");
		txtpnAlCrearseUna.setBounds(8, 361, 313, 38);
		panelRegistrarUsuario.add(txtpnAlCrearseUna);
		
		JLabel lblFechaNac = new JLabel("Fecha nac.");
		lblFechaNac.setBounds(8, 197, 67, 14);
		panelRegistrarUsuario.add(lblFechaNac);
		
		JButton botonRegistrar = new JButton("Registrarse");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				boolean bloqueo = false;
				int contadorArrobas = 0;
				
				if(!cIdRegistrar.getText().equals("")) {
					
					if(!nombreRegistrar.getText().equals("")) {
						
						if(!apellidoRegistrar.getText().equals("")) {
							
							if(!(fechaDeNacimiento.getDate() == null)) {
								
								if(!correoRegistrar.getText().equals("")) {
									
									if(!String.valueOf(contraseñaRegistrar.getPassword()).equals("")) {
										
										if(!String.valueOf(confirmarContraseñaRegistrar.getPassword()).equals("")) {
											
											for(int i=0;i < cIdRegistrar.getText().length();i++) {
												
												if(!bloqueo) {
													
													if(cIdRegistrar.getText().charAt(i) == '.' || cIdRegistrar.getText().charAt(i) == '-') {
														
														JOptionPane.showMessageDialog(null, "Error, ingrese su cédula sin puntos ni guiones...");
														bloqueo = true;
														
													}
													
												}				
												
											}
											
											if(!bloqueo) {
											
											for(int i=0;i < correoRegistrar.getText().length();i++) {
												
												if(correoRegistrar.getText().charAt(i) == '@') {
													
													contadorArrobas++;
													
												}
												
											}
											
												if(!(contadorArrobas == 1)) {
												
													JOptionPane.showMessageDialog(null, "Correo inválido...");
													bloqueo = true;
													
												}
											
											}
											
											if(!String.valueOf(contraseñaRegistrar.getPassword()).equals(String.valueOf(confirmarContraseñaRegistrar.getPassword()))) {
			
												JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden...");
				
											}
											
											if(!bloqueo) {
												
												registrarUsuario(cIdRegistrar.getText(), correoRegistrar.getText(), String.valueOf(contraseñaRegistrar.getPassword()), nombreRegistrar.getText(), apellidoRegistrar.getText(), fechaDeNacimiento.getDate());
											
											}											
											
										} else {
											
											JOptionPane.showMessageDialog(null, "¡Error, el campo de confirmar contraseña no puede estár vacío...");
											
										}
										
									} else {
										
										JOptionPane.showMessageDialog(null, "¡Error, el campo de contraseña no puede estár vacío...");
										
									}
									
								} else {
									
									JOptionPane.showMessageDialog(null, "¡Error, el campo de correo no puede estár vacío...");
									
								}
								
							} else {
								
								JOptionPane.showMessageDialog(null, "¡Error, el campo de fecha de nacimiento no puede estar vacío...");
								
							}
							
						} else {
							
							JOptionPane.showMessageDialog(null, "¡Error, el campo de apellido no puede estár vacío...");
							
						}
						
					} else {
						
						JOptionPane.showMessageDialog(null, "¡Error, el campo de nombre no puede estár vacío...");
						
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null, "¡Error, el campo de cédula no puede estár vacío...");
					
				}
				
			}
		});
		botonRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		botonRegistrar.setBounds(126, 318, 193, 31);
		panelRegistrarUsuario.add(botonRegistrar);
		
		JButton salirRegistro = new JButton("Salir");
		salirRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panelPrincipal.setVisible(true);
				panelRegistrarUsuario.setVisible(false);
			}
		});
		salirRegistro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		salirRegistro.setBounds(8, 319, 108, 31);
		panelRegistrarUsuario.add(salirRegistro);
		
	}
	
	private void iniciarSesion(String cId, String contraseña) {
		
		boolean bloqueo = false;
		
		if(!cIdIniciarSesion.getText().equals("")) {
			
			if(!String.valueOf(contraseñaIniciarSesion.getPassword()).equals("")) {
				
				for(int i=0;i < cIdIniciarSesion.getText().length();i++) {
					
					if(!bloqueo) {
						
						if(cIdIniciarSesion.getText().charAt(i) == '.' || cIdIniciarSesion.getText().charAt(i) == '-') {
							
							JOptionPane.showMessageDialog(null, "Error, ingrese su cédula sin puntos ni guiones");
							bloqueo = true;
							
						}
						
					}
					
				}
				
				if(!bloqueo) {
					
					try {
						
						MySQL.open();
						st = MySQL.getConnection().createStatement();
						rs = st.executeQuery("SELECT cId, nombre, apellido, tipo FROM persona WHERE cId='" + cId + "' AND contraseña='" + contraseña + "';");
						
						if(rs.next()) {
							
							VentanaPrincipal.cedula = rs.getString("cId");
							VentanaPrincipal.nombre = rs.getString("nombre");
							VentanaPrincipal.apellido = rs.getString("apellido");
							menuPrincipal.nombreYApellidoInteresado.setText(VentanaPrincipal.nombre + " - " + VentanaPrincipal.apellido);
							menuPrincipal.nombreYApellidoGerente.setText(VentanaPrincipal.nombre + " - " + VentanaPrincipal.apellido);

							if(rs.getString("tipo").equals("gerente")) {
								
								VentanaPrincipal.tipo = 0;
								
							} else {
								
								VentanaPrincipal.tipo = 1;
								
							}
							
							JOptionPane.showMessageDialog(null, "¡Inicio de sesión correcto! ¡Bienvenido " + VentanaPrincipal.nombre + "!");
							VentanaPrincipal.inicioDeSesion = true;
							VentanaPrincipal.comprobarPrivilegios();
							
							VentanaPrincipal.menuPrincipal();
							
							if (rs.getString("tipo").equals("gerente")) {
								
								VentanaPrincipal.establecerTitulo("Inmobiliaria GALAXY - Gerente");
								
							} else {
								
								VentanaPrincipal.establecerTitulo("Inmobiliaria GALAXY - Interesado");
								
							}
										
						} else {
							
							JOptionPane.showMessageDialog(null, "Cédula y/o contraseña incorrecto");
							
						}
						cIdIniciarSesion.setText("");
						contraseñaIniciarSesion.setText("");
						MySQL.getConnection().close();
						
					} catch (SQLException e) {
						
						JOptionPane.showMessageDialog(null, "Error con la base de datos.");
						
					}
					
				}
				
			} else {
				
				JOptionPane.showMessageDialog(null, "Error, el campo de contraseña no puede estar vacío...");
				
			}
			
		} else {
			
			JOptionPane.showMessageDialog(null, "Error, el campo de cédula no puede estar vacío...");
			
		}
					
	}
	
	private void registrarUsuario(String cId, String correo, String contraseña, String nombre, String apellido, Date fechaDeNacimientoVar) {

		long d = fechaDeNacimientoVar.getTime();
		java.sql.Date fecha = new java.sql.Date(d);
		
		try {
			
			MySQL.open();
			Statement st = MySQL.getConnection().createStatement();
			st.executeUpdate("INSERT INTO persona VALUES('" + cId + "', '" + correo +  "', '" + contraseña + "', '" + nombre + "', '" + apellido + "', '" + fecha + "', 'interesado');");
			JOptionPane.showMessageDialog(null, "¡Su cuenta ha sido registrada con éxito!");
			panelPrincipal.setVisible(true);
			panelRegistrarUsuario.setVisible(false);
			cIdRegistrar.setText("");
			nombreRegistrar.setText("");
			apellidoRegistrar.setText("");
			correoRegistrar.setText("");
			contraseñaRegistrar.setText("");
			confirmarContraseñaRegistrar.setText("");
			fechaDeNacimiento.setCalendar(null);
			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Hubo un error al crear su cuenta" + "\n" + e.getMessage());
			
		}

	}
}
