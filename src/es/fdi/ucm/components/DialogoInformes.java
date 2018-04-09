package es.fdi.ucm.components;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;

import es.ucm.fdi.MVC.ObservadorSimuladorTrafico;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.vehiculos.Vehiculo;

/*
	 * Esta componente será una observadora, ya que puede estar visible u oculta, pero
		debe tener siempre actualizados los objetos de la simulación presentes en cada paso.
		El JDialog contiene tres paneles, y cada uno de estos paneles contendrá un JList para
		mostrar la información sobre los correspondientes objetos de la simulación y poder
		seleccionarlos.
		 Por tanto vamos a tener una clase genérica para estos paneles PanelObjSim<T>,
		que será la que contenga el correspondiente objeto Jlist<T> objList. 
	 */
public class DialogoInformes extends JDialog implements ObservadorSimuladorTrafico {
	
	private PanelBotones panelBotones;
	private PanelObjSim<Vehiculo> panelVehiculos;
	private PanelObjSim<Carretera> panelCarreteras;
	private PanelObjSim<CruceGenerico<?>> panelCruces;
	
	private void initGUI() {
	 
		 this.panelVehiculos = new PanelObjSim<Vehiculo>("Vehiculos");
		 this.panelCarreteras = new PanelObjSim<Carretera>("Carreteras");
		 this.panelCruces = new PanelObjSim<CruceGenerico<?>>("Cruces");
		 this.panelBotones = new PanelBotones(this);
		 InformationPanel panelInfo = new InformationPanel();
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
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
	 this.setMapa(mapa);
	}
	
	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
	 this.setMapa(mapa);
	}
	
	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
	 this.setMapa(mapa);
	}
}
	

