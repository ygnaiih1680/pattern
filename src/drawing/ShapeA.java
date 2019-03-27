package drawing;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class ShapeA {
	protected Point start, end;
	protected DrawedShape drawed;
	protected final float[] dotlinef = { 2f, 5f };
	protected BasicStroke dotline, line;

	{
		dotline = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dotlinef, 0);
		line = new BasicStroke();
	}

	public abstract void draw(Graphics2D g2d);

	public abstract void drawComplete();

	public abstract void addPoint();

	public void initialize(DrawedShape drawed) {
		this.drawed = drawed;
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

	public boolean isSelect() {
		return drawed.isSelected();
	}
	
	public void changing() {
		drawed.prepare2change(start);
	}

	public void move() {
		drawed.moveShape(end);
	}

	public void rotate() {
		drawed.startRotate(end);
	}

	public void resize() {
		drawed.startResize(end);
	}
}
