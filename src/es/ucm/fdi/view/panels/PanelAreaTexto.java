package es.ucm.fdi.view.panels;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
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
		String T = this.getTexto();
		this.areatexto.setText(T + System.getProperty("line.separator") + texto);
	}
	
	public void limpiar() {
		this.areatexto.setText("");
	}
	
	public void inserta(String valor) {
		this.areatexto.insert(valor, this.areatexto.getCaretPosition());
	}
	
	public void habilitar(boolean a) {
		this.areatexto.setEditable(a);
	}
}
