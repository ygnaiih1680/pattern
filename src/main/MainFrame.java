package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import global.Constants.CInternalFrame;
import global.Constants.CMainFrame;
import global.Constants.FileMenuItem;
import menu.MenuBar;
import panel.DrawingPanel;
import panel.InternalFrame;
import toolbar.Toolbar;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private Toolbar toolbar;
	private MenuBar menubar;
	private ButtonSelect buttonselect;
	private MenuItemSelect menuItemSelect;
	private IFrameFocus iframeFocus;
	private InternalFrame iframe;
	private JDesktopPane desktop;
	private ArrayList<InternalFrame> iframeList;
	private int index;

	public MainFrame() {
		this.index = -1;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setBounds(CMainFrame.x.getValue(), CMainFrame.y.getValue(), CMainFrame.width.getValue(),
				CMainFrame.height.getValue());
		this.setLayout(new BorderLayout());
		this.iframeList = new ArrayList<InternalFrame>();
		this.iframeFocus = new IFrameFocus();
		this.desktop = new JDesktopPane();
		this.iframe = new InternalFrame(null, this.iframeFocus);
		this.index++;
		this.iframe.setIndex(index);
		this.buttonselect = new ButtonSelect();
		this.toolbar = new Toolbar(this.buttonselect);
		this.menuItemSelect = new MenuItemSelect();
		this.menubar = new MenuBar(menuItemSelect);
		this.desktop.add(iframe);
		this.desktop.setVisible(true);
		this.iframe.setVisible(true);

		this.setJMenuBar(menubar);
		this.getContentPane().add(desktop);
		this.add(toolbar, BorderLayout.NORTH);
		this.iframeList.add(iframe);
	}
	
	private class ButtonSelect implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			iframe.setType(e.getActionCommand());
		}
	}

	private class MenuItemSelect implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(FileMenuItem.newFile.getCommand())) {
				iframe = new InternalFrame(null, iframeFocus);
				index++;
				iframe.setIndex(index);
				iframe.setBounds(CInternalFrame.x.getValue(), CInternalFrame.y.getValue(), CInternalFrame.w2.getValue(), CInternalFrame.h2.getValue());
				iframe.setVisible(true);
				desktop.add(iframe);
				toolbar.reselect();
				iframeList.add(iframe);
			}
		}
	}

	private class IFrameFocus implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() instanceof InternalFrame) {
				InternalFrame temp = (InternalFrame) e.getSource();
				iframe = iframeList.get(temp.getIndex());
				toolbar.reselect();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() instanceof InternalFrame) {
				iframe.focusLost();
			}
		}
	}

	// private class MenuSelect implements MenuListener, MenuKeyListener{
	//
	// @Override
	// public void menuSelected(MenuEvent e) {
	// if(e.getSource() instanceof JMenu) {
	// JMenu menu = (JMenu) e.getSource();
	// System.out.println(menu.getText());
	// }
	// }
	//
	// @Override
	// public void menuDeselected(MenuEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void menuCanceled(MenuEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void menuKeyTyped(MenuKeyEvent e) {
	// }
	//
	// @Override
	// public void menuKeyPressed(MenuKeyEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void menuKeyReleased(MenuKeyEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// }

}
