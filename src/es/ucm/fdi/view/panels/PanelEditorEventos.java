package es.ucm.fdi.view.panels;



import es.ucm.fdi.view.VentanaPrincipal;

public class PanelEditorEventos extends PanelAreaTexto {

	private static final long serialVersionUID = 1L;

	public PanelEditorEventos(String titulo, String texto, boolean editable, VentanaPrincipal mainWindow) {
			 
		super(titulo,editable);
		this.setTexto(texto);
		// OPCIONAL
		/*
		PopUpMenu popUp = new PopUpMenu(mainWindow);
		this.areatexto.add(popUp);
		this.areatexto.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				 
				 if (e.isPopupTrigger() && areatexto.isEnabled())
					 popUp.show(e.getComponent(), e.getX(), e.getY());
			}
			 
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		*/
	}
	 
	 
}
