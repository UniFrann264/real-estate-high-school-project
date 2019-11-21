package jpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.Blob;

import principal.MySQL;
import principal.VentanaPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class visualizadorPropiedad extends JPanel {

	private fondo fondo = new fondo();
	private static JLabel foto = new JLabel("");
	private static Statement st = null;
	private static ResultSet rs = null;
	public static int id = 0;
	private static JLabel direccion = new JLabel("");
	private static JLabel precio = new JLabel("");
	private static JTextArea descripcion = new JTextArea();
	private static JLabel tipo = new JLabel("");
	private	static JLabel dormitorios = new JLabel("");
	private static JLabel baños = new JLabel("");
	private static JLabel garaje = new JLabel("");
	private static JLabel amoblada = new JLabel("");
	private JDateChooser fechaDate = new JDateChooser();
	private JButton reservarFecha = new JButton("Reservar fecha");
	private JButton refrescar = new JButton("Refrescar");
	/**
	 * Create the panel.
	 */
	public visualizadorPropiedad() {
		setLayout(null);
		
		JLabel retroceder = new JLabel("");
		retroceder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VentanaPrincipal.visualizadorInteresado(visualizadorInteresado.solicitud);
				reservarFecha.setEnabled(false);
				limpiar();
				
			}
		});
		retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		retroceder.setIcon(new ImageIcon(visualizadorPropiedad.class.getResource("/img/retrocederIcono.png")));
		retroceder.setBounds(10, 30, 35, 34);
		add(retroceder);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setBounds(27, 123, 548, 415);
		add(panel);
		panel.setLayout(null);
		
		foto.setBounds(10, 11, 528, 393);
		panel.add(foto);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelDatos.setBounds(604, 123, 286, 466);
		add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n");
		lblInformacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInformacin.setBounds(10, 11, 266, 47);
		panelDatos.add(lblInformacin);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTipo.setBounds(10, 69, 54, 23);
		panelDatos.add(lblTipo);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrecio.setBounds(10, 137, 70, 23);
		panelDatos.add(lblPrecio);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccin.setBounds(10, 103, 84, 23);
		panelDatos.add(lblDireccin);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDescripcin.setBounds(10, 171, 107, 23);
		panelDatos.add(lblDescripcin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 171, 149, 132);
		panelDatos.add(scrollPane);
		
		descripcion.setEditable(false);
		scrollPane.setViewportView(descripcion);
		
		tipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tipo.setBounds(57, 69, 219, 23);
		panelDatos.add(tipo);
		direccion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		direccion.setBounds(86, 103, 190, 23);
		panelDatos.add(direccion);
		precio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		precio.setBounds(75, 137, 201, 23);
		panelDatos.add(precio);
		
		JLabel lblDormitorios = new JLabel("Dormitorios:");
		lblDormitorios.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDormitorios.setBounds(10, 312, 107, 23);
		panelDatos.add(lblDormitorios);
		
		JLabel lblBaos = new JLabel("Ba\u00F1os:");
		lblBaos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBaos.setBounds(10, 346, 70, 23);
		panelDatos.add(lblBaos);
		
		JLabel lblGaraje = new JLabel("Garaje:");
		lblGaraje.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGaraje.setBounds(10, 380, 70, 23);
		panelDatos.add(lblGaraje);
		
		JLabel lblAmoblada = new JLabel("Amoblada:");
		lblAmoblada.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmoblada.setBounds(10, 414, 84, 23);
		panelDatos.add(lblAmoblada);
		dormitorios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		dormitorios.setBounds(119, 314, 157, 23);
		panelDatos.add(dormitorios);
		baños.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		baños.setBounds(75, 346, 201, 23);
		panelDatos.add(baños);
		garaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		garaje.setBounds(75, 380, 201, 23);
		panelDatos.add(garaje);
		amoblada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		amoblada.setBounds(104, 414, 172, 23);
		panelDatos.add(amoblada);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_1.setBounds(27, 549, 548, 40);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAgendarse = new JLabel("Agendarse:");
		lblAgendarse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAgendarse.setBounds(10, 8, 116, 25);
		panel_1.add(lblAgendarse);
		fechaDate.getCalendarButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				reservarFecha.setEnabled(false);
				
			}
		});
		fechaDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				reservarFecha.setEnabled(false);

			}
		});
		
		fechaDate.setDateFormatString("yyyy-MM-dd");
		fechaDate.setBounds(136, 8, 112, 21);
		panel_1.add(fechaDate);
		reservarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(fechaDate.getDate());
				
				boolean disponible = consultarFechaDisponible(date);
					
					if(!(fechaDate.getCalendar() == null)) {
						
						if(disponible) {
							
							int opcionVar = JOptionPane.showConfirmDialog(null, "¿Está seguro de hacer la reserva el día: " + date + "?");
							
							if(opcionVar == 0) {
								
								registrarFecha();
								
							}
							
						} else {
							
							JOptionPane.showMessageDialog(null, "Lo sentimos, esta fecha esta ocupada");
							
						}
							
					} else {
						
						JOptionPane.showMessageDialog(null, "Error, ingrese una fecha correcta.");
						
					}
				
			}
		});
		reservarFecha.setCursor(new Cursor(Cursor.HAND_CURSOR));
		reservarFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		reservarFecha.setBounds(258, 6, 147, 27);
		panel_1.add(reservarFecha);
		reservarFecha.setEnabled(false);
		
		refrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					
				if(!(fechaDate.getCalendar() == null)) {
						
					reservarFecha.setEnabled(true);
						
				} else {
						
					JOptionPane.showMessageDialog(null, "Error, ingrese una fecha correcta.");
						
				}
			

			}
		});
		refrescar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		refrescar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		refrescar.setBounds(415, 6, 123, 27);
		panel_1.add(refrescar);
		
		fondo.setBounds(0, 0, 900, 600);
		add(fondo);
		fondo.setLayout(null);

	}
	
	public static void descargarImagen() {

		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			rs = st.executeQuery("SELECT imagen FROM foto WHERE idFoto='" + id + "';");
			
			if(rs.next()) {
				
				Blob blob = (Blob) rs.getBlob("imagen");
				
				int bloblargo = (int) blob.length();  
				byte[] blobenBytes = blob.getBytes(1, bloblargo);
				
				ByteArrayInputStream bis = new ByteArrayInputStream(blobenBytes);
				
				try { 
					
					BufferedImage imagenBuffered = ImageIO.read(bis);
					ImageIcon imagenFinal2 = new ImageIcon(imagenBuffered.getScaledInstance(528, 393, Image.SCALE_SMOOTH));
					foto.setIcon(imagenFinal2);
					
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, "Error al aplicar la imagen: " + e.getMessage());
					
				}
	
			}
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar descargar la foto: " + e.getMessage());
			
		}
		
	}
	
	public static void obtenerDatos() {
		
		descargarImagen();
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM propiedad WHERE idPropiedad='" + id + "';");
			
			if(rs.next()) {
				
				if(rs.getString("tipo").equals("casa")) {
					
					tipo.setText("Casa");
					
				} else {
					
					if(rs.getString("tipo").equals("apartamento")) {
						
						tipo.setText("Apartamento");
						
					} else {
						
						if(rs.getString("tipo").equals("local comercial")) {
							
							tipo.setText("Local comercial");
							
						}
						
					}
					
				}
				
				direccion.setText(rs.getString("direccion"));
				precio.setText(rs.getString("precio"));
				descripcion.setText(rs.getString("descripcion"));
				dormitorios.setText(rs.getString("dormitorios"));
				baños.setText(rs.getString("baños"));
				garaje.setText(rs.getString("garaje"));
				amoblada.setText(rs.getString("amoblada"));
				
			}

			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar los datos de la propiedad: " + "\n" + e.getMessage());
			
		}
		
	}
	
	private boolean consultarFechaDisponible(String fecha) {
		
		boolean resultado = false;
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			rs = st.executeQuery("SELECT fecha FROM agenda WHERE fecha='" + fecha + "' AND para='" + id + "';");
			
			if(rs.next()) {
				
				resultado = false;
				
			} else {
				
				resultado = true;
				
			}
			
			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar consultar la fecha: " + e.getMessage());
			
		}
		
		return resultado;
	}
	
	private void registrarFecha() {
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(fechaDate.getDate());
			st.executeUpdate("INSERT INTO agenda (`fecha`, `estado`, `registra`, `para`) VALUES ('" + date + "', 'pendiente', '" + VentanaPrincipal.cedula + "', '" + id + "');");
			MySQL.getConnection().close();
			JOptionPane.showMessageDialog(null, "¡Su fecha ha sido ingresada con éxito!");
			limpiar();
			reservarFecha.setEnabled(false);
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar ingresar su fecha: " + e.getMessage());
			
		}
		
	}
	
	private void limpiar() {
		
		fechaDate.setCalendar(null);
		
	}
	
}
