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
	
	@Override // necesario para que se visualicen los datos	
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0:
				s = this.lista.get(indiceFil).getID(); break;
			case 1:
				s = this.lista.get(indiceFil).getCruceOrigen(); break;
			case 2:
				s = this.lista.get(indiceFil).getCruceDest(); break;
			case 3:
				s = this.lista.get(indiceFil).getLongitud(); break;
			case 4:
				s = this.lista.get(indiceFil).getVMax(); break;
			case 5:
				s = this.lista.get(indiceFil).getStringVehiculos(); break;
			default: 
				assert (false);
		}
		return s;
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
	
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {

		this.lista = mapa.getCarreteras();
		this.fireTableStructureChanged();
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.fireTableStructureChanged();

	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		for(int i = 0; i < getRowCount(); i++ )
			this.removeRow(i);
		this.fireTableStructureChanged();
	}

	

}
