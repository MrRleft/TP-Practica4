package es.fdi.ucm.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import es.ucm.fdi.Exceptions.ErrorDeSimulacion;
import es.ucm.fdi.MVC.ObservadorSimuladorTrafico;
import es.ucm.fdi.carreteras.Carretera;
import es.ucm.fdi.carreteras.CarreteraEntrante;
import es.ucm.fdi.control.Controlador;
import es.ucm.fdi.model.MapaCarreteras;
import es.ucm.fdi.cruces.CruceGenerico;
import es.ucm.fdi.events.Evento;
import es.ucm.fdi.vehiculos.Vehiculo;

public class ComponenteMapa extends JComponent implements ObservadorSimuladorTrafico {

	private static final long serialVersionUID = 1L;
	
	private static final int radioCruce = 20;
	private static final int radioVehiculo = 5;
	
	private class Punto {
		int cX;
		int cY;
		int tX;
		int tY;

		public Punto(int cX, int cY, int tX, int tY) {
			this.cX = cX;
			this.cY = cY;
			this.tX = tX;
			this.tY = tY;
		}
	}
	
	
	
	
	private MapaCarreteras mapa;
	private Map<String, Punto> posicionesDeNodos;
	private int ultimaAnchura;
	private int ultimaAltura;
	
	public ComponenteMapa(Controlador controlador) {
	  posicionesDeNodos = new HashMap<>();
	  this.setMinimumSize(new Dimension(400, 100));
	  this.setPreferredSize(new Dimension(400, 100));
	  ultimaAnchura = -1;
	  ultimaAltura = -1;
	  controlador.addObserver(this);
	  this.mapa = null;
	}

	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if (this.mapa == null || this.mapa.getCruces().size() == 0) {
			g.setColor(Color.red);
			g.drawString("Mapa vacio!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			drawMap(g);
		}
	}

	private void drawMap(Graphics g) {

		// if the size of the component has changed since the last time we
		// calculated the positions of the nodes, then we recalculate again.
		// This way the map get scaled down/up.
		if (ultimaAltura != getHeight() || ultimaAnchura != getWidth()) {
			ultimaAltura = getHeight();
			ultimaAnchura = getWidth();
			calculaCoordenadasCruce();
		}

		// draw nodes
		for (CruceGenerico<?> j : this.mapa.getCruces()) {
			Punto p = posicionesDeNodos.get(j.getId());
			g.setColor(Color.blue);
			g.fillOval(p.cX - radioCruce / 2, p.cY - radioCruce / 2, radioCruce, radioCruce);
			g.setColor(Color.black);
			g.drawString(j.getId(), p.tX, p.tY);
		}

		// draw edges
		for (Carretera e : this.mapa.getCarreteras()) {
			Punto p1 = posicionesDeNodos.get(e.getCruceOrigen().getId());
			Punto p2 = posicionesDeNodos.get(e.getCruceDest().getId());

			// draw the edge
			Color arrowColor = hasGreen(e) ? Color.GREEN : Color.RED;
			drawArrowLine(g, p1.cX, p1.cY, p2.cX, p2.cY, 15, 5, Color.BLACK, arrowColor);

			// draw dots as circles. Dots at the same location are drawn with circles of
			// different diameter.
			int ultimaLocalizacion = -1;
			int diam = radioVehiculo;
			for (Vehiculo d : e.getVehiculos()) {
				if (ultimaLocalizacion != d.getLocalizacion()) {
					ultimaLocalizacion = d.getLocalizacion();
					diam = radioVehiculo;
				} else {
					diam += radioVehiculo;
				}
				Color dotColor = d.getTiempoDeInfraccion() > 0.5 ? Color.RED : Color.MAGENTA;
				drawCircleOnALine(g, p1.cX, p1.cY, p2.cX, p2.cY, e.getLongitud(), d.getLocalizacion(), diam, dotColor,
						d.getId());
			}
		}
	}

	private boolean hasGreen(Carretera e) {
		for (CarreteraEntrante r : e.getCruceDest().getCarreteras()) {
			if (r.getCarretera().equals(e)) {
				if (r.tieneSemaforoVerde())
					return true;
				else
					return false;
			}
		}
		// never reached in principle
		return false;
	}

	/**
	 * put the objects in a circle, for each one store the center coordinate and a
	 * coordinate for a corresponding text.
	 */
	private void calculaCoordenadasCruce() {

		int r = Math.min(ultimaAltura, ultimaAnchura) / 2 - radioCruce - 50; // 50 for
																			// text
		int tr = (r + radioCruce + 10);

		int xc = ultimaAnchura / 2 - 10;
		int yc = ultimaAltura / 2 - 10;

		double slice = 2 * Math.PI / this.mapa.getCruces().size();
		int i = 0;
		for (CruceGenerico<?> n : this.mapa.getCruces()) {

			double angle = slice * i;
			int cX = (int) (xc + r * Math.cos(angle));
			int cY = (int) (yc + r * Math.sin(angle));
			int tX = (int) (xc + tr * Math.cos(angle));
			int tY = (int) (yc + tr * Math.sin(angle));

			posicionesDeNodos.put(n.getId(), new Punto(cX, cY, tX, tY));
			i++;
		}

	}

	/**
	 * Draws a circle on the line from (x1,y1) to (x2,y2). Assuming the (virtual)
	 * length of the line is virtualLength, the circles is drawn at location
	 * virtualLocation (0..virtualLength). The diameter is 'diam'
	 */
	private void drawCircleOnALine(Graphics g, int x1, int y1, int x2, int y2, int virtualLength, int virtualLocation,
			int diam, Color c, String txt) {

		// The actual length of the line
		double lineActualLength = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) - 45;

		// the angle of the line with the horizontal axis
		double alpha = Math.atan(((double) Math.abs(x1 - x2)) / ((double) Math.abs(y1 - y2)));

		// the actual location on the line (0..lineActualLength)
		double actualLocation = lineActualLength * ((double) virtualLocation) / ((double) virtualLength) + 15;

		// the coordinates of the location
		double x = Math.sin(alpha) * actualLocation;
		double y = Math.cos(alpha) * actualLocation;

		// signs repressing the direction of the line (left/right, up/down)
		int xDir = x1 < x2 ? 1 : -1;
		int yDir = y1 < y2 ? 1 : -1;

		// draw the point
		g.setColor(c);
		g.drawOval(x1 + xDir * ((int) x) - diam / 2, y1 + yDir * ((int) y) - diam / 2, diam, diam);

		// draw the text
		g.setColor(Color.darkGray);
		g.drawString(txt, x1 + xDir * ((int) x) - diam / 2, y1 + yDir * ((int) y) - diam / 2);
	}

	/**
	 * Draws a line from (x1,y1) to (x2,y2) with an arrow of width d and height h.
	 * The color of the line is lineColor and that of the arrow is arrowColor.
	 */
	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h, Color lineColor,
			Color arrowColor) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);
	}

	
	private void refresh() {
		repaint();
	}
	
	

	@Override
	public void errorSimulador(int tiempo, MapaCarreteras mapa, List<Evento> eventos, ErrorDeSimulacion e) {
		this.mapa = mapa;
		calculaCoordenadasCruce();
		refresh();
	}

	@Override
	public void avanza(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.mapa = mapa;
		calculaCoordenadasCruce();
		refresh();
	}

	@Override
	public void addEvento(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.mapa = mapa;
		calculaCoordenadasCruce();
		refresh();
	}

	@Override
	public void reinicia(int tiempo, MapaCarreteras mapa, List<Evento> eventos) {
		this.mapa = mapa;
		calculaCoordenadasCruce();
		refresh();
	}


}
