package toolbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import global.Constants.BasicPath;
import global.Constants.ButtonImgSize;
import global.Constants.ChooseButtonCommand;
import global.Constants.SelectButtonCommand;
import global.Constants.SelectedPath;
import ui.ToolbarUI;

public class Toolbar extends JToolBar{
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttons;
	private ToolbarUI ui;
	private ActionListener action;

	public Toolbar(ActionListener action) {
		super("Toolbar");
		this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		this.setBorderPainted(true);
		this.setBackground(Color.WHITE);
		this.action = action;
		
		this.buttons = new ButtonGroup();
		this.ui = new ToolbarUI();
		this.setUI(ui);
		for(int i = BasicPath.values().length-1, c = SelectButtonCommand.values().length-1;i>ChooseButtonCommand.values().length-1;i--,c--) {
			this.setButton(i, c);
		}
		for(int i = ChooseButtonCommand.values().length-1;i>-1;i--) {
			this.setToggle(i);
		}
		if(this.getComponents()[this.getComponentCount()-1] instanceof AbstractButton) {
			AbstractButton button = (AbstractButton) this.getComponents()[this.getComponentCount()-1];
			button.doClick();
		}
	}
	
	public void reselect() {
		for(Component comp:this.getComponents()) {
			if(comp instanceof AbstractButton) {
				AbstractButton button = (AbstractButton) comp;
				if(button.isSelected())button.doClick();
			}
		}
	}
	
	private void setToggle(int i) {
		ImageIcon icon = new ImageIcon(BasicPath.values()[i].getPath());
		ImageIcon selicon = new ImageIcon(SelectedPath.values()[i].getPath());
		JToggleButton button = new JToggleButton();
		icon = new ImageIcon(icon.getImage().getScaledInstance(ButtonImgSize.width.getValue(), ButtonImgSize.height.getValue(), Image.SCALE_AREA_AVERAGING));
		selicon = new ImageIcon(selicon.getImage().getScaledInstance(ButtonImgSize.width.getValue(), ButtonImgSize.height.getValue(), Image.SCALE_AREA_AVERAGING));
		button.setActionCommand(ChooseButtonCommand.values()[i].getCommand());
		button.addActionListener(action);
		button.setIcon(icon);
		button.setSelectedIcon(selicon);
		this.buttons.add(button);
		this.add(button);
	}
	
	private void setButton(int i, int c) {
		ImageIcon icon = new ImageIcon(BasicPath.values()[i].getPath());
		JButton button = new JButton();
		icon = new ImageIcon(icon.getImage().getScaledInstance(ButtonImgSize.width.getValue(), ButtonImgSize.height.getValue(), Image.SCALE_AREA_AVERAGING));
		button.setActionCommand(SelectButtonCommand.values()[c].getCommand());
		button.addActionListener(action);
		button.setIcon(icon);
		this.add(button);
	}
}
