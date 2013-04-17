package org.malai.sphinx4.ex.draw.model;

import java.awt.*;
import javax.swing.*;

public class SphinxRect extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(10,10,80,80);
		g.setColor(c);
	}

}
  