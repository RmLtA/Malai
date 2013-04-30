package org.malai.ex.speech.ui;

import java.awt.Graphics; 
import javax.swing.JPanel; 

import org.malai.sphinx4.interaction.library.OneWordInstruction;

public class SpeechPanel extends JPanel { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static OneWordInstruction o;
	
	public SpeechPanel(){}
	 

	public void paintComponent(Graphics g){ 

	g.drawString("Tiens ! SLT ! ! !", 10, 20);
	setO(new OneWordInstruction());

	} 
	
	public static OneWordInstruction getO() {
		return o;
	}

	public static void setO(OneWordInstruction o) {
		SpeechPanel.o = o;
	}
} 
