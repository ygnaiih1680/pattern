package menu;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	private FileMenu fileMenu;
	public MenuBar(ActionListener action){
		this.fileMenu = new FileMenu("file", action);
		this.add(fileMenu);
	}

}
