package es.ucm.fdi.view.tablas;

import java.util.List;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.model.vehiculos.Vehiculo;


@SuppressWarnings("serial")
public class ModeloTablaVehiculos extends ModeloTabla<Vehiculo> {

	public ModeloTablaVehiculos(String[] columnidvehiculo, Controlador controlador) {
		// TODO Auto-generated constructor stub
		super(columnidvehiculo, controlador);
	}
	
	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0:
				s = this.lista.get(indiceFil).getId(); break;
			case 1:
				s = this.lista.get(indiceFil).getRoad(); break;
			case 2:
				s = this.lista.get(indiceFil).getLocalizacion(); break;
			case 3:
				s = this.lista.get(indiceFil).getSpeed(); break;
			case 4:
				s = this.lista.get(indiceFil).getKM(); break;
			case 5:
				s = this.lista.get(indiceFil).getTiempoDeInfraccion(); break;
			case 6:
				s = this.lista.get(indiceFil).getIti(); break;
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

		this.lista = mapa.getVehiculos();
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
