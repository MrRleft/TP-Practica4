package es.ucm.fdi.view.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
	
	private Controlador c;
	private JSpinner steps;
	private JTextField time;
	public ToolBar(VentanaPrincipal mainWindow, Controlador controlador){
		super();
		this.c = controlador;
		controlador.addObserver(this);
		JButton botonCargar = new JButton();
		botonCargar.setToolTipText("Carga un fichero de eventos");
		botonCargar.setIcon(new ImageIcon("resources/icons/open.png"));
		botonCargar.addActionListener(new ActionListener() {
				@Override
				 public void actionPerformed(ActionEvent e) {
					try {
						mainWindow.cargaFichero();
						controlador.ejecuta(1);
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
				 InputStream contenido = mainWindow.getEventos();
				 controlador.cargaEventos(contenido);
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
				mainWindow.setMensaje("Ejecutados "+ pasos +"!");
			}
		 });
		this.add(run);
		
		JButton restart = new JButton();
		restart.setToolTipText("Para la ejecucion del simulador");
		restart.setIcon(new ImageIcon("resources/icons/reset.png"));
		restart.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				
				controlador.reinicia();
			 	mainWindow.setMensaje("Se ha reiniciado el sistema");
			 }
		 });
		this.add(restart);
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
		this.time.setText(""+(this.c.getTime()));
	}
	
	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
	}
	
	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.time.setText(""+(this.c.getTime()));
	}
	
	public int getSteps() {
		
		return (int) this.steps.getValue();
	}
}