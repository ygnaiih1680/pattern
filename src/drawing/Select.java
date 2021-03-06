package drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Select extends ShapeA{
	private Rectangle2D rect;
	
	public Select() {
		this.rect = new Rectangle2D.Double();
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(this.start==null)return;
		this.rect.setRect(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()),
				Math.abs(end.getX()-start.getX()), Math.abs(end.getY()-start.getY()));
		g2d.setColor(new Color(108, 108, 108, 100));
		g2d.fill(rect);
		g2d.setColor(Color.BLACK);
	}

	@Override
	public void drawComplete() {
		this.start = null;
		drawed.drawTrans(rect);
		this.rect = new Rectangle2D.Double();
	}

	@Override
	public void addPoint() {
	}
}
