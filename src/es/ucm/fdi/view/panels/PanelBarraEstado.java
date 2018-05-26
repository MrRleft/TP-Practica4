package es.ucm.fdi.view.panels;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;

public class PanelBarraEstado extends JPanel implements ObservadorSimuladorTrafico {

	private JLabel infoEjecucion;
	
	public PanelBarraEstado(String mensaje, Controlador controlador) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.infoEjecucion = new JLabel(mensaje);
		this.add(this.infoEjecucion);
		this.setBorder(BorderFactory.createBevelBorder(1));
		controlador.addObserver(this);
	}
	
	public void setMensaje(String mensaje) {
		this.infoEjecucion.setText(mensaje);
	} // la ventana principal se comunica con el panel

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.infoEjecucion.setText("Paso: " + tiempo + " del Simulador completado");
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.infoEjecucion.setText("Evento insertado"
				+ " al simulador");
	}
	
	@Override	
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		setMensaje("Se ha reiniciado el simulador");
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		setMensaje("ERROR: " + e.getMessage());
		
	}

}