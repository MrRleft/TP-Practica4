package es.fdi.ucm.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import es.ucm.fdi.MVC.VentanaPrincipal;
import es.ucm.fdi.control.Controlador;


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
		 cargar.setMnemonic(KeyEvent.VK_L);
		 cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
		 ActionEvent.ALT_MASK));
		 cargar.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 mainWindow.cargaFichero();
			 }
		 });
		 
		 menu.add(cargar);
		 menu.add(salvar);
		 menu.addSeparator();
		 menu.add(salvarInformes);
		 menu.addSeparator();
		 menu.add(salir);
		}

	 private void creaMenuInformes(JMenu menuReport, VentanaPrincipal mainWindow) {
			 
		 JMenuItem generaInformes = new JMenuItem("Generar");
		 generaInformes.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 // OPCIONAL
				 mainWindow.generaInformes();
			 }
		 });
		 menuReport.add(generaInformes);
		 JMenuItem limpiaAreaInformes = new JMenuItem("Clear");
		
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
		
		menuSimulador.add(ejecuta);
		menuSimulador.add(reinicia);
	}

}
