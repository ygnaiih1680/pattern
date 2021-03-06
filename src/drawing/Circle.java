package drawing;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circle extends ShapeA {
	private Ellipse2D circle;
	
	public Circle() {
		this.circle = new Ellipse2D.Double();
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(start==null)return;
		double width = Math.max(Math.abs(end.getX()-start.getX()), Math.abs(end.getY()-start.getY()));
		this.circle.setFrame(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()),
				width, width);
		g2d.setStroke(dotline);
		g2d.draw(circle);
	}

	@Override
	public void drawComplete() {
		this.start = null;
		drawed.drawComplete(circle);
		this.circle = new Ellipse2D.Double();
	}

	@Override
	public void addPoint() {}

}
