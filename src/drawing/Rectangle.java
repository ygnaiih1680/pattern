package drawing;

import java.awt.geom.Rectangle2D;

public class Rectangle extends ShapeA{
	private Rectangle2D rect;
	
	public Rectangle() {
		this.rect = new Rectangle2D.Double();
	}
	
	public void draw() {
		if(this.start==null)return;
		this.rect.setRect(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()),
				Math.abs(end.getX()-start.getX()), Math.abs(end.getY()-start.getY()));
		g2d.setStroke(dotline);
		g2d.draw(rect);
	}

	@Override
	public void drawComplete() {
		drawed.drawComplete(this.rect);
		this.start = null;
		this.rect = new Rectangle2D.Double();
	}

	@Override
	public void addPoint() {
	}
}
