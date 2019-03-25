package drawing;

import java.awt.Polygon;

public class PolygonA extends ShapeA {
	private Polygon poly;

	public PolygonA() {
		this.poly = new Polygon();
	}

	@Override
	public void draw() {
		if (poly.npoints == 0 || g2d == null)
			return;
		else if (poly.npoints == 1) {
			poly.xpoints[0] = start.x;
			poly.ypoints[0] = start.y;
			this.poly.addPoint(end.x, end.y);
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
		drawed.drawComplete(poly);
		this.poly = new Polygon();
	}

}
