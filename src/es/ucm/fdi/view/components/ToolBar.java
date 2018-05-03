package es.ucm.fdi.view.components;

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

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;
import es.ucm.fdi.view.VentanaPrincipal;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar implements ObservadorSimuladorTrafico {
	
	private JSpinner steps;
	private JTextField time;
	public ToolBar(VentanaPrincipal mainWindow, Controlador controlador){
		super();
		controlador.addObserver(this);
		JButton botonCargar = new JButton();
		botonCargar.setToolTipText("Carga un fichero de eventos");
		botonCargar.setIcon(new ImageIcon("resources/icons/open.png"));
		botonCargar.addActionListener(new ActionListener() {
				@Override
				 public void actionPerformed(ActionEvent e) {
					try {
						mainWindow.cargaFichero();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						System.err.println("Problema al cargar los eventos desde el GUI");
						e1.printStackTrace();
					}
				 }
			});
		this.add(botonCargar);
		
		JButton botonCheckIn = new JButton();
		
		botonCheckIn.setToolTipText("CheckIn");
		botonCheckIn.setIcon(new ImageIcon("resources/icons/events.png"));
		botonCheckIn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 try {
				 byte[] contenido = mainWindow.getTextoEditorEventos().getBytes();
				 controlador.cargaEventos(new ByteArrayInputStream(contenido));
			 } catch (ErrorDeSimulacion err) {
				 System.err.println("Problema al cargar los eventos desde el GUI");
				 err.printStackTrace();
			 }
			 	mainWindow.setMensaje("Eventos cargados al simulador!");
			 }
		 });
		this.add(botonCheckIn);
		
		JButton run = new JButton();
		run.setToolTipText("Ejecuta el simulador");
		run.setIcon(new ImageIcon("resources/icons/play.png"));
		run.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int pasos = mainWindow.getSteps();
			 controlador.ejecuta(pasos);
			/* byte[] contenido = mainWindow.getTextoEditorEventos().getBytes();
			 controlador.cargaEventos(new ByteArrayInputStream(contenido));*/
			 	mainWindow.setMensaje("Eventos cargados al simulador!");
			 }
		 });
		this.add(run);
		
		JButton stop = new JButton();
		stop.setToolTipText("Para la ejecucion del simulador");
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));
		stop.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 try {
				 controlador.reinicia();
				 byte[] contenido = mainWindow.getTextoEditorEventos().getBytes();
				 controlador.cargaEventos(new ByteArrayInputStream(contenido));
			 } catch (ErrorDeSimulacion err) {
				 
			 }
			 	mainWindow.setMensaje("Parado");
			 }
		 });
		this.add(stop);
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
		/*
		JButton botonGeneraReports = new JButton();
		botonGeneraReports.setToolTipText("Generar informes");
		botonGeneraReports.setIcon(new
		 ImageIcon("resources/icons/report.png"));
		botonGeneraReports.addActionListener(new ActionListener() {
		 @Override
		 public void actionPerformed(ActionEvent e) {
		 mainWindow.generaInformes();
		 }
		});
		this.add(botonGeneraReports);
		*/
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

}