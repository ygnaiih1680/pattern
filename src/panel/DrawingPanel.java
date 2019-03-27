package panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import drawing.DrawedShape;
import drawing.PolygonA;
import drawing.Select;
import drawing.ShapeA;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MouseHandler mouse;
	private ShapeA shapeA;
	private DrawedShape drawed;
	
	public DrawingPanel() {
		this.setBackground(Color.WHITE);
		this.mouse = new MouseHandler();
		this.drawed = new DrawedShape();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void setType(ShapeA shapeA) {
		this.shapeA = shapeA;
		this.shapeA.initialize(drawed);
	}

	public void draw(Graphics2D g2d) {
		this.shapeA.draw(g2d);
	}

	public void complete() {
		repaint();
		this.shapeA.drawComplete();
	}

	public boolean checkNull() {
		if (shapeA == null)
			return true;
		return false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (drawed.selected()) {
			this.changePaint(g2d);
		} else if (!this.checkNull()) {
			this.drawPaint(g2d);
		}
		this.drawed.redraw(g2d);
	}

	public void changePaint(Graphics2D g2d) {
		if(drawed.isSelected()) {drawed.drawPoint(g2d);}
		if (this.ready2move())
			this.move();
		else if (this.ready2rotate())
			this.rotate();
		else if (this.ready2resize())
			this.resize();
	}

	public void drawPaint(Graphics2D g2d) {
		this.draw(g2d);
	}

	public void move() {
		this.shapeA.move();
	}

	public void resize() {
		this.shapeA.resize();
	}

	public void rotate() {
		this.shapeA.rotate();
	}

	public void focusLost() {
		repaint();
	}

	public void start(Point start) {
		this.shapeA.setStart(start);
		this.shapeA.setEnd(start);
	}

	public boolean ready2resize() {
		if (this.getCursor().getType() == Cursor.CROSSHAIR_CURSOR)
			return false;
		return true;
	}

	public boolean ready2move() {
		if (this.getCursor().getType() == Cursor.MOVE_CURSOR)
			return true;
		return false;
	}

	public boolean ready2rotate() {
		if (this.getCursor().getType() == Cursor.HAND_CURSOR)
			return true;
		return false;
	}

	public boolean ready2change() {
		if (this.shapeA.isSelect())
			return true;
		return false;
	}

	private class MouseHandler implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && shapeA instanceof PolygonA)
				complete();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			start(e.getPoint());
			if (shapeA instanceof Select)
				shapeA.changing();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (shapeA instanceof PolygonA) {
				shapeA.addPoint();
				repaint();
			} else {
				complete();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			shapeA.setEnd(e.getPoint());
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (shapeA instanceof PolygonA) {
				shapeA.setEnd(e.getPoint());
				repaint();
			}
			setCursor(shapeA.setCursor(e.getPoint()));
		}
	}
}
