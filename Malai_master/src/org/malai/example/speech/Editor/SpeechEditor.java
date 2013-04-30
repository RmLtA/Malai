package org.malai.example.speech.Editor;



import java.awt.Graphics;

import org.malai.ex.speech.ui.SpeechEditorFrame;
import org.malai.ex.speech.ui.SpeechPanel;


public class SpeechEditor {

	
	public static void main(String[] args) {
		/* Creation of the main GUI of the application. */
		Graphics gr = null;
		@SuppressWarnings("unused")
		SpeechEditorFrame frame = new SpeechEditorFrame();
		SpeechPanel p= new SpeechPanel();
		p.paintComponent(gr);

	}

}
