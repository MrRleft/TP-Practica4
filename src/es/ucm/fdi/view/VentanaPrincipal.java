package es.ucm.fdi.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.carreteras.Carretera;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.model.vehiculos.Vehiculo;
import es.ucm.fdi.view.components.BarraMenu;
import es.ucm.fdi.view.components.ComponenteMapa;
import es.ucm.fdi.view.components.DialogoInformes;
import es.ucm.fdi.view.components.ToolBar;
import es.ucm.fdi.view.panels.PanelAreaTexto;
import es.ucm.fdi.view.panels.PanelBarraEstado;
import es.ucm.fdi.view.panels.PanelEditorEventos;
import es.ucm.fdi.view.panels.PanelInformes;
import es.ucm.fdi.view.panels.PanelTabla;
import es.ucm.fdi.view.tablas.ModeloTablaCarreteras;
import es.ucm.fdi.view.tablas.ModeloTablaCruces;
import es.ucm.fdi.view.tablas.ModeloTablaEventos;
import es.ucm.fdi.view.tablas.ModeloTablaVehiculos;

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
	//private DialogoInformes dialogoInformes; // opcional
	// MODEL PART - VIEW CONTROLLER MODEL
	private File ficheroActual;

	private DialogoInformes dialogoInformes;
	
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
				Salir();
			}	
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}	
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
	
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
		 JPanel panelSup = this.createPanelSuperior(panelCentral);
		 panelCentral.add(panelSup);
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
		this.dialogoInformes = new DialogoInformes();
		this.pack();
		this.setVisible(true);
		
	}
	
	public PanelAreaTexto getPanelEditorEventos() {
		return panelEditorEventos;
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
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(3,1));
		panelInferior.setLayout(new GridLayout(1,1));
		this.panelVehiculos = new PanelTabla<Vehiculo>("Vehiculos",
				new ModeloTablaVehiculos(VentanaPrincipal.columnIdVehiculo, this.controlador));
		this.panelCarreteras = new PanelTabla<Carretera>("Carretras",
				new ModeloTablaCarreteras(VentanaPrincipal.columnIdCarretera, this.controlador));
		this.panelCruces = new PanelTabla<CruceGenerico<?>>("Cruces",
				new ModeloTablaCruces(VentanaPrincipal.columnIdCruce, this.controlador));
		
		this.componenteMapa = new ComponenteMapa(this.controlador);
		// a�adir un ScroolPane al panel inferior donde se coloca la
		// componente.

		left.add(panelVehiculos);
		left.add(panelCarreteras);
		left.add(panelCruces);
		panelInferior.add(left);
		panelInferior.add(this.componenteMapa);///////????????????

		panelCentral.add(panelInferior);
		

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
		 String texto = "";
		 texto = this.leeFichero(this.ficheroActual);	
			
		 this.panelEditorEventos = new PanelEditorEventos(this.ficheroActual.getName(),texto,true,this);
		 panelSuperior.add(this.panelEditorEventos);
		 this.panelColaEventos = new PanelTabla<Evento>("Cola Eventos: ",
				 new ModeloTablaEventos(VentanaPrincipal.columnIdEventos, this.controlador));
		 panelSuperior.add(this.panelColaEventos);
		 this.panelInformes = new PanelInformes("Informes: ",false, this.controlador);
		 panelSuperior.add(this.panelInformes);
		 
		return panelSuperior;
	 }
	 
	
	 
	private String leeFichero(File ficheroActual2) {
		// TODO Auto-generated method stub
		String s = "";
		try {
			Scanner sc = new Scanner(ficheroActual2);
			s = sc.useDelimiter("\\A").next();
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error al cargar los datos del archivo de texto");
			//aqui iria una excepcion
		}
		return s;
	}

	private void muestraDialogoError(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(getComponent(0),string);
	}

	public void cargaFichero() throws FileNotFoundException {
		 int returnVal = this.fc.showOpenDialog(null);
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
		 
			 File fichero = this.fc.getSelectedFile();
			 String s = leeFichero(fichero);
			 this.controlador.reinicia();
			 this.ficheroActual = fichero;
			 this.panelEditorEventos.setTexto(s);
			 this.panelEditorEventos.setBorde(this.ficheroActual.getName());
			 this.panelBarraEstado.setMensaje("Fichero " + fichero.getName() +
					 " de eventos cargado into the editor");
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
	
	//De aqui en adelante tenemos las funciones que nos van a ir sirviendo para que funcionen los diferentes componentes

	public int getSteps() {
		//Consigue los pasos que se encuantran en el toolbar
		
		return this.toolbar.getSteps();
	}

	public void generaInformes() {
		// TODO Auto-generated method stub
		//Este es opcional, ya veremos si podemos llegar a hacerlo
		
		
	}

	public String getTextoEditorEventos() {
		// TODO Auto-generated method stub
		return this.leeFichero(this.ficheroActual);
	}

	public void setMensaje(String string) {
		// TODO Auto-generated method stub	
		
	}

	public void guardar() throws IOException{
		 int fcAux = this.fc.showOpenDialog(null);
		 if (fcAux == JFileChooser.APPROVE_OPTION) {
			 File fichero = this.fc.getSelectedFile();
			 escribeArchivo(fichero,this.panelEditorEventos.getTexto());
		 }
	}
	
	public static void escribeArchivo(File auxFile, String strFile) throws IOException {
		try {
			PrintWriter pw = new PrintWriter(auxFile);
			pw.print(strFile);
			pw.close();
		} catch (IOException e) {
			throw new IOException();
		}
	}
	
	
	public void Salir() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to close this window?", "Really Closing?", 
		   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
		}

	}



	public void guardarResultados() {
		// TODO Auto-generated method stub
		//Esto se encarga de guardar ela salida del programa tal y como lo har�a en el modo batch
		int saveR = fc.showSaveDialog(null);
		if (saveR == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				escribeArchivo(file, this.panelInformes.getTexto());
			} catch (IOException e) {
				
			}
		}
		//statusBarText.setText("All reports have been saved!");    
		//stateBar.add(statusBarText);
	}



	public InputStream getEventos() {
		// TODO Auto-generated method stub
		InputStream iS = new ByteArrayInputStream(panelEditorEventos.getTexto().getBytes(StandardCharsets.UTF_8));
		return iS;
	}

	

}
