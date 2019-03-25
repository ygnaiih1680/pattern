package panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import drawing.PolygonA;
import drawing.Select;
import drawing.ShapeA;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MouseHandler mouse;
	private ShapeA shapeA;

	public DrawingPanel() {
		this.setBackground(Color.WHITE);
		this.mouse = new MouseHandler();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void setType(ShapeA shapeA) {
		this.shapeA = shapeA;
	}

	public void draw(Graphics2D g2d) {
		this.shapeA.setGraphics2D(g2d);
		this.shapeA.draw();
	}

	public void complete() {
		this.shapeA.drawComplete();
		this.shapeA.redraw();
	}

	public boolean checkNull() {
		if (shapeA == null)
			return true;
		return false;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		this.shapeA.setGraphics2D(g2d);
		if (this.ready2change()) {
			if (this.ready2move())
				this.shapeA.move();
			else if (this.ready2rotate())
				this.shapeA.rotate();
			else if (this.ready2resize())
				this.shapeA.resize();
		} else if (!this.checkNull()) {
			g2d.setColor(Color.BLACK);
			this.draw(g2d);
		}
		this.shapeA.redraw();
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
			repaint();
			if (e.getClickCount() == 1)
				shapeA.addPoint();
			else if (e.getClickCount() == 2)
				complete();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			start(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (shapeA instanceof PolygonA) return;
			complete();
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			repaint();
			shapeA.setEnd(e.getPoint());
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
