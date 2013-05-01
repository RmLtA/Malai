package org.malai.speech.interaction;

import org.malai.interaction.Transition;
import org.malai.stateMachine.SourceableState;
import org.malai.stateMachine.TargetableState;

import edu.cmu.sphinx.recognizer.Recognizer.State;


public class SpeechTransition extends Transition {
	protected String word;
	protected State status;
	
	public State getStatus() {
		return status;
	}

	public void setStatus(State status) {
		this.status = status;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * {@link Transition#Transition(SourceableState, TargetableState)}
	 */
	public SpeechTransition(final SourceableState inputState, final TargetableState outputState) {
		super(inputState, outputState);
	}


}
