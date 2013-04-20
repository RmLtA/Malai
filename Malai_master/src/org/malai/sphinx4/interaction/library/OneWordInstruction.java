package org.malai.sphinx4.interaction.library;


import org.malai.interaction.TerminalState;
import org.malai.sphinx4.interaction.SpeechInteraction;
import org.malai.sphinx4.interaction.SpeechTransition;


public class OneWordInstruction extends SpeechInteraction{
	protected String word;


	public OneWordInstruction() {
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
				setLastHIDUsed(this.hid);
				OneWordInstruction.this.word = this.word;
			}
		};
	}
	
	

}	
	

        

       
    
