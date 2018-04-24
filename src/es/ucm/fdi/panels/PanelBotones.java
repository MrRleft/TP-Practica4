package es.ucm.fdi.panels;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import es.fdi.ucm.components.DialogoInformes;
import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.MVC.ObservadorSimuladorTrafico;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.model.MapaCarreteras;

public class PanelBotones extends JPanel implements ObservadorSimuladorTrafico  {
	
	public PanelBotones(DialogoInformes d) {
		

	}
	
	void crearBotones() {
		
		JButton J = new JButton();
		this.add(J);

		
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
