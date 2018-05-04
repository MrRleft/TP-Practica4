package es.ucm.fdi.view.tablas;

import java.util.List;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.view.Describable;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;
import es.ucm.fdi.view.components.ComponenteMapa;

public class ModeloTablaEventos extends ModeloTabla<Evento>  {
	
	private List<Evento> events;
	
	public ModeloTablaEventos(String[] columnIdEventos, Controlador ctrl) { 
		super(columnIdEventos,ctrl);
		this.events = ctrl.getSimulador().getEventos();
		
	}
	@Override // necesario para que se visualicen los datos
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		switch (indiceCol) {
			case 0:
				s = indiceFil; break;
			case 1:
				s = this.lista.get(indiceFil).getTiempo(); break;
			case 2:
				s = this.lista.get(indiceFil).toString(); break;
			default: 
				assert (false);
		}
		return s;
	}
	
	//...
	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
	this.lista = eventos; 
	this.fireTableStructureChanged();
	}
	
	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		eventos = this.events;
		this.fireTableStructureChanged();
		
	}
	
	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.fireTableStructureChanged();
	}
	
	@Override
	public void errorSimulador(int tiempo, MapaCarreteras map, List<Evento> eventos, ErrorDeSimulacion e) {
		// TODO Auto-generated method stub
		this.fireTableStructureChanged();
	}
	public List<Evento> getEvents() {
		return events;
	}
	public void setEvents(List<Evento> events) {
		this.events = events;
	}
	

}