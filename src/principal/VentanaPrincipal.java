package principal;

import jpanel.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private static inicioDeSesion panelInicioDeSesion = new inicioDeSesion();
	private static menuPrincipal panelMenuPrincipal = new menuPrincipal();
	public static agenda panelAgenda = new agenda();
	public static visualizadorInteresado panelVisualizadorInteresado = new visualizadorInteresado();
	public static administradorDePropiedades panelAdministradorDePropiedades = new administradorDePropiedades();
	public static gestionarPropiedad panelGestionarPropiedad = new gestionarPropiedad();
	public static visualizadorPropiedad panelVisualizadorPropiedad = new visualizadorPropiedad();
	private int x;
    private int y;
    private static boolean primeraVez = true;
	private static Intro intro = new Intro();
	private static JLabel titulo = new JLabel("Inmobiliaria GALAXY");
	public static String cedula = "";
	public static String nombre = "";
	public static String apellido = "";
	public static int tipo = 1;
	public static boolean inicioDeSesion = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySQL.open();
					Timer timer = new Timer();
					TimerTask tarea = new TimerTask() {
						@Override
						public void run() {
							
							if(primeraVez) {

								intro.setVisible(true);
								primeraVez = false;
								
							} else {
								
								VentanaPrincipal frame = new VentanaPrincipal();
								frame.setVisible(true);
								intro.dispose();
								timer.cancel();
								
							}

						}
					};
					timer.schedule(tarea, 0, 3000);
					MySQL.getConnection().close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/icono.png")));
		setResizable(false);
		setTitle("Inmobiliaria GALAXY");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				Point point = MouseInfo.getPointerInfo().getLocation();
                setLocation(point.x - x, point.y - y);
				
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				x = e.getX();
                y = e.getY();
				
			}
		});
		panel.setBounds(0, 0, 900, 26);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnX = new JButton("X");
		btnX.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(inicioDeSesion) {
					
					int decision = JOptionPane.showConfirmDialog(null, "¿Estás seguro de salir? Se va a cerrar tu sesión.");
					
					if(decision == 0) {
						
						System.exit(0);
						
					}
					
				} else {
					
					System.exit(0);
					
				}
				
			}
		});
		btnX.setBounds(855, 0, 45, 26);
		panel.add(btnX);
		
		titulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		titulo.setBounds(6, 0, 839, 25);
		panel.add(titulo);
		
		panelInicioDeSesion.setBounds(0, 0, 900, 600);
		contentPane.add(panelInicioDeSesion);
		
		panelMenuPrincipal.setBounds(0, 0, 900, 600);
		contentPane.add(panelMenuPrincipal);
		panelMenuPrincipal.setVisible(false);
		
		panelAgenda.setBounds(0, 0, 900, 600);
		contentPane.add(panelAgenda);
		panelAgenda.setVisible(false);
		
		panelVisualizadorInteresado.setBounds(0, 0, 900, 600);
		contentPane.add(panelVisualizadorInteresado);
		panelVisualizadorInteresado.setVisible(false);
		
		panelAdministradorDePropiedades.setBounds(0, 0, 900, 600);
		contentPane.add(panelAdministradorDePropiedades);
		panelAdministradorDePropiedades.setVisible(false);
		
		panelGestionarPropiedad.setBounds(0, 0, 900, 600);
		contentPane.add(panelGestionarPropiedad);
		panelGestionarPropiedad.setVisible(false);
		
		panelVisualizadorPropiedad.setBounds(0, 0, 900, 600);
		contentPane.add(panelVisualizadorPropiedad);
		panelVisualizadorPropiedad.setVisible(false);
		
	}
	
	public static void inicioDeSesion() {
		
		panelInicioDeSesion.setVisible(true);
		panelMenuPrincipal.setVisible(false);
		
	}
	
	public static void menuPrincipal() {
		
		panelInicioDeSesion.setVisible(false);
		panelMenuPrincipal.setVisible(true);
		panelAgenda.setVisible(false);
		panelVisualizadorInteresado.setVisible(false);
		panelAdministradorDePropiedades.setVisible(false);
		
	}
	
	public static void agenda() {
		
		panelAgenda.obtenerDatos(false, 0);
		panelAgenda.setVisible(true);
		panelMenuPrincipal.setVisible(false);
		
	}
	
	public static void visualizadorInteresado(int solicitud) {
		
		visualizadorInteresado.detectarPropiedades(false, 0);
		visualizadorInteresado.solicitud = solicitud;
		panelVisualizadorInteresado.setVisible(true);
		panelMenuPrincipal.setVisible(false);
		panelVisualizadorPropiedad.setVisible(false);
		
	}
	
	public static void administrarPropiedades() {
		
		administradorDePropiedades.obtenerDatos();
		panelAdministradorDePropiedades.setVisible(true);
		panelGestionarPropiedad.setVisible(false);
		panelMenuPrincipal.setVisible(false);
		
		
	}
	
	public static void gestionarPropiedad(int opcion, int id) {
		
		if(opcion == 0) {
			
			gestionarPropiedad.opcion = 0;
			gestionarPropiedad.inicializacion();
			gestionarPropiedad.titulo.setText("Publicar propiedad");
			
		} else {
			
			if(opcion == 1) {
				
				gestionarPropiedad.opcion = 1;
				gestionarPropiedad.id = id;
				gestionarPropiedad.inicializacion();
				gestionarPropiedad.obtenerDatos();
				gestionarPropiedad.titulo.setText("Editar propiedad");
				gestionarPropiedad.fotoBool = true;
				gestionarPropiedad.actualizarFoto = false;
				
			}
			
		}	

		panelGestionarPropiedad.setVisible(true);
		panelAdministradorDePropiedades.setVisible(false);
		panelMenuPrincipal.setVisible(false);
		
	}
	
	public static void visualizadorPropiedad() {
		
		panelVisualizadorPropiedad.setVisible(true);
		panelVisualizadorInteresado.setVisible(false);
		visualizadorPropiedad.obtenerDatos();
		
	}
	
	public static void establecerTitulo(String tituloVar) {
		
		titulo.setText(tituloVar);
		
	}
	
	public static void comprobarPrivilegios() {
		
		if(tipo == 0) {
			
			panelMenuPrincipal.gerente();
			
		} else {
			
			panelMenuPrincipal.interesado();
			
		}
		
	}
	
}
