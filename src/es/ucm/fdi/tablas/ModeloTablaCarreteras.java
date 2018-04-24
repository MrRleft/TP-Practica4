package es.ucm.fdi.tablas;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.MVC.ObservadorSimuladorTrafico;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.model.MapaCarreteras;

public class ModeloTablaCarreteras extends ModeloTabla<Carretera> {

	public ModeloTablaCarreteras(String[] columnidcarretera, Controlador controlador) {
		// TODO Auto-generated constructor stub
		super(columnidcarretera, controlador);
	}

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub
		this.fireTableStructureChanged();
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		this.fireTableStructureChanged();
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		this.fireTableStructureChanged();

	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		// TODO Auto-generated method stub
		this.fireTableStructureChanged();
	}



}
