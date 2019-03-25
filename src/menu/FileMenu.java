package menu;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;

import global.Constants.FileMenuItem;

public class FileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	public FileMenu(String text, ActionListener action){
		super(text);
		for(FileMenuItem item:FileMenuItem.values()) {
			JMenuItem newItem = new JMenuItem(item.getCommand());
			newItem.addActionListener(action);
			this.add(newItem);
		}
	}
}
