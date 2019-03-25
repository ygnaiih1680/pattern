package global;

import java.util.HashMap;
import java.util.Map;

import drawing.Circle;
import drawing.Ellipse;
import drawing.Line;
import drawing.PolygonA;
import drawing.Rectangle;
import drawing.Select;
import drawing.ShapeA;

public class Constants {
	private static Map<String, ShapeA> shapeAMap;
	
	static {
		shapeAMap = new HashMap<String, ShapeA>();
		shapeAMap.put(ChooseButtonCommand.RButton.getCommand(), new Rectangle());
		shapeAMap.put(ChooseButtonCommand.SButton.getCommand(), new Select());
		shapeAMap.put(ChooseButtonCommand.PButton.getCommand(), new PolygonA());
		shapeAMap.put(ChooseButtonCommand.LButton.getCommand(), new Line());
		shapeAMap.put(ChooseButtonCommand.EButton.getCommand(), new Ellipse());
		shapeAMap.put(ChooseButtonCommand.CButton.getCommand(), new Circle());
	}
	
	public static ShapeA getShapeA(String command) {
		return shapeAMap.get(command);
	}

	public enum CMainFrame {
		x(300), y(200), width(700), height(800);
		private int value;
		private CMainFrame(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	}
	
	public enum CInternalFrame {
		x(50), y(50), width(500), height(500), w2(300), h2(300);
		private int value;
		private CInternalFrame(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	}
	
	public enum ButtonImgSize {
		width(25), height(25);
		private int value;
		private ButtonImgSize(int i) {
			this.value = i;
		}
		public int getValue() {
			return this.value;
		}
	}
	
	public static enum BasicPath{
		select("icon/select1.png"), rect("icon/rect1.png"), arc("icon/arc1.png"), circle("icon/circle1.png"), ellipse("icon/ellipse1.png"),
		line("icon/line1.png"), polygon("icon/polygon1.png"), erase("icon/erase1.png"), undo("icon/undo1.png"), redo("icon/redo1.png");
		String path;
		private BasicPath(String temp) {
			path = temp;
		}
		public String getPath() {
			return this.path;
		}
	}
	
	public static enum SelectedPath{
		select("icon/select2.png"), rect("icon/rect2.png"), arc("icon/arc2.png"), circle("icon/circle2.png"), ellipse("icon/ellipse2.png"),
		line("icon/line2.png"), polygon("icon/polygon2.png"), erase("icon/erase2.png"), undo("icon/undo2.png"), redo("icon/redo2.png");
		String path;
		private SelectedPath(String temp) {
			path = temp;
		}
		public String getPath() {
			return this.path;
		}
	}
	
	public static enum ChooseButtonCommand{
		SButton("Select"), RButton("Rectangle"), AButton("Arc"), CButton("Circle"), EButton("Ellipse"),
		LButton("Line"), PButton("Polygon"), eButton("Erase");
		String command;
		private ChooseButtonCommand(String temp) {
			command = temp;
		}
		public String getCommand() {
			return this.command;
		}
	}
	
	public static enum SelectButtonCommand{
		unButton("Undo"), reButton("Redo");
		String command;
		private SelectButtonCommand(String temp) {
			command = temp;
		}
		public String getCommand() {
			return this.command;
		}
	}
	
	public static enum FileMenuItem{
		newFile("새 파일"), exit("종료");
		String command;
		private FileMenuItem(String temp) {
			command = temp;
		}
		public String getCommand() {
			return this.command;
		}
	}


}
