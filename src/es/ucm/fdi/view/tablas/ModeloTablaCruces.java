package es.ucm.fdi.view.tablas;

import java.util.List;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.carreteras.Carretera;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;

public class ModeloTablaCruces extends ModeloTabla<CruceGenerico<?>> {

	public ModeloTablaCruces(String[] columnidcruce, Controlador controlador) {
		super(columnidcruce,controlador);
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		List<CruceGenerico<?>> lista = mapa.getCruces();
		for(CruceGenerico<?> c : lista) {
			Object[] row = {c.getId(), c.getGreenRoad(), c.getRedRoads()};
			this.addRow(row);
		}
		this.fireTableStructureChanged();
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.avanza(tiempo, mapa, eventos);
		this.fireTableStructureChanged();
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {

		for(int i = 0; i < getRowCount(); i++ )
			this.removeRow(i);
		this.fireTableStructureChanged();
	}


}
