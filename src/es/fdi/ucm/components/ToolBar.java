package es.fdi.ucm.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.List;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.MVC.ObservadorSimuladorTrafico;
import es.ucm.fdi.MVC.VentanaPrincipal;
import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.model.MapaCarreteras;

public class ToolBar extends JToolBar implements ObservadorSimuladorTrafico {
	
	private JSpinner steps;
	private JTextField time;
	public ToolBar(VentanaPrincipal mainWindow, Controlador controlador){
		super();
		controlador.addObserver(this);
		JButton botonCargar = new JButton();
		botonCargar.setToolTipText("Carga un fichero de ventos");
		botonCargar.setIcon(new ImageIcon("resources/icons/open.png"));
		botonCargar.addActionListener(new ActionListener() {
				@Override
				 public void actionPerformed(ActionEvent e) {
					try {
						mainWindow.cargaFichero();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
			});
		this.add(botonCargar);
		
		JButton botonCheckIn = new JButton();
		botonCheckIn.setToolTipText("Carga los eventos al simulador");
		botonCheckIn.setIcon(new ImageIcon("resources/icons/events.png"));
		botonCheckIn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 try {
				 controlador.reinicia();
				 byte[] contenido = mainWindow.getTextoEditorEventos().getBytes();
				 controlador.cargaEventos(new ByteArrayInputStream(contenido));
			 } catch (ErrorDeSimulacion err) {
				 
			 }
			 	mainWindow.setMensaje("Eventos cargados al simulador!");
			 }
		 });
		this.add(botonCheckIn);
		
		this.add(new JLabel(" Pasos: "));
		this.steps = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
		this.steps.setToolTipText("pasos a ejecutar: 1-1000");
		this.steps.setMaximumSize(new Dimension(70, 70));
		this.steps.setMinimumSize(new Dimension(70, 70));
		this.steps.setValue(1);
		this.add(steps);
		
		this.add(new JLabel(" Tiempo: "));
		this.time = new JTextField("0", 5);
		this.time.setToolTipText("Tiempo actual");
		this.time.setMaximumSize(new Dimension(70, 70));
		this.time.setMinimumSize(new Dimension(70, 70));
		this.time.setEditable(false);
		this.add(this.time);
		
		// OPCIONAL
		JButton botonGeneraReports = new JButton();
		botonGeneraReports.setToolTipText("Generata informes");
		botonGeneraReports.setIcon(new
		 ImageIcon("resources/icons/report.png"));
		botonGeneraReports.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
		 mainWindow.generaInformes();
		 }
		});
		this.add(botonGeneraReports);
	}
	
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		
	}
	
	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
	 this.time.setText(""+tiempo);
	}
	
	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
	}
	
	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		 this.steps.setValue(1);
		 this.time.setText("0");
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