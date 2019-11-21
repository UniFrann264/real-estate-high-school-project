package jpanel;

import javax.swing.JPanel;

import principal.MySQL;
import principal.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class administradorDePropiedades extends JPanel {
	
	private fondo fondo = new fondo();
	private static JTable tablaDatos = new JTable();
	private JButton editarPropiedad = new JButton("Editar propiedad");
	private JButton eliminarPropiedad = new JButton("Eliminar propiedad");
	private int seleccion;
	private static Statement st = null;
	private static ResultSet rs = null;

	/**
	 * Create the panel.
	 */
	public administradorDePropiedades() {
		setLayout(null);
		
		JLabel retroceder = new JLabel("");
		retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		retroceder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				VentanaPrincipal.menuPrincipal();
				
			}
		});
		retroceder.setIcon(new ImageIcon(administradorDePropiedades.class.getResource("/img/retrocederIcono.png")));
		retroceder.setBounds(10, 36, 35, 34);
		add(retroceder);
		
		JButton agregarPropiedad = new JButton("Agregar propiedad");
		agregarPropiedad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		agregarPropiedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaPrincipal.gestionarPropiedad(0, 0);
				
			}
		});
		agregarPropiedad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		agregarPropiedad.setBounds(10, 81, 209, 34);
		add(agregarPropiedad);
		eliminarPropiedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea borrar la propiedad?");
				
				if(opcion == 0) {
					
					DefaultTableModel modeloTemporal=(DefaultTableModel) tablaDatos.getModel();
					seleccion = Integer.parseInt(String.valueOf(modeloTemporal.getValueAt(tablaDatos.getSelectedRow(), 0)));
					
					try {
						
						MySQL.open();
						st = MySQL.getConnection().createStatement();
						st.executeUpdate("DELETE FROM propiedad WHERE idPropiedad='" + seleccion + "';");
						st.executeUpdate("DELETE FROM foto WHERE idFoto='" + seleccion + "';");
						MySQL.getConnection().close();
						
						obtenerDatos();
						
					} catch (SQLException e) {
						
						JOptionPane.showMessageDialog(null, "Error al intentar eliminar la propiedad...");
						
					}
					
				}
				
			}
		});
		
		eliminarPropiedad.setEnabled(false);
		eliminarPropiedad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		eliminarPropiedad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		eliminarPropiedad.setBounds(429, 81, 209, 34);
		add(eliminarPropiedad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 880, 463);
		add(scrollPane);
		tablaDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel modeloTemporal=(DefaultTableModel) tablaDatos.getModel();
				seleccion = Integer.parseInt(String.valueOf(modeloTemporal.getValueAt(tablaDatos.getSelectedRow(), 0)));
				editarPropiedad.setEnabled(true);
				eliminarPropiedad.setEnabled(true);
				
			}
		});
		
		tablaDatos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Propiedad", "Tipo", "Direcci\u00F3n", "Precio", "Descripci\u00F3n", "Dormitorios", "Ba\u00F1os", "Garaje", "Amoblada"
			}
		));
		scrollPane.setViewportView(tablaDatos);
		editarPropiedad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editarPropiedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaPrincipal.gestionarPropiedad(1, seleccion);
				
			}
		});
		
		editarPropiedad.setEnabled(false);
		editarPropiedad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		editarPropiedad.setBounds(229, 81, 190, 34);
		add(editarPropiedad);
		
		JButton refrescar = new JButton("Refrescar");
		refrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				obtenerDatos();
				
			}
		});
		refrescar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		refrescar.setBounds(55, 36, 164, 34);
		add(refrescar);

		fondo.setBounds(0, 0, 900, 600);
		add(fondo);
		fondo.setLayout(null);
		
	}
	
	public static void obtenerDatos() {
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM propiedad;");
			
			DefaultTableModel modeloTemporal=(DefaultTableModel) tablaDatos.getModel();
	        
	        for (int i = tablaDatos.getRowCount() -1; i >= 0; i--){
	        	
	        	modeloTemporal.removeRow(i);
	        	
	        }
			
	        tablaDatos.setModel(modeloTemporal);
	        
	        DefaultTableModel modelo=(DefaultTableModel) tablaDatos.getModel();
				
			while(rs.next()) {
					
				modelo.addRow(new Object[] {rs.getString("idPropiedad"), rs.getString("tipo"), rs.getString("direccion"), rs.getString("precio"), rs.getString("descripcion"), rs.getString("dormitorios"), rs.getString("baños"), rs.getString("garaje"), rs.getString("amoblada")});
					
			}
			
			tablaDatos.setModel(modelo);
			MySQL.getConnection().close();
			
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Hubo un error al intentar consultar las propiedades: " + "\n" + e.getMessage());
			
		}
		
	}
}
