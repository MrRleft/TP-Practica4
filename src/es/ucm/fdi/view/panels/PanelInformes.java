package es.ucm.fdi.view.panels;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;

@SuppressWarnings("serial")
public class PanelInformes extends PanelAreaTexto implements ObservadorSimuladorTrafico {

	 public PanelInformes(String titulo, boolean editable, Controlador ctrl) {
		 super(titulo, editable);
		 ctrl.addObserver(this); // se a�ade como observador
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		/*
		super.setTexto("ERROR: " + e.getMessage());
		super.setTexto("Pulsa reiniciar y vuelve a intentarlo");
		*/
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
		this.setTexto(mapa.generateReport(tiempo));	
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		super.limpiar();
	}


}
