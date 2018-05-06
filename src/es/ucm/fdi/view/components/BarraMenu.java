package es.ucm.fdi.view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.view.VentanaPrincipal;


@SuppressWarnings("serial")
public class BarraMenu extends JMenuBar {


	 public BarraMenu(VentanaPrincipal mainWindow, Controlador controlador) {
	 
		 super();
		 // MANEJO DE FICHEROS
		 JMenu menuFicheros = new JMenu("Ficheros");
		 this.add(menuFicheros);
		 this.creaMenuFicheros(menuFicheros,mainWindow);
		 // SIMULADOR
		 JMenu menuSimulador = new JMenu("Simulador");
		 this.add(menuSimulador);
		 this.creaMenuSimulador(menuSimulador,controlador,mainWindow);
		 // INFORMES
		 JMenu menuReport = new JMenu("Informes");
		 this.add(menuReport);
		 this.creaMenuInformes(menuReport,mainWindow);
	 }

	 private void creaMenuFicheros(JMenu menu,VentanaPrincipal mainWindow) {
		
		 JMenuItem cargar = new JMenuItem("Carga Eventos");
		 cargar.addActionListener(new ActionListener() {
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
		 
		 JMenuItem salvar = new JMenuItem("Guardar eventos");
		 salvar.setMnemonic(KeyEvent.VK_L);
		 salvar.addActionListener(new ActionListener() {
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
		 
		
		 JMenuItem salvarInformes = new JMenuItem("Guardar informes");
		 cargar.setMnemonic(KeyEvent.VK_L);
		 cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
		 ActionEvent.ALT_MASK));
		 cargar.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 mainWindow.guardarResultados();
			 }
		 });
		
		 
		 JMenuItem salir = new JMenuItem("Salir");
		 salir.setMnemonic(KeyEvent.VK_L);
		 salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
		 ActionEvent.ALT_MASK));
		 salir.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 mainWindow.Salir();
			 }
		 });
		 
		 menu.add(cargar);
		 menu.addSeparator();
		 menu.add(salvar);
		 menu.addSeparator();
		 menu.add(salvarInformes);
		 menu.addSeparator();
		 menu.add(salir);
		}

	 private void creaMenuInformes(JMenu menuReport, VentanaPrincipal mainWindow) {
			 

;
		 JMenuItem limpiaAreaInformes = new JMenuItem("Clear");
		 limpiaAreaInformes.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 mainWindow.limpiaInformes();
			 }
		 });
		 
		 menuReport.add(limpiaAreaInformes);
	}

	private void creaMenuSimulador(JMenu menuSimulador, Controlador controlador, VentanaPrincipal mainWindow) {
			
		JMenuItem ejecuta = new JMenuItem("Ejecuta");
		ejecuta.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int pasos = mainWindow.getSteps();
			controlador.ejecuta(pasos);
			}
		});
		
		JMenuItem reinicia = new JMenuItem("Reinicia");
		ejecuta.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			controlador.reinicia();
			}
		});
		
		menuSimulador.add(ejecuta);
		menuSimulador.add(reinicia);
	}

}
