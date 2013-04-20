package org.malai.sphinx4.interaction.library;


import org.malai.interaction.TerminalState;
import org.malai.sphinx4.interaction.SpeechInteraction;
import org.malai.sphinx4.interaction.SpeechTransition;

import edu.cmu.sphinx.recognizer.Recognizer.State;



public class StatusChanged extends SpeechInteraction{
	
	protected State state;
	
	public StatusChanged() {
		super();
		initStateMachine();
	}


	public void reinit() {
		super.reinit();
		state = null;
	}
	
	protected void initStateMachine() {
		final TerminalState changed = new TerminalState("changed"); 

		addState(changed);

		new SpeechTransition(initState, changed) {
			public void action() {
				super.action();
				StatusChanged.this.state = this.status;
			}
		};
	}

	
}
