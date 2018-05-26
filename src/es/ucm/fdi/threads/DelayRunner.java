package es.ucm.fdi.threads;

import es.ucm.fdi.controller.Controlador;
import es.ucm.fdi.view.components.ToolBar;
import static java.lang.Thread.sleep;

public class DelayRunner implements Runnable{
	
	private int Delay;
	private int Steps;
	private ToolBar Toolb;
	private Controlador c;
	
	public DelayRunner(int Delay, int Steps, ToolBar t, Controlador c) {
		
		this.Delay = Delay;
		this.Steps = Steps;
		this.Toolb = t;
		this.c = c;
		
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.Steps > 0 && !Thread.interrupted()) {
			
			try {
				sleep(this.Delay);
			} catch (InterruptedException e) {
				
				Thread.currentThread().interrupt();
			}
			this.Steps--;
			this.c.ejecuta(1);


			this.Toolb.changeSteps(this.Steps);
		}
		Toolb.enableButtons(true);
	}

}
