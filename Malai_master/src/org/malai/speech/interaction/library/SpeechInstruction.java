package org.malai.speech.interaction.library;

import org.malai.interaction.TerminalState;
import org.malai.speech.interaction.SpeechInteraction;
import org.malai.speech.interaction.SpeechTransition;


public class SpeechInstruction extends SpeechInteraction{
	protected String word;


	public SpeechInstruction() {
		super();
		System.out.println("Loading...");
		initStateMachine();	
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
			}
		};
	}
	
	

}	
	

        

       
    
