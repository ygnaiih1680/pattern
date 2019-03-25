package drawing;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class Line extends ShapeA{
	private Line2D line;
	
	public Line() {
		this.line = new Line2D.Double();
	}
	
	public Shape draw(Graphics2D g2d, Point start, Point end) {
		
		return this.line;
	}
	
	public void complete() {
		
	}

	@Override
	public void draw() {
		this.line.setLine(start.getX(), start.getY(), end.getX(), end.getY());
		g2d.setStroke(dotline);
		g2d.draw(line);
	}

	@Override
	public void drawComplete() {
		drawed.drawComplete(line);
		this.line = new Line2D.Double();
	}

	@Override
	public void addPoint() {
		// TODO Auto-generated method stub
		
	}
	
}
