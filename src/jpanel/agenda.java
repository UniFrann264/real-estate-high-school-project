package jpanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import principal.MySQL;
import principal.VentanaPrincipal;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class agenda extends JPanel {
	
	private fondo fondo = new fondo();
	private JTextField idReserva = new JTextField();
	private JTextField idAgenda = new JTextField();
	private JTextField registra = new JTextField();
	private JLabel retroceder;
	private JTable tablaDatos = new JTable();
	private JButton refrescarDatos;
	private static Statement st = null;
	private static ResultSet rs = null;
	private JTextField idPropiedad = new JTextField();;
	private JComboBox estado = new JComboBox();
	private JDateChooser fecha = new JDateChooser();
	private JPanel panelAcciones = new JPanel();

	/**
	 * Create the panel.
	 */
	public agenda() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 224, 880, 365);
		add(scrollPane);
		tablaDatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
	
				if (tablaDatos.getSelectedRow() != -1) {
					
					DefaultTableModel modeloTemporal=(DefaultTableModel) tablaDatos.getModel();

		            obtenerDatosDetalles(Integer.parseInt(String.valueOf(modeloTemporal.getValueAt(tablaDatos.getSelectedRow(), 0))));
		            panelAcciones.setVisible(true);
		            
		        } else {
		        	
		            JOptionPane.showMessageDialog(null, "Seleccione una fila");
		            
		        }
				
			}
		});
		
		tablaDatos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Agenda", "Fecha", "Estado", "Registra", "ID Propiedad"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaDatos.getColumnModel().getColumn(0).setResizable(false);
		tablaDatos.getColumnModel().getColumn(2).setResizable(false);
		tablaDatos.getColumnModel().getColumn(3).setResizable(false);
		tablaDatos.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(tablaDatos);
		
		JLabel lblIdReserva = new JLabel("ID reserva");
		lblIdReserva.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblIdReserva.setBounds(10, 86, 130, 35);
		add(lblIdReserva);
		
		idReserva = new JTextField();
		idReserva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				if(idReserva.getText().equals("")) {
					
					limpiarTablaDetalles();
					
				}
				
			}
		});
		idReserva.setFont(new Font("Tahoma", Font.PLAIN, 20));
		idReserva.setBounds(10, 132, 94, 35);
		add(idReserva);
		idReserva.setColumns(10);
		
		JButton buscar = new JButton("Buscar");
		buscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		buscar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buscar.setBounds(10, 178, 130, 35);
		add(buscar);
		
		panelAcciones.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3, true), "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAcciones.setBounds(426, 41, 464, 172);
		add(panelAcciones);
		panelAcciones.setLayout(null);
		panelAcciones.setVisible(false);
		
		JButton editarReserva = new JButton("Editar reserva");
		editarReserva.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				editarReserva();
				
			}
		});
		editarReserva.setBounds(20, 23, 166, 35);
		panelAcciones.add(editarReserva);
		editarReserva.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton eliminarReserva = new JButton("Eliminar reserva");
		editarReserva.setCursor(new Cursor(Cursor.HAND_CURSOR));
		eliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				eliminarReserva();
				obtenerDatos(false, 0);
				
			}
		});
		eliminarReserva.setBounds(20, 69, 166, 35);
		panelAcciones.add(eliminarReserva);
		eliminarReserva.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		idAgenda.setEditable(false);
		idAgenda.setBounds(306, 23, 148, 20);
		panelAcciones.add(idAgenda);
		idAgenda.setColumns(10);
		
		registra.setBounds(306, 111, 148, 20);
		panelAcciones.add(registra);
		registra.setColumns(10);
		fecha.setDateFormatString("yyyy-MM-dd");
		
		fecha.setBounds(306, 54, 97, 20);
		panelAcciones.add(fecha);
		
		JLabel lblIdAgenda = new JLabel("ID Agenda");
		lblIdAgenda.setBounds(217, 26, 62, 14);
		panelAcciones.add(lblIdAgenda);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(217, 54, 46, 14);
		panelAcciones.add(lblFecha);
		estado.setModel(new DefaultComboBoxModel(new String[] {"Pendiente", "Modificada", "Realizada", "Cancelada"}));
		
		estado.setBounds(306, 80, 148, 20);
		panelAcciones.add(estado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(217, 83, 46, 14);
		panelAcciones.add(lblEstado);
		
		JLabel lblRegistra = new JLabel("Registra");
		lblRegistra.setBounds(217, 114, 58, 14);
		panelAcciones.add(lblRegistra);
		
		idPropiedad.setBounds(306, 142, 148, 19);
		panelAcciones.add(idPropiedad);
		idPropiedad.setColumns(10);
		
		JLabel lblIdPropiedad = new JLabel("ID Propiedad");
		lblIdPropiedad.setBounds(217, 145, 73, 14);
		panelAcciones.add(lblIdPropiedad);
		
		retroceder = new JLabel("");
		retroceder.setIcon(new ImageIcon(agenda.class.getResource("/img/retrocederIcono.png")));
		retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		retroceder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VentanaPrincipal.menuPrincipal();
				
			}
		});
		retroceder.setBounds(10, 30, 35, 34);
		add(retroceder);
		
		refrescarDatos = new JButton("Refrescar datos");
		refrescarDatos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		refrescarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				obtenerDatos(false, 0);
				
			}
		});
		refrescarDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		refrescarDatos.setBounds(150, 178, 195, 35);
		add(refrescarDatos);
		
		fondo.setBounds(0, 0, 900, 600);
		add(fondo);
		fondo.setLayout(null);

	}
	
	public void obtenerDatos(boolean personalizado, int id) {
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			
			if(personalizado) {
				
				rs = st.executeQuery("SELECT * FROM agenda WHERE idAgenda='" + id + "';");
				
			} else {
				
				rs = st.executeQuery("SELECT * FROM agenda;");
				
			}
			
			DefaultTableModel modeloTemporal=(DefaultTableModel) tablaDatos.getModel();
	        
	        for (int i = tablaDatos.getRowCount() -1; i >= 0; i--){
	        	
	        	modeloTemporal.removeRow(i);
	        	
	        }
			
	        tablaDatos.setModel(modeloTemporal);
	        
	        DefaultTableModel modelo=(DefaultTableModel) tablaDatos.getModel();
				
			while(rs.next()) {
					
				modelo.addRow(new Object[] {rs.getString("idAgenda"), rs.getString("fecha"), rs.getString("estado"), rs.getString("registra"), rs.getString("para")});
					
			}
			
			tablaDatos.setModel(modelo);
			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar obtener los datos de la agenda: " + "\n" + e.getMessage());
			
		}
		
	}
	
	private void obtenerDatosDetalles(int id) {
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			rs = st.executeQuery("SELECT * FROM agenda WHERE idAgenda='" + id + "';");
			
			if(rs.next()) {
				
				idAgenda.setText(rs.getString("idAgenda"));
				fecha.setDate(rs.getDate("fecha"));
			
				if(rs.getString("estado").equals("pendiente")) {
				
					estado.setSelectedIndex(0);
				
				} else {
				
					if(rs.getString("estado").equals("modificada")) {
					
						estado.setSelectedIndex(1);
					
					} else {
					
						if(rs.getString("estado").equals("realizada")) {
						
							estado.setSelectedIndex(2);
						
						} else {
						
							if(rs.getString("estado").equals("cancelada")) {
							
								estado.setSelectedIndex(3);
							
							}
						
						}
					
					}
						
				}
				
			}
			
			registra.setText(rs.getString("registra"));
			idPropiedad.setText(rs.getString("para"));
			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Hubo un error al intentar cargar los datos: " + e.getMessage());
			
		}

	}
	
	private void editarReserva() {
		
		long d = fecha.getDate().getTime();
		java.sql.Date fechaFinal = new java.sql.Date(d);
		
		String itemEstado = "";
		
		if(estado.getSelectedIndex() == 0) {
			
			itemEstado = "pendiente";
			
		} else {
			
			if(estado.getSelectedIndex() == 1) {
				
				itemEstado = "modificada";
				
			} else {
				
				if(estado.getSelectedIndex() == 2) {
					
					itemEstado = "realizada";
					
				} else {
					
					if(estado.getSelectedIndex() == 3) {
						
						itemEstado = "cancelada";
						
					}
					
				}
				
			}
			
		}
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			st.executeUpdate("UPDATE agenda SET fecha='" + fechaFinal + "', estado='" + itemEstado + "', registra='" + registra.getText() + "', para='" + idPropiedad.getText() + "' WHERE idAgenda='" + idAgenda.getText() + "';");
			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar editar los datos: " + e.getMessage());
			
		}
		
		panelAcciones.setVisible(false);
		limpiarTablaDetalles();
		obtenerDatos(false, 0);
		
	}
	
	private void eliminarReserva() {
		
		try {
			
			MySQL.open();
			st = MySQL.getConnection().createStatement();
			st.executeUpdate("DELETE FROM agenda WHERE idAgenda='" + idAgenda.getText() + "';");
			MySQL.getConnection().close();
			limpiarTablaDetalles();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar borrar la reserva: " + e.getMessage());
			
		}
		
	}
	
	private void limpiarTablaDetalles() {
		
		idAgenda.setText("");
		fecha.setCalendar(null);
		estado.setSelectedIndex(0);
		registra.setText("");
		idPropiedad.setText("");
		panelAcciones.setVisible(false);
		
	}
	
}
