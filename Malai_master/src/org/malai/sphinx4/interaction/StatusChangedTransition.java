package org.malai.sphinx4.interaction;

import org.malai.interaction.Transition;
import org.malai.stateMachine.SourceableState;
import org.malai.stateMachine.TargetableState;


import edu.cmu.sphinx.recognizer.Recognizer.State;

public class StatusChangedTransition extends Transition {
	protected State state;
	
	/**
	 * {@link Transition#Transition(SourceableState, TargetableState)}
	 */
	public StatusChangedTransition(final SourceableState inputState, final TargetableState outputState) {
		super(inputState, outputState);
	}


	/**
	 * @return .
	 * @since 0.2
	 */
	public State getState() {
		return state;
	}


	/**
	 * 
	 * @param 
	 * @since 0.2
	 */
	public void setState(final State state) {
		if(state!=null)
			this.state = state;
	}

}
