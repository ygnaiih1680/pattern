package panel;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.border.BevelBorder;

import global.Constants;
import global.Constants.CInternalFrame;
import ui.InFrameUI;

public class InternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	private DrawingPanel drawing;
	private InFrameUI ui;
	private int index;

	public InternalFrame(String text, FocusListener focus) {
		super(text, true, true, true, true);
		this.addFocusListener(focus);
		this.setFocusable(true);
		this.setBounds(CInternalFrame.x.getValue(), CInternalFrame.y.getValue(), CInternalFrame.width.getValue(), CInternalFrame.height.getValue());
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createEtchedBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY)));
		this.drawing = new DrawingPanel();
		this.ui = new InFrameUI(this);
		this.setUI(ui);
		this.add(drawing);
	}

	public void focusLost() {
		this.drawing.focusLost();
	}

	public void setType(String actionCommand) {
		this.drawing.setType(Constants.getShapeA(actionCommand));
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
