package es.ucm.fdi.panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

abstract public class PanelAreaTexto extends JPanel{

	protected JTextArea areatexto;
	public PanelAreaTexto(String titulo, boolean editable) {
	
		this.setLayout(new GridLayout(1,1));
		this.areatexto = new JTextArea(40, 30);
		this.areatexto.setEditable(editable);
		this.add(new JScrollPane(areatexto));
		this.setBorde(titulo);
		
	}
	
	public void setBorde(String titulo){
		this.setBorder(BorderFactory.createTitledBorder(titulo));
	}
	
	public String getTexto() {
		return areatexto.getText();
	}
	
	public void setTexto(String texto) {
		...
	}
	
	public void limpiar() {
		...
	}
	
	public void inserta(String valor) {
		this.areatexto.insert(valor, this.areatexto.getCaretPosition());
	}
}
