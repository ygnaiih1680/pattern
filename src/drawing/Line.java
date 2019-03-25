package drawing;

import java.awt.geom.Line2D;

public class Line extends ShapeA{
	private Line2D line;
	
	public Line() {
		this.line = new Line2D.Double();
	}

	@Override
	public void draw() {
		if(start==null)return;
		this.line.setLine(start.getX(), start.getY(), end.getX(), end.getY());
		g2d.setStroke(dotline);
		g2d.draw(line);
	}

	@Override
	public void drawComplete() {
		this.start = null;
		drawed.drawComplete(line);
		this.line = new Line2D.Double();
	}

	@Override
	public void addPoint() {
		// TODO Auto-generated method stub
		
	}
	
}
