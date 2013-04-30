package org.malai.sphinx4.interaction.library;


import java.awt.Graphics;

import org.malai.interaction.TerminalState;
import org.malai.sphinx4.interaction.SpeechEventManager;
import org.malai.sphinx4.interaction.SpeechInteraction;
import org.malai.sphinx4.interaction.SpeechTransition;


public class OneWordInstruction extends SpeechInteraction{
	protected String word;
	protected Graphics g;


	public OneWordInstruction() {
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
	

        

       
    
