package es.ucm.fdi.view.tablas;

import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;

//EL MODELO SE REGISTRA COMO OBSERVADOR
public abstract class ModeloTabla<T> extends DefaultTableModel implements ObservadorSimuladorTrafico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String[] columnIds;
	protected List<T> lista;
	public ModeloTabla(String[] columnIdEventos, Controlador ctrl) {
	
		this.lista = null;
		this.columnIds=columnIdEventos;
		ctrl.addObserver(this);
	}
	
	@Override
	public String getColumnName(int col) { return this.columnIds[col]; }
	
	@Override
	public int getColumnCount() {return this.columnIds.length;}
	
	@Override
	public int getRowCount() {
		return this.lista == null ? 0 :	this.lista.size();
	}
	
	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {

		this.lista = null;
		this.fireTableStructureChanged();
	
	}
}