package es.ucm.fdi.view.components;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.model.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.model.carreteras.Carretera;
import es.ucm.fdi.model.cruces.CruceGenerico;
import es.ucm.fdi.model.events.Evento;
import es.ucm.fdi.model.vehiculos.Vehiculo;
import es.ucm.fdi.view.ObservadorSimuladorTrafico;
import es.ucm.fdi.view.VentanaPrincipal;
import es.ucm.fdi.view.panels.PanelBotones;
import es.ucm.fdi.view.panels.PanelInformes;

/*
	 * Esta componente ser� una observadora, ya que puede estar visible u oculta, pero
		debe tener siempre actualizados los objetos de la simulaci�n presentes en cada paso.
		El JDialog contiene tres paneles, y cada uno de estos paneles contendr� un JList para
		mostrar la informaci�n sobre los correspondientes objetos de la simulaci�n y poder
		seleccionarlos.
		 Por tanto vamos a tener una clase gen�rica para estos paneles PanelObjSim<T>,
		que ser� la que contenga el correspondiente objeto Jlist<T> objList. 
	 */
@SuppressWarnings("serial")
public class DialogoInformes extends JDialog implements ObservadorSimuladorTrafico {
	
	private PanelBotones panelBotones;
	private PanelObjSim<Vehiculo> panelVehiculos;
	private PanelObjSim<Carretera> panelCarreteras;
	private PanelObjSim<CruceGenerico<?>> panelCruces;
	
	public void initGUI(VentanaPrincipal panelPrincipal, Controlador c) {
	 
		 this.panelVehiculos = new PanelObjSim<Vehiculo>("Vehiculos");
		 this.panelCarreteras = new PanelObjSim<Carretera>("Carreteras");
		 this.panelCruces = new PanelObjSim<CruceGenerico<?>>("Cruces");
		 this.panelBotones = new PanelBotones(this);
		 PanelInformes panelInfo = new PanelInformes("Placeholder", rootPaneCheckingEnabled, c);
		 panelPrincipal.add(panelInfo,BorderLayout.PAGE_START);
		 
	 
	}
	
	private void setMapa(MapaCarreteras mapa) {
	
		this.panelVehiculos.setList(mapa.getVehiculos());
		this.panelCarreteras.setList(mapa.getCarreteras());
		this.panelCruces.setList(mapa.getCruces());
		
	}
	
	public List<Vehiculo> getVehiculosSeleccionados() {
		 return this.panelVehiculos.getSelectedItems();
	}
	
	public List<Carretera> getCarreterasSeleccionadas() {
		 return this.panelCarreteras.getSelectedItems();
	}
	
	public List<CruceGenerico<?>> getCrucesSeleccionados() {
		 return this.panelCruces.getSelectedItems();
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
	

