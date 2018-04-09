package es.ucm.fdi.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import es.ucm.fdi.tablas.ModeloTabla;
import es.ucm.fdi.tablas.ModeloTablaEventos;

public class PanelTabla<T> extends JPanel {
	
	private ModeloTabla<T> modelo;
	
	public PanelTabla(String bordeId, ModeloTabla<T> modelo){
	
		this.setLayout(new GridLayout(1,1));
		this.setBorder(Border);
		this.modelo = modelo;
		JTable tabla = new JTable(this.modelo);
		this.add(new JScrollPane(tabla));
	}
}