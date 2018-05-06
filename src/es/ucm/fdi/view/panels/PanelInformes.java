package es.ucm.fdi.view.panels;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;

public class PanelInformes extends PanelAreaTexto implements ObservadorSimuladorTrafico {

	 public PanelInformes(String titulo, boolean editable, Controlador ctrl) {
		 super(titulo, editable);
		 ctrl.addObserver(this); // se a�ade como observador
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		
		super.setTexto("ERROR: " + e.getMessage());
		super.setTexto("Pulsa reiniciar y vuelve a intentarlo");
		
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		
		super.limpiar();
		this.setTexto(mapa.generateReport(tiempo));
		for(Evento e: eventos)
			if(e.getTiempo() == tiempo)
				this.setTexto("Ha entrado el evento de la cola " + e.toString());
		
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		super.setTexto("NUEVO EVENTO INSERTADO");
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		super.limpiar();
		super.setTexto("SE HA REINICIADO EL SIMULADOR");
	}


}
