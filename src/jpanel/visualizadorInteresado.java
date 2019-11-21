package jpanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;

import principal.MySQL;
import principal.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class visualizadorInteresado extends JPanel {
	
	private fondo fondo = new fondo();
	private JTextField id = new JTextField();
	private static JTable tablaDatos = new JTable();
	private static JButton seleccionar = new JButton("Seleccionar");
	private static ResultSet rs = null;
	private static Statement st = null;
	public static int solicitud = 0;
	private static int seleccion = 0;

	/**
	 * Create the panel.
	 */
	public visualizadorInteresado() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 880, 379);
		add(scrollPane);
		tablaDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel modeloTemporal=(DefaultTableModel) tablaDatos.getModel();
				seleccion = Integer.parseInt(String.valueOf(modeloTemporal.getValueAt(tablaDatos.getSelectedRow(), 0)));
				seleccionar.setEnabled(true);

			}
		});
		
		scrollPane.setViewportView(tablaDatos);
		tablaDatos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Propiedad", "Tipo", "Direcci\u00F3n", "Precio", "Descripci\u00F3n", "Dormitorios", "Ba\u00F1os", "Garaje", "Amoblada"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaDatos.getColumnModel().getColumn(0).setResizable(false);
		tablaDatos.getColumnModel().getColumn(1).setResizable(false);
		tablaDatos.getColumnModel().getColumn(2).setResizable(false);
		tablaDatos.getColumnModel().getColumn(3).setResizable(false);
		tablaDatos.getColumnModel().getColumn(4).setMinWidth(40);
		tablaDatos.getColumnModel().getColumn(5).setResizable(false);
		tablaDatos.getColumnModel().getColumn(6).setResizable(false);
		tablaDatos.getColumnModel().getColumn(6).setPreferredWidth(40);
		tablaDatos.getColumnModel().getColumn(7).setResizable(false);
		tablaDatos.getColumnModel().getColumn(7).setPreferredWidth(50);
		tablaDatos.getColumnModel().getColumn(8).setResizable(false);
		tablaDatos.getColumnModel().getColumn(8).setPreferredWidth(59);
		
		JLabel lblBuscador = new JLabel("Buscador ID:");
		lblBuscador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBuscador.setBounds(10, 85, 157, 35);
		add(lblBuscador);
		
		id = new JTextField();
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(id.getText().equals("")) {
					
					seleccionar.setEnabled(false);
					detectarPropiedades(false, 0);
					
				}
				
			}
		});
		id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		id.setBounds(10, 118, 96, 35);
		add(id);
		id.setColumns(10);
		
		JButton buscar = new JButton("Buscar");
		buscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				detectarPropiedades(true, Integer.parseInt(id.getText()));
				
			}
		});
		buscar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buscar.setBounds(10, 164, 96, 35);
		add(buscar);
		
		JLabel retroceder = new JLabel("");
		retroceder.setIcon(new ImageIcon(visualizadorInteresado.class.getResource("/img/retrocederIcono.png")));
		retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		retroceder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				VentanaPrincipal.menuPrincipal();
				
			}
		});
		retroceder.setBounds(10, 30, 35, 34);
		add(retroceder);
		seleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				visualizadorPropiedad.id = seleccion;
				VentanaPrincipal.visualizadorPropiedad();
				
			}
		});
		seleccionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		seleccionar.setEnabled(false);
		seleccionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		seleccionar.setBounds(116, 164, 142, 35);
		add(seleccionar);
		
		fondo.setBounds(0, 0, 900, 600);
		add(fondo);
		fondo.setLayout(null);
		
	}
	
	public static void detectarPropiedades(boolean idPersonalizada, int id) {

		DefaultTableModel modeloTemporal=(DefaultTableModel) tablaDatos.getModel();
        
        for (int i = tablaDatos.getRowCount() -1; i >= 0; i--){
        	
        	modeloTemporal.removeRow(i);
        	
        }
		
        tablaDatos.setModel(modeloTemporal);
        
        DefaultTableModel modelo=(DefaultTableModel) tablaDatos.getModel();
        
        try {
        	
        	MySQL.open();
			st = MySQL.getConnection().createStatement();
        	
        	if(solicitud == 0) {
				
				if(idPersonalizada) {
					
					rs = st.executeQuery("SELECT * FROM propiedad WHERE precio='compra' AND idPropiedad='" + id + "';");
					
				} else {
					
					rs = st.executeQuery("SELECT * FROM propiedad WHERE precio='compra';");
					
				}
		
			} else {
				
				if(idPersonalizada) {
					
					rs = st.executeQuery("SELECT * FROM propiedad WHERE precio='alquiler' AND idPropiedad='" + id + "';");
					
				} else {
					
					rs = st.executeQuery("SELECT * FROM propiedad WHERE precio='alquiler';");
					
				}
					
			}
        	
        	try {
        			
        		while(rs.next()) {
    					
    				modelo.addRow(new Object[] {rs.getString("idPropiedad"), rs.getString("tipo"), rs.getString("direccion"), rs.getString("precio"), rs.getString("descripcion"), rs.getString("dormitorios"), rs.getString("baños"), rs.getString("garaje"), rs.getString("amoblada")});
    					
    				if(idPersonalizada) {
    						
    					tablaDatos.changeSelection(0, 1, false, false);
    					seleccionar.setEnabled(true);
    						
    				}
    					
    			}
    				
    			tablaDatos.setModel(modelo);
				MySQL.getConnection().close();
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Error al intentar ingresar datos en la tabla..." + "\n" + e.getMessage());
				
			}
        	
        } catch (SQLException e) {
        	
        	JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos");
        	
        }
        
	}

}
