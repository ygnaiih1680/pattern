package drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Transform {
	private AffineTransform trans;
	private ArrayList<Point> points;
	private ArrayList<Boolean> isRect;

	public Transform() {
		this.trans = new AffineTransform();
		this.points = new ArrayList<Point>();
		this.isRect = new ArrayList<Boolean>();
	}
	
	public void pointReset() {
		points.clear();
		isRect.clear();
	}

	public void setResizeShape(Shape shape, boolean rect) {
		isRect.add(rect);
		Point point = new Point((int) shape.getBounds2D().getMinX(), (int) shape.getBounds2D().getMinY());
		this.points.add(point);
		point = new Point((int) shape.getBounds2D().getMaxX(), (int) shape.getBounds2D().getMinY());
		this.points.add(point);
		point = new Point((int) shape.getBounds2D().getMinX(), (int) shape.getBounds2D().getMaxY());
		this.points.add(point);
		point = new Point((int) shape.getBounds2D().getMaxX(), (int) shape.getBounds2D().getMaxY());
		this.points.add(point);
		point = new Point((int) shape.getBounds2D().getCenterX(),
				(int) (shape.getBounds2D().getCenterY() - shape.getBounds2D().getHeight() / 2 - 50));
		this.points.add(point);
	}

	public void createResizePoint(Graphics2D g2d) {
		if (points.size() == 0)
			return;
		g2d.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < points.size(); i += 5) {
			if (!isRect.get(i / 5)) {
				Rectangle2D bounds = new Rectangle2D.Double(points.get(i).getX(), points.get(i).getY(),
						points.get(i + 3).getX() - points.get(i).getX(),
						points.get(i + 3).getY() - points.get(i).getY());
				g2d.draw(bounds);
			}
		}
		for (Point point : points) {
			Ellipse2D resizeP = new Ellipse2D.Double(point.getX() - 4, point.getY() - 4, 8, 8);
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fill(resizeP);
			g2d.setColor(Color.BLACK);
			g2d.draw(resizeP);
		}
	}

	public int isOnPoint(Point mpoint) {
		for (Point point : points) {
			if (mpoint.distance(point) < 5)
				return points.indexOf(point);
		}
		return -1;
	}

	public double changedXRatio(Point before, Point after, double width) {
		double gapx = (after.getX() - before.getX()) / width;
		return gapx;
	}

	public double changedYRatio(Point before, Point after, double height) {
		double gapy = (after.getY() - before.getY()) / height;
		return gapy;
	}

	public Shape changeScale(Shape pSrc, double xratio, double yratio, int poi) {
		double width = pSrc.getBounds2D().getWidth(), height = pSrc.getBounds2D().getHeight(),
				x = pSrc.getBounds2D().getX(), y = pSrc.getBounds2D().getY();
		switch (poi % 5) {
		case 0:
			trans.translate((x * (xratio)) + (width * xratio), (y * (yratio)) + (height * yratio));
			trans.scale(1-xratio, 1-yratio);
			break;
		case 1:
			trans.translate(x * (-xratio), (y * yratio) + (height * yratio));
			trans.scale(1+xratio, 1-yratio);
			break;
		case 2:
			trans.translate((x * xratio) + (width * xratio), y * (-yratio));
			trans.scale(1-xratio, 1+yratio);
			break;
		case 3:
			trans.translate(x * (-xratio), y * (-yratio));
			trans.scale(1 + xratio, 1 + yratio);
			break;
		default:
			break;
		}
		Shape temp = trans.createTransformedShape(pSrc);
		trans = new AffineTransform();
		return temp;
	}

	public Shape move(Shape pSrc, Point before, Point after) {
		trans.translate(after.getX() - before.getX(), after.getY() - before.getY());
		Shape temp = trans.createTransformedShape(pSrc);
		trans = new AffineTransform();
		return temp;
	}

	public double calculateTheta(Point before, Point after) {
		double y = after.getY() - before.getY(), x = after.getX() - before.getX();
		return Math.atan2(y, x);
	}

	public Shape rotate(Shape pSrc, Point anchor, double theta) {
		trans.rotate(theta, anchor.getX(), anchor.getY());
		Shape temp = trans.createTransformedShape(pSrc);
		trans = new AffineTransform();
		return temp;
	}
}
