package dama;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JButton;

public class Casella extends JButton {
	
public Casella() {
	this.setBackground(Color.GRAY);
	this.createActionListener();
}
public Point getPosizione () {
	Rectangle r = this.getBounds();
	Point O = r.getLocation();
	int row = O.y / r.height;
	int col = O.x / r.width;
	
	return new Point(row, col);
}
}
