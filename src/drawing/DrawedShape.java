package drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawedShape {
	private ArrayList<Shape> drawed;
	private ArrayList<Shape> selected;
	private ArrayList<Integer> index;
	private ArrayList<Boolean> isRect;
	private Transform trans;
	private int pointindex;
	private Point before;
	private double width, height;
	private boolean isSelected;

	public DrawedShape() {
		this.drawed = new ArrayList<Shape>();
		this.selected = new ArrayList<Shape>();
		this.index = new ArrayList<Integer>();
		this.isRect = new ArrayList<Boolean>();
		this.trans = new Transform();
		this.isSelected = false;
	}

	public Cursor setCursor(Point point) {
		pointindex = trans.isOnPoint(point);
		if (isSelected) {
			for (Shape temp : this.selected) {
				if (temp.contains(point))
					return new Cursor(Cursor.MOVE_CURSOR);
			}
		}
		switch (pointindex % 5) {
		case 0:
			return new Cursor(Cursor.NW_RESIZE_CURSOR);
		case 1:
			return new Cursor(Cursor.NE_RESIZE_CURSOR);
		case 2:
			return new Cursor(Cursor.SW_RESIZE_CURSOR);
		case 3:
			return new Cursor(Cursor.SE_RESIZE_CURSOR);
		case 4:
			return new Cursor(Cursor.HAND_CURSOR);
		default:
			return new Cursor(Cursor.CROSSHAIR_CURSOR);
		}
	}
	

	public void drawComplete(Shape shape) {
		if (shape == null)
			return;
		this.isRect.add(this.isRect(shape));
		drawed.add(shape);
	}

	public boolean isSelected() {
		if (selected.size() > 0)
			return true;
		return false;
	}

	public void moveShape(Point after) {
		for (Shape sel : selected) {
			Shape temp = trans.move(sel, before, after);
			drawed.set(index.get(selected.indexOf(sel)), temp);
		}
	}

	public void redraw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke());
		for (Shape shape : drawed) {
			g2d.draw(shape);
		}
	}

	public void prepare2change(Point before) {
		if (drawed.size() == 0)
			return;
		this.before = before;
		Shape onpoint = drawed.get(pointindex / 5);
		this.width = onpoint.getBounds2D().getWidth();
		this.height = onpoint.getBounds2D().getHeight();
	}

	public void startResize(Point after) {
		if (selected.size() == 0)
			return;
		double gapx = trans.changedXRatio(before, after, width), gapy = trans.changedYRatio(before, after, height);
		for (Shape sel : selected) {
			Shape temp = trans.changeScale(sel, gapx, gapy, this.pointindex);
			drawed.set(index.get(selected.indexOf(sel)), temp);
		}
	}

	public void startRotate(Point after) {
		for (Shape sel : selected) {
			double theta = trans.calculateTheta(before, after);
			Shape temp = trans.rotate(sel,
					new Point((int) sel.getBounds2D().getCenterX(), (int) sel.getBounds2D().getCenterY()), theta);
			drawed.set(index.get(selected.indexOf(sel)), temp);
		}
	}

	public void drawTrans(Graphics2D g2d, Shape selectArea) {
		 SelectArea area = new SelectArea(g2d, selectArea);
		 Thread t = new Thread(area);
		 t.start();
	}

	public void drawPoint(Graphics2D g2d) {
		trans.createResizePoint(g2d);
	}

	public boolean isRect(Shape shape) {
		if (shape == null)
			return false;
		if (shape instanceof Rectangle2D)
			return true;
		return false;
	}

	public boolean isEllipse(Shape shape) {
		if (shape == null)
			return false;
		if (shape instanceof Ellipse2D)
			return true;
		return false;
	}

	public boolean isCircle(Shape shape) {
		if (shape == null)
			return false;
		if (shape instanceof Rectangle2D && shape.getBounds2D().getWidth() == shape.getBounds2D().getHeight())
			return true;
		return false;
	}

	public boolean isLine(Shape shape) {
		if (shape == null)
			return false;
		if (shape instanceof Line2D)
			return true;
		return false;
	}

	public boolean isPolygon(Shape shape) {
		if (shape == null)
			return false;
		if (shape instanceof Polygon)
			return true;
		return false;
	}

	private class SelectArea implements Runnable {
		private Graphics2D g2d;
		private Shape selectArea;

		public SelectArea(Graphics2D g2d, Shape selectArea) {
			this.g2d = g2d;
			this.selectArea = selectArea;
		}

		@Override
		public void run() {
			trans = new Transform();
			selected.clear();
			index.clear();
			for (Shape shape : drawed) {
				if (selectArea.contains(shape.getBounds2D())) {
					trans.setResizeShape(shape, isRect.get(drawed.indexOf(shape)));
					selected.add(shape);
					index.add(drawed.indexOf(shape));
					isSelected = true;
				}
			}
			trans.createResizePoint(g2d);
		}
	}
}
