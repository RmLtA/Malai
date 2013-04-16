package org.malai.sphinx4.interaction.library;


import org.malai.interaction.TerminalState;
import org.malai.sphinx4.interaction.Sphinx4Interaction;
import org.malai.sphinx4.interaction.StatusChangedTransition;

import edu.cmu.sphinx.recognizer.Recognizer.State;



public class StatusChanged extends Sphinx4Interaction{
	
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
		final TerminalState heard = new TerminalState("heard"); 

		addState(heard);

		new StatusChangedTransition(initState, heard) {
			public void action() {
				super.action();
				StatusChanged.this.state = this.state;
			}
		};
	}

	
}
