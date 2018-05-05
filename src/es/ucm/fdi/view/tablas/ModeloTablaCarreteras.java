package es.ucm.fdi.view.tablas;

import java.util.List;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.carreteras.Carretera;
import es.ucm.fdi.model.events.Evento;

@SuppressWarnings("serial")
public class ModeloTablaCarreteras extends ModeloTabla<Carretera> {

	public ModeloTablaCarreteras(String[] columnidcarretera, Controlador controlador) {
		super(columnidcarretera, controlador);
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {

		List<Carretera> lista = mapa.getCarreteras();
		System.out.println(lista.size());
		for(Carretera c : lista) {
			Object[] row = {c.getLongitud(), c.getID(), c.getCruceOrigen(), c.getCruceDest(), c.getStringVehiculos()};
			this.addRow(row);
//			System.out.println(row);
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
