package es.ucm.fdi.tablas;

import java.util.List;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.MVC.ObservadorSimuladorTrafico;
import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.model.MapaCarreteras;

public class ModeloTablaEventos extends ModeloTabla<Evento>  {
	
	public ModeloTablaEventos(String[] columnIdEventos, Controlador ctrl) { 
		super(columnIdEventos,ctrl);
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
	@Override
	public void addObservador(ObservadorSimuladorTrafico o) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeObservador(ObservadorSimuladorTrafico o) {
		// TODO Auto-generated method stub
		
	}
}