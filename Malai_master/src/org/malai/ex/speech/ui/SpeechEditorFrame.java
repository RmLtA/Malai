package org.malai.ex.speech.ui;

import org.malai.instrument.Instrument;
import org.malai.swing.ui.UI;

/*
 * The main GUI of the application.
 * Each GUI of the application should inherit
 * of the Malai class UI that represents a GUI.
 * A Malai UI is a JFrame. 
 */
public class SpeechEditorFrame extends UI {
	private static final long serialVersionUID = 1L;
	
	public SpeechEditorFrame() {
		super();
		/* Setting the name of the application. */
		setName("A simple editor");

	}
	
	@Override
	public void initialisePresentations() {

	}

	@Override
	public Instrument[] getInstruments() {
		// TODO Auto-generated method stub
		return null;
	}
}