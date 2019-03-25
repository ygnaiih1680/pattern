package drawing;

import java.awt.geom.Ellipse2D;

public class Ellipse extends ShapeA{
	private Ellipse2D ellipse;
	
	public Ellipse() {
		this.ellipse = new Ellipse2D.Double();
	}
	
	@Override
	public void draw() {
		this.ellipse.setFrame(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()),
				Math.abs(end.getX()-start.getX()), Math.abs(end.getY()-start.getY()));
		g2d.setStroke(dotline);
		g2d.draw(ellipse);
	}

	@Override
	public void drawComplete() {
		drawed.drawComplete(ellipse);
		this.ellipse = new Ellipse2D.Double();
	}

	@Override
	public void addPoint() {}

}
