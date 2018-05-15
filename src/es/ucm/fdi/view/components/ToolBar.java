package es.ucm.fdi.view.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import es.ucm.fdi.threads.DelayRunner;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;
import es.ucm.fdi.view.VentanaPrincipal;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar implements ObservadorSimuladorTrafico {
	
	private Controlador c;
	private JSpinner steps;
	private JTextField time;
	private JSpinner delay;
	private JButton stop;
	private JButton botonCargar; 
	private JButton botonCheckIn;
	private JButton Clean;
	private JButton restart;
	private JButton save;
	private JButton saveReport;
	private JButton exit;
	private JButton run;
	private Thread t1;
	private VentanaPrincipal mW;
	private JButton Clean2;
	
	
	public ToolBar(VentanaPrincipal mainWindow, Controlador controlador){
		super();
		this.c = controlador;
		controlador.addObserver(this);
		this.mW = mainWindow;
		this.botonCargar = new JButton();
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
		
		this.botonCheckIn = new JButton();
		
		botonCheckIn.setToolTipText("Carga los eventos");
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
		
		this.Clean = new JButton();
		
		Clean.setToolTipText("Limpia la caja de texto para los eventos");
		Clean.setIcon(new ImageIcon("resources/icons/delete_report.png"));
		Clean.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			 	mainWindow.limpiaEventos();
			}
		
		 });
		this.add(Clean);
		
		Clean2 = new JButton();
		
		Clean2.setToolTipText("Limpia la caja de textode los informes");
		Clean2.setIcon(new ImageIcon("resources/icons/clear.png"));
		Clean2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			 	mainWindow.limpiaInformes();
			}
		
		 });
		this.add(Clean2);
		
		run = new JButton();
		run.setToolTipText("Ejecuta el simulador");
		run.setIcon(new ImageIcon("resources/icons/play.png"));
		run.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				int pasos = mainWindow.getSteps();
				int delay = mainWindow.getDelay();
				if(delay == 0) {
					controlador.ejecuta(pasos);
					mainWindow.setMensaje("Ejecutados "+ pasos +"!");
				}
				else {
					practica6Funcion();
				}
			}


		 });
		this.add(run);
		
		this.stop = new JButton();
		stop.setToolTipText("Detiene el delay");
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));
		stop.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				//IMPLEMENTAR
				interruptThread();
			}
		 });
		this.add(stop);
		stop.setEnabled(false);
		
		this.restart = new JButton();
		restart.setToolTipText("Reinicia el simulador");
		restart.setIcon(new ImageIcon("resources/icons/reset.png"));
		restart.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				
				controlador.reinicia();
			 	mainWindow.setMensaje("Se ha reiniciado el sistema");
			 }
		 });		
		this.add(restart);
		
		this.add(new JLabel(" Delay: "));
		this.delay = new JSpinner(new SpinnerNumberModel(0, 0, 1000000, 100));
		this.delay.setToolTipText("Numero de ejecutas seguidos");
		this.delay.setMaximumSize(new Dimension(70, 70));
		this.delay.setMinimumSize(new Dimension(70, 70));
		this.add(this.delay);
		
		
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
		
		save = new JButton();
		save.setToolTipText("Guarda los resultados de la ejecución");
		save.setIcon(new ImageIcon("resources/icons/save.png"));
		save.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				
			 	mainWindow.guardarResultados();
			 }
		 });
		this.add(save);
		
		saveReport = new JButton();
		saveReport.setToolTipText("Guarda lo que esta en la caja de eventos en un fichero ");
		saveReport.setIcon(new ImageIcon("resources/icons/save_report.png"));
		saveReport.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				
			 	try {
					mainWindow.guardarEntrada();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
		 });
		this.add(saveReport);
		
		exit = new JButton();
		exit.setToolTipText("Cierra la practica");
		exit.setIcon(new ImageIcon("resources/icons/exit.png"));
		exit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
				interruptThread();
				mainWindow.Salir();
			}
		 });
		this.add(exit);
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
		this.time.setText(""+(this.c.getTime()+1));
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

	public int getDelay() {

		return (int) this.delay.getValue();
	}
	
	private void practica6Funcion() {
		
		System.out.println("HOLA PERRAS");
		enableButtons(false);
		Runnable t = new DelayRunner(this.getDelay(), this.getSteps(), this, this.c);
		this.t1 = new Thread(t);
		t1.start();
		
	}
	
	public void enableButtons(boolean a) {
		
		this.stop.setEnabled(!a);
		this.botonCargar.setEnabled(a);
		this.botonCheckIn.setEnabled(a);
		this.Clean2.setEnabled(a);
		this.Clean.setEnabled(a);
		this.restart.setEnabled(a);
		this.save.setEnabled(a);
		this.saveReport.setEnabled(a);
		this.run.setEnabled(a);
		this.steps.setEnabled(a);
		this.mW.enableThings(a);
		
	}
	
	public void changeSteps(int steps) {
		 
		this.steps.setValue(steps);
	}
	
	private void interruptThread() {
		
		
		this.t1.interrupt();
		this.enableButtons(true);
	}
}