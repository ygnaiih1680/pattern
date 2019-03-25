package drawing;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class ShapeA {
	protected Point start, end;
	protected Graphics2D g2d;
	protected static DrawedShape drawed;
	protected final float[] dotlinef = {2f, 5f};
	protected BasicStroke dotline, line;
	
	{
		drawed = new DrawedShape();
		dotline = new BasicStroke(1 ,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dotlinef, 0);
		line = new BasicStroke();
	}
	
	public abstract void draw();
	public abstract void drawComplete();
	public abstract void addPoint();
	
	public void move() {
		drawed.moveShape(end);
	}
	
	public void resize() {
		drawed.startResize(end);
	}
	
	public void rotate() {
		drawed.startRotate(end);
	}
	
	public void prepareChanging() {
		if(start==null)return;
		drawed.drawPoint(g2d);
		drawed.prepare2change(start);
	}
	
	public Cursor setCursor(Point point) {
		return drawed.setCursor(point);
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public void setGraphics2D(Graphics2D g) {
		this.g2d = g;
	}
	
	public void redraw() {
		drawed.redraw(g2d);
	}
	public boolean isSelect() {
		return drawed.isSelected();
	}
}
