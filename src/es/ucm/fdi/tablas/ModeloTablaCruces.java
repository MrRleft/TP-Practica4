package es.ucm.fdi.tablas;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.model.MapaCarreteras;

public class ModeloTablaCruces extends ModeloTabla<CruceGenerico<?>> {

	public ModeloTablaCruces(String[] columnidcruce, Controlador controlador) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub

	}

}