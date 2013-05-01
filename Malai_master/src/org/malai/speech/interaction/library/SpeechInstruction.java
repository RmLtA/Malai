package org.malai.speech.interaction.library;


import java.awt.Graphics;

import org.malai.interaction.TerminalState;
import org.malai.speech.interaction.SpeechEventManager;
import org.malai.speech.interaction.SpeechInteraction;
import org.malai.speech.interaction.SpeechTransition;


public class SpeechInstruction extends SpeechInteraction{
	protected String word;
	protected Graphics g;


	public SpeechInstruction() {
		super();
		g.drawString("Loading...", 10, 20);
		initStateMachine();
		SpeechEventManager e = new SpeechEventManager();
	    e.oneWordSpeechEvent();
		
	}


	public void reinit() {
		super.reinit();
		word = null;
	}
	

	protected void initStateMachine() {
		final TerminalState listened = new TerminalState("listened");
		
		addState(listened);
		
		new SpeechTransition(initState, listened) {
			@Override
			public void action() {
				super.action();
			    /*SpeechEventManager e = new SpeechEventManager();
			    e.oneWordSpeechEvent();*/
				/*OneWordInstruction.this.word = this.word;*/
			}
		};
	}
	
	

}	
	

        

       
    
