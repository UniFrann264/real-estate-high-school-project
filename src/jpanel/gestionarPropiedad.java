package jpanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.Blob;

import principal.MySQL;
import principal.VentanaPrincipal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class gestionarPropiedad extends JPanel {
	
	private fondo fondo = new fondo();
	private static JTextField direccion = new JTextField();
	private static JTextArea descripcion = new JTextArea();
	public static int opcion = 0;
	public static int id = 0;
	private static JButton publicar = new JButton("Publicar");
	public static boolean fotoBool = false;
	private ButtonGroup grupoGaraje = new ButtonGroup();
	private ButtonGroup grupoAmoblada = new ButtonGroup();
	private static JRadioButton garajeSi = new JRadioButton("Si");
	private static JRadioButton garajeNo = new JRadioButton("No");
	private static JRadioButton amobladaSi = new JRadioButton("Si");
	private static JRadioButton amobladaNo = new JRadioButton("No");
	private FileInputStream inputFinal;
	private JLabel retroceder = new JLabel("");
	private static JLabel foto = new JLabel("");
	private static Statement st = null;
	private static ResultSet rs = null;
	private PreparedStatement prstm = null;
	private static JComboBox tipo = new JComboBox();
	private static JComboBox precio = new JComboBox();
	private InputStream URLFoto = null;
	private static boolean cambiaFoto = false;
	private static JComboBox dormitorios = new JComboBox();
	private static JComboBox baños = new JComboBox();
	public static JLabel titulo = new JLabel("Publicar propiedad");
	public static boolean actualizarFoto = false;

	/**
	 * Create the panel.
	 */
	public gestionarPropiedad() {
		setLayout(null);
		
		grupoGaraje.add(garajeSi);
		grupoGaraje.add(garajeNo);
		grupoAmoblada.add(amobladaSi);
		grupoAmoblada.add(amobladaSi);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelDatos.setBounds(571, 68, 279, 466);
		add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n");
		lblInformacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInformacin.setBounds(10, 11, 259, 39);
		panelDatos.add(lblInformacin);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(10, 59, 59, 14);
		panelDatos.add(lblDireccin);
		
		direccion.setBounds(89, 56, 180, 20);
		panelDatos.add(direccion);
		direccion.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 118, 180, 127);
		panelDatos.add(scrollPane);
		
		scrollPane.setViewportView(descripcion);
		
		tipo.setModel(new DefaultComboBoxModel(new String[] {"Casa", "Apartamento", "Local comercial"}));
		tipo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tipo.setBounds(89, 378, 163, 20);
		panelDatos.add(tipo);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 90, 46, 14);
		panelDatos.add(lblPrecio);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(10, 124, 74, 14);
		panelDatos.add(lblDescripcin);
		
		JLabel lblDormitorios = new JLabel("Dormitorios");
		lblDormitorios.setBounds(10, 259, 74, 14);
		panelDatos.add(lblDormitorios);
		
		JLabel lblBaos = new JLabel("Ba\u00F1os");
		lblBaos.setBounds(10, 290, 46, 14);
		panelDatos.add(lblBaos);
		
		JLabel lblGaraje = new JLabel("Garaje");
		lblGaraje.setBounds(10, 321, 46, 14);
		panelDatos.add(lblGaraje);
		
		JLabel lblAmoblada = new JLabel("Amoblada");
		lblAmoblada.setBounds(10, 352, 65, 14);
		panelDatos.add(lblAmoblada);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 381, 46, 14);
		panelDatos.add(lblTipo);
		publicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!direccion.getText().equals("")) {
				
						if(!descripcion.getText().equals("")) {
									
									if(fotoBool) {
										
										retroceder.setEnabled(false);
										
										String itemGaraje = "";
										String itemAmoblada = "";
										String itemTipo = "";
										String itemPrecio = "";
										String itemDormitorios = "";
										String itemBaños = "";
										
										if(precio.getSelectedIndex() == 0) {
											
											itemPrecio = "compra";
											
										} else {
											
											itemPrecio = "alquiler";
											
										}
										
										if(dormitorios.getSelectedIndex() == 0) {
											
											itemDormitorios = "1";
											
										} else {
											
											if(dormitorios.getSelectedIndex() == 1) {
												
												itemDormitorios = "2";
												
											} else {
												
												if(dormitorios.getSelectedIndex() == 2) {
													
													itemDormitorios = "3";
													
												} else {
													
													if(dormitorios.getSelectedIndex() == 3) {
														
														itemDormitorios = "4";
														
													} else {
														
														if(dormitorios.getSelectedIndex() == 4) {
															
															itemDormitorios = "5";
															
														}
														
													}
													
												}
												
											}
											
										}
										
										if(baños.getSelectedIndex() == 0) {
											
											itemBaños = "1";
											
										} else {
											
											if(baños.getSelectedIndex() == 1) {
												
												itemBaños = "2";
												
											} else {
												
												if(baños.getSelectedIndex() == 2) {
													
													itemBaños = "3";
													
												} else {
													
													if(baños.getSelectedIndex() == 3) {
														
														itemBaños = "4";
														
													} else {
														
														if(baños.getSelectedIndex() == 4) {
															
															itemBaños = "5";
															
														} 
														
													}
													
												}
												
											}
											
										}
										
										if(garajeSi.isSelected()) {
											
											itemGaraje = "Si";
											
										} else {
											
											itemGaraje = "No";
											
										}
										
										if(amobladaSi.isSelected()) {
											
											itemAmoblada = "Si";
											
										} else {
											
											itemAmoblada = "No";
											
										}
										
										if(tipo.getSelectedIndex() == 0) {
											
											itemTipo = "casa";
											
										} else {
											
											if(tipo.getSelectedIndex() == 1) {
												
												itemTipo = "apartamento"; 
												
											} else {
												
												itemTipo = "local comercial"; 
												
											}
											
										}
										
										try {
											
											MySQL.open();
											st = MySQL.getConnection().createStatement();
											if(fotoBool && opcion == 0) {
												
												prstm = MySQL.getConnection().prepareStatement("INSERT INTO foto (`imagen`) VALUES (?)");
												prstm.setBinaryStream(1, URLFoto);
												prstm.executeUpdate();
												
												st.executeUpdate("INSERT INTO propiedad (`tipo`, `direccion`, `precio`, `descripcion`, `dormitorios`, `baños`, `garaje`, `amoblada`) VALUES ('" + itemTipo + "', '" + direccion.getText() + "', '" + itemPrecio + "', '" + descripcion.getText() + "', '" + itemDormitorios + "', '" + itemBaños + "', '" + itemGaraje + "', '" + itemAmoblada + "');");
											
											} else {
												
												if(fotoBool && opcion == 1) {
													
													if(actualizarFoto) {
														
														prstm = MySQL.getConnection().prepareStatement("UPDATE foto SET=(`imagen`) VALUES (?) WHERE idFoto='" + id + "';");
														prstm.setBinaryStream(1, URLFoto);
														prstm.executeUpdate();
														
													}
													
													st.executeUpdate("UPDATE propiedad SET tipo='"+ itemTipo + "', direccion='" + direccion.getText() + "', precio='" + itemPrecio + "', descripcion='" + descripcion.getText() + "', dormitorios='" + itemDormitorios + "', baños='" + itemBaños + "', garaje='" + itemGaraje + "', amoblada='" + itemAmoblada + "' WHERE idPropiedad='" + id + "';");
													
												}
												
											}
											
											JOptionPane.showMessageDialog(null, "¡La acción ha sido realizada con éxito!");
											retroceder.setEnabled(true);
											MySQL.getConnection().close();
											resetearDatos();
											
											
										} catch (SQLException e2) {
											
											JOptionPane.showMessageDialog(null, "Error al intentar realizar acciones " + "\n" + e2.getMessage());
											
										}
										
									} else {
										
										JOptionPane.showMessageDialog(null, "Es obligatorio subir una foto de la inmobiliaria...");
										
									}
								
							
						} else {
							
							JOptionPane.showMessageDialog(null, "Error, el campo de descripción no puede estar vacío...");
							
						}

				} else {
					
					JOptionPane.showMessageDialog(null, "Error, el campo de dirección no puede estar vacío...");
					
				}
				
			}
		});
		publicar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		publicar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		publicar.setBounds(10, 408, 259, 44);
		panelDatos.add(publicar);
		
		garajeSi.setBounds(89, 314, 46, 23);
		panelDatos.add(garajeSi);
		
		garajeNo.setBounds(137, 314, 46, 23);
		panelDatos.add(garajeNo);
		
		amobladaSi.setBounds(89, 348, 46, 23);
		panelDatos.add(amobladaSi);
		
		amobladaNo.setBounds(137, 348, 46, 23);
		panelDatos.add(amobladaNo);
		
		precio.setModel(new DefaultComboBoxModel(new String[] {"Compra", "Alquiler"}));
		precio.setCursor(new Cursor(Cursor.HAND_CURSOR));
		precio.setBounds(89, 87, 180, 20);
		panelDatos.add(precio);

		dormitorios.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		dormitorios.setBounds(89, 256, 46, 20);
		panelDatos.add(dormitorios);
		
		
		baños.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		baños.setBounds(89, 287, 46, 20);
		panelDatos.add(baños);
		
		retroceder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				VentanaPrincipal.administrarPropiedades();
				administradorDePropiedades.obtenerDatos();
				resetearDatos();
				
			}
		});
		
		retroceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		retroceder.setIcon(new ImageIcon(gestionarPropiedad.class.getResource("/img/retrocederIcono.png")));
		retroceder.setBounds(10, 30, 35, 34);
		add(retroceder);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setBounds(27, 164, 486, 318);
		fondo.add(panelFoto);
		panelFoto.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panelFoto.setLayout(null);
		foto.setIcon(new ImageIcon(gestionarPropiedad.class.getResource("/img/iconoCasa.png")));
		foto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				elegirImagen();
				
			}
		});
		
		foto.setBounds(10, 11, 466, 296);
		foto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelFoto.add(foto);
		
		titulo.setBounds(61, 30, 261, 46);
		fondo.add(titulo);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		fondo.setBounds(0, 0, 900, 600);
		add(fondo);
		fondo.setLayout(null);

	}
	
	public static void inicializacion() {
		
		if(opcion == 0) {
			
			publicar.setText("Publicar");
			
		} else {
			
			publicar.setText("Editar datos");
			
		}
		
		foto.setIcon(new ImageIcon(gestionarPropiedad.class.getResource("/img/iconoCasa.png")));
		cambiaFoto = false;
		fotoBool = false;
		
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
					ImageIcon imagenFinal2 = new ImageIcon(imagenBuffered.getScaledInstance(466, 296, Image.SCALE_SMOOTH));
					foto.setIcon(imagenFinal2);
					
				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, "Error al aplicar la imagen: " + e.getMessage());
					
				}
	
			}
			
			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar descargar la foto: " + e.getMessage());
			
		}
		
	}
	
	public static void obtenerDatos() {
		
		descargarImagen();
		
		try {
			
			MySQL.open();
			Statement st = MySQL.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM propiedad WHERE idPropiedad='" + id + "';");
				while(rs.next()) {
					
					direccion.setText(rs.getString("direccion"));
					
					if(rs.getString("precio").equals("compra")) {
						
						precio.setSelectedIndex(0);
						
					} else {
						
						precio.setSelectedIndex(1);
						
					}
					
					descripcion.setText(rs.getString("descripcion"));
					
					if(rs.getString("dormitorios").equals("1")) {
						
						dormitorios.setSelectedIndex(0);
						
					} else {
						
						if(rs.getString("dormitorios").equals("2")) {
							
							dormitorios.setSelectedIndex(1);
							
						} else {
							
							if(rs.getString("dormitorios").equals("3")) {
								
								dormitorios.setSelectedIndex(2);
								
							} else {
								
								if(rs.getString("dormitorios").equals("4")) {
									
									dormitorios.setSelectedIndex(3);
									
								} else {
									
									if(rs.getString("dormitorios").equals("5")) {
										
										dormitorios.setSelectedIndex(4);
										
									}
									
								}
								
							}
							
						}
						
					}
					
					if(rs.getString("baños").equals("1")) {
						
						baños.setSelectedIndex(0);
						
					} else {
						
						if(rs.getString("baños").equals("2")) {
							
							baños.setSelectedIndex(1);
							
						} else {
							
							if(rs.getString("baños").equals("3")) {
								
								baños.setSelectedIndex(2);
								
							} else {
								
								if(rs.getString("baños").equals("4")) {
									
									baños.setSelectedIndex(3);
									
								} else {
									
									if(rs.getString("baños").equals("5")) {
										
										baños.setSelectedIndex(4);
										
									}
									
								}
								
							}
							
						}
						
					}
					
					if(rs.getString("garaje").equals("Si")) {
						
						garajeSi.setSelected(true);
						garajeNo.setSelected(false);
						
					} else {
						
						garajeSi.setSelected(false);
						garajeNo.setSelected(true);
						
					}
					
					if(rs.getString("amoblada").equals("Si")) {
						
						amobladaSi.setSelected(true);
						amobladaNo.setSelected(false);
						
					} else {
						
						amobladaSi.setSelected(false);
						amobladaNo.setSelected(true);
						
					}
					
					if(rs.getString("tipo").equals("casa")) {
						
						tipo.setSelectedIndex(0);
						
					} else {
						
						if(rs.getString("tipo").equals("apartamento")) {
							
							tipo.setSelectedIndex(1);
							
						} else {
							
							if(rs.getString("tipo").equals("local comercial")) {
								
								tipo.setSelectedIndex(2);
								
						}
							
					}
						
				}
				
			}
			MySQL.getConnection().close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Error al intentar obtener datos de la propiedad: " + e.getMessage());
			
		}
		
	}
	
	public void elegirImagen() {
		
		try {
			
			JFileChooser selector = new JFileChooser();
			
			if(selector.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				
				File archivo = selector.getSelectedFile();
				String direccion = archivo.getPath();
				Image icono = new ImageIcon(direccion).getImage();
				ImageIcon iconoRedimensionado = new ImageIcon(icono.getScaledInstance(466, 296, Image.SCALE_SMOOTH));
				foto.setIcon(iconoRedimensionado);
				  		  
				  fotoBool = true;
				   
				   if(opcion == 1) {
					   
					   actualizarFoto = true;
					   
				   }
				   
				   subirImagen(direccion);
				
			} else {
				
				fotoBool = false;
				
			}
			
		} catch (Exception e2) {
			
			
		}
		
	}
	
	public void subirImagen(String ruta) throws SQLException, FileNotFoundException {
	
		MySQL.open();
		
		if(actualizarFoto) {
			
			st = MySQL.getConnection().createStatement();
			String sql = "UPDATE foto SET=(imagen) VALUES (?) WHERE idFoto='" + id + "';";
			prstm = MySQL.getConnection().prepareStatement(sql);
			File imagen = new File(ruta);
			FileInputStream   fis = new FileInputStream(imagen);
			prstm.setBinaryStream(1, fis, (int) imagen.length());
			prstm.executeUpdate();

		} else {
			
			st = MySQL.getConnection().createStatement();
			String sql = "INSERT INTO foto (imagen) VALUES (?)";
			prstm = MySQL.getConnection().prepareStatement(sql);
			File imagen = new File(ruta);
			FileInputStream   fis = new FileInputStream(imagen);
			prstm.setBinaryStream(1, fis, (int) imagen.length());
			prstm.execute();
			
		}
		
		MySQL.getConnection().close();
		 
	}
	
	private void resetearDatos() {
		
		foto.setIcon(new ImageIcon(gestionarPropiedad.class.getResource("/img/iconoCasa.png")));
		fotoBool = false;
		actualizarFoto = false;
		direccion.setText("");
		precio.setSelectedIndex(0);
		descripcion.setText("");
		dormitorios.setSelectedIndex(0);
		baños.setSelectedIndex(0);
		grupoGaraje.clearSelection();
		grupoAmoblada.clearSelection();
		tipo.setSelectedIndex(0);
		
	}

}
