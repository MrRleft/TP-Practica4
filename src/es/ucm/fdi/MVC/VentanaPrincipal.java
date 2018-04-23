package es.ucm.fdi.MVC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import es.fdi.ucm.components.BarraMenu;
import es.fdi.ucm.components.ComponenteMapa;
import es.fdi.ucm.components.DialogoInformes;
import es.fdi.ucm.components.ToolBar;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.panels.PanelAreaTexto;
import es.ucm.fdi.panels.PanelBarraEstado;
import es.ucm.fdi.panels.PanelEditorEventos;
import es.ucm.fdi.panels.PanelInformes;
import es.ucm.fdi.panels.PanelTabla;
import es.ucm.fdi.tablas.ModeloTablaCarreteras;
import es.ucm.fdi.tablas.ModeloTablaCruces;
import es.ucm.fdi.tablas.ModeloTablaEventos;
import es.ucm.fdi.tablas.ModeloTablaVehiculos;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.vehiculos.Vehiculo;

public class VentanaPrincipal extends JFrame implements ObservadorSimuladorTrafico {
	
	private static final long serialVersionUID = 1L;

	private Controlador controlador;
	
	public static Border bordePorDefecto = BorderFactory.createLineBorder(Color.black, 2);
	// SUPERIOR PANEL
	static private final String[] columnIdEventos = { "#", "Tiempo", "Tipo" };
	private JPanel mainPanel;
	private JPanel contentPanel_1; // tantos como zonas en la ventana
	private PanelAreaTexto panelEditorEventos;
	private PanelInformes panelInformes;
	private PanelTabla<Evento> panelColaEventos;
	// MENU AND TOOL BAR
	private JFileChooser fc;
	private ToolBar toolbar;
	// GRAPHIC PANEL
	private ComponenteMapa componenteMapa;
	// STATUS BAR (INFO AT THE BOTTOM OF THE WINDOW)
	private PanelBarraEstado panelBarraEstado;
	// INFERIOR PANEL
	static private final String[] columnIdVehiculo = { "ID", "Carretera",
			"Localizacion", "Vel.", "Km", "Tiempo. Ave.", "Itinerario" };
	static private final String[] columnIdCarretera = { "ID", "Origen",
			"Destino", "Longitud", "Vel. Max", "Vehiculos" };
	static private final String[] columnIdCruce = { "ID", "Verde", "Rojo" };
	private PanelTabla<Vehiculo> panelVehiculos;
	private PanelTabla<Carretera> panelCarreteras;
	private PanelTabla<CruceGenerico<?>> panelCruces;
	// REPORT DIALOG
	private DialogoInformes dialogoInformes; // opcional
	// MODEL PART - VIEW CONTROLLER MODEL
	private File ficheroActual;
	
	public VentanaPrincipal(String ficheroEntrada,Controlador ctrl) throws FileNotFoundException {
		super("Simulador de Trafico");
		this.controlador = ctrl;
		this.ficheroActual = ficheroEntrada != null ? new File(ficheroEntrada) : null;
		this.initGUI();
	 // a�adimos la ventana principal como observadora
		ctrl.addObserver(this);
	}
	
	
	
	private void initGUI() {
		 
		 this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
				JFrame frame = new JFrame();
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(frame, "Aye you sure you want to close this window?", "Really Closing?", 
				   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
			}
				
			public void windowClosed(WindowEvent e) {	
			}

			public void windowIconified(WindowEvent e) {	
			}
			
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub	
			}
			public void windowActivated(WindowEvent e) {	
			}
			public void windowDeactivated(WindowEvent e) {
			}
	
		 });
		 
		 /*PANEL PRINCIPAL*/
		 
		 JPanel panelPrincipal = this.createPanelPrincipal();
		 this.setContentPane(panelPrincipal);

		 // BARRA DE ESTADO INFERIOR
		 // (contiene una JLabel para mostrar el estado delsimulador)
		 this.addBarraEstado(panelPrincipal);
		 // PANEL QUE CONTIENE EL RESTO DE COMPONENTES
		 // (Lo dividimos en dos paneles (superior e inferior)
		 JPanel panelCentral = this.createPanelCentral();
		 panelPrincipal.add(panelCentral,BorderLayout.CENTER);
		// PANEL SUPERIOR
		 this.createPanelSuperior(panelCentral);
		 // MENU
		 BarraMenu menubar = new BarraMenu(this,this.controlador);
		 this.setJMenuBar(menubar);
		 // PANEL INFERIOR
		 this.createPanelInferior(panelCentral);
		 // BARRA DE HERRAMIENTAS
		 this.addToolBar(panelPrincipal);
		 // FILE CHOOSER
		 this.fc = new JFileChooser();
		 // REPORT DIALOG (OPCIONAL)
		 this.dialogoInformes = new DialogoInformes(this,this.controlador);
		 this.pack();
		 this.setVisible(true);
		
	}
	
	private void addBarraEstado(JPanel panelPrincipal) {
		this.panelBarraEstado = new PanelBarraEstado("Bienvenido al simulador !",this.controlador);
		// se a�ade al panel principal (el que contiene al panel
		// superior y al inferior)
		panelPrincipal.add(this.panelBarraEstado,BorderLayout.PAGE_END);
	}
	
	private void addToolBar(JPanel panelPrincipal){
		this.toolbar = new ToolBar(this, this.controlador);
		panelPrincipal.add(this.toolbar, BorderLayout.PAGE_START);
	}
	
	private void createPanelInferior(JPanel panelCentral) {
		// TODO Auto-generated method stub
		/*
		  El panel inferior ser� un JPanel con BoxLayout en el eje X.
			 Contendr� un panel para meter las tres tablas, y un panel para el gr�fico.
			 El panel para las tablas (puedes usar GridLayout(3,1)) contendr� a su vez otros tres
			paneles: 
		 */
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.X_AXIS));
		panelInferior.setLayout(new GridLayout(3,1));
		this.panelVehiculos = new PanelTabla<Vehiculo>("Vehiculos",
				new ModeloTablaVehiculos(VentanaPrincipal.columnIdVehiculo, this.controlador));
		this.panelCarreteras = new PanelTabla<Carretera>("Carretras",
				new ModeloTablaCarreteras(VentanaPrincipal.columnIdCarretera, this.controlador));
		this.panelCruces = new PanelTabla<CruceGenerico<?>>("Cruces",
				new ModeloTablaCruces(VentanaPrincipal.columnIdCruce, this.controlador));
		
		this.componenteMapa = new ComponenteMapa(this.controlador);
		// a�adir un ScroolPane al panel inferior donde se coloca la
		// componente.
		panelInferior.add(new JScrollPane(this.componenteMapa));///////????????????
	}

	
	private JPanel createPanelPrincipal() {
		// TODO Auto-generated method stub
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		return panelPrincipal;
	}


	private JPanel createPanelCentral() {
		 JPanel panelCentral = new JPanel();
		 // para colocar el panel superior e inferior
		 panelCentral.setLayout(new GridLayout(2,1));
		 return panelCentral;
	}
	
	 private JPanel createPanelSuperior(JPanel panelCentral) {
		 /*
		  *Creamos un panel con layout �BoxLayout� alineado en el eje de las X. Contendr�:
			panelEditorEventos: Contiene un area de texto para mostrar los eventos. Puede
			crearse con el �texto� de un fichero previamente cargado. No es observador.
		 
			 panelColaEventos: Contendr� una tabla de eventos. Es observador.
			 
			 panelInformes: Contiene un area de texto para mostrar los informes. Es observador.
			 this.panelInformes = new PanelInformes("Informes: ",false,
			 this.controlador);
		  */
		 JPanel panelSuperior = new JPanel();
			panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
		 this.panelEditorEventos = new PanelEditorEventos(titulo,texto,true,this);
		 this.panelColaEventos = new PanelTabla<Evento>("Cola Eventos: ",
				 new ModeloTablaEventos(VentanaPrincipal.columnIdEventos, this.controlador));
		 this.panelInformes = new PanelInformes("Informes: ",false, this.controlador);
		 
		 String texto = "";
		 try {
			 texto = this.leeFichero(this.ficheroActual);
		 } 
		 catch (FileNotFoundException e) {
			 this.ficheroActual = null;
			 this.muestraDialogoError("Error durante la lectura del fichero: " + e.getMessage());
		 }
	 }
	 
	
	 
	private String leeFichero(File ficheroActual2) {
		// TODO Auto-generated method stub
		return null;
	}

	private void muestraDialogoError(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(parentComponent,string);
	}

	public void cargaFichero() {
		 int returnVal = this.fc.showOpenDialog(null);
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
		 
			 File fichero = this.fc.getSelectedFile();
			 try {
				 String s = leeFichero(fichero);
				 this.controlador.reinicia();
				 this.ficheroActual = fichero;
				 this.panelEditorEventos.setTexto(s);
				 this.panelEditorEventos.setBorde(this.ficheroActual.getName());
				 this.panelBarraEstado.setMensaje("Fichero " + fichero.getName() +
						 " de eventos cargado into the editor");
			 }
			 catch (FileNotFoundException e) {
				 this.muestraDialogoError("Error durante la lectura del fichero: " +
				 e.getMessage());
			 }
		 }
	}
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		
	}

	public int getSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void generaInformes() {
		// TODO Auto-generated method stub
		
	}

	public Object getTextoEditorEventos() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMensaje(String string) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addObservador(ObservadorSimuladorTrafico o) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void removeObservador(ObservadorSimuladorTrafico o) {
		// TODO Auto-generated method stub
		
	}

}
