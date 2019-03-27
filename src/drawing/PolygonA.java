package drawing;

import java.awt.Graphics2D;
import java.awt.Polygon;

public class PolygonA extends ShapeA {
	private Polygon poly;

	public PolygonA() {
		this.poly = new Polygon();
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(start==null)return;
		if (poly.npoints == 0) {
			this.poly.addPoint(start.x, start.y);
			this.poly.addPoint(start.x, start.y);
		} else {
			poly.xpoints[poly.npoints - 1] = end.x;
			poly.ypoints[poly.npoints - 1] = end.y;
		}
		g2d.setStroke(dotline);
		g2d.draw(poly);
	}

	public void addPoint() {
		this.poly.addPoint(end.x, end.y);
	}

	@Override
	public void drawComplete() {
		int x[] = new int[this.poly.npoints-1], y[] = new int[this.poly.npoints-1];
		for(int i = 0;i<this.poly.npoints-1;i++) {
			x[i] = this.poly.xpoints[i];
			y[i] = this.poly.ypoints[i];
		}
		this.poly.npoints = x.length;
		this.poly.xpoints = x;
		this.poly.ypoints = y;
		this.start = null;
		drawed.drawComplete(poly);
		this.poly = new Polygon();
	}

}
