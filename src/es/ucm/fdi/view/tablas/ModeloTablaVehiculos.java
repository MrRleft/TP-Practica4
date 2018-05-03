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
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {

	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		List<Vehiculo> lista = mapa.getVehiculos();
		for(Vehiculo v : lista) {
			Object[] row = {v.getId(), v.getRoad(), v.getLocalizacion(), v.getSpeed(), v.getKM(),
					v.getTiempoDeInfraccion(), v.getIti()};
			this.addRow(row);
		}
		this.fireTableStructureChanged();
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		this.avanza(tiempo, mapa, eventos);
		this.fireTableStructureChanged();
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < getRowCount(); i++ )
			this.removeRow(i);
		this.fireTableStructureChanged();
	
	}


}
