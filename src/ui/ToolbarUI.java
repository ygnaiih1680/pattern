package ui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicToolBarUI;

public class ToolbarUI extends BasicToolBarUI {

	public ToolbarUI() {
		this.dockingBorderColor = Color.YELLOW;
		this.dockingColor = Color.DARK_GRAY;
		this.floatingBorderColor = Color.YELLOW;
		this.floatingColor = Color.LIGHT_GRAY;
	}

	protected Border createRolloverBorder() {
		return BorderFactory.createRaisedSoftBevelBorder();
	}
	
	protected MouseInputListener createDockingListener() {
		MouseHandler handler = new MouseHandler();
		handler.tb = this.toolBar;
		return handler;
	}
	
	private class MouseHandler extends MouseInputAdapter{
		JToolBar tb;
        boolean isDragging = false;
        Point origin = null;
        
		public void mousePressed(MouseEvent evt) {
            if (!tb.isEnabled()) {
                return;
            }
            isDragging = false;
        }

        public void mouseReleased(MouseEvent evt) {
            if (!tb.isEnabled()) {
                return;
            }
            if (isDragging) {
            	tb.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                Point position = evt.getPoint();
                if (origin == null)
                    origin = evt.getComponent().getLocationOnScreen();
                floatAt(position, origin);
            }else {tb.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);}
            origin = null;
            isDragging = false;
        }

        public void mouseDragged(MouseEvent evt) {
        	
            if (!tb.isEnabled()) {
                return;
            }
            isDragging = true;
            Point position = evt.getPoint();
            if (origin == null) {
                origin = evt.getComponent().getLocationOnScreen();
            }
            dragTo(position, origin);
        }

        public void mouseEntered(MouseEvent evt) {tb.setCursor(new Cursor(Cursor.MOVE_CURSOR));}
        public void mouseExited(MouseEvent evt) {tb.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}
	}

}
